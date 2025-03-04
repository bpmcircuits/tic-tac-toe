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
        board.init();
    }

    @Test
    void testNoughtWinRows() {
        // Given
        for (int row = 0; row < 3; row++)
            board.setFigure(new Point(row,0), new Nought());

        // When
        boolean actual = board.checkRows();

        // Then
        assertTrue(actual);
    }

    @Test
    void testNoughtWinCols() {
        // Given
        for (int col = 0; col < 3; col++)
            board.setFigure(new Point(0, col), new Nought());

        // When
        boolean actual = board.checkColumns();

        // Then
        assertTrue(actual);
    }

    @Test
    void testNoughtWinDiagonals() {
        // Given
        for (int colRow = 0; colRow < 3; colRow++)
            board.setFigure(new Point(colRow, colRow), new Nought());

        // When
        boolean actual = board.checkDiagonals();

        // Then
        assertTrue(actual);
    }

    @Test
    void testCrossWinRows() {
        // Given
        for (int row = 0; row < 3; row++)
            board.setFigure(new Point(row, 0), new Cross());

        // When
        boolean actual = board.checkRows();

        // Then
        assertTrue(actual);
    }

    @Test
    void testCrossWinCols() {
        // Given
        for (int col = 0; col < 3; col++)
            board.setFigure(new Point(0, col), new Cross());

        // When
        boolean actual = board.checkColumns();

        // Then
        assertTrue(actual);
    }

    @Test
    void testCrossWinDiagonals() {
        // Given
        for (int colRow = 0; colRow < 3; colRow++)
            board.setFigure(new Point(colRow, colRow), new Cross());

        // When
        boolean actual = board.checkDiagonals();

        // Then
        assertTrue(actual);
    }

    @Test
    void testDraw() {
        // Given
        board.setFigure(new Point(0, 0), new Cross());
        board.setFigure(new Point(0, 1), new Cross());
        board.setFigure(new Point(0, 2), new Nought());
        board.setFigure(new Point(1, 0), new Cross());
        board.setFigure(new Point(1, 1), new Cross());
        board.setFigure(new Point(1, 2), new Nought());
        board.setFigure(new Point(2, 0), new Nought());
        board.setFigure(new Point(2, 1), new Nought());
        board.setFigure(new Point(2, 2), new Cross());

        // When
        boolean actual = board.isBoardFull();

        // Then
        assertTrue(actual);
    }

    @Test
    void testWhenIllegalMove() {
        // Given
        board.setFigure(new Point(0, 0), new Cross());

        // When
        boolean actual = board.setFigure(new Point(0, 0), new Nought());

        // Then
        assertFalse(actual);
    }
}
