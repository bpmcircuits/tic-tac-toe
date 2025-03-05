package com.kodilla;

import com.kodilla.figures.Cross;
import com.kodilla.figures.Nought;
import com.kodilla.logic.Board;
import static org.junit.jupiter.api.Assertions.*;
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
        for (int row = 0; row < 3; row++)
            board.setFigureToPosition(new Nought(), );

        // When
        boolean actual = board.checkWinningRows();

        // Then
        assertTrue(actual);
    }

    @Test
    void testNoughtWinCols() {
        // Given
        for (int col = 0; col < 3; col++)
            board.setFigureToPosition(new Nought(), );

        // When
        boolean actual = board.checkWinningColumns();

        // Then
        assertTrue(actual);
    }

    @Test
    void testNoughtWinDiagonals() {
        // Given
        for (int colRow = 0; colRow < 3; colRow++)
            board.setFigureToPosition(new Nought(), );

        // When
        boolean actual = board.checkWinningDiagonals();

        // Then
        assertTrue(actual);
    }

    @Test
    void testCrossWinRows() {
        // Given
        for (int row = 0; row < 3; row++)
            board.setFigureToPosition(new Cross(), );

        // When
        boolean actual = board.checkWinningRows();

        // Then
        assertTrue(actual);
    }

    @Test
    void testCrossWinCols() {
        // Given
        for (int col = 0; col < 3; col++)
            board.setFigureToPosition(new Cross(), );

        // When
        boolean actual = board.checkWinningColumns();

        // Then
        assertTrue(actual);
    }

    @Test
    void testCrossWinDiagonals() {
        // Given
        for (int colRow = 0; colRow < 3; colRow++)
            board.setFigureToPosition(new Cross(), );

        // When
        boolean actual = board.checkWinningDiagonals();

        // Then
        assertTrue(actual);
    }

    @Test
    void testDraw() {
        // Given
        board.setFigureToPosition(new Cross(), );
        board.setFigureToPosition(new Cross(), );
        board.setFigureToPosition(new Nought(), );
        board.setFigureToPosition(new Cross(), );
        board.setFigureToPosition(new Cross(), );
        board.setFigureToPosition(new Nought(), );
        board.setFigureToPosition(new Nought(), );
        board.setFigureToPosition(new Nought(), );
        board.setFigureToPosition(new Cross(), );

        // When
        boolean actual = board.isBoardFull();

        // Then
        assertTrue(actual);
    }

    @Test
    void testWhenIllegalMove() {
        // Given
        board.setFigureToPosition(new Cross(), );

        // When
        boolean actual = board.setFigureToPosition(new Nought(), );

        // Then
        assertFalse(actual);
    }
}
