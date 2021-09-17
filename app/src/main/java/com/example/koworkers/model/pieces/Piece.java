package com.example.koworkers.model.pieces;

import android.graphics.Point;

import com.example.koworkers.model.Colour;

import java.util.ArrayList;

public interface Piece {

    ArrayList<Point> getPossibleMoves();
    Colour getColour();
    int getImageResource();
}
