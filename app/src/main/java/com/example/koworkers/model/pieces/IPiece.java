package com.example.koworkers.model.pieces;

import com.example.koworkers.model.Point;

import com.example.koworkers.model.Colour;

import java.util.ArrayList;

public interface IPiece {

    ArrayList<Point> getPossibleMoves(ArrayList<Point> boardPositions);
    ArrayList<Point> getSurroundingCoordinates(Point personalPosition, Point otherPosition);
    Colour getColour();
    int getImageResource();
}
