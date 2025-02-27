package com.kodilla.logic;

import java.io.IOException;
import java.util.Properties;

public class Settings {

    private static final Properties appProps = new Properties();

    private static int minBoardSize;
    private static int maxBoardSize;
    private static int maxWinLength;

    static {
        try {
            appProps.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("app.properties"));
            minBoardSize = Integer.parseInt(appProps.getProperty("MIN_BOARD_SIZE"));
            maxBoardSize = Integer.parseInt(appProps.getProperty("MAX_BOARD_SIZE"));
            maxWinLength = Integer.parseInt(appProps.getProperty("MAX_WIN_LENGTH"));
        } catch (IOException e) {
            System.out.println("Error loading app properties!");
        }
    }

    public static int getMinBoardSize() {
        return minBoardSize;
    }

    public static int getMaxBoardSize() {
        return maxBoardSize;
    }

    public static int getMaxWinLength() {
        return maxWinLength;
    }

    public enum PLAYER {
        FIRST,
        SECOND
    }
}
