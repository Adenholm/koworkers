package com.example.koworkers.model.pieces;

import android.graphics.Point;

import com.example.koworkers.model.Colour;

import java.util.ArrayList;

public interface Piece {

    //boardPositions is an array of the positions of all the pieces on the board, with the position of the piece that is supposed to move first
    public ArrayList<Point> getPossibleMoves(ArrayList<Point> boardPositions);
    public Colour getColour();
}
