package com.example.zombie_apocalypse.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@AllArgsConstructor
@Getter
public final class Position {

    private int x;
    private int y;

    public Position move(Position offset, int gridSize) {
        return new Position(correct(x + offset.x, gridSize),
                correct(y + offset.y, gridSize));
    }

    private int correct(int position, int gridSize) {
        return position >= gridSize ? 0 : position;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        if (x != position.x) return false;
        return y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
