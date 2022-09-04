package com.example.zombie_apocalypse.dto;

import lombok.AllArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;


@AllArgsConstructor
public final class Position {
    @NotNull
    private Integer x;
    @NotNull
    private Integer y;
    public Position move(Integer gridSize, Position offset) {
        int indexX = getX() + offset.getX();
        int indexY = getY() + offset.getY();
        indexX = indexX >= gridSize ? 0 : indexX;
        indexY = indexY >= gridSize ? 0 : indexY;
        return new Position(indexX, indexY);
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return Objects.equals(x, position.x) && Objects.equals(y, position.y);
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
