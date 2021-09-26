package com.example.koworkers.model;

import java.util.ArrayList;

public class PlayerTurn implements IPublisher{

    private final Board board = new Board();
    private final PlayerHand blackHand = new PlayerHand();
    private final PlayerHand whiteHand = new PlayerHand();

    private PlayerHand currentPlayer;

    private int whiteNumberOfTurns;
    private int blackNumberOfTurns;

    private final ArrayList<ISubscriber> subscribers;

    public PlayerTurn(ArrayList<ISubscriber> subscribers){
        this.subscribers = subscribers;
        currentPlayer = whiteHand;
    }

    @Override
    public void subscribe(ISubscriber subscriber){
        subscribers.add(subscriber);
    }

    @Override
    private void notifySubscribers(){
        for(ISubscriber subscriber: subscribers){
            subscriber.update;
        }
    }

    /**
     * Changes currentPlayer to the other colour that's not currently playing
     */
    private void switchPlayer(){
        if (currentPlayer == whiteHand){
            currentPlayer = blackHand;
        }else{
            currentPlayer = whiteHand;
        }
    }
}
