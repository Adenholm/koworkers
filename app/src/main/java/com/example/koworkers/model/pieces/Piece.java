package com.example.koworkers.model.pieces;

import android.graphics.Point;

import com.example.koworkers.model.Colour;

import java.util.ArrayList;

abstract class Piece implements IPiece{
    private final Colour colour;
    private final int imageResource;

    protected Piece(Colour colour, int imageResource) {
        this.colour = colour;
        this.imageResource = imageResource;
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
