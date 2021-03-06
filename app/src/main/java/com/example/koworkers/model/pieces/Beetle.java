package com.example.koworkers.model.pieces;

import com.example.koworkers.model.Point;

import com.example.koworkers.model.Colour;

import java.util.ArrayList;

/** The beetle piece
 * The beetle can only move one step at the time, but can also jump oon top of other pieces, which then locks that piec
 * @author Stina Hansson
 * @author Hanna Adenholm
 */
class Beetle extends Piece {

    /**
     * Creates a beetle
     * @param colour The player's colour
     */
    public Beetle(Colour colour){
        super(colour);
        setName("beetle");
    }



    @Override
    public ArrayList<Point> getPossibleMoves(ArrayList<Point> boardPositions) {
        ArrayList<Point> possibleMoves = new ArrayList<>();
        Point beetlePoint = boardPositions.get(0);
        //if beetle is stacked on top of another piece, the hive is always cohesive even if the beetle moves, and the beetle is neve stuck
        if(!isBeetleStacked(boardPositions)){
            if(!isHiveCohesiveAfterMove(boardPositions)){
                return possibleMoves;
            }
        }
        //Beetle can move 1 step, the surrounding pieces of the beetle is the moves it can take
        ArrayList<Point> currentList = getSurroundingCoordinates(beetlePoint);
        for(Point point: currentList){
            for(Point surroundpoint: getSurroundingCoordinates(point)) {
                //if the beetle is stuck, it should not be able to slide out to an empty space, but it should still be able to jump on top of other pieces
                if(pieceIsStuck(boardPositions,beetlePoint)){
                    break;
                }
                //if the beetle is stacked on top of another piece, it can move to spaces that is surrounded by other pieces, and is always connected to the hive
                if(isBeetleStacked(boardPositions) && !surroundpoint.equals(beetlePoint) && !possibleMoves.contains(point)){
                    possibleMoves.add(point);
                }
                //if the beetle is not stacked it cant move to spaces that is surrounded by other pieces
                //checks if the piece is still connected to the hive if it moves to the place
                else if (!surroundpoint.equals(beetlePoint) && boardPositions.contains(surroundpoint) && !possibleMoves.contains(point) && !pieceIsStuck(boardPositions,point)) {
                    possibleMoves.add(point);
                }
            }
            //beetle can jump on top of other pieces
            if(boardPositions.contains(point) && !possibleMoves.contains(point)){
                possibleMoves.add(point);
            }
        }
        return possibleMoves;

    }

    /**
     * Checks if the current beetle is stacked on top of another piece
     * @param boardPositions List with coordinates of all the pieces on the board
     * @return True if the beetle is stacked, and false if it is not
     */
    private boolean isBeetleStacked(ArrayList<Point> boardPositions){
        int count = 0;
        for(Point p:boardPositions){
            if(p.equals(boardPositions.get(0))){
                count++;
            }
        }
        return count > 1;
    }
}
