package com.example.koworkers.model.pieces;

import com.example.koworkers.model.Colour;

public class PieceFactory {
    public Piece createNewQueen(Colour colour){
        return new Queen(colour);
    }

    public Piece createNewAnt(Colour colour){
        return new Ant(colour);
    }

    public Piece createNewBeetle(Colour colour){
        return new Beetle(colour);
    }

    public Piece createNewGrasshopper(Colour colour){
        return new Grasshopper(colour);
    }

    public Piece createNewSpider(Colour colour){
        return new Spider(colour);
    }
}
