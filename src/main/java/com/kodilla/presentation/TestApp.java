package com.kodilla.presentation;

import com.kodilla.figures.Cross;
import com.kodilla.figures.Nought;
import com.kodilla.logic.Board;

public class TestApp {

    public static void main(String[] args) {

//        Board board = new Board();
//        board.init();
//        board.setFigure(0, 0, new Cross());
//        board.setFigure(2, 2, new Nought());
//        board.setFigure(1, 1, new Cross());
//        System.out.println(board);
        UserInterface userInterface = new UserInterface();
        userInterface.run();

    }

}
