package com.example.koworkers.model;

import com.example.koworkers.model.pieces.IPiece;

import java.util.ArrayList;

public class PlayerTurn implements IPublisher{

    private static PlayerTurn instance = null;

    private final Board board = new Board();
    private final PlayerHand blackHand = new PlayerHand(Colour.BLACK);
    private final PlayerHand whiteHand = new PlayerHand(Colour.WHITE);

    private PlayerHand currentPlayer;

    private IPiece selectedPiece;

    //private final ArrayList<ISubscriber> subscribers = new ArrayList<>();

    /**
     * Constructor that should never be called, use getInstance() instead.
     */
    protected PlayerTurn(){
        currentPlayer = whiteHand;
    }

    /**
     * Returns the single instance of the PlayerTurn class.
     */
    public static PlayerTurn getInstance(){
        if (instance == null) {
            instance = new PlayerTurn();
        }
        return instance;
    }


    /**
     * moves the selected Piece to the provided position on the board and changes to the other players turn.
     * @param point point where piece should be moved
     */
    public void movePiece(Point point){
        if(selectedPiece!= null && getCurrentPlayerHandPieces().contains(selectedPiece)){
            currentPlayer.removePiece(selectedPiece);
            board.placePiece(selectedPiece, point);
        }else if(selectedPiece!= null){
            board.movePiece(selectedPiece, point);
        }
        currentPlayer.incNumberOfTurns();
        switchPlayer();
        notifySubscribers();
        selectedPiece = null;
    }

    public void selectPiece(IPiece piece){
        selectedPiece = piece;
    }

    public boolean aPieceIsSelected(){
        return selectedPiece != null;
    }


    /*
     * Places the provided piece on the board at the provided point and removes it from the players hand.
     * also changes the currentplayer
     * @param piece the piece to be placed
     * @param point point where piece should be placed
     */
    /*
    public void placePiece(IPiece piece, Point point){
        currentPlayer.removePiece(piece);
        board.placePiece(piece, point);
        currentPlayer.incNumberOfTurns();
        switchPlayer();
        notifySubscribers();
    }
    */


    /**
     * checks wheter the current Players queen should be placed.
     * @return true if the player has played 3 turns and still haven't placed their queen
     */
    public boolean playersQueenShouldBePlaced(){
        return !currentPlayer.queenHasBeenPlayed() && currentPlayer.getNumberOfTurns() == 3;
    }


    /**
     * returns a list with the current players pieces
     * @return current players pieces
     */
    public ArrayList<IPiece> getCurrentPlayerHandPieces(){
        return currentPlayer.getPieces();
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
            subscriber.update();
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
