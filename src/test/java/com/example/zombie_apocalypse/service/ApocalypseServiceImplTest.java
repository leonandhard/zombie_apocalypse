package com.example.zombie_apocalypse.service;

import com.example.zombie_apocalypse.model.Creature;
import com.example.zombie_apocalypse.model.Position;
import com.example.zombie_apocalypse.model.World;
import com.example.zombie_apocalypse.dto.Result;
import com.example.zombie_apocalypse.dto.ZombiesAndCreatures;
import com.example.zombie_apocalypse.model.Zombie;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class ApocalypseServiceImplTest {

    @Autowired
    private ApocalypseService apocalypseService;

    @Test
    public void test() {
        World world = new World();
        world.setDimensions(10);
        Zombie zombie = new Zombie(new Position(2, 1));
        world.setZombie(zombie);
        List<Creature> creatures = new ArrayList<>();
        creatures.add(new Creature(new Position(3, 1)));
        creatures.add(new Creature(new Position(4, 1)));
        creatures.add(new Creature(new Position(5, 1)));
        creatures.add(new Creature(new Position(6, 1)));
        creatures.add(new Creature(new Position(7, 1)));
        creatures.add(new Creature(new Position(8, 1)));
        creatures.add(new Creature(new Position(9, 1)));
        creatures.add(new Creature(new Position(0, 1)));
        creatures.add(new Creature(new Position(9, 9)));
        world.setCreatures(creatures);
        world.setCommands("RD");
        Result result = apocalypseService.infection(world);
        ZombiesAndCreatures data = (ZombiesAndCreatures) result.getData();
        Assertions.assertEquals("[Zombie(position=Position(x=3, y=2)), Zombie(position=Position(x=4, y=2)), Zombie(position=Position(x=5, y=2)), Zombie(position=Position(x=6, y=2)), Zombie(position=Position(x=7, y=2)), Zombie(position=Position(x=8, y=2)), Zombie(position=Position(x=9, y=2)), Zombie(position=Position(x=0, y=2)), Zombie(position=Position(x=1, y=2))]",
                data.getZombies().toString());
//        System.out.println(data.getZombies());
        Assertions.assertEquals("[Creature(position=Position(x=9, y=9))]",
                data.getCreatures().toString());
//        System.out.println(data.getCreatures());
    }
}