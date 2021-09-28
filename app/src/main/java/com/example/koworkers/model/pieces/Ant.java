package com.example.koworkers.model.pieces;


import android.net.wifi.aware.AwareResources;

import com.example.koworkers.R;
import com.example.koworkers.model.Colour;
import com.example.koworkers.model.Point;

import java.util.ArrayList;

public class Ant implements Piece{
    private final Colour colour;
    private final int imageResource = R.drawable.white_hexagon; //TODO change picture

    public Ant(Colour colour){
        this.colour = colour;
    }

    @Override
    public ArrayList<Point> getPossibleMoves(ArrayList<Point> boardPositions) {
        ArrayList<Point> possibleMoves = new ArrayList<>();
        for(int i=1;i<boardPositions.size();i++){
            ArrayList<Point> currentList = getSurroundingCoordinates(boardPositions.get(0),boardPositions.get(i));
            for(int j=0;j<currentList.size();j++){
                if(!possibleMoves.contains(currentList.get(j)) && !boardPositions.contains(currentList.get(j))){
                    possibleMoves.add(currentList.get(j));
                }
            }
        }

        return possibleMoves;
    }

    private ArrayList<Point> getSurroundingCoordinates(Point personalPosition, Point otherPosition){
        ArrayList<Point> currentMoves = new ArrayList<>();
        for(int i=-1;i<=1;i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 1 && j != 0) {
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
