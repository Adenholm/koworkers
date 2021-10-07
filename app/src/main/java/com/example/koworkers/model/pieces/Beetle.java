package com.example.koworkers.model.pieces;

import com.example.koworkers.model.Point;

import com.example.koworkers.R;
import com.example.koworkers.model.Colour;

import java.util.ArrayList;

/** The beetle piece
 * The beetle can only move one step at the time, but can also jump oon top of other pieces, which then locks that piec
 * @author Stina Hansson
 * @author Hanna Adenholm
 */
public class Beetle extends Piece {

    /**
     * Creates a beetle
     * @param colour The player's colour
     */
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
        Point beetlePoint = boardPositions.get(0);
        //Beetle can move 1 step, the surrounding pieces of the beetle is the moves it can take
        ArrayList<Point> currentList = getSurroundingCoordinates(beetlePoint);
        for(Point point: currentList){
            for(Point surroundpoint: getSurroundingCoordinates(point)) {
                //checks if the piece is still connected to the hive if it moves to the place
                if (!surroundpoint.equals(beetlePoint) && isInList(surroundpoint, boardPositions) && !isInList(point, possibleMoves)) {
                    possibleMoves.add(point);
                }
            }
            if(isInList(point, boardPositions) && !isInList(point, possibleMoves)){
                possibleMoves.add(point);
            }
        }
        return possibleMoves;

    }
}
