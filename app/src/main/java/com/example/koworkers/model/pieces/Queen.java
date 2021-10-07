package com.example.koworkers.model.pieces;

import com.example.koworkers.model.Point;

import com.example.koworkers.R;
import com.example.koworkers.model.Colour;

import java.util.ArrayList;

/** The Queen piece
 * The queen must be one of the three first to be placed
 *
 * @author Stina Hansson
 */
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
        Point queenPoint = boardPositions.get(0);
        //Queen can move 1 step, the surrounding pieces of the queen is the moves it can take
        ArrayList<Point> currentList = getSurroundingCoordinates(boardPositions.get(0));
        for(Point point: currentList){
            for(Point surroundpoint: getSurroundingCoordinates(point)) {
                //checks if the piece is still connected to the hive if it moves to the place
                if(!surroundpoint.equals(boardPositions.get(0))){
                    break;
                }
                else if(isInList(surroundpoint,boardPositions) && !isInList(point,boardPositions)){
                    possibleMoves.add(point);
                }
            }

        }
        return possibleMoves;
    }
}
