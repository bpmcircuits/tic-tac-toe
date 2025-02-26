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
                case PLAYER_VS_COMPUTER -> UserInterface.showStatistics();
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

        UserInterface.displayPlayers(playerOne, playerTwo);

        Board board = new Board(boardSize, playerOne.playerFigure());

        board.init();

        System.out.println(board);

        boolean finishedGame = false;
        while (!finishedGame) {

            UserInterface.showWhichPlayerTurn(board.getCurrentPlayer().getClass() == playerOne.playerFigure().getClass()
                    ? playerOneName : playerTwoName);

            String position = UserInterface.getFigurePosition(board.getSize());

            if (board.setFigure(MoveChecker.findPoint(position),
                    board.getCurrentPlayer().getClass() == playerOne.playerFigure().getClass()
                            ? playerOne.playerFigure()
                            : playerTwo.playerFigure())) {
                if (board.checkWinner() != null) {
                    finishedGame = true;
                    UserInterface.showWinner(board.getWinner() == playerOne.playerFigure() ? playerOneName : playerTwoName);
                } else if (board.isBoardFull()) {
                    finishedGame = true;
                    UserInterface.showDraw();
                } else {
                    board.nextTurn();
                }
            } else {
                UserInterface.wrongPlace();
            }

            System.out.println(board);
        }
    }
}
