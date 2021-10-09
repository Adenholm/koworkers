package com.example.koworkers.model;

import com.example.koworkers.model.pieces.IPiece;

import java.util.ArrayList;

/**
 * Represents the game, has a board and two playerhands
 *
 * @author Hanna Adenholm
 */
public class Hive implements IPublisher{

    private static Hive instance = null; //TODO remove singleton pattern

    private final Board board = new Board();
    private final PlayerHand blackHand = new PlayerHand(Colour.BLACK);
    private final PlayerHand whiteHand = new PlayerHand(Colour.WHITE);

    private PlayerHand currentPlayer;

    private int round = 1; //number of rounds that has been played

    private IPiece selectedPiece; //the currently selected piece

    private final ArrayList<Isubscriber> subscribers = new ArrayList<>(); // a list of subscibers

    public Hive(){
        currentPlayer = whiteHand;
        //test();
    }

    /**
     * Returns the single instance of the PlayerTurn class.
     */
    public static Hive getInstance(){ //TODO remove signleton pattern
        if (instance == null) {
            instance = new Hive();
        }
        return instance;
    }


    /**
     * Moves the selected Piece to the provided position on the board and changes to the other players turn.
     * Also removes the selected piece from the playerhand if the selected piece is in there and checks if a player has won the game.
     *
     * @param point Point where piece should be moved.
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
                for(Isubscriber subscriber: subscribers){
                    subscriber.playerWon(board.getWinner());
                }
            }


            switchPlayer();
            for(Isubscriber subscriber: subscribers){
                subscriber.pieceWasMoved(selectedPiece, point);
            }
            deSelectPiece();
        }
    }

    /**
     * Checks if provided piece is of same color as currentPlayer, if so then select the provided piece.
     * Returns true if success
     *
     * @param piece To be selected.
     * @return True if success.
     */
    public boolean selectPiece(IPiece piece){
        for(Isubscriber subscriber: subscribers){
            subscriber.pieceWasDeselected();
        }
        if(piece.getColour().equals(currentPlayer.getColour()) && (!playersQueenShouldBePlaced() || currentPlayer.thisIsMyQueen(piece))){
            selectedPiece = piece;
            for(Isubscriber subscriber: subscribers){
                subscriber.pieceWasSelected(selectedPiece);
            }
            return true;
        }
        return false;
    }

    /**
     * Deselects the currently selected piece.
     */
    public void deSelectPiece(){
        for(Isubscriber subscriber: subscribers) {
            subscriber.pieceWasDeselected();
        }
        selectedPiece = null;
    }

    /**
     * Returns true if a piece is selected.
     *
     * @return True if a piece is selected.
     */
    public boolean aPieceIsSelected(){
        return selectedPiece != null;
    }



    /**
     * Checks whether the current Players queen should be placed.
     *
     * @return True if the player has played 3 turns and still haven't placed their queen.
     */
    public boolean playersQueenShouldBePlaced(){
        return !currentPlayer.queenHasBeenPlayed() && round >= 3;
    }


    /**
     * Returns a list with the current players pieces with the queen first in the list if it hasn't already been played.
     *
     * @return Current players pieces in hand.
     */
    public ArrayList<IPiece> getCurrentPlayerHandPieces(){
        return currentPlayer.getPieces();
    }

    /**
     * Returns the position the provided piece has on the bord.
     *
     * @param piece Piece you want position of.
     * @return Position of provided piece.
     */
    public Point getPoint(IPiece piece){
        return board.getPoint(piece);
    }


    /**
     * Returns a list of possible positions the selected piece are able to move to.
     *
     * @return List of possible positions
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
     * Changes currentPlayer to the other colour that's not currently playing.
     */
    private void switchPlayer(){
        if (currentPlayer == whiteHand){
            currentPlayer = blackHand;
        }else{
            currentPlayer = whiteHand;
            round++;
        }
        for(Isubscriber subscriber: subscribers){
            subscriber.playerWasChanged(currentPlayer.getColour());
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
