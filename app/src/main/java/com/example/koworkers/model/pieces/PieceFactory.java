package com.example.koworkers.model.pieces;

import com.example.koworkers.model.Colour;

public class PieceFactory {
    public IPiece createNewQueen(Colour colour){
        return new Queen(colour);
    }

    public IPiece createNewAnt(Colour colour){
        return new Ant(colour);
    }

    public IPiece createNewBeetle(Colour colour){
        return new Beetle(colour);
    }

    public IPiece createNewGrasshopper(Colour colour){
        return new Grasshopper(colour);
    }

    public IPiece createNewSpider(Colour colour){
        return new Spider(colour);
    }
}
