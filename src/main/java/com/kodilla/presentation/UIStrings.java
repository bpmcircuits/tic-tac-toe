package com.kodilla.presentation;

import com.kodilla.logic.Settings;

public class UIStrings {

    public static String MAIN_MENU = """
                                    Main Menu: \s
                                    1. New Game \s
                                    2. Quit
                                    """;
    public static String NEW_GAME_MENU = """
                                        1. Player vs Player
                                        2. Player vs Computer
                                        3. Back
                                        """;

    public static String BOARD_SIZE = "Please provide a board size between " + Settings.getMinBoardSize() + " and " + Settings.getMaxBoardSize();

    public static String OPTION = "Option: ";
    public static String ON_QUIT = " Are you sure? \n"
                                    + "(type yes or no): ";
    public static String CHOOSE_RIGHT_OPTION_ONE_TWO = "Please choose the option between 1 and 2.";
    public static String CHOOSE_RIGHT_OPTION_ONE_THREE = "Please choose the option between 1 and 3.";
    public static String WRONG_OPTION = "Wrong option!";
    public static String PRESS_ENTER = "(press enter to go back)";

    public static String PLAYER_ONE_NAME = "Please write first player name";
    public static String PLAYER_TWO_NAME = "Please write second player name";
    public static String PLAYER_NAME = "Please write your name";
    public static String CHOOSE_FIGURE = "Please choose a figure between X and O";
    public static String PLAYER_VS_PLAYER = "%s vs %s \n";

    public static String PLAYER_TURN = "%s's turn \n";
    public static String PLACE_YOUR_MARK = "Place your mark: ";
    public static String WRONG_PLACE = "Wrong place!";
    public static String WINNER = "Player %s won!%n";
    public static String DRAW = "Draw!";
}
