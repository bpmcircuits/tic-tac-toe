package com.kodilla.logic;

import com.kodilla.figures.Cross;
import com.kodilla.figures.Figure;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Board {

    private List<BoardRow> rows = new ArrayList<>();

    public Board() {}

    public void init() {
        for (int row = 0; row < 3; row++)
            rows.add(new BoardRow());
    }

    public void setFigure(Point point, Figure figure) {
        rows.get(point.y).getCols().set(point.x, figure);
    }

    public Figure getFigure(Point point) {
        return rows.get(point.y).getCols().get(point.x);
    }

    @Override
    public String toString() {
        String s = "   A B C \n";
        for (int row = 0; row < 3; row++) {
            s += rows.get(row).toString(row+1);
        }
        return s;
    }


}
