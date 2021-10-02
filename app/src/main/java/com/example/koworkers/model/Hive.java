package com.example.koworkers.model;

import com.example.koworkers.model.pieces.IPiece;

import java.util.ArrayList;

public class Hive implements IPublisher{

    private static Hive instance = null;

    private final Board board = new Board();
    private final PlayerHand blackHand = new PlayerHand(Colour.BLACK);
    private final PlayerHand whiteHand = new PlayerHand(Colour.WHITE);

    private PlayerHand currentPlayer;

    private IPiece selectedPiece;

    private final ArrayList<Isubscriber> subscribers = new ArrayList<>();

    /**
     * Constructor that should never be called, use getInstance() instead.
     */
    protected Hive(){
        currentPlayer = whiteHand;
        test();
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
            if(getCurrentPlayerHandPieces().contains(selectedPiece)){
                currentPlayer.removePiece(selectedPiece);
            }
            board.movePiece(selectedPiece, point);
            currentPlayer.incNumberOfTurns();
            switchPlayer();
            selectedPiece = null;
            notifySubscribers();
        }
    }

    public void selectPiece(IPiece piece){
        if(piece.getColour().equals(currentPlayer.getColour())){
            selectedPiece = piece;
            notifySubscribers();
        }
    }

    public boolean aPieceIsSelected(){
        return selectedPiece != null;
    }



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

    public Point getPoint(IPiece piece){
        return board.getPoint(piece);
    }

    public ArrayList<IPiece> getPiecesOnBoard(){
        return board.getPiecesOnBoard();
    }

    public ArrayList<Point> getPossibleMoves(){
        if(getCurrentPlayerHandPieces().contains(selectedPiece)){
            return board.getPossibleplacements();
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



    private void test(){
        selectPiece(getCurrentPlayerHandPieces().get(1));
        movePiece(new Point(0,0));
        selectPiece(getCurrentPlayerHandPieces().get(1));
        movePiece(new Point(1,1));
        selectPiece(getCurrentPlayerHandPieces().get(1));
    }
}
