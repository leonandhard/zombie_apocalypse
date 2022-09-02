package com.example.zombie_apocalypse.service;

import com.example.zombie_apocalypse.dto.Result;
import com.example.zombie_apocalypse.dto.ZombiesAndCreatures;
import com.example.zombie_apocalypse.model.Position;
import com.example.zombie_apocalypse.model.World;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
public class InfectionSimulation {
    private final Queue<Position> zombiesQueue;
    private final int[][] grid;
    private final String commands;
    private final List<Position> zombies;
    private final Set<Position> creaturesSet;

    public InfectionSimulation(World world) {
        this.zombiesQueue = new LinkedList<>();
        this.grid = initWorld(world, zombiesQueue);
        this.zombies = new ArrayList<>();
        this.commands = world.getCommands();
        List<Position> creatures = world.getCreatures();
        this.creaturesSet = new HashSet<>(creatures);
    }

    public Result infection() {
        zombieStartMove();
        ZombiesAndCreatures zombiesAndCreatures = new ZombiesAndCreatures(zombies, new ArrayList<>(creaturesSet));
        return Result.success(zombiesAndCreatures);
    }

    private void zombieStartMove() {
        int zombieNumber = 0;
        while (!zombiesQueue.isEmpty()) {
            Position zombie = zombiesQueue.poll();
            for (int i = 0; i < commands.length(); i++) {
                char command = commands.charAt(i);
                zombieMove(zombie, command, grid.length);
                log.info("Zombie {} moved to ({}, {}).", zombieNumber, zombie.getX(), zombie.getY());
                if (grid[zombie.getX()][zombie.getY()] == 1) {
                    grid[zombie.getX()][zombie.getY()] = 0;
                    zombiesQueue.offer(new Position(zombie.getX(), zombie.getY()));
                    creaturesSet.remove(zombie);
                    log.info("Zombie {} infected creature at ({}, {}).", zombieNumber, zombie.getX(), zombie.getY());
                }
            }
            zombieNumber++;
            zombies.add(zombie);
        }
        log.info("=============================");
    }

    private void zombieMove(Position zombie, char command, int length) {
        Character[] commandStep = new Character[]{'R', 'U', 'D', 'L'};
        Position[] actionStep = new Position[]{new Position(1, 0),
                new Position(0, -1), new Position(0, 1), new Position(1, 0)};
        Map<Character, Position> map = new HashMap<>();
        for (int i = 0; i < commandStep.length; i++) {
            map.put(commandStep[i], actionStep[i]);
        }
        int indexX = zombie.getX() + map.get(command).getX();
        int indexY = zombie.getY() + map.get(command).getY();

        indexX = indexX >= length ? 0 : indexX;
        indexY = indexY >= length ? 0 : indexY;
        zombie.setX(indexX);
        zombie.setY(indexY);
    }

    private int[][] initWorld(World world, Queue<Position> zombiesQueue) {
        // 0 means no creatures, 1 means one creature stand there
        int[][] grid = new int[world.getDimensions()][world.getDimensions()];
        Position zombie = world.getZombie();
        zombiesQueue.offer(zombie);
        List<Position> creatures = world.getCreatures();
        for (Position creature : creatures) {
            grid[creature.getX()][creature.getY()] = 1;
        }
        return grid;
    }
}
