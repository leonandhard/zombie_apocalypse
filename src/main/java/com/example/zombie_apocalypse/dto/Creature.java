package com.example.zombie_apocalypse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.Valid;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Creature {
    @Valid
    @NonNull
    private Position position;

}
