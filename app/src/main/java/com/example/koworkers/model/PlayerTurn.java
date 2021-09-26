package com.example.koworkers.model;

import com.example.koworkers.model.pieces.IPiece;

import java.util.ArrayList;

public class PlayerTurn implements IPublisher{

    private final Board board = new Board();
    private final PlayerHand blackHand = new PlayerHand();
    private final PlayerHand whiteHand = new PlayerHand();

    private PlayerHand currentPlayer;

    private final ArrayList<ISubscriber> subscribers;

    public PlayerTurn(ArrayList<ISubscriber> subscribers){
        this.subscribers = subscribers;
        currentPlayer = whiteHand;
    }


    /**
     * moves the provided Piece to the provided position on the board and changes to the pther players turn
     * @param piece the piece to be moved
     * @param point point where piece should be moved
     */
    public void movePiece(IPiece piece, Point point){
        board.movePiece(piece, point);
        currentPlayer.incNumberOfTurns;
        switchPlayer();
        notifySubscribers();
    }


    /**
     * Places the provided piece on the board at the provided point and removes it from the players hand.
     * also changes the currentplayer
     * @param piece the piece to be placed
     * @param point point where piece should be placed
     */
    public void placePiece(IPiece piece, Point point){
        currentPlayer.removePiece(piece);
        board.placePiece(piece, point);
        currentPlayer.incNumberOfTurns;
        switchPlayer();
        notifySubscribers();
    }


    /**
     * checks wheter the current Players queen should be placed.
     * @return true if the player has played 3 turns and still haven't placed their queen
     */
    public boolean playersQueenShouldBePlaced(){
        if(!currentPlayer.queenHasBeenPlayed() && currentPlayer.getNumberOfTurns() == 3){
            return true;
        }
        return false;
    }


    /**
     * returns a list with the current players pieces
     * @return current players pieces
     */
    public ArrayList<IPiece> getCurrentPlayerHandPieces(){
        return currentPlayer.getPieces;
    }


    /**
     * returns current Players Colour
     * @return current Players colour
     */
    public Colour getCurrentPlayersColour(){
        return currentPlayer.getColour();
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
