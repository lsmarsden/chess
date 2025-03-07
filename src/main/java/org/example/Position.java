package org.example;

import lombok.Getter;

import java.util.Objects;

@Getter
public class Position {
    private final int x;
    private final int y;

    public Position(int x, int y) {
        if (x < 0 || y < 0 || x > 7 || y > 7) {
            throw new IllegalArgumentException("Position out of bounds");
        }

        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return getX() == position.getX() && getY() == position.getY();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY());
    }

    @Override
    public String toString() {
        return "[" + x + "," + y + ']';
    }
}
