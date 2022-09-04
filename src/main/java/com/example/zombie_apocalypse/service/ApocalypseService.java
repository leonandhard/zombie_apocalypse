package com.example.zombie_apocalypse.service;

import com.example.zombie_apocalypse.exception.CommandNotFoundException;
import com.example.zombie_apocalypse.exception.InputDimensionsUnexpectedException;
import com.example.zombie_apocalypse.exception.ZombieNotFoundException;
import com.example.zombie_apocalypse.model.Creature;
import com.example.zombie_apocalypse.model.Position;
import com.example.zombie_apocalypse.dto.World;
import com.example.zombie_apocalypse.dto.infectionResponse;
import com.example.zombie_apocalypse.dto.ZombiesAndCreatures;
import com.example.zombie_apocalypse.model.Zombie;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class ApocalypseService {

    private final Map<Character, Position> map = Map.ofEntries(
            Map.entry('R', new Position(1,0)),
            Map.entry('L', new Position(-1,0)),
            Map.entry('U', new Position(0,-1)),
            Map.entry('D', new Position(0,1))
    );


    public infectionResponse infection(World world) {
        Queue<Zombie> zombiesQueue = new LinkedList<>();
        var grid = initWorld(world, zombiesQueue);
        List<Zombie> zombies = new ArrayList<>();
        String commands = world.getCommands();
        List<Creature> creatures = world.getCreatures();
        Set<Creature> creaturesSet = new HashSet<>(creatures);

        int zombieNumber = 0;
        while (!zombiesQueue.isEmpty()) {
            Zombie zombie = zombiesQueue.poll();
            for (int i = 0; i < commands.length(); i++) {
                char command = commands.charAt(i);
                zombieMove(zombie, command, grid.length);
                log.info("Zombie {} moved to ({}, {}).", zombieNumber,
                        zombie.getPosition().getX(), zombie.getPosition().getY());
                if (grid[zombie.getPosition().getX()][zombie.getPosition().getY()] == 1) {
                    grid[zombie.getPosition().getX()][zombie.getPosition().getY()] = 0;
                    zombiesQueue.offer(new Zombie(
                            new Position(zombie.getPosition().getX(), zombie.getPosition().getY())
                    ));
                    Creature creature = zombieInfectCreature(zombie, creaturesSet);
                    creaturesSet.remove(creature);
                    log.info("Zombie {} infected creature at ({}, {}).",
                            zombieNumber, zombie.getPosition().getX(), zombie.getPosition().getY());
                }
            }
            zombieNumber++;
            zombies.add(zombie);
        }
        log.info("=============================");
//        zombieStartMove();
        ZombiesAndCreatures zombiesAndCreatures = new ZombiesAndCreatures(zombies, new ArrayList<>(creaturesSet));
        return new infectionResponse().success(zombiesAndCreatures);
    }

    private void zombieMove(Zombie zombie, char command, int length) {
        int indexX = zombie.getPosition().getX() + map.get(command).getX();
        int indexY = zombie.getPosition().getY() + map.get(command).getY();
        indexX = indexX >= length ? 0 : indexX;
        indexY = indexY >= length ? 0 : indexY;
        zombie.getPosition().setX(indexX);
        zombie.getPosition().setY(indexY);
    }

    private Creature zombieInfectCreature(Zombie zombie, Set<Creature> creaturesSet) {

        for (Creature creature :
                creaturesSet) {
            if (Objects.equals(creature.getPosition().getX(), zombie.getPosition().getX()) &&
                    Objects.equals(creature.getPosition().getY(), zombie.getPosition().getY())) return creature;
        }

        return null;
    }

    private int[][] initWorld(World world, Queue<Zombie> zombiesQueue) {
        // 0 means no creatures, 1 means one creature stand there
        if (world.getDimensions() <= 0) {
            throw new InputDimensionsUnexpectedException(world.getDimensions());
        }

        int[][] grid = new int[world.getDimensions()][world.getDimensions()];

        if (world.getZombie() == null) {
            throw new ZombieNotFoundException();
        }
        if (!world.getCommands().matches("[RULD]+")) {
            throw new CommandNotFoundException(world.getCommands());
        }


        Zombie zombie = world.getZombie();
        zombiesQueue.offer(zombie);
        List<Creature> creatures = world.getCreatures();
        for (Creature creature : creatures) {
            grid[creature.getPosition().getX()][creature.getPosition().getY()] = 1;
        }
        return grid;
    }

}
