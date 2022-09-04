package com.example.zombie_apocalypse.model;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class World {
    @NotNull(message = "Grid size must not be null")
    @Min(value = 2, message = "The minimum value of grid size is 1")
    private Integer dimensions;

    @NotNull(message = "The first zombie's position cannot be null")
    private Zombie zombie;

    @NotNull(message = "Creatures must not be null")
    private List<Creature> creatures;

    @NotBlank(message = "Commands must not be null")
    private String commands;
}
