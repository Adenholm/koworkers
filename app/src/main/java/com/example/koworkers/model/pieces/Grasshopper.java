package com.example.koworkers.model.pieces;



import com.example.koworkers.R;
import com.example.koworkers.model.Colour;
import com.example.koworkers.model.Point;

import java.util.ArrayList;

public class Grasshopper implements Piece{
    private final Colour colour;
    private final int imageResource = R.drawable.white_hexagon; //TODO change picture

    public Grasshopper(Colour colour){
        this.colour = colour;
    }

    @Override
    public ArrayList<Point> getPossibleMoves(ArrayList<Point> boardPositions) {
        Point startPosition = boardPositions.get(0);
        ArrayList<Point> possibleMoves = new ArrayList<>();
        for(int i= 0;i<boardPositions.size();i++){
            Point currentPoint = boardPositions.get(i);
            if(startPosition.getX() == currentPoint.getX() && !boardPositions.contains(currentPoint)){
                possibleMoves.add(currentPoint);
            }
            else if(currentPoint.getX()/currentPoint.getY() == -1 && !boardPositions.contains(currentPoint)){
                possibleMoves.add(currentPoint);
            }
            else if(startPosition.getY() == currentPoint.getY() && !boardPositions.contains(currentPoint)){
                possibleMoves.add(currentPoint);
            }
        }
        return possibleMoves;
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
