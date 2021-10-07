package com.example.koworkers.model.pieces;

import com.example.koworkers.model.Point;

import com.example.koworkers.R;
import com.example.koworkers.model.Colour;

import java.util.ArrayList;

public class Grasshopper extends Piece {

    public Grasshopper(Colour colour){
        super(colour);
        if(colour == Colour.BLACK){
            this.setImageResource(R.drawable.black_grass_piece);
        }
        else{
            this.setImageResource(R.drawable.grass_piece);
        }
    }

    //TODO check so that the grasshopper can't jump over gaps
    @Override
    public ArrayList<Point> getPossibleMoves(ArrayList<Point> boardPositions) {
        Point grassPosition = boardPositions.get(0);
        ArrayList<Point> possibleMoves = new ArrayList<>();
        for(int i= 1;i<boardPositions.size();i++){
            Point currentPoint = boardPositions.get(i);
            //checks if the current point is on a straight line (diagonal or vertical) from the start position
            if(grassPosition.getX() == currentPoint.getX() && !boardPositions.contains(currentPoint)){
                possibleMoves.add(currentPoint);
            }
            else if(currentPoint.getX()+currentPoint.getY() == boardPositions.get(0).getX()+boardPositions.get(0).getY() && !boardPositions.contains(currentPoint)){
                possibleMoves.add(currentPoint);
            }
            else if(grassPosition.getY() == currentPoint.getY() && !boardPositions.contains(currentPoint)){
                possibleMoves.add(currentPoint);
            }
        }
        return possibleMoves;
    }

}
