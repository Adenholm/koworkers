package com.example.koworkers.model.pieces;

import android.graphics.Point;

import com.example.koworkers.R;
import com.example.koworkers.model.Colour;

import java.util.ArrayList;

public class Grasshopper implements Piece{
    private final Colour colour;
    private final int imageResource = R.drawable.white_hexagon; //TODO change picture

    public Grasshopper(Colour colour){
        this.colour = colour;
    }

    @Override
    public ArrayList<Point> getPossibleMoves(ArrayList<Point> boardPositions) {
        return null;
    }

    @Override
    public Colour getColour() {
        return colour;
    }

    @Override
    public int getImageResource() {
        return imageResource;
    }
}
