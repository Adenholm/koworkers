package com.example.koworkers.model.pieces;

import android.graphics.Point;

import com.example.koworkers.model.Colour;

import java.util.ArrayList;

public class Beetle implements Piece{
    private final Colour colour;

    public Beetle(Colour colour){
        this.colour = colour;
    }

    @Override
    public ArrayList<Point> getPossibleMoves() {

    }

    @Override
    public Colour getColour() {
        return colour;
    }
}
