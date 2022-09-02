package com.example.zombie_apocalypse.model;

import lombok.Data;

import java.util.List;

@Data
public class World {
    private Integer dimensions;

    private Position zombie;

    private List<Position> creatures;

    private String commands;
}
