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

    public void setFigureToPosition(Figure figure, String position) {
        Point point = MoveChecker.findPoint(position);
        rows.get(point.y).getCols().set(point.x, figure);
    }

    public Figure getFigureFromPosition(String position) {
        Point point = MoveChecker.findPoint(position);
        return rows.get(point.y).getCols().get(point.x);
    }

    public boolean isPointOccupied(String position) {
        return getFigureFromPosition(position).getClass() != None.class;
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
