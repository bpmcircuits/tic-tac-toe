package com.kodilla.figures;

import java.util.Objects;

public class Nought implements Figure {

    private final int id = 1;

    @Override
    public String toString() {
        return "O";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Nought nought = (Nought) o;
        return id == nought.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
