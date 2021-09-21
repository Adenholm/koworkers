package com.example.koworkers.model;

import com.example.koworkers.model.pieces.IPiece;
import com.example.koworkers.model.pieces.PieceFactory;

import java.util.ArrayList;

public class PlayerHand {
    private final ArrayList<IPiece> pieces = new ArrayList<>();
    private IPiece queen;
    private final Colour colour;

    public PlayerHand(Colour colour){
        this.colour = colour;
        PieceFactory pieceFactory = new PieceFactory();
        queen = pieceFactory.createNewQueen(colour);

        //add 3 ants
        for (int i = 0; i < 3; i++) {
            pieces.add(pieceFactory.createNewAnt(colour));
        }

        //add 3 grasshoppers
        for (int i = 0; i < 3; i++) {
            pieces.add(pieceFactory.createNewGrasshopper(colour));
        }

        // add 2 spiders
        pieces.add(pieceFactory.createNewSpider(colour));
        pieces.add(pieceFactory.createNewSpider(colour));

        // add 2 beetles
        pieces.add(pieceFactory.createNewBeetle(colour));
        pieces.add(pieceFactory.createNewBeetle(colour));
    }

    /**
     * removes the provided piece from the playerhand, returns true if succes, false if fail
     * @param piece the piece to be removed
     * @return boolean
     */
    public boolean removePiece(IPiece piece){
        if(piece.equals(queen)){
            queen = null;
            return true;
        }
        return pieces.remove(piece);
    }

    /**
     * checks wether the queen has ben played or not
     * @return true if queen has been played
     */
    public boolean queenHasBeenPlayed(){
        return queen == null;
    }

    /**
     * returns a copy of the Piece list with the queen first in the list if it hasn't already been played
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

}
