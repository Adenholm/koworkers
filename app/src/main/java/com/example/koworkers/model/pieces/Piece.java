package com.example.koworkers.model.pieces;



import com.example.koworkers.model.Colour;
import com.example.koworkers.model.Point;

import java.util.ArrayList;

public interface Piece {

    ArrayList<Point> getPossibleMoves(ArrayList<Point> boardPositions);
    Colour getColour();
    int getImageResource();
}
