package com.example.zombie_apocalypse.service;

import com.example.zombie_apocalypse.dto.Result;
import com.example.zombie_apocalypse.dto.ZombiesAndCreatures;
import com.example.zombie_apocalypse.model.Creature;
import com.example.zombie_apocalypse.model.Position;
import com.example.zombie_apocalypse.model.World;
import com.example.zombie_apocalypse.model.Zombie;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
public class InfectionSimulation {
    private  Queue<Zombie> zombiesQueue;
    private final int[][] grid;
    private final String commands;
    private  List<Zombie> zombies;
    private Set<Creature> creaturesSet;
    private final Map<Character, Position> map = Map.ofEntries(
            Map.entry('R', CommandActions.RIGHT.getAction()),
            Map.entry('L', CommandActions.LEFT.getAction()),
            Map.entry('U', CommandActions.UP.getAction()),
            Map.entry('D', CommandActions.DOWN.getAction())
    );

    public enum CommandActions {
        LEFT {
            @Override
            public Position getAction() {
                return new Position(-1, 0);
            }
        }, RIGHT {
            @Override
            public Position getAction() {
                return new Position(1, 0);
            }
        }, UP {
            @Override
            public Position getAction() {
                return new Position(0, -1);
            }
        }, DOWN {
            @Override
            public Position getAction() {
                return new Position(0, 1);
            }
        };


        public Position getAction() {
            return null;
        }
    }

    public InfectionSimulation(World world) {
        this.zombiesQueue = new LinkedList<>();
        this.grid = initWorld(world, zombiesQueue);
        this.zombies = new ArrayList<>();
        this.commands = world.getCommands();
        List<Creature> creatures = world.getCreatures();
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
            Zombie zombie = zombiesQueue.poll();
            for (int i = 0; i < commands.length(); i++) {
                char command = commands.charAt(i);
                zombieMove(zombie, command, grid.length);
                log.info("Zombie {} moved to ({}, {}).", zombieNumber, zombie.getPosition().getX(), zombie.getPosition().getY());
                if (grid[zombie.getPosition().getX()][zombie.getPosition().getY()] == 1) {
                    grid[zombie.getPosition().getX()][zombie.getPosition().getY()] = 0;
                    zombiesQueue.offer(new Zombie(zombie.getPosition()));
                    Creature creature = zombieInfectCreature(zombie);
                    creaturesSet.remove(creature);
                    log.info("Zombie {} infected creature at ({}, {}).", zombieNumber, zombie.getPosition().getX(), zombie.getPosition().getY());
                }
            }
            zombieNumber++;
            zombies.add(zombie);
        }
        log.info("=============================");
    }

    private void zombieMove(Zombie zombie, char command, int length) {
        int indexX = zombie.getPosition().getX() + map.get(command).getX();
        int indexY = zombie.getPosition().getY() + map.get(command).getY();
        System.out.println(" = " +map.get(command));
        indexX = indexX >= length ? 0 : indexX;
        indexY = indexY >= length ? 0 : indexY;
        zombie.getPosition().setX(indexX);
        zombie.getPosition().setY(indexY);
    }
    private Creature zombieInfectCreature(Zombie zombie){

        for (Creature creature:
             creaturesSet) {
            if (Objects.equals(creature.getPosition().getX(), zombie.getPosition().getX()) &&
                    Objects.equals(creature.getPosition().getY(), zombie.getPosition().getY())) return  creature;
        }

        return null;
    }

    private int[][] initWorld(World world, Queue<Zombie> zombiesQueue) {
        // 0 means no creatures, 1 means one creature stand there
        int[][] grid = new int[world.getDimensions()][world.getDimensions()];
        Zombie zombie = world.getZombie();
        zombiesQueue.offer(zombie);
        List<Creature> creatures = world.getCreatures();
        for (Creature creature : creatures) {
            grid[creature.getPosition().getX()][creature.getPosition().getY()] = 1;
        }
        return grid;
    }
}
