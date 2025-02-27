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

    public void init() {
        for (int row = 0; row < size; row++)
            rows.add(new BoardRow(size));
    }

    public int getSize() {
        return size;
    }

    public boolean setFigure(Point point, Figure figure) {
        if (getFigure(point).getClass() == None.class) {
            rows.get(point.y).getCols().set(point.x, figure);
            return true;
        } else return false;
    }

    public Figure getFigure(Point point) {
        return rows.get(point.y).getCols().get(point.x);
    }

    public void nextTurn() {
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
        if (checkRows()) return winner;
        else if (checkColumns()) return winner;
        else if (checkDiagonals()) return winner;
        else return null;
    }

    private boolean checkRows() {
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

    private boolean checkColumns() {
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

    private boolean checkDiagonals() {
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
