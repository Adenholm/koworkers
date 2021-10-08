package com.example.koworkers.model.pieces;

import com.example.koworkers.model.Point;

import com.example.koworkers.model.Colour;

import java.util.ArrayList;
import java.util.Arrays;

/** The superclass to all different pieces
 * @author Stina Hansson
 * @author Hanna Adenholm
 */
abstract class Piece implements IPiece{
    private final Colour colour;
    private String name;

    /**
     *Constructor that sets the colour for the piece
     * @param colour The colour
     */
    protected Piece(Colour colour) {
        this.colour = colour;
    }

    /**
     * Sets the name for the piece
     * @param name The name of the piece
     */
    protected void setName(String name){
        this.name = name;
    }

    /**
     * Calculates the possible moves a piece can take
     * @param boardPositions A list of points that represents the other pieces on the board
     * @return A list of the possible moves
     */
    @Override
    public abstract ArrayList<Point> getPossibleMoves(ArrayList<Point> boardPositions);

    /**
     * Generates a list of the surrounding coordinates of a point
     * @param position The point that the surrounding coordinates are wanted for
     * @return A list of the surrounding coordinates
     */
    @Override
    public ArrayList<Point> getSurroundingCoordinates(Point position){
        ArrayList<Point> currentMoves = new ArrayList<>();
        ArrayList<Point> surroundingCoordinates = new ArrayList<>(Arrays.asList(new Point[]{new Point(-1,1),new Point(-1,0),new Point(0,1),new Point(0,-1),new Point(1,0),new Point(1,-1)}));
        for(int i= 0;i<surroundingCoordinates.size();i++){
            currentMoves.add(new Point(position.getX()+surroundingCoordinates.get(i).getX(),position.getY()+surroundingCoordinates.get(i).getY()));
        }
        return currentMoves;
    }

    //TODO add method that checks if a piece is stuck, and therefore can't move

    //TODO add method that makes sure that a piece can't move if moving the piece means that the hive is not cohesive


    /**
     * Returns the colour of the piece
     * @return A colour
     */
    @Override
    public Colour getColour() {
        return colour;
    }

    /**
     * Returns the name of the piece
     * @return name of piece
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Checks if a certain position is in a list
     * @param point The point that is searched for
     * @param points The list that is searched within
     * @return True if the point is in the list, and false otherwise
     */
    protected boolean isInList(Point point, ArrayList<Point> points){
        for(Point listPoint: points){
            if(point.equals(listPoint)){
                return true;
            }
        }
        return false;
    }

}
