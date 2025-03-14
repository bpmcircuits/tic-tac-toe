package com.kodilla.logic;

import com.kodilla.figures.Cross;
import com.kodilla.figures.Figure;
import com.kodilla.figures.None;
import com.kodilla.figures.Nought;
import com.kodilla.presentation.MenuEnum;

import java.awt.Point;
import java.util.Random;

public class Computer {

    private final Figure computerFigure;
    private final int boardSize;
    private final MenuEnum.ComputerLevelEnum computerLevel;
    private final Board board;
    private static final int MAX_DEPTH = 4;

    public Computer(Board board, Figure computerFigure, int boardSize, MenuEnum.ComputerLevelEnum computerLevel) {
        this.computerFigure = computerFigure;
        this.boardSize = boardSize;
        this.computerLevel = computerLevel;
        this.board = board;
    }

    public Point generateComputerMove() {
        return computerLevel == MenuEnum.ComputerLevelEnum.EASY ? getPointLevelEasy() : getPointLevelHard();
    }

    private Point getPointLevelEasy() {
        Random rand = new Random();
        Point move;
        do {
            int randomX = rand.nextInt(boardSize);
            int randomY = rand.nextInt(boardSize);
            move = new Point(randomX, randomY);
        } while (board.isPointOccupied(move));
        return move;
    }

    private Point getPointLevelHard() {
        int bestScore = Integer.MIN_VALUE;
        Point bestMove = null;
        for (int y = 0; y < board.getSize(); y++) {
            for (int x = 0; x < board.getSize(); x++) {
                Point move = new Point(x, y);
                if (!board.isPointOccupied(move)) {
                    board.setFigureToPosition(computerFigure, move);
                    int score = minimax(1, false);
                    board.setFigureToPosition(new None(), move);
                    if (score > bestScore) {
                        bestScore = score;
                        bestMove = move;
                    }
                }
            }
        }
        return bestMove != null ? bestMove : getPointLevelEasy();
    }

    private int minimax(int depth, boolean isMaximizing) {

        Figure winner = board.checkWinner();
        if (winner != null) {
            if (winner.getClass().equals(computerFigure.getClass())) {
                return 1000 - depth;
            } else {
                return -1000 + depth;
            }
        }

        if (board.isBoardFull()) {
            return 0;
        }

        if (depth == MAX_DEPTH) {
            return evaluateBoard();
        }

        int bestScore = isMaximizing ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        Figure currentFigure = isMaximizing ? computerFigure : getOpponentFigure();

        for (int y = 0; y < board.getSize(); y++) {
            for (int x = 0; x < board.getSize(); x++) {
                Point move = new Point(x, y);
                if (!board.isPointOccupied(move)) {
                    board.setFigureToPosition(currentFigure, move);
                    int score = minimax(depth + 1, !isMaximizing);
                    board.setFigureToPosition(new None(), move);
                    if (isMaximizing) {
                        bestScore = Math.max(bestScore, score);
                    } else {
                        bestScore = Math.min(bestScore, score);
                    }
                }
            }
        }
        return bestScore;
    }

    private int evaluateBoard() {
        int score = 0;
        for (int y = 0; y < board.getSize(); y++) {
            for (int x = 0; x < board.getSize(); x++) {
                Point point = new Point(x, y);
                Figure figure = board.getFigureFromPosition(point);
                if (!(figure instanceof None)) {
                    int countRow = board.countRowFiguresFromPosition(figure, point);
                    int countCol = board.countColumnFiguresFromPosition(figure, point);
                    int countDiag = board.countDiagonalFiguresFromPosition(figure, point);
                    int maxCount = Math.max(countRow, Math.max(countCol, countDiag)) + 1;
                    if (figure.getClass().equals(computerFigure.getClass())) {
                        score += maxCount;
                    } else {
                        score -= maxCount;
                    }
                }
            }
        }
        return score;
    }

    private Figure getOpponentFigure() {
        if (computerFigure.getClass().equals(Cross.class)) {
            return new Nought();
        } else {
            return new Cross();
        }
    }


    public Figure getComputerFigure() {
        return computerFigure;
    }

    public String getComputerName() {
        return "ENIAC";
    }
}
