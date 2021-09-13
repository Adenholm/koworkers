package com.example.koworkers.model.pieces;

import android.graphics.Point;

import com.example.koworkers.model.Colour;

import java.util.ArrayList;

public class Queen implements Piece{
    private final Colour colour;

    public Queen(Colour colour){
        this.colour = colour;
    }

    @Override
    public ArrayList<Point> getPossibleMoves() {
        return null;
    }

    @Override
    public Colour getColour() {
        return colour;
    }
}
