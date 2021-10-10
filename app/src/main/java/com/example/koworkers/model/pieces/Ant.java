package com.example.koworkers.model.pieces;

import com.example.koworkers.model.Point;

import com.example.koworkers.model.Colour;

import java.util.ArrayList;

/** The ant piece
 * The ant have an unlimited amount of steps, and can move around the hive in any direction
 * @author Stina Hansson
 */
public class Ant extends Piece {

    /**
     * Creates an ant
     * @param colour The player's colour
     */
    public Ant(Colour colour){
        super(colour);
        setName("ant");
    }


    @Override
    public ArrayList<Point> getPossibleMoves(ArrayList<Point> boardPositions) {
        Point antPoint = boardPositions.get(0);
        ArrayList<Point> possibleMoves = new ArrayList<>();
        if(pieceIsStuck(boardPositions,antPoint) || !isHiveCohesiveAfterMove(boardPositions)){
            return possibleMoves;
        }
        for(int i=1;i<boardPositions.size();i++){
            ArrayList<Point> currentList = getSurroundingCoordinates(boardPositions.get(i));
            for(Point surroundPoint : currentList){
                if(!isInList(surroundPoint,possibleMoves) && !isInList(surroundPoint, boardPositions) && !surroundPoint.equals(antPoint)){
                    possibleMoves.add(surroundPoint);
                }
            }
        }

        return possibleMoves;
    }




}
