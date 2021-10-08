package com.example.koworkers.model.pieces;

import com.example.koworkers.model.Point;

import com.example.koworkers.model.Colour;

import java.util.ArrayList;

public class Beetle extends Piece {

    public Beetle(Colour colour){
        super(colour);
        setName("beetle");
    }



    @Override
    public ArrayList<Point> getPossibleMoves(ArrayList<Point> boardPositions) {
        ArrayList<Point> possibleMoves = new ArrayList<>();
        Point beetlePoint = boardPositions.get(0);
        //Beetle can move 1 step, the surrounding pieces of the beetle is the moves it can take
        ArrayList<Point> currentList = getSurroundingCoordinates(beetlePoint);
        for(Point point: currentList){
            for(Point surroundpoint: getSurroundingCoordinates(point)) {
                //checks if the piece is still connected to the hive if it moves to the place
                if (!surroundpoint.equals(beetlePoint) && isInList(surroundpoint, boardPositions) && !isInList(point, possibleMoves)) {
                    possibleMoves.add(point);
                }
            }
            if(isInList(point, boardPositions) && !isInList(point, possibleMoves)){
                possibleMoves.add(point);
            }
        }
        return possibleMoves;

    }
}
