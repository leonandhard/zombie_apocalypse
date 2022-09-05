package com.example.zombie_apocalypse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Zombie {
    @Valid
    private Position position;

    public void move(Position offset, int gridSize) {
        this.position = position.move(offset, gridSize);
    }
}
