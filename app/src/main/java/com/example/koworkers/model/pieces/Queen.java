package com.example.koworkers.model.pieces;

import android.graphics.Point;

import com.example.koworkers.R;
import com.example.koworkers.model.Colour;

import java.util.ArrayList;

public class Queen implements Piece{
    private final Colour colour;
    private final int imageResource = R.drawable.white_hexagon; //TODO change picture

    public Queen(Colour colour){
        this.colour = colour;
    }

    @Override
    public ArrayList<Point> getPossibleMoves(ArrayList<Point> boardPositions) {
        ArrayList<Point> possibleMoves = new ArrayList<>();
        //Queen can move 1 step, the surrounding pieces of the queen is the moves it can take
        ArrayList<Point> currentList = getSurroundingCoordinates(boardPositions.get(0), boardPositions.get(0));
        for(int i=0;i<currentList.size();i++){
            //checks if the piece is still connected to the hive if it moves to the place, and makes sure the place isn't already occupied
            for(int j=0;j<getSurroundingCoordinates(currentList.get(i), boardPositions.get(0)).size();j++){
                if(boardPositions.contains(getSurroundingCoordinates(currentList.get(i),boardPositions.get(0)).get(j)) && !boardPositions.contains(currentList.get(i))){
                    possibleMoves.add(currentList.get(i));
                }
            }
        }
        return possibleMoves;
    }

    private ArrayList<Point> getSurroundingCoordinates(Point personalPosition, Point queenPosition){
        ArrayList<Point> currentMoves = new ArrayList<>();
        for(int i=-1;i<=1;i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 1 && j != 0) {
                    break;
                }
                Point currentPoint = new Point(personalPosition.x + i, personalPosition.y + j);
                if (personalPosition == currentPoint || queenPosition == currentPoint)  {
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
