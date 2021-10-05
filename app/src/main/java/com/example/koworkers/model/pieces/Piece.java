package com.example.koworkers.model.pieces;

import com.example.koworkers.model.Point;

import com.example.koworkers.model.Colour;

import java.util.ArrayList;
import java.util.Arrays;

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
    public ArrayList<Point> getSurroundingCoordinates(Point position){
        ArrayList<Point> currentMoves = new ArrayList<>();
        ArrayList<Point> surroundingCoordinates = new ArrayList<>(Arrays.asList(new Point[]{new Point(-1,1),new Point(-1,0),new Point(0,1),new Point(0,-1),new Point(1,0),new Point(1,-1)}));
        for(int i= 0;i<surroundingCoordinates.size();i++){
            currentMoves.add(new Point(position.getX()+surroundingCoordinates.get(i).getX(),position.getY()+surroundingCoordinates.get(i).getY()));
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
