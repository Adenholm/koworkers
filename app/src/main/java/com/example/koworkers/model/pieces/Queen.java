package com.example.koworkers.model.pieces;

import com.example.koworkers.model.Point;

import com.example.koworkers.R;
import com.example.koworkers.model.Colour;

import java.util.ArrayList;

public class Queen extends Piece {

    public Queen(Colour colour){
        super(colour);
        if(colour == Colour.BLACK){
            this.setImageResource(R.drawable.black_queen_piece);
        }
        else{
            this.setImageResource(R.drawable.queen_piece);
        }
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
}
