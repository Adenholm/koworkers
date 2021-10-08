package com.example.koworkers.model.pieces;

import com.example.koworkers.model.Point;

import com.example.koworkers.model.Colour;

import java.util.ArrayList;

public class Ant extends Piece {

    public Ant(Colour colour){
        super(colour);
        setName("ant");
    }


    @Override
    public ArrayList<Point> getPossibleMoves(ArrayList<Point> boardPositions) {
        ArrayList<Point> possibleMoves = new ArrayList<>();
        for(int i=1;i<boardPositions.size();i++){
            ArrayList<Point> currentList = getSurroundingCoordinates(boardPositions.get(i));
            for(int j=0;j<currentList.size();j++){
                //if(!possibleMoves.contains(currentList.get(j)) && !boardPositions.contains(currentList.get(j)) && !currentList.get(j).equals(boardPositions.get(0))){
                if(isInList(currentList.get(j), possibleMoves) && !isInList(currentList.get(j), boardPositions) && !currentList.get(j).equals(boardPositions.get(0))){
                    possibleMoves.add(currentList.get(j));
                }
            }
        }

        return possibleMoves;
    }




}
