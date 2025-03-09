package com.kodilla;

import com.kodilla.figures.Cross;
import com.kodilla.figures.Nought;
import com.kodilla.logic.Board;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

public class TicTacToeTestSuite {

    private Board board;

    @BeforeEach
    public void setUp() {
        board = new Board(3, new Cross());
        board.initBoard();
    }

    @Test
    void testNoughtWinRows() {
        // Given
        for (int row = 0; row < 3; row++) {
            board.setFigureToPosition(new Nought(), new Point(row, 0));
        }

        // When
        boolean actual = board.checkWinningRows();

        // Then
        assertTrue(actual);
    }

    @Test
    void testNoughtWinCols() {
        // Given
        for (int row = 0; row < 3; row++) {
            board.setFigureToPosition(new Nought(), new Point(0, row));
        }

        // When
        boolean actual = board.checkWinningColumns();

        // Then
        assertTrue(actual);
    }

    @Test
    void testNoughtWinDiagonals() {
        // Given
        for (int rowCols = 0; rowCols < 3; rowCols++) {
            board.setFigureToPosition(new Nought(), new Point(rowCols, rowCols));
        }

        // When
        boolean actual = board.checkWinningDiagonals();

        // Then
        assertTrue(actual);
    }

    @Test
    void testCrossWinRows() {
        // Given
        for (int row = 0; row < 3; row++) {
            board.setFigureToPosition(new Cross(), new Point(row, 0));
        }

        // When
        boolean actual = board.checkWinningRows();

        // Then
        assertTrue(actual);
    }

    @Test
    void testCrossWinCols() {
        // Given
        for (int row = 0; row < 3; row++) {
            board.setFigureToPosition(new Cross(), new Point(0, row));
        }

        // When
        boolean actual = board.checkWinningColumns();

        // Then
        assertTrue(actual);
    }

    @Test
    void testCrossWinDiagonals() {
        // Given
        for (int rowCols = 0; rowCols < 3; rowCols++) {
            board.setFigureToPosition(new Cross(), new Point(rowCols, rowCols));
        }

        // When
        boolean actual = board.checkWinningDiagonals();

        // Then
        assertTrue(actual);
    }

    @Test
    void testDraw() {
        // Given
        board.setFigureToPosition(new Cross(), new Point(0, 0));
        board.setFigureToPosition(new Cross(), new Point(0, 1));
        board.setFigureToPosition(new Nought(), new Point(0, 2));
        board.setFigureToPosition(new Cross(), new Point(1, 0));
        board.setFigureToPosition(new Cross(), new Point(1, 1));
        board.setFigureToPosition(new Nought(), new Point(1, 2));
        board.setFigureToPosition(new Nought(), new Point(2, 0));
        board.setFigureToPosition(new Nought(), new Point(2, 1));
        board.setFigureToPosition(new Cross(), new Point(2, 2));

        // When
        boolean actual = board.isBoardFull();

        // Then
        assertTrue(actual);
    }

    @Test
    void testWhenIllegalMove() {
        // Given
        board.setFigureToPosition(new Cross(), new Point(0, 0));

        // When
        boolean actual = board.isPointOccupied(new Point(0, 0));

        // Then
        assertTrue(actual);
    }
}
