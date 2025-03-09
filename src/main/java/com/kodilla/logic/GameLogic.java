package com.kodilla.logic;

import com.kodilla.figures.Cross;
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
                case STATISTICS -> UserInterface.showStatistics();
                case ABOUT -> UserInterface.showAbout();
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

        int boardSize = UserInterface.getBoardSize();
        String playerOneName = UserInterface.getPlayerName(Settings.PLAYER.FIRST);
        String playerTwoName;
        String figure = UserInterface.getFigure();
        Player playerOne = new Player(playerOneName, figure.equals("X") ? new Cross() : new Nought());
        Player playerTwo = null;
        Computer computer = null;

        if (playerOfChoice == MenuEnum.HumanOrComputerEnum.HUMAN) {
            playerTwoName = UserInterface.getPlayerName(Settings.PLAYER.SECOND);
            playerTwo = new Player(playerTwoName, figure.equals("X") ? new Nought() : new Cross());
        } else {
            computer = new Computer(figure.equals("X") ? new Nought() : new Cross(), boardSize);
            playerTwoName = computer.getComputerName();
        }

        UserInterface.displayPlayers(playerOneName, playerTwoName);

        Board board = new Board(boardSize, playerOne.playerFigure());
        board.initBoard();

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
                    do {
                        position = computer.generateComputerMove();
                    } while (board.isPointOccupied(position));
                    board.setFigureToPosition(computer.getComputerFigure(), position);
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
}
