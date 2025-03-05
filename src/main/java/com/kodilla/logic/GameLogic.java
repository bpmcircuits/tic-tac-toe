package com.kodilla.logic;

import com.kodilla.figures.Cross;
import com.kodilla.figures.Nought;
import com.kodilla.presentation.MenuEnum;
import com.kodilla.presentation.UserInterface;

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
                case PLAYER_VS_PLAYER -> renderPlayerVsPlayer();
                case PLAYER_VS_COMPUTER -> renderPlayerVsComputer();
                case BACK -> {
                    return;
                }
            }
        }
    }

    private void renderPlayerVsPlayer() {

        int boardSize = UserInterface.getBoardSize();
        String playerOneName = UserInterface.getPlayerName(Settings.PLAYER.FIRST);
        String figure = UserInterface.getFigure();
        Player playerOne = new Player(playerOneName, figure.equals("X") ? new Cross() : new Nought());
        String playerTwoName = UserInterface.getPlayerName(Settings.PLAYER.SECOND);
        Player playerTwo = new Player(playerTwoName, figure.equals("X") ? new Nought() : new Cross());

        UserInterface.displayPlayers(playerOne.username(), playerTwo.username());

        Board board = new Board(boardSize, playerOne.playerFigure());

        board.initBoard();

        System.out.println(board);

        boolean finishedGame = false;
        while (!finishedGame) {

            UserInterface.showWhichPlayerTurn(board.getCurrentPlayer().getClass() == playerOne.playerFigure().getClass()
                    ? playerOneName : playerTwoName);

            String position = UserInterface.getFigurePosition(board.getSize());

            if (!board.isPointOccupied(position)) {

                board.setFigureToPosition(
                        board.getCurrentPlayer().getClass() == playerOne.playerFigure().getClass()
                            ? playerOne.playerFigure()
                            : playerTwo.playerFigure(), position);

                if (board.checkWinner() != null) {
                    finishedGame = true;
                    UserInterface.showWinner(board.getWinner() == playerOne.playerFigure() ? playerOneName : playerTwoName);
                } else if (board.isBoardFull()) {
                    finishedGame = true;
                    UserInterface.showDraw();
                } else {
                    board.switchToNextTurn();
                }
            } else {
                UserInterface.wrongPlace();
            }

            System.out.println(board);
        }
    }

    public void renderPlayerVsComputer() {

        int boardSize = UserInterface.getBoardSize();
        String playerOneName = UserInterface.getPlayerName(Settings.PLAYER.FIRST);
        String figure = UserInterface.getFigure();
        Player playerOne = new Player(playerOneName, figure.equals("X") ? new Cross() : new Nought());

        Computer computer = new Computer(figure.equals("X") ? new Nought() : new Cross(), boardSize);

        UserInterface.displayPlayers(playerOne.username(), computer.getComputerName());

        Board board = new Board(boardSize, playerOne.playerFigure());
        board.initBoard();

        System.out.println(board);

        boolean finishedGame = false;
        while (!finishedGame) {

            String position;

            if (board.getCurrentPlayer().getClass() == playerOne.playerFigure().getClass()) {
                UserInterface.showWhichPlayerTurn(playerOneName);
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
                UserInterface.showWhichPlayerTurn(computer.getComputerName());
                do {
                    position = computer.generateComputerMove();
                } while (board.isPointOccupied(position));
                board.setFigureToPosition(computer.getComputerFigure(), position);
            }

            if (board.checkWinner() != null) {
                finishedGame = true;
                UserInterface.showWinner(board.getWinner() == playerOne.playerFigure() ? playerOneName : computer.getComputerName());
            } else if (board.isBoardFull()) {
                finishedGame = true;
                UserInterface.showDraw();
            } else {
                board.switchToNextTurn();
            }

            System.out.println(board);
        }


    }
}
