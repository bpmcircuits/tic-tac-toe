package com.kodilla.logic;

import com.kodilla.figures.Cross;
import com.kodilla.figures.Figure;
import com.kodilla.figures.Nought;
import com.kodilla.presentation.MenuEnum;
import com.kodilla.presentation.UserInterface;

import java.awt.*;

public class GameLogic {

    public void run() {

        while (true) {

            int menuChoice = UserInterface.mainMenuChoice();
            MenuEnum.MainMenuOption mainMenuOption = MenuEnum.MainMenuOption.fromValue(menuChoice);

            switch (mainMenuOption) {
                case NEW_GAME -> newGameMenu();
                case EXIT -> {
                    if (UserInterface.onExit()) return;
                }
            }
        }
    }

    private void newGameMenu() {

        while (true) {

            int menuChoice = UserInterface.newGameMenu();
            MenuEnum.NewGameMenuOption newGameMenuOption = MenuEnum.NewGameMenuOption.fromValue(menuChoice);

            switch (newGameMenuOption) {
                case PLAYER_VS_PLAYER -> renderGameWith(MenuEnum.HumanOrComputerEnum.HUMAN);
                case PLAYER_VS_COMPUTER -> renderGameWith(MenuEnum.HumanOrComputerEnum.COMPUTER);
                case BACK -> {
                    return;
                }
            }
        }
    }

    private void renderGameWith(MenuEnum.HumanOrComputerEnum playerOfChoice) {

        int boardSize = UserInterface.chooseBoardSize();
        String playerOneName = UserInterface.choosePlayerName(Settings.PLAYER.FIRST);
        String playerTwoName;
        String figure = UserInterface.chooseFigure();
        Player playerOne = new Player(playerOneName, getFigure(figure));
        Player playerTwo = null;
        Computer computer = null;

        Board board = new Board(boardSize, playerOne.playerFigure());
        board.initBoard();

        if (playerOfChoice == MenuEnum.HumanOrComputerEnum.HUMAN) {
            playerTwoName = UserInterface.choosePlayerName(Settings.PLAYER.SECOND);
            playerTwo = new Player(playerTwoName, getOpponentFigure(figure));
        } else {
            MenuEnum.ComputerLevelEnum computerLevel = UserInterface.chooseComputerLevel();
            computer = new Computer(board, getOpponentFigure(figure), boardSize, computerLevel);
            playerTwoName = computer.getComputerName();
        }

        UserInterface.displayPlayers(playerOneName, playerTwoName);

        System.out.println(board);

        boolean finishedGame = false;
        while (!finishedGame) {

            UserInterface.showWhichPlayerTurn(isCurrentPlayerTurn(board, playerOne) ? playerOneName : playerTwoName);

            Point position;
            if (playerOfChoice == MenuEnum.HumanOrComputerEnum.HUMAN) {

                position = UserInterface.getFigurePosition(board.getSize());

                if (board.isPointOccupied(position)) {
                    UserInterface.wrongPlace();
                    continue;
                }

                board.setFigureToPosition(isCurrentPlayerTurn(board, playerOne)
                        ? playerOne.playerFigure()
                        : playerTwo.playerFigure(), position);

            } else {

                if (isCurrentPlayerTurn(board, playerOne)) {
                    while (true) {
                        position = UserInterface.getFigurePosition(board.getSize());
                        if (board.isPointOccupied(position)) {
                            UserInterface.wrongPlace();
                        } else {
                            board.setFigureToPosition(playerOne.playerFigure(), position);
                            break;
                        }
                    }

                } else {
                    board.setFigureToPosition(computer.getComputerFigure(), computer.generateComputerMove());
                }
            }

            if (board.checkWinner() != null) {
                finishedGame = true;
                UserInterface.showWinner(isCurrentPlayerWinner(board, playerOne) ? playerOneName : playerTwoName);
            } else if (board.isBoardFull()) {
                finishedGame = true;
                UserInterface.showDraw();
            } else {
                board.switchToNextTurn();
            }

            System.out.println(board);
        }
    }



    private boolean isCurrentPlayerTurn(Board board, Player player) {
        return board.getCurrentPlayer().getClass() == player.playerFigure().getClass();
    }

    private boolean isCurrentPlayerWinner(Board board, Player player) {
        return board.getWinner() == player.playerFigure();
    }

    private Figure getFigure(String figure) {
        return figure.equals("X") ? new Cross() : new Nought();
    }

    private Figure getOpponentFigure(String figure) {
        return figure.equals("X") ? new Nought() : new Cross();
    }
}
