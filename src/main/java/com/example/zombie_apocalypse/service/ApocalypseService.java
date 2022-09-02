package com.example.zombie_apocalypse.service;

import com.example.zombie_apocalypse.model.Position;
import com.example.zombie_apocalypse.model.World;
import com.example.zombie_apocalypse.dto.Result;
import com.example.zombie_apocalypse.dto.ZombiesAndCreatures;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class ApocalypseService {
    public Result infection(World world) {
//        Queue<Position> zombiesQueue = new LinkedList<>();
//        int[][] grid = initWorld(world, zombiesQueue);
//        String commands = world.getCommands();
//        List<Position> zombies = new ArrayList<>();
//        List<Position> creatures = world.getCreatures();
//        Set<Position> creaturesSet = new HashSet<>(creatures);
//        zombieStartMove(grid, zombiesQueue, commands, zombies, creaturesSet);
//        ZombiesAndCreatures zombiesAndCreatures = new ZombiesAndCreatures(zombies, new ArrayList<>(creaturesSet));
//        return Result.success(zombiesAndCreatures);
        return new InfectionSimulation(world).infection();
    }
//
//    private void zombieStartMove(int[][] grid, Queue<Position> zombiesQueue, String commands, List<Position> zombies, Set<Position> creaturesSet) {
//        int zombieNumber = 0;
//        while (!zombiesQueue.isEmpty()) {
//            Position zombie = zombiesQueue.poll();
//            for (int i = 0; i < commands.length(); i++) {
//                char command = commands.charAt(i);
//                zombie = zombieMove(zombie, command, grid.length);
//                log.info("Zombie {} moved to ({}, {}).", zombieNumber, zombie.getX(), zombie.getY());
//                if (grid[zombie.getX()][zombie.getY()] == 1) {
//                    grid[zombie.getX()][zombie.getY()] = 0;
//                    zombiesQueue.offer(new Position(zombie.getX(), zombie.getY()));
//                    creaturesSet.remove(zombie);
//                    log.info("Zombie {} infected creature at ({}, {}).", zombieNumber, zombie.getX(), zombie.getY());
//                }
//            }
//            zombieNumber++;
//            zombies.add(zombie);
//        }
//        log.info("=============================");
//    }
//
//    private Position zombieMove(Position zombie, char command, int length) {
//        if (command == 'R') {
//            int indexX = zombie.getX();
//            indexX = indexX + 1 >= length ? 0 : indexX + 1;
//            zombie.setX(indexX);
//        }
//        if (command == 'L') {
//            int indexX = zombie.getX();
//            indexX = indexX - 1 < 0 ? length - 1 : indexX - 1;
//            zombie.setX(indexX);
//        }
//        if (command == 'D') {
//            int indexY = zombie.getY();
//            indexY = indexY + 1 >= length ? 0 : indexY + 1;
//            zombie.setY(indexY);
//        }
//        if (command == 'U') {
//            int indexY = zombie.getY();
//            indexY = indexY - 1 < 0 ? length - 1 : indexY - 1;
//            zombie.setY(indexY);
//        }
//        return zombie;
//    }
//
//    private int[][] initWorld(World world, Queue<Position> zombiesQueue) {
//        // 0 means no creatures, 1 means one creature stand there
//        int[][] grid = new int[world.getDimensions()][world.getDimensions()];
//        Position zombie = world.getZombie();
//        zombiesQueue.offer(zombie);
//        List<Position> creatures = world.getCreatures();
//        for (Position creature : creatures) {
//            grid[creature.getX()][creature.getY()] = 1;
//        }
//        return grid;
//    }
}
