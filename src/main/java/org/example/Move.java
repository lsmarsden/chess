package org.example;

public record Move(Colour player, Position from, Position to) {

    @Override
    public String toString() {
        return from + " -> " + to;
    }
}
