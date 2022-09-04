package com.example.zombie_apocalypse.service;

import com.example.zombie_apocalypse.dto.Creature;
import com.example.zombie_apocalypse.dto.Position;
import com.example.zombie_apocalypse.dto.InitialInfo;
import com.example.zombie_apocalypse.dto.InfectionResponse;
import com.example.zombie_apocalypse.dto.ZombiesAndCreatures;
import com.example.zombie_apocalypse.dto.Zombie;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class ApocalypseService {
    private final Map<Character, Position> commandMap = Map.of(
            'R', new Position(1, 0),
            'L', new Position(-1, 0),
            'U', new Position(0, -1),
            'D', new Position(0, 1)
    );

    public InfectionResponse infection(InitialInfo world) {
        Queue<Zombie> zombiesQueue = new LinkedList<>();
//        var grid = initWorld(world, zombiesQueue);
        Integer gridSize = world.getDimensions();
        List<Zombie> zombies = new ArrayList<>();
        String commands = world.getCommands();
        List<Creature> creatures = world.getCreatures();
        Set<Creature> creaturesSet = new HashSet<>(creatures);
        Zombie zombie = world.getZombie();
        zombiesQueue.offer(zombie);

        int zombieNumber = 0;
        while (!zombiesQueue.isEmpty()) {
            Zombie moveZombie = zombiesQueue.poll();
            for (int i = 0; i < commands.length(); i++) {
                char command = commands.charAt(i);
                Position actionPosition = moveZombie.getPosition();
                Position offset = commandMap.get(command);
                moveZombie.setPosition(actionPosition.move(gridSize, offset));
                log.info("Zombie {} moved to ({}, {}).", zombieNumber,
                        moveZombie.getPosition().getX(), moveZombie.getPosition().getY());
                Creature creature = zombieInfectCreature(moveZombie, creaturesSet);
                if (creature != null) {
                    zombiesQueue.offer(new Zombie(
                            moveZombie.getPosition()
                    ));
                    creaturesSet.remove(creature);
                    log.info("Zombie {} infected creature at ({}, {}).",
                            zombieNumber, moveZombie.getPosition().getX(), moveZombie.getPosition().getY());
                }
            }

            zombieNumber++;
            zombies.add(moveZombie);
        }
        log.info("=============================");
        ZombiesAndCreatures zombiesAndCreatures = new ZombiesAndCreatures(zombies, new ArrayList<>(creaturesSet));
        return new InfectionResponse().success(zombiesAndCreatures);
    }

    private Creature zombieInfectCreature(Zombie zombie, Set<Creature> creaturesSet) {
        for (Creature creature :
                creaturesSet) {
            if (creature.getPosition().equals(zombie.getPosition())) return creature;
        }
        return null;
    }

}
