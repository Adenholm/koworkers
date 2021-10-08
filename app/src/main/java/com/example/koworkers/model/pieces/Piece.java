package com.example.koworkers.model.pieces;

import com.example.koworkers.model.Point;

import com.example.koworkers.model.Colour;

import java.util.ArrayList;
import java.util.Arrays;

abstract class Piece implements IPiece{
    private final Colour colour;
    private String name;

    protected Piece(Colour colour) {
        this.colour = colour;
    }
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

    protected boolean isInList(Point point, ArrayList<Point> points){
        for(Point listPoint: points){
            if(point.equals(listPoint)){
                return true;
            }
        }
        return false;
    }

}
