package com.example.koworkers.model.pieces;

import android.graphics.Point;

import com.example.koworkers.R;
import com.example.koworkers.model.Colour;

import java.util.ArrayList;

public class Spider implements Piece{
    private final Colour colour;
    private final int imageResource = R.drawable.white_hexagon; //TODO change picture

    public Spider(Colour colour){
        this.colour = colour;
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
            thirdPositions.addAll(calculateMoves(boardPositions,secondPositions.get(i));
        }
        return thirdPositions;
    }

    /*
    startPosition is what is used to calculate the next move, previousPosition is to make sure that it isn't possible to go back the same way.
    So, when calculating the second moves, startPosition is the first position, and previousPosition is the original spider position
     */
    private ArrayList<Point> calculateMoves(ArrayList<Point> boardPositions, Point startPosition){
        ArrayList<Point> possibleMoves = new ArrayList<>();
        ArrayList<Point> currentList = getSurroundingCoordinates(startPosition,boardPositions.get(0));
        for(int i=0;i<currentList.size();i++){
            //checks if the piece is still connected to the hive if it moves to the place, and makes sure the place isn't already occupied
            //kollar också så att den inte tror att spindelns originalposition fortfarande är en del av hive, alltså att den tror att den är connected till hive fast den inte är det
            //TODO finns kanske bättre sätt att skriva på
            for(int j=0;j<getSurroundingCoordinates(currentList.get(i),startPosition).size();j++){
                Point currentSurroundingCoordinate = getSurroundingCoordinates(currentList.get(i),startPosition).get(j);
                if(boardPositions.contains(currentSurroundingCoordinate) && (currentSurroundingCoordinate != boardPositions.get(0)) && !boardPositions.contains(currentList.get(i))){
                    possibleMoves.add(currentList.get(i));
                }
            }
        }
        return possibleMoves;
    }

    private ArrayList<Point> getSurroundingCoordinates(Point personalPosition, Point spiderPosition){
        ArrayList<Point> currentMoves = new ArrayList<>();
        for(int i=-1;i<=1;i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 1 && j != 0) {
                    break;
                }
                Point currentPoint = new Point(personalPosition.x + i, personalPosition.y + j);
                if (personalPosition == currentPoint || spiderPosition == currentPoint)  {
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
