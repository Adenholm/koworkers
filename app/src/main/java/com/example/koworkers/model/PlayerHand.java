package com.example.koworkers.model;

import com.example.koworkers.model.pieces.IPiece;
import com.example.koworkers.model.pieces.PieceFactory;

import java.util.ArrayList;

/**
 * Represents the players hand of pieces that they are able to place
 * @author Hanna Adenholm
 */
public class PlayerHand {
    private final ArrayList<IPiece> pieces = new ArrayList<>();
    private IPiece queen;
    private final Colour colour;

    /**
     * Constructor for playerhand that adds the standard beginning set of pieces and sets the players colour.
     *
     * @param colour The players colour.
     */
    public PlayerHand(Colour colour){
        this.colour = colour;
        queen = PieceFactory.createNewQueen(colour);

        //add 3 ants
        for (int i = 0; i < 3; i++) {
            pieces.add(PieceFactory.createNewAnt(colour));
        }

        //add 3 grasshoppers
        for (int i = 0; i < 3; i++) {
            pieces.add(PieceFactory.createNewGrasshopper(colour));
        }

        // add 2 spiders
        pieces.add(PieceFactory.createNewSpider(colour));
        pieces.add(PieceFactory.createNewSpider(colour));

        // add 2 beetles
        pieces.add(PieceFactory.createNewBeetle(colour));
        pieces.add(PieceFactory.createNewBeetle(colour));
    }

    /**
     * Removes the provided piece from the playerhand, returns true if succes, false if fail.
     *
     * @param piece The piece to be removed
     * @return boolean Return true if success
     */
    public boolean removePiece(IPiece piece){
        if(piece.equals(queen)){
            queen = null;
            return true;
        }
        return pieces.remove(piece);
    }

    /**
     * Checks whether the queen has been played or not.
     *
     * @return true if queen has been played
     */
    public boolean queenHasBeenPlayed(){
        return queen == null;
    }

    /**
     * Returns a copy of the Piece list with the queen first in the list if it hasn't already been played.
     *
     * @return copy of pieceList in playerhand
     */
    public ArrayList<IPiece> getPieces(){
        ArrayList<IPiece> newPieceList = new ArrayList<>();
        if(!queenHasBeenPlayed()){
            newPieceList.add(queen);
        }
        newPieceList.addAll(pieces);
        return newPieceList;
    }

    /**
     * Returns the players colour
     *
     * @return The players colour
     */
    public Colour getColour() {
        return colour;
    }

    /**
     * Returns true if the provided piece is the players queen
     *
     * @param piece The piece to be checked
     * @return True if piece is the players queen
     */
    public boolean thisIsMyQueen(IPiece piece){
        return piece.equals(queen);
    }
}
