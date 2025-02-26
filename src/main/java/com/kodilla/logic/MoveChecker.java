package com.kodilla.logic;

import java.awt.*;

public class MoveChecker {

    public static boolean checkStringPositionName(String move, int boardSize) {
        if (move == null || move.length() < 2) {
            return false;
        }
        char colChar = move.charAt(0);
        if (colChar < 'A' || colChar > ('A' + boardSize - 1)) {
            return false;
        }
        String rowPart = move.substring(1);
        try {
            int row = Integer.parseInt(rowPart);
            return row >= 1 && row <= boardSize;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static Point findPoint(String move) {
        char colChar = move.charAt(0);
        int col = colChar - 'A';
        int row = Integer.parseInt(move.substring(1)) - 1;
        return new Point(col, row);
    }
}
