package com.example.koworkers.model.pieces;

import android.graphics.Point;
import android.net.wifi.aware.AwareResources;

import com.example.koworkers.R;
import com.example.koworkers.model.Colour;

import java.util.ArrayList;

public class Ant extends Piece {

    public Ant(Colour colour){
        super(colour, R.drawable.white_hexagon); //TODO change picture
    }

    @Override
    public ArrayList<Point> getPossibleMoves(ArrayList<Point> boardPositions) {
        ArrayList<Point> possibleMoves = new ArrayList<>();
        for(int i=1;i<boardPositions.size();i++){
            ArrayList<Point> currentList = getSurroundingCoordinates(boardPositions.get(0),boardPositions.get(i));
            for(int j=0;j<currentList.size();j++){
                if(!alreadyInList(possibleMoves,currentList.get(j)) &&!alreadyInList(boardPositions,currentList.get(j))){
                    possibleMoves.add(currentList.get(j));
                }
            }
        }

        return possibleMoves;
    }

    private boolean alreadyInList(ArrayList<Point> list, Point point){
        for(int i=0;i<list.size();i++){
            if(point == list.get(i)){
                return true;
            }
        }
        return false;
    }
    private ArrayList<Point> getSurroundingCoordinates(Point personalPosition, Point otherPosition){
        ArrayList<Point> currentMoves = new ArrayList<>();
        for(int i=-1;i<=1;i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 3 && j != 1) {
                    break;
                }

                Point currentPoint = new Point(otherPosition.x + i, otherPosition.y + j);
                if (personalPosition.x == currentPoint.x && personalPosition.y == currentPoint.y) {
                    break;
                }
                currentMoves.add(currentPoint);

            }
        }
        return currentMoves;

    }


}
