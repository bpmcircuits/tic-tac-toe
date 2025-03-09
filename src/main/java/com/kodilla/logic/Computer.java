package com.kodilla.logic;

import com.kodilla.figures.Figure;

import java.awt.*;
import java.util.Random;

public class Computer {

    private final Figure computerFigure;
    private final int boardSize;

    public Computer(Figure computerFigure, int boardSize) {
        this.computerFigure = computerFigure;
        this.boardSize = boardSize;
    }

    public Point generateComputerMove() {
        Random rand = new Random();

        int randomX = rand.nextInt(boardSize);
        int randomY = rand.nextInt(boardSize);

        return new Point(randomX, randomY);
    }

    public Figure getComputerFigure() {
        return computerFigure;
    }

    public String getComputerName() {
        return "ENIAC";
    }
}
