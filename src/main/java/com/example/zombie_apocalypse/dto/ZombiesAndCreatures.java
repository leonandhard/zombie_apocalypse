package com.example.zombie_apocalypse.dto;

import com.example.zombie_apocalypse.model.Creature;
import com.example.zombie_apocalypse.model.Zombie;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ZombiesAndCreatures {
    private List<Zombie> zombies;

    private List<Creature> creatures;
}
