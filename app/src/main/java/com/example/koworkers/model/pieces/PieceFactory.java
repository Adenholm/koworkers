package com.example.koworkers.model.pieces;

import com.example.koworkers.model.Colour;

public class PieceFactory {
    public static IPiece createNewQueen(Colour colour){
        return new Queen(colour);
    }

    public static IPiece createNewAnt(Colour colour){
        return new Ant(colour);
    }

    public static IPiece createNewBeetle(Colour colour){
        return new Beetle(colour);
    }

    public static IPiece createNewGrasshopper(Colour colour){
        return new Grasshopper(colour);
    }

    public static IPiece createNewSpider(Colour colour){
        return new Spider(colour);
    }
}
