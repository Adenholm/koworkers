package com.example.koworkers.model.pieces;

import com.example.koworkers.model.Point;

import com.example.koworkers.R;
import com.example.koworkers.model.Colour;

import java.util.ArrayList;

public class Spider extends Piece {
    public Spider(Colour colour){
        super(colour);
        if(colour == Colour.BLACK){
            this.setImageResource(R.drawable.black_spider_piece);
        }
        else{
            this.setImageResource(R.drawable.spider_piece);
        }
    }

    @Override
    public ArrayList<Point> getPossibleMoves(ArrayList<Point> boardPositions) {
        ArrayList<Point> firstPositions = calculateMoves(boardPositions,boardPositions.get(0));
        ArrayList<Point> secondPositions =  new ArrayList<>();
        ArrayList<Point> thirdPositions =  new ArrayList<>();
        for(int i=0;i<firstPositions.size();i++){
            secondPositions.addAll(calculateMoves(boardPositions, firstPositions.get(i)));
        }
        for(int i=0;i<secondPositions.size();i++){
            thirdPositions.addAll(calculateMoves(boardPositions,secondPositions.get(i)));
        }
        return thirdPositions;
    }


    private ArrayList<Point> calculateMoves(ArrayList<Point> boardPositions, Point startPosition){
        Point spiderPosition = boardPositions.get(0);
        ArrayList<Point> possibleMoves = new ArrayList<>();
        ArrayList<Point> currentList = getSurroundingCoordinates(startPosition);
        //checks if the piece is still connected to the hive if it moves to the place, and makes sure the place isn't already occupied
        for(Point point:currentList){
            for(Point surroundPoint:getSurroundingCoordinates(point)){
                if(isInList(surroundPoint,boardPositions) && !surroundPoint.equals(spiderPosition) && !isInList(point,boardPositions)){
                    possibleMoves.add(point);
                }
            }
        }
        return possibleMoves;
    }
}
