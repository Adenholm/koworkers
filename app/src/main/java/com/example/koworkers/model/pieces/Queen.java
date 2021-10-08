package com.example.koworkers.model.pieces;

import com.example.koworkers.model.Point;

import com.example.koworkers.model.Colour;

import java.util.ArrayList;

public class Queen extends Piece {

    public Queen(Colour colour){
        super(colour);
        setName("queen");
    }

    @Override
    public ArrayList<Point> getPossibleMoves(ArrayList<Point> boardPositions) {
        ArrayList<Point> possibleMoves = new ArrayList<>();
        //Queen can move 1 step, the surrounding pieces of the queen is the moves it can take
        ArrayList<Point> currentList = getSurroundingCoordinates(boardPositions.get(0));
        for(int i=0;i<currentList.size();i++){
            for(int j=0;j<getSurroundingCoordinates(currentList.get(i)).size();j++){
                //makes sure that it doesn't register the beetle's starting position as a surrounding coordinate
                if(!getSurroundingCoordinates(currentList.get(i)).get(j).equals(boardPositions.get(0))){
                    break;
                }
                //checks if the piece is still connected to the hive if it moves to the place, and makes sure the place isn't already occupied
                /*else if(isInList(boardPositions,getSurroundingCoordinates(currentList.get(i)).get(j)) && !isInList(boardPositions,currentList.get(i))){
                    possibleMoves.add(currentList.get(i));
                }

                 */

                //checks if the piece is still connected to the hive if it moves to the place, and makes sure the place isn't already occupied
                else if(boardPositions.contains(getSurroundingCoordinates(currentList.get(i)).get(j)) && !boardPositions.contains(currentList.get(i))){
                    possibleMoves.add(currentList.get(i));
                }
            }
        }
        return possibleMoves;
    }
}
