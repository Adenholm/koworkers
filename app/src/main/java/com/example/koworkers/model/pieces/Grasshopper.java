package com.example.koworkers.model.pieces;

import com.example.koworkers.model.Point;

import com.example.koworkers.model.Colour;

import java.util.ArrayList;

/** The grasshopper piece
 * The grasshopper can jump over the hive on a straight line, diagonal or vertical
 * @author Stina Hansson
 * @author Hanna Adenholm
 */
class Grasshopper extends Piece {

    /**
     * Creates a grasshopper
     * @param colour The player's colour
     */
    public Grasshopper(Colour colour){
        super(colour);
        setName("grass");
    }

    @Override
    public ArrayList<Point> getPossibleMoves(ArrayList<Point> boardPositions) {
        Point grassPosition = boardPositions.get(0);
        ArrayList<Point> possibleMoves = new ArrayList<>();
        if(!isHiveCohesiveAfterMove(boardPositions)){
            return possibleMoves;
        }

        for(Point currentPoint: getSurroundingCoordinates(grassPosition)){
            if(boardPositions.contains(currentPoint)){
                Point comparePoint = new Point(currentPoint.getX()-grassPosition.getX(),currentPoint.getY()- grassPosition.getY());
                possibleMoves.add(getPossiblePosition(boardPositions,currentPoint,comparePoint));

            }
        }
        return possibleMoves;

    }

    private Point getPossiblePosition(ArrayList<Point> boardPositions, Point currentPoint, Point comparePoint){
        Point nextPoint = new Point(currentPoint.getX()+comparePoint.getX(),currentPoint.getY()+comparePoint.getY());
        if(boardPositions.contains(nextPoint) ){
            nextPoint = getPossiblePosition(boardPositions,nextPoint,comparePoint);
        }
        return nextPoint;
    }






}
