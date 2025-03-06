package com.kodilla;

import com.kodilla.figures.Cross;
import com.kodilla.figures.Nought;
import com.kodilla.logic.Board;
import static org.junit.jupiter.api.Assertions.*;

import com.kodilla.logic.Settings;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        board.setFigureToPosition(new Nought(), "A1");
        board.setFigureToPosition(new Nought(), "B1");
        board.setFigureToPosition(new Nought(), "C1");

        // When
        boolean actual = board.checkWinningRows();

        // Then
        assertTrue(actual);
    }

    @Test
    void testNoughtWinCols() {
        // Given
        board.setFigureToPosition(new Nought(), "A1");
        board.setFigureToPosition(new Nought(), "A2");
        board.setFigureToPosition(new Nought(), "A3");

        // When
        boolean actual = board.checkWinningColumns();

        // Then
        assertTrue(actual);
    }

    @Test
    void testNoughtWinDiagonals() {
        // Given
        board.setFigureToPosition(new Nought(), "A1");
        board.setFigureToPosition(new Nought(), "B2");
        board.setFigureToPosition(new Nought(), "C3");

        // When
        boolean actual = board.checkWinningDiagonals();

        // Then
        assertTrue(actual);
    }

    @Test
    void testCrossWinRows() {
        // Given
        board.setFigureToPosition(new Cross(), "A1");
        board.setFigureToPosition(new Cross(), "B1");
        board.setFigureToPosition(new Cross(), "C1");

        // When
        boolean actual = board.checkWinningRows();

        // Then
        assertTrue(actual);
    }

    @Test
    void testCrossWinCols() {
        // Given
        board.setFigureToPosition(new Cross(), "A1");
        board.setFigureToPosition(new Cross(), "A2");
        board.setFigureToPosition(new Cross(), "A3");

        // When
        boolean actual = board.checkWinningColumns();

        // Then
        assertTrue(actual);
    }

    @Test
    void testCrossWinDiagonals() {
        // Given
        board.setFigureToPosition(new Cross(), "A1");
        board.setFigureToPosition(new Cross(), "B2");
        board.setFigureToPosition(new Cross(), "C3");

        // When
        boolean actual = board.checkWinningDiagonals();

        // Then
        assertTrue(actual);
    }

    @Test
    void testDraw() {
        // Given
        board.setFigureToPosition(new Cross(), "A1");
        board.setFigureToPosition(new Cross(), "A2");
        board.setFigureToPosition(new Nought(), "A3");
        board.setFigureToPosition(new Cross(), "B1");
        board.setFigureToPosition(new Cross(), "B2");
        board.setFigureToPosition(new Nought(), "B3");
        board.setFigureToPosition(new Nought(), "C1");
        board.setFigureToPosition(new Nought(), "C2");
        board.setFigureToPosition(new Cross(), "C3");

        // When
        boolean actual = board.isBoardFull();

        // Then
        assertTrue(actual);
    }

    @Test
    void testWhenIllegalMove() {
        // Given
        board.setFigureToPosition(new Cross(), "A1");

        // When
        boolean actual = board.isPointOccupied("A1");

        // Then
        assertTrue(actual);
    }
}
