package com.example.zombie_apocalypse.dto;

import lombok.Data;
import lombok.NonNull;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class InitialInfo {
    @Min(1)
    private Integer dimensions;
    @Valid
    @NonNull
    private Zombie zombie;
    @Valid
    @NonNull
    private List<Creature> creatures;
    @Pattern(regexp = "[RULD]+",message = "Command error")
    private String commands;
}
