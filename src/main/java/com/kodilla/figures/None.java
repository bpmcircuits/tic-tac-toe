package com.kodilla.figures;

import java.util.Objects;

public class None implements Figure {

    private final int id = 0;

    @Override
    public String toString() {
        return " ";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        None none = (None) o;
        return id == none.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
