package com.example.koworkers.model.pieces;

import com.example.koworkers.model.Point;

import com.example.koworkers.model.Colour;

import java.util.ArrayList;

/** The queen piece
 * The queen must be one of the three first to be placed
 * The queen can only move one step at a time
 * @author Stina Hansson
 */
public class Queen extends Piece {

    /**
     * Creates a queen
     * @param colour The player's colour
     */
    public Queen(Colour colour){
        super(colour);
        setName("queen");
    }

    @Override
    public ArrayList<Point> getPossibleMoves(ArrayList<Point> boardPositions) {
        ArrayList<Point> possibleMoves = new ArrayList<>();
        Point queenPoint = boardPositions.get(0);
        if(pieceIsStuck(boardPositions,queenPoint) || !isHiveCohesiveAfterMove(boardPositions)){
            return possibleMoves;
        }
        //Queen can move 1 step, the surrounding pieces of the queen is the moves it can take
        ArrayList<Point> currentList = getSurroundingCoordinates(queenPoint);
        for(Point point: currentList){
            for(Point surroundPoint: getSurroundingCoordinates(point)) {
                //checks if the piece is still connected to the hive if it moves to the place
                if(!surroundPoint.equals(queenPoint)){
                    break;
                }
                else if(isInList(surroundPoint,boardPositions) && !isInList(point,boardPositions) && !pieceIsStuck(boardPositions,point)){
                    possibleMoves.add(point);
                }
            }

        }
        return possibleMoves;
    }
}
