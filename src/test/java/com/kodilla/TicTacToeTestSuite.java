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

    @Test
    void testCountWhenZero() {
        int actual = board.countRowFiguresFromPosition(new Cross(), new Point(2, 2));
        int actual1 = board.countRowFiguresFromPosition(new Cross(), new Point(2, 2));
        int actual2 = board.countRowFiguresFromPosition(new Cross(), new Point(2, 2));

        assertEquals(0, actual);
        assertEquals(0, actual1);
        assertEquals(0, actual2);
    }

    @Test
    void testCountWhenOneAnywhere() {
        Board board = new Board(3, new Cross());
        board.initBoard();
        board.setFigureToPosition(new Cross(), new Point(0, 0));

        int actual1 = board.countRowFiguresFromPosition(new Cross(), new Point(1, 0));
        int actual2 = board.countColumnFiguresFromPosition(new Cross(), new Point(0, 1));
        int actual3 = board.countDiagonalFiguresFromPosition(new Cross(), new Point(1, 1));

        assertEquals(1, actual1);
        assertEquals(1, actual2);
        assertEquals(1, actual3);

    }

    @Test
    void testCountWhenThreeInRow() {

        Board board2 = new Board(10, new Cross());
        board2.initBoard();

        board2.setFigureToPosition(new Cross(), new Point(0, 0));
        board2.setFigureToPosition(new Cross(), new Point(1, 0));
        board2.setFigureToPosition(new Cross(), new Point(2, 0));

        int actual = board2.countRowFiguresFromPosition(new Cross(), new Point(3, 0));

        assertEquals(3, actual);
    }

    @Test
    void testCountWhenThreeInRowAndFourthIsDifferent() {

        Board board2 = new Board(10, new Cross());
        board2.initBoard();

        board2.setFigureToPosition(new Cross(), new Point(0, 0));
        board2.setFigureToPosition(new Cross(), new Point(1, 0));
        board2.setFigureToPosition(new Cross(), new Point(2, 0));
        board2.setFigureToPosition(new Nought(), new Point(3, 0));

        int actual = board2.countRowFiguresFromPosition(new Cross(), new Point(4, 0));

        assertEquals(0, actual);
    }

    @Test
    void testCountWhenThreeInColumn() {

        Board board2 = new Board(10, new Cross());
        board2.initBoard();

        board2.setFigureToPosition(new Cross(), new Point(0, 0));
        board2.setFigureToPosition(new Cross(), new Point(0, 1));
        board2.setFigureToPosition(new Cross(), new Point(0, 2));

        int actual = board2.countColumnFiguresFromPosition(new Cross(), new Point(0, 3));

        assertEquals(3, actual);
    }

    @Test
    void testCountWhenThreeInColumnAndFourthIsDifferent() {

        Board board2 = new Board(10, new Cross());
        board2.initBoard();

        board2.setFigureToPosition(new Cross(), new Point(0, 0));
        board2.setFigureToPosition(new Cross(), new Point(0, 1));
        board2.setFigureToPosition(new Cross(), new Point(0, 2));
        board2.setFigureToPosition(new Nought(), new Point(0, 3));

        int actual = board2.countColumnFiguresFromPosition(new Cross(), new Point(0, 4));

        assertEquals(0, actual);
    }

    @Test
    void testCountWhenThreeInDiagonal() {

        Board board2 = new Board(10, new Cross());
        board2.initBoard();

        board2.setFigureToPosition(new Cross(), new Point(0, 0));
        board2.setFigureToPosition(new Cross(), new Point(1, 1));
        board2.setFigureToPosition(new Cross(), new Point(2, 2));

        int actual = board2.countDiagonalFiguresFromPosition(new Cross(), new Point(3, 3));

        assertEquals(3, actual);
    }

    @Test
    void testCountWhenThreeInDiagonalAndFourthIsDifferent() {

        Board board2 = new Board(10, new Cross());
        board2.initBoard();

        board2.setFigureToPosition(new Cross(), new Point(0, 0));
        board2.setFigureToPosition(new Cross(), new Point(1, 1));
        board2.setFigureToPosition(new Cross(), new Point(2, 2));
        board2.setFigureToPosition(new Nought(), new Point(3, 3));

        int actual = board2.countDiagonalFiguresFromPosition(new Cross(), new Point(4, 4));

        assertEquals(0, actual);
    }
}
