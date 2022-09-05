package com.example.zombie_apocalypse.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class ZombiesAndCreatures {

    private List<Zombie> zombies;

    private List<Creature> creatures;
}
