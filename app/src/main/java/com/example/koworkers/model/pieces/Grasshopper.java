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

    //inte kunna hoppa över hål
    //om det finns ett kort avstånd hittat, kan inte hoppa längre då
    @Override
    public ArrayList<Point> getPossibleMoves(ArrayList<Point> boardPositions) {
        Point startPosition = boardPositions.get(0);
        ArrayList<Point> possibleMoves = new ArrayList<>();
        for(int i= 0;i<boardPositions.size();i++){
            Point currentPoint = boardPositions.get(i);
            if(startPosition.getX() == currentPoint.getX() && !boardPositions.contains(currentPoint)){
                possibleMoves.add(currentPoint);
            }
            else if(currentPoint.getX()+currentPoint.getY() == boardPositions.get(0).getX()+boardPositions.get(0).getY() && !boardPositions.contains(currentPoint)){
                possibleMoves.add(currentPoint);
            }
            else if(startPosition.getY() == currentPoint.getY() && !boardPositions.contains(currentPoint)){
                possibleMoves.add(currentPoint);
            }
        }
        return possibleMoves;
    }

}
