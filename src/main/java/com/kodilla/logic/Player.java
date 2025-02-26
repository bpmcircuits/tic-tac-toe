package com.kodilla.logic;

import com.kodilla.figures.Figure;

public class Player {

    private final String username;
    private final Figure playerFigure;
    private boolean isPlayerTurn = false;
    private boolean playerWin = false;

    public Player(String username, Figure playerFigure) {
        this.username = username;
        this.playerFigure = playerFigure;
    }

    public String getUsername() {
        return username;
    }

    public Figure getPlayerFigure() {
        return playerFigure;
    }

    public boolean isPlayerTurn() {
        return isPlayerTurn;
    }

    public void setPlayerTurn(boolean playerTurn) {
        isPlayerTurn = playerTurn;
    }

    public boolean isPlayerWin() {
        return playerWin;
    }

    public void setPlayerWin(boolean playerWin) {
        this.playerWin = playerWin;
    }
}
