package com.example.zombie_apocalypse.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
@NoArgsConstructor
public class InitialInfo {
    @Min(1)
    private int dimensions;

    @Valid
    @NonNull
    private Zombie zombie;

    @Valid
    @NonNull
    private List<Creature> creatures;

    @Pattern(regexp = "[RULD]+",message = "Command error")
    private String commands;
}
