package com.example.zombie_apocalypse.dto;

import com.example.zombie_apocalypse.dto.Position;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.Valid;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Zombie {
    @Valid
    private Position position;

    public void setPosition(Position position) {
        this.position = new Position(position.getX(), position.getY());
    }


}
