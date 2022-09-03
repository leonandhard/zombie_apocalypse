package com.example.zombie_apocalypse.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
public class World {
    private Integer dimensions;

    private Zombie zombie;

    private List<Creature> creatures;

    private String commands;
}
