package com.example.zombie_apocalypse.service;

import com.example.zombie_apocalypse.dto.Creature;
import com.example.zombie_apocalypse.dto.InfectionResponse;
import com.example.zombie_apocalypse.dto.InitialInfo;
import com.example.zombie_apocalypse.dto.Position;
import com.example.zombie_apocalypse.dto.Zombie;
import com.example.zombie_apocalypse.dto.ZombiesAndCreatures;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Queue;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ApocalypseService {
    private final static Map<Character, Position> commandOffsetMap = Map.of(
            'R', new Position(1, 0),
            'L', new Position(-1, 0),
            'U', new Position(0, -1),
            'D', new Position(0, 1)
    );

    public InfectionResponse infection(InitialInfo world) {

        List<Zombie> allZombies = new ArrayList<>();
        Map<Position, Creature> creatureMap = world.getCreatures().stream()
                .collect(Collectors.toMap(Creature::getPosition, Function.identity()));

        int gridSize = world.getDimensions();
        String commands = world.getCommands();

        Queue<Zombie> zombiesQueue = new LinkedList<>();
        zombiesQueue.offer(world.getZombie());

        while (!zombiesQueue.isEmpty()) {
            Zombie movingZombie = zombiesQueue.poll();

            for (int i = 0; i < commands.length(); i++) {
                char command = commands.charAt(i);

                movingZombie.move(commandOffsetMap.get(command), gridSize);
                log.info("Zombie {} moved to ({}).", allZombies.size(), movingZombie.getPosition());

                infectCreature(movingZombie, creatureMap).ifPresent(creature -> {
                    zombiesQueue.offer(new Zombie(creature.getPosition()));
                    creatureMap.remove(creature.getPosition());
                    log.info("Zombie {} infected creature at ({}).", allZombies.size(), movingZombie.getPosition());
                });
            }

            allZombies.add(movingZombie);
        }

        log.info("=============================");
        ZombiesAndCreatures zombiesAndCreatures = ZombiesAndCreatures.builder().zombies(allZombies).creatures(new ArrayList<>(creatureMap.values())).build();
        return InfectionResponse.builder().success(true).code(200).msg("success").data(zombiesAndCreatures)
                .build();
    }

    private Optional<Creature> infectCreature(Zombie movingZombie, Map<Position, Creature> creatureMap) {
       return Optional.ofNullable(creatureMap.get(movingZombie.getPosition()));
    }

    private Optional<Creature> zombieInfectCreature(Zombie zombie, Set<Creature> creaturesSet) {
        return creaturesSet.stream()
                .filter(c -> c.getPosition().equals(zombie.getPosition()))
                .findFirst();
    }

}
