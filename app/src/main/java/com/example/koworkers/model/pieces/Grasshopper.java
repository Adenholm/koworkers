package com.example.koworkers.model.pieces;

import com.example.koworkers.model.Point;

import com.example.koworkers.model.Colour;

import java.util.ArrayList;

/** The grasshopper piece
 * The grasshopper can jump over the hive on a straight line, diagonal or vertical
 * @author Stina Hansson
 * @author Hanna Adenholm
 */
public class Grasshopper extends Piece {

    /**
     * Creates a grasshopper
     * @param colour The player's colour
     */
    public Grasshopper(Colour colour){
        super(colour);
        setName("grass");
    }


    //TODO check so that the grasshopper can't jump over gaps
    @Override
    public ArrayList<Point> getPossibleMoves(ArrayList<Point> boardPositions) {
        Point grassPosition = boardPositions.get(0);
        ArrayList<Point> possibleMoves = new ArrayList<>();
        if(!isHiveCohesiveAfterMove(boardPositions)){
            return possibleMoves;
        }
    /*for(int i= 1;i<boardPositions.size();i++){
        Point currentPoint = boardPositions.get(i);
        for(Point surroundPoint:getSurroundingCoordinates(currentPoint)){
            //checks if the current point is on a straight line (diagonal or vertical) from the start position
            if(grassPosition.getX() == surroundPoint.getX() && !isInList(surroundPoint,boardPositions)){
                possibleMoves.add(surroundPoint);
            }
            else if(surroundPoint.getX()+surroundPoint.getY() == boardPositions.get(0).getX()+boardPositions.get(0).getY() && !isInList(surroundPoint,boardPositions)){
                possibleMoves.add(surroundPoint);
            }
            else if(grassPosition.getY() == surroundPoint.getY() && !isInList(surroundPoint,boardPositions)){
                possibleMoves.add(surroundPoint);
            }
        }

    }

     */

        ArrayList<Point> relevantPieces = new ArrayList<>();
        for(int i=1;i<boardPositions.size();i++){
            if(isOnStraightLine(grassPosition, boardPositions.get(i))){
                relevantPieces.add(boardPositions.get(i));
            }
        }
        for(Point currentPoint:relevantPieces) {
            for (Point surroundPoint : getSurroundingCoordinates(currentPoint)) {
                if(isOnStraightLine(grassPosition, surroundPoint) && !boardPositions.contains(surroundPoint)){
                    possibleMoves.add(surroundPoint);
                }

            }
        }

        return possibleMoves;

    }

    private boolean isOnStraightLine(Point grassPosition, Point otherPosition){
        if(grassPosition.getX() == otherPosition.getX()){
            return true;
        }
        else if(otherPosition.getX()+otherPosition.getY() == grassPosition.getX()+grassPosition.getY()){
            return true;
        }
        else if(grassPosition.getY() == otherPosition.getY()){
            return true;
        }
        return false;
    }

}
