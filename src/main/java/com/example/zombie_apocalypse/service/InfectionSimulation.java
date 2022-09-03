package com.example.zombie_apocalypse.service;

import com.example.zombie_apocalypse.dto.Result;
import com.example.zombie_apocalypse.dto.ZombiesAndCreatures;
import com.example.zombie_apocalypse.model.Creature;
import com.example.zombie_apocalypse.model.Position;
import com.example.zombie_apocalypse.model.World;
import com.example.zombie_apocalypse.model.Zombie;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.function.BiConsumer;

@Slf4j
public class InfectionSimulation {
    private final Queue<Zombie> zombiesQueue;
    private final int[][] grid;
    private final String commands;
    private final List<Zombie> zombies;
    private final List<Creature> creatures;
    static Map<Character, BiConsumer<Position, Integer>> actionMap = new HashMap<>();

    static {
        actionMap.put('R', (p, length) -> {
            p.setX(p.getX() + 1 >= length ? 0 : p.getX() + 1);
        });
        actionMap.put('L', (p, length) -> {
            p.setX(p.getX() - 1 < 0 ? length - 1 : p.getX() - 1);
        });
        actionMap.put('U', (p, length) -> {
            p.setY(p.getY() - 1 < 0 ? length - 1 : p.getY() - 1);
        });
        actionMap.put('D', (p, length) -> {
            p.setY(p.getY() + 1 >= length ? 0 : p.getY() + 1);
        });
    }

    public InfectionSimulation(World world) {
        this.zombiesQueue = new LinkedList<>();
        this.grid = initWorld(world, zombiesQueue);
        this.zombies = new ArrayList<>();
        this.commands = world.getCommands();
        this.creatures = world.getCreatures();
    }

    public Result infection() {
        zombieStartMove();
        ZombiesAndCreatures zombiesAndCreatures = new ZombiesAndCreatures(zombies, checkCreatures());
        return Result.success(zombiesAndCreatures);
    }

    private List<Creature> checkCreatures() {
        List<Creature> results = new ArrayList<>();
        for (Creature creature : creatures) {
            if (grid[creature.getPosition().getX()][creature.getPosition().getY()] == 1) {
                results.add(creature);
            }
        }
        return results;
    }

    private void zombieStartMove() {
        int zombieNumber = 0;
        while (!zombiesQueue.isEmpty()) {
            Zombie zombie = zombiesQueue.poll();
            for (int i = 0; i < commands.length(); i++) {
                char command = commands.charAt(i);
                zombieMove(zombie, command, grid.length);
                log.info("Zombie {} moved to ({}, {}).", zombieNumber, zombie.getPosition().getX(), zombie.getPosition().getY());
                if (grid[zombie.getPosition().getY()][zombie.getPosition().getX()] == 1) {
                    grid[zombie.getPosition().getY()][zombie.getPosition().getX()] = 0;
                    zombiesQueue.offer(new Zombie(new Position(zombie.getPosition().getX(), zombie.getPosition().getY())));
                    Position position = new Position(zombie.getPosition().getX(), zombie.getPosition().getY());
                    log.info("Zombie {} infected creature at ({}, {}).", zombieNumber, zombie.getPosition().getX(), zombie.getPosition().getY());
                }
            }
            zombieNumber++;
            zombies.add(zombie);
        }
        log.info("=============================");
    }

    private void zombieMove(Zombie zombie, char command, int length) {
        BiConsumer<Position, Integer> positionIntegerBiConsumer = actionMap.get(command);
        positionIntegerBiConsumer.accept(zombie.getPosition(), length);
    }

    private int[][] initWorld(World world, Queue<Zombie> zombiesQueue) {
        // 0 means no creatures, 1 means one creature stand there
        int[][] grid = new int[world.getDimensions()][world.getDimensions()];
        Zombie zombie = world.getZombie();
        zombiesQueue.offer(zombie);
        List<Creature> creatures = world.getCreatures();
        for (Creature creature : creatures) {
            grid[creature.getPosition().getY()][creature.getPosition().getX()] = 1;
        }
        return grid;
    }
}
