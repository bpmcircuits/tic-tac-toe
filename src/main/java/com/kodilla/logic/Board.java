package com.kodilla.logic;

import com.kodilla.figures.Cross;
import com.kodilla.figures.Figure;
import com.kodilla.figures.None;
import com.kodilla.figures.Nought;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Board {

    private final int size;
    private List<BoardRow> rows = new ArrayList<>();
    private Figure currentPlayer;
    private Figure winner;
    private int winLength = Settings.getMaxWinLength();

    public Board(int size, Figure playerFigure) {
        if (size <= winLength) winLength = size;
        this.size = size;
        currentPlayer = playerFigure;
    }

    public void initBoard() {
        for (int row = 0; row < size; row++)
            rows.add(new BoardRow(size));
    }

    public int getSize() {
        return size;
    }

    public void setFigureToPosition(Figure figure, Point point) {
        rows.get(point.y).getCols().set(point.x, figure);
    }

    public Figure getFigureFromPosition(Point point) {
        return rows.get(point.y).getCols().get(point.x);
    }

    public boolean isPointOccupied(Point point) {
        return getFigureFromPosition(point).getClass() != None.class;
    }

    public void switchToNextTurn() {
        currentPlayer = (currentPlayer.getClass() == Cross.class) ? new Nought() : new Cross();
    }

    public Figure getCurrentPlayer() {
        return currentPlayer;
    }

    public boolean isBoardFull() {
        for (BoardRow row : rows) {
            for (Figure fig : row.getCols()) {
                if (fig instanceof None) {
                    return false;
                }
            }
        }
        return true;
    }

    public Figure getWinner() {
        return winner;
    }

    public Figure checkWinner() {
        if (checkWinningRows()) return winner;
        else if (checkWinningColumns()) return winner;
        else if (checkWinningDiagonals()) return winner;
        else return null;
    }

    public boolean checkWinningRows() {
        for (int i = 0; i < size; i++) {
            List<Figure> cols = rows.get(i).getCols();
            for (int j = 0; j <= size - winLength; j++) {
                Figure first = cols.get(j);
                if (first instanceof None) continue;
                boolean win = true;
                for (int k = 1; k < winLength; k++) {
                    if (cols.get(j + k).getClass() != first.getClass()) {
                        win = false;
                        break;
                    }
                }
                if (win) {
                    winner = first;
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkWinningColumns() {
        for (int col = 0; col < size; col++) {
            for (int row = 0; row <= size - winLength; row++) {
                Figure first = rows.get(row).getCols().get(col);
                if (first instanceof None) continue;
                boolean win = true;
                for (int k = 1; k < winLength; k++) {
                    if (rows.get(row + k).getCols().get(col).getClass() != first.getClass()) {
                        win = false;
                        break;
                    }
                }
                if (win) {
                    winner = first;
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkWinningDiagonals() {
        for (int i = 0; i <= size - winLength; i++) {
            for (int j = 0; j <= size - winLength; j++) {
                Figure first = rows.get(i).getCols().get(j);
                if (first instanceof None) continue;
                boolean win = true;
                for (int k = 1; k < winLength; k++) {
                    if (rows.get(i + k).getCols().get(j + k).getClass() != first.getClass()) {
                        win = false;
                        break;
                    }
                }
                if (win) {
                    winner = first;
                    return true;
                }
            }
        }

        for (int i = 0; i <= size - winLength; i++) {
            for (int j = winLength - 1; j < size; j++) {
                Figure first = rows.get(i).getCols().get(j);
                if (first instanceof None) continue;
                boolean win = true;
                for (int k = 1; k < winLength; k++) {
                    if (rows.get(i + k).getCols().get(j - k).getClass() != first.getClass()) {
                        win = false;
                        break;
                    }
                }
                if (win) {
                    winner = first;
                    return true;
                }
            }
        }
        return false;
    }

    public int countRowFiguresFromPosition(Figure target, Point point) {
        int count = 0;
        int col = point.x - 1; // liczenie w lewą stronę
        while (col >= 0 && isTargetFigureRow(target, point, col)) {
            count++;
            col--;
        }

        col = point.x + 1; // liczenie w prawą stronę
        while (col < size && isTargetFigureRow(target, point, col)) {
            count++;
            col++;
        }
        return count;
    }

    public int countColumnFiguresFromPosition(Figure target, Point point) {
        int count = 0;
        int row = point.y - 1; // liczenie w dół
        while (row >= 0 && isTargetFigureColumn(target, point, row)) {
            count++;
            row--;
        }

        row = point.y + 1; // liczenie w górę
        while (row < size && isTargetFigureColumn(target, point, row)) {
            count++;
            row++;
        }
        return count;
    }

    public int countDiagonalFiguresFromPosition(Figure target, Point point) {
        int countMain = 0;

        // liczenie w lewy dół
        int row = point.y - 1;
        int col = point.x - 1;
        while (row >= 0 && col >= 0 && isTargetFigureDiagonal(target, row, col)) {
            countMain++;
            row--;
            col--;
        }

        // liczenie w prawą górę
        row = point.y + 1;
        col = point.x + 1;
        while (row < size && col < size && isTargetFigureDiagonal(target, row, col)) {
            countMain++;
            row++;
            col++;
        }

        // liczenie w prawy dół
        int countAnti = 0;
        row = point.y - 1;
        col = point.x + 1;
        while (row >= 0 && col < size && isTargetFigureDiagonal(target, row, col)) {
            countAnti++;
            row--;
            col++;
        }

        // liczenie w lewą górę
        row = point.y + 1;
        col = point.x - 1;
        while (row < size && col >= 0 && isTargetFigureDiagonal(target, row, col)) {
            countAnti++;
            row++;
            col--;
        }

        return Math.max(countMain, countAnti);
    }

    private boolean isTargetFigureRow(Figure target, Point point, int col) {
        return rows.get(point.y).getCols().get(col).getClass() == target.getClass();
    }

    private boolean isTargetFigureColumn(Figure target, Point point, int row) {
        return rows.get(row).getCols().get(point.x).getClass() == target.getClass();
    }

    private boolean isTargetFigureDiagonal(Figure target, int row, int col) {
        return rows.get(row).getCols().get(col).getClass() == target.getClass();
    }


    @Override
    public String toString() {
        String s = "   ";
        for (char ch = 'A'; ch < 'A' + size; ch++) {
            s += ch + " ";
        }
        s += "\n";
        for (int row = 0; row < size; row++) {
            s += rows.get(row).toString(row+1);
        }
        return s;
    }
}
