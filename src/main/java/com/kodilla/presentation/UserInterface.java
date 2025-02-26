package com.kodilla.presentation;

import com.kodilla.figures.Cross;
import com.kodilla.figures.Nought;
import com.kodilla.logic.Board;
import com.kodilla.logic.Player;
import com.kodilla.logic.PossibleMove;

import java.util.Scanner;

public class UserInterface {

    private Scanner scanner = new Scanner(System.in);

    public void run() {
        int menuChoice = -1;
        while (menuChoice != 4) {

            System.out.println(UIStrings.MAIN_MENU);

            System.out.print(UIStrings.OPTION);
            String input = scanner.nextLine().trim();

            try {
                menuChoice = Integer.parseInt(input);
                if (menuChoice < 1 || menuChoice > 5) {
                    System.out.println(UIStrings.CHOOSE_RIGHT_OPTION_ONE_FIVE);
                    continue;
                }

                switch (menuChoice) {
                    case 1 -> newGameMenu();
                    case 2 -> showStatistics();
                    case 3 -> showAbout();
                    case 4 -> {
                        boolean validOption = false;
                        while (!validOption) {
                            System.out.print(UIStrings.ON_QUIT);
                            String userExitQ = scanner.nextLine().trim();
                            if (!userExitQ.equals("yes") && !userExitQ.equals("no")) {
                                System.out.println(UIStrings.WRONG_OPTION);
                                continue;
                            }
                            if (userExitQ.equals("no")) menuChoice = -1;
                            validOption = true;
                        }
                    }
                }

            } catch (NumberFormatException e) {
                System.out.println(UIStrings.CHOOSE_RIGHT_OPTION_ONE_FIVE);
            }
        }
    }

    private void showStatistics() {
        System.out.println("Statistics: to be added...");
        System.out.println(UIStrings.PRESS_ENTER);
        scanner.nextLine();
    }

    private void showAbout() {
        System.out.println("About: blablabla");
        System.out.println(UIStrings.PRESS_ENTER);
        scanner.nextLine();
    }

    private void newGameMenu() {

        int menuChoice = -1;
        while (menuChoice != 3) {

            System.out.println(UIStrings.NEW_GAME_MENU);

            System.out.print(UIStrings.OPTION);
            String input = scanner.nextLine().trim();

            try {
                menuChoice = Integer.parseInt(input);
                if (menuChoice < 1 || menuChoice > 3) {
                    System.out.println(UIStrings.CHOOSE_RIGHT_OPTION_ONE_THREE);
                    continue;
                }

                switch (menuChoice) {
                    case 1 -> renderPlayerVsPlayer();
                    case 2 -> renderPlayerVsComputer();
                }

            } catch (NumberFormatException e) {
                System.out.println(UIStrings.CHOOSE_RIGHT_OPTION_ONE_THREE);
            }
        }
    }

    private void renderPlayerVsPlayer() {

        System.out.println(UIStrings.PLAYER_ONE_NAME);
        String playerOneName = scanner.nextLine().trim();
        String figure = "";

        boolean validFigure = false;
        while (!validFigure) {

            System.out.println(UIStrings.CHOOSE_FIGURE);
            figure = scanner.nextLine().trim();

            if (figure.equals("X") || figure.equals("O")) {
                validFigure = true;
            } else {
                System.out.println(UIStrings.WRONG_OPTION);
            }
        }

        Player playerOne = new Player(playerOneName, figure.equals("X") ? new Cross() : new Nought());

        System.out.println(UIStrings.PLAYER_TWO_NAME);
        String playerTwoName = scanner.nextLine().trim();

        Player playerTwo = new Player(playerTwoName, figure.equals("X") ? new Nought() : new Cross());

        System.out.printf(UIStrings.PLAYER_VS_PLAYER, playerOneName, playerTwoName);

        Board board = new Board();

        board.init();
        playerOne.setPlayerTurn(true);
        boolean finishedGame = false;
        while (!finishedGame) {
            System.out.printf(UIStrings.PLAYER_TURN, playerOne.isPlayerTurn() ? playerOneName : playerTwoName);
            String place = "";
            boolean validPlace = false;
            while (!validPlace) {
                System.out.print(UIStrings.PLACE_YOUR_MARK);
                place = scanner.nextLine().trim();
                if (place.equals("quit")) {
                    finishedGame = true;
                    break;
                }
                else if (PossibleMove.checkStringPositionName(place)) validPlace = true;
                else System.out.println(UIStrings.WRONG_PLACE);
            }

            if (finishedGame) break;

            if (PossibleMove.checkPossibleMove(board, place)) {
                System.out.println("Empty space, possible move!");
                board.setFigure(PossibleMove.findPoint(place), playerOne.isPlayerTurn() ? playerOne.getPlayerFigure() : playerTwo.getPlayerFigure());
            }
            else System.out.println("Occupied place!");

            System.out.println(board);

            playerOne.setPlayerTurn(playerTwo.isPlayerTurn());
            playerTwo.setPlayerTurn(!playerOne.isPlayerTurn());
        }
    }

    private void renderPlayerVsComputer() {
    }
}
