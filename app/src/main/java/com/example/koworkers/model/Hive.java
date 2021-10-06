package com.example.koworkers.model;

import com.example.koworkers.model.pieces.IPiece;

import java.util.ArrayList;

public class Hive implements IPublisher{

    private static Hive instance = null;

    private final Board board = new Board();
    private final PlayerHand blackHand = new PlayerHand(Colour.BLACK);
    private final PlayerHand whiteHand = new PlayerHand(Colour.WHITE);

    private PlayerHand currentPlayer;

    private int round = 1;

    private IPiece selectedPiece;

    private final ArrayList<Isubscriber> subscribers = new ArrayList<>();

    /**
     * Constructor that should never be called, use getInstance() instead.
     */
    protected Hive(){
        currentPlayer = whiteHand;
        //test();
    }

    /**
     * Returns the single instance of the PlayerTurn class.
     */
    public static Hive getInstance(){
        if (instance == null) {
            instance = new Hive();
        }
        return instance;
    }


    /**
     * moves the selected Piece to the provided position on the board and changes to the other players turn.
     * @param point point where piece should be moved
     */
    public void movePiece(Point point){
        if (aPieceIsSelected()){
            if(getCurrentPlayerHandPieces().contains(selectedPiece)){ // checks if the selected piece is in the player hand, if so remove it from there
                if(currentPlayer.thisIsMyQueen(selectedPiece)){     //checks if the selected piece is a queen
                    if(currentPlayer.getColour() == Colour.BLACK)
                        board.setBlackQueen(selectedPiece);
                    else{
                        board.setWhiteQueen(selectedPiece);
                    }
                }
                currentPlayer.removePiece(selectedPiece);
            }
            board.movePiece(selectedPiece, point);

            if(round > 3 && board.aQueenIsSurrounded()){

            }


            switchPlayer();
            for(Isubscriber subscriber: subscribers){
                subscriber.movePiece(selectedPiece, point);
            }
            deSelectPiece();
        }
    }

    /**
     * checks if provided piece is of same color as currentPlayer, if so then select the provided piece
     * @param piece to be selected
     */
    public boolean selectPiece(IPiece piece){
        for(Isubscriber subscriber: subscribers){
            subscriber.deselectPiece();
        }
        if(piece.getColour().equals(currentPlayer.getColour())){
            selectedPiece = piece;
            for(Isubscriber subscriber: subscribers){
                subscriber.selectPiece(selectedPiece);
            }
            return true;
        }
        return false;
    }

    /**
     * deselects the currently selected piece
     */
    public void deSelectPiece(){
        for(Isubscriber subscriber: subscribers) {
            subscriber.deselectPiece();
        }
        selectedPiece = null;
    }

    /**
     * returns true if a piece is selected
     * @return true if a piece is selected
     */
    public boolean aPieceIsSelected(){
        return selectedPiece != null;
    }



    /**
     * checks wheter the current Players queen should be placed.
     * @return true if the player has played 3 turns and still haven't placed their queen
     */
    public boolean playersQueenShouldBePlaced(){
        return !currentPlayer.queenHasBeenPlayed() && round == 3;
    }


    /**
     * returns a list with the current players pieces with the queen first in the list if it hasn't already been played
     * @return current players pieces
     */
    public ArrayList<IPiece> getCurrentPlayerHandPieces(){
        return currentPlayer.getPieces();
    }

    /**
     * returns the position the provided piece has on the bord
     * @param piece piece you want position of
     * @return position of provided piece
     */
    public Point getPoint(IPiece piece){
        return board.getPoint(piece);
    }


    /**
     * returns a list of possible positions the selected piece are able to move to
     * @return list of possible positions
     */
    public ArrayList<Point> getPossibleMoves(){
        if(getCurrentPlayerHandPieces().contains(selectedPiece)){
            return board.getPossibleplacements(currentPlayer.getColour());
        } else {
            return board.getPossibleMoves(selectedPiece);
        }
    }


    @Override
    public void subscribe(Isubscriber subscriber){
        subscribers.add(subscriber);
    }


    @Override
    public void notifySubscribers(){
        for(Isubscriber subscriber: subscribers){
            //subscriber.update();
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
            round++;
        }
        for(Isubscriber subscriber: subscribers){
            subscriber.switchPlayer(currentPlayer.getColour());
        }
    }


    private void test(){
        selectPiece(getCurrentPlayerHandPieces().get(1));
        movePiece(new Point(0,0));
        selectPiece(getCurrentPlayerHandPieces().get(1));
        movePiece(new Point(1,-1));
        selectPiece(getCurrentPlayerHandPieces().get(1));
    }
}
