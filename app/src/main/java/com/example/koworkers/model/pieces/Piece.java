package com.example.koworkers.model.pieces;

import com.example.koworkers.model.Point;

import com.example.koworkers.model.Colour;

import java.util.ArrayList;

abstract class Piece implements IPiece{
    private final Colour colour;
    private int imageResource;

    protected Piece(Colour colour) {
        this.colour = colour;
    }
    protected void setImageResource(int img){
        imageResource = img;
    }


    @Override
    public abstract ArrayList<Point> getPossibleMoves(ArrayList<Point> boardPositions);
    @Override
    public ArrayList<Point> getSurroundingCoordinates(Point personalPosition, Point otherPosition){
        ArrayList<Point> currentMoves = new ArrayList<>();
        for(int i=-1;i<=1;i++) {
            for (int j = -1; j <= 1; j++) {
                if ((i == -1 && j == -1) || (i==1 && j==1)) {
                    break;
                }

                Point currentPoint = new Point(otherPosition.getX() + i, otherPosition.getY() + j);
                if (personalPosition == currentPoint) {
                    break;
                }
                currentMoves.add(currentPoint);

            }
        }
        return currentMoves;

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
