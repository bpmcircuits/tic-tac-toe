package com.kodilla.logic;

import com.kodilla.figures.Figure;
import com.kodilla.figures.None;

import java.awt.*;
import java.util.Objects;
import java.util.regex.Pattern;

public class MoveChecker {

    public static boolean checkStringPositionName(String move) {
        return Pattern.compile("^(?:[ABC][123])$").matcher(move).matches();
    }

    public static boolean checkPossibleMove(Board board, String move) {
        return Objects.equals(board.getFigure(findPoint(move)), new None());
    }

    public static Point findPoint(String move) {
        return switch (move) {
            case "A1" -> new Point(0, 0);
            case "A2" -> new Point(0, 1);
            case "A3" -> new Point(0, 2);
            case "B1" -> new Point(1, 0);
            case "B2" -> new Point(1, 1);
            case "B3" -> new Point(1, 2);
            case "C1" -> new Point(2, 0);
            case "C2" -> new Point(2, 1);
            case "C3" -> new Point(2, 2);
            default -> throw new IllegalStateException("Unexpected value: " + move);
        };
    }

    public static boolean checkWin(Board board, Player player) {
        if (hasWinningLine(board)) {
            player.setPlayerWin(true);
            return true;
        }
        return false;
    }

    private static boolean hasWinningLine(Board board) {

        for (int i = 0; i < 3; i++) {
            if (checkLine(board, new Point(0, i), new Point(1, i), new Point(2, i)) ||
                    checkLine(board, new Point(i, 0), new Point(i, 1), new Point(i, 2))) {
                return true;
            }
        }

        return checkLine(board, new Point(0, 0), new Point(1, 1), new Point(2, 2)) ||
                checkLine(board, new Point(0, 2), new Point(1, 1), new Point(2, 0));
    }

    private static boolean checkLine(Board board, Point... points) {
        Figure first = board.getFigure(points[0]);
        if (first.equals(new None())) {
            return false;
        }

        for (Point p : points) {
            if (!first.equals(board.getFigure(p))) {
                return false;
            }
        }
        return true;
    }
}
