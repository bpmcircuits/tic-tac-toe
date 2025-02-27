package com.kodilla.presentation;

import com.kodilla.logic.Player;
import com.kodilla.logic.MoveChecker;
import com.kodilla.logic.Settings;

import java.util.Scanner;

public class UserInterface {

    private static Scanner scanner = new Scanner(System.in);

    public static int mainMenuChoice() {
        int menuChoice = -1;
        System.out.println(UIStrings.MAIN_MENU);
        System.out.print(UIStrings.OPTION);
        String input = scanner.nextLine().trim();
        try {
            menuChoice = Integer.parseInt(input);
            if (menuChoice < 1 || menuChoice > 5) {
                System.out.println(UIStrings.CHOOSE_RIGHT_OPTION_ONE_FIVE);
            }
        } catch (NumberFormatException e) {
            System.out.println(UIStrings.CHOOSE_RIGHT_OPTION_ONE_FIVE);
        }
        return menuChoice;
    }

    public static boolean onExit() {
        boolean validOption = false;
        while (!validOption) {
            System.out.print(UIStrings.ON_QUIT);
            String userExitQ = scanner.nextLine().trim();
            if (!userExitQ.equals("yes") && !userExitQ.equals("no")) {
                System.out.println(UIStrings.WRONG_OPTION);
                continue;
            }
            if (userExitQ.equals("no")) return false;
            validOption = true;
        }
        return true;
    }


    public static void showStatistics() {
        System.out.println("Statistics: to be added...");
        System.out.println(UIStrings.PRESS_ENTER);
        scanner.nextLine();
    }

    public static void showAbout() {
        System.out.println("About: tic tac toe");
        System.out.println(UIStrings.PRESS_ENTER);
        scanner.nextLine();
    }

    public static int newGameMenu() {

        int menuChoice = -1;
        System.out.println(UIStrings.NEW_GAME_MENU);
        System.out.print(UIStrings.OPTION);
        String input = scanner.nextLine().trim();
        try {
            menuChoice = Integer.parseInt(input);
            if (menuChoice < 1 || menuChoice > 3) {
                System.out.println(UIStrings.CHOOSE_RIGHT_OPTION_ONE_THREE);
            }
        } catch (NumberFormatException e) {
            System.out.println(UIStrings.CHOOSE_RIGHT_OPTION_ONE_THREE);
        }
        return menuChoice;
    }

    public static String getPlayerName(Settings.PLAYER player) {
        System.out.println(player == Settings.PLAYER.FIRST ? UIStrings.PLAYER_ONE_NAME : UIStrings.PLAYER_TWO_NAME);
        return scanner.nextLine().trim();
    }

    public static String getFigure() {
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
        return figure;
    }

    public static void displayPlayers(Player one, Player two) {
        System.out.printf(UIStrings.PLAYER_VS_PLAYER, one.username(), two.username());
    }

    public static int getBoardSize() {
        int boardSize = 3;
        boolean validBoardSize = false;
        while (!validBoardSize) {
            System.out.println(UIStrings.BOARD_SIZE);
            System.out.print(UIStrings.OPTION);
            String input = scanner.nextLine().trim();
            try {
                boardSize = Integer.parseInt(input);
                if (boardSize < Settings.getMinBoardSize() || boardSize > Settings.getMaxBoardSize()) {
                    continue;
                }
                validBoardSize = true;
            } catch (NumberFormatException e) {
                System.out.println(UIStrings.WRONG_OPTION);
            }
        }

        return boardSize;
    }

    public static void showWhichPlayerTurn(String playerName) {
        System.out.printf(UIStrings.PLAYER_TURN, playerName);
    }

    public static String getFigurePosition(int boardSize) {
        String position = "";
        boolean validPlace = false;
        while (!validPlace) {
            System.out.print(UIStrings.PLACE_YOUR_MARK);
            position = scanner.nextLine().trim();
            if (MoveChecker.checkStringPositionName(position, boardSize)) validPlace = true;
            else System.out.println(UIStrings.WRONG_PLACE);
        }
        return position;
    }

    public static void showWinner(String playerName) {
        System.out.printf(UIStrings.WINNER, playerName);
    }

    public static void showDraw() {
        System.out.println(UIStrings.DRAW);
    }

    public static void wrongPlace() {
        System.out.println(UIStrings.WRONG_PLACE);
    }

}
