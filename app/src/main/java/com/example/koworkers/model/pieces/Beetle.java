package com.example.koworkers.model.pieces;

import android.graphics.Point;

import com.example.koworkers.R;
import com.example.koworkers.model.Colour;

import java.util.ArrayList;

public class Beetle extends Piece {

    public Beetle(Colour colour){
        super(colour, R.drawable.white_hexagon); //TODO change picture
    }

    @Override
    public ArrayList<Point> getPossibleMoves(ArrayList<Point> boardPositions) {
    return null;
    }

}
