package com.example.koworkers.model.pieces;

import com.example.koworkers.model.Point;
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
                if(!possibleMoves.contains(currentList.get(j)) && !boardPositions.contains(currentList.get(j))){
                    possibleMoves.add(currentList.get(j));
                }
            }
        }

        return possibleMoves;
    }




}
