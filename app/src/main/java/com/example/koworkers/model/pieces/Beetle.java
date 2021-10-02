package com.example.koworkers.model.pieces;

import com.example.koworkers.model.Point;

import com.example.koworkers.R;
import com.example.koworkers.model.Colour;

import java.util.ArrayList;

public class Beetle extends Piece {

    public Beetle(Colour colour){
        super(colour);
        if(colour == Colour.BLACK){
            this.setImageResource(R.drawable.black_beetle_piece);
        }
        else{
            this.setImageResource(R.drawable.beetle_piece);
        }
    }

    @Override
    public ArrayList<Point> getPossibleMoves(ArrayList<Point> boardPositions) {
        ArrayList<Point> possibleMoves = new ArrayList<>();
        //Beetle can move 1 step, the surrounding pieces of the beetle is the moves it can take
        ArrayList<Point> currentList = getSurroundingCoordinates(boardPositions.get(0),boardPositions.get(0));
        for(int i=0;i<currentList.size();i++){
            //checks if the piece is still connected to the hive if it moves to the place
            for(int j=0;j<getSurroundingCoordinates(currentList.get(i),boardPositions.get(0)).size();j++){
                if(boardPositions.contains(getSurroundingCoordinates(currentList.get(i),boardPositions.get(0)).get(j))){
                    possibleMoves.add(currentList.get(i));
                }
            }
        }
        return possibleMoves;
    }

}
