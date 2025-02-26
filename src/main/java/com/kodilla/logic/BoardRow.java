package com.kodilla.logic;

import com.kodilla.figures.Figure;
import com.kodilla.figures.None;

import java.util.ArrayList;
import java.util.List;

public class BoardRow {

    private final int size;
    private List<Figure> cols = new ArrayList<>();

    public BoardRow(int size) {
        this.size = size;
        for (int col = 0; col < size; col++) {
            cols.add(new None());
        }
    }

    public List<Figure> getCols() {
        return cols;
    }

    public String toString(int rowNumber) {
        String s = rowNumber + " |";
        for (int col = 0; col < size; col++) {
            s += cols.get(col) + "|";
        }
        s += "\n";
        return s;
    }
}
