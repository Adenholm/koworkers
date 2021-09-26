package com.example.koworkers.model;

public class PlayerTurn {
    private final Board board = new Board();
    private final PlayerHand blackHand = new PlayerHand();
    private final PlayerHand whiteHand = new PlayerHand();
    private PlayerHand currentPlayer;

    private int whiteNumberOfTurns;
    private int blackNumberOfTurns;

    public PlayerTurn(){
        currentPlayer = whiteHand;
    }

    private void switchPlayer(){
        if (currentPlayer == whiteHand){
            currentPlayer = blackHand;
        }else{
            currentPlayer = whiteHand;
        }
    }
}
