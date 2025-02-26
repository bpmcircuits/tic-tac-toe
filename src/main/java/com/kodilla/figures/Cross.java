package com.kodilla.figures;

import java.util.Objects;

public class Cross implements Figure {

    private final int id = 2;

    @Override
    public String toString() {
        return "X";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Cross cross = (Cross) o;
        return id == cross.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
