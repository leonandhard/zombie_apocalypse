package com.example.zombie_apocalypse.dto;

import com.example.zombie_apocalypse.model.Position;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ZombiesAndCreatures {
    private List<Position> zombies;

    private List<Position> creatures;
}
