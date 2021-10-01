package com.example.koworkers.model.pieces;

import android.graphics.Point;

import com.example.koworkers.model.Colour;

import java.util.ArrayList;

abstract class Piece implements IPiece{
    private final Colour colour;
    private int imageResource;

    protected Piece(Colour colour) {
        this.colour = colour;
    }
    protected void setImageResource(int img){
        imageResource = img;
    }


    @Override
    public abstract ArrayList<Point> getPossibleMoves(ArrayList<Point> boardPositions);

    @Override
    public Colour getColour() {
        return colour;
    }

    @Override
    public int getImageResource() {
        return imageResource;
    }
}
