package com.kodilla.logic;

import com.kodilla.figures.Figure;
import com.kodilla.figures.None;

import java.awt.*;
import java.util.Objects;
import java.util.regex.Pattern;

public class PossibleMove {

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
}
