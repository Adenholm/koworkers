package com.example.koworkers.model;

import com.example.koworkers.model.pieces.IPiece;

import java.util.ArrayList;

/**
 * Represents the game, has a board and two playerhands
 *
 * @author Hanna Adenholm
 */
public class Hive{

    private Board board = new Board();
    private PlayerHand blackHand = new PlayerHand(Colour.BLACK);
    private PlayerHand whiteHand = new PlayerHand(Colour.WHITE);

    private PlayerHand currentPlayer;

    private int round = 1; //number of rounds that has been played

    private IPiece selectedPiece; //the currently selected piece

    private final ArrayList<Isubscriber> subscribers = new ArrayList<>();               // a list of subscibers
    private final ArrayList<IWinSubscriber> winSubscribers = new ArrayList<>();         //list containing subscribers who want to know if a player won

    public Hive(){
        currentPlayer = whiteHand;
    }


    public void restart(){
        blackHand = new PlayerHand(Colour.BLACK);
        whiteHand = new PlayerHand(Colour.WHITE);
        board = new Board();
        currentPlayer = whiteHand;
        round = 1;
        notifyUpdate();
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
                    if(selectedPiece.getColour() == Colour.BLACK)
                        board.setBlackQueen(selectedPiece);
                    else{
                        board.setWhiteQueen(selectedPiece);
                    }
                }
                currentPlayer.removePiece(selectedPiece);
            }
            board.movePiece(selectedPiece, point);

            if(round > 3 && board.aQueenIsSurrounded()){
                notifyPlayerWon(board.getWinner());
                return;
            }

            switchPlayer();
            deSelectPiece();

            if(newPlayerCantMove()){
                switchPlayer();
                notifyUpdate();
            }
        }
    }

    private boolean newPlayerCantMove(){
        return (currentPlayer.getPieces().isEmpty() || board.getPossibleplacements(currentPlayer.getColour()).isEmpty())
                && !board.playerCanMakeMove(currentPlayer.getColour());
    }

    /**
     * Checks if provided piece is of same color as currentPlayer, if so then select the provided piece.
     * Returns true if success
     *
     * @param piece To be selected.
     * @return True if success.
     */
    public boolean selectPiece(IPiece piece){
        if(aPieceIsSelected()){
            deSelectPiece();
        }
        if(piece.getColour().equals(currentPlayer.getColour()) && (!playersQueenShouldBePlaced() || currentPlayer.thisIsMyQueen(piece))){
            selectedPiece = piece;
            notifyUpdate();
            return true;
        }
        return false;
    }

    /**
     * Deselects the currently selected piece.
     */
    public void deSelectPiece(){
        selectedPiece = null;
        notifyUpdate();
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
     * Returns list with the pieces on the board.
     *
     * @return pieces on the board
     */
    public ArrayList<IPiece> getPiecesOnBoard(){
        return board.getPiecesOnBoard();
    }

    public Colour getCurrentPlayerColour(){
        return currentPlayer.getColour();
    }

    public IPiece getSelectedPiece(){
        return selectedPiece;
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

    public void subscribe(Isubscriber subscriber){
        subscribers.add(subscriber);
    }

    public void subscribeWin(IWinSubscriber winSubscriber){
        winSubscribers.add(winSubscriber);
    }

    private void notifyPlayerWon(Colour winner){
        for(IWinSubscriber winSubscriber: winSubscribers){
            winSubscriber.playerWon(winner);
        }
        notifyUpdate();
    }

    private void notifyUpdate(){
        for(Isubscriber subscriber: subscribers){
            subscriber.update();
        }
    }


    /**
     * Changes currentPlayer to the other colour that's not currently playing and notifies subscribers
     */
    private void switchPlayer(){
        if (currentPlayer == whiteHand){
            currentPlayer = blackHand;
        }else{
            currentPlayer = whiteHand;
            round++;
        }
    }
}
