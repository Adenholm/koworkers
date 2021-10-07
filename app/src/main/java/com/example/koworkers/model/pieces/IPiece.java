package com.example.koworkers.model.pieces;

import com.example.koworkers.model.Point;

import com.example.koworkers.model.Colour;

import java.util.ArrayList;

/** The interface to all different pieces
 * @author All
 */
public interface IPiece {

    ArrayList<Point> getPossibleMoves(ArrayList<Point> boardPositions);
    ArrayList<Point> getSurroundingCoordinates(Point position);
    Colour getColour();
    int getImageResource();
}
