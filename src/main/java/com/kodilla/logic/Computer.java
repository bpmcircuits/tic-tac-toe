package com.kodilla.logic;

import com.kodilla.figures.Figure;

import java.util.Random;

public class Computer {

    private final Figure computerFigure;
    private final int boardSize;

    public Computer(Figure computerFigure, int boardSize) {
        this.computerFigure = computerFigure;
        this.boardSize = boardSize;
    }

    public String generateComputerMove() {
        Random rand = new Random();

        int randomLetter = rand.nextInt(boardSize);
        int randomNumber = rand.nextInt(1, boardSize + 1);

        return String.valueOf((char) ('A' + randomLetter)) + randomNumber;
    }

    public Figure getComputerFigure() {
        return computerFigure;
    }

    public String getComputerName() {
        return "ENIAC";
    }
}
