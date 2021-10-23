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
        //gives surrounding coordinates in clockwise order
        ArrayList<Point> surroundingCoordinates = new ArrayList<>(Arrays.asList(new Point[]{new Point(0,1), new Point(1,0), new Point(1,-1), new Point(0,-1), new Point(-1,0), new Point(-1,1)}));
        for(int i= 0;i<surroundingCoordinates.size();i++){
            currentMoves.add(new Point(position.getX()+surroundingCoordinates.get(i).getX(),position.getY()+surroundingCoordinates.get(i).getY()));
        }
        return currentMoves;
    }

    /**
     * Checks if the piece that would be moved is not possible to slide out, and therefore is stuck
     * @param boardPositions List with coordinates of all the pieces on the board
     * @param position Coordinates of the piece that would be moved
     * @return True if the piece is stuck, and false if it isn't
     */
    protected boolean pieceIsStuck(ArrayList<Point> boardPositions, Point position){
        int occupiedNeighbours = 0;
        Point ownPosition = boardPositions.get(0);
        for(Point p: getSurroundingCoordinates(position)){
            if(boardPositions.contains(p) && !p.equals(ownPosition)){
                occupiedNeighbours++;
            }
        }
        //if a piece is surrounded på 5 or more pieces, it's always stuck
        if(occupiedNeighbours >= 5){
            return true;
        }
        else if(occupiedNeighbours == 4){
            ArrayList<Point> surroundPoints = getSurroundingCoordinates(position);
            int nextPoint;
            for(int i= 0;i<surroundPoints.size();i++){
                if(i+1 < surroundPoints.size()){
                    nextPoint = i+1;
                }
                else{
                    nextPoint = 0;
                }
                if(!boardPositions.contains(surroundPoints.get(i)) && !boardPositions.contains(surroundPoints.get(nextPoint))){
                    return false;
                }
            }
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Checks if the hive is still cohesive if the piece moved from its current position
     * @param boardPositions List with coordinates of all the pieces on the board
     * @return True if the hive still is cohesive, false if it isn't
     */
    protected boolean isHiveCohesiveAfterMove(ArrayList<Point> boardPositions){
        ArrayList<Point> noDuplicates = new ArrayList<>();
        for(Point p:boardPositions){
            if(!noDuplicates.contains(p)){
                noDuplicates.add(p);
            }
        }
        boolean visited[] = new boolean[noDuplicates.size()];
        //doesn't want to include first element of boardpositions, since that is the piece we want to move
        DFS(1,noDuplicates,visited);

        //check if all pieces has been visited, if yes then the hive is cohesive
        int count =0;
        for(int i=1;i<visited.length;i++){
            if(visited[i]){
                count++;
            }
        }
        if(noDuplicates.size()-1 == count){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Goes through all of the pieces using depth-first search
     * @param point The index of the piece that is currently being searched
     * @param boardPositions List with coordinates of all the pieces on the board
     * @param visited A boolean array where an element is true if it has been visited
     */
    private void DFS(int point, ArrayList<Point> boardPositions, boolean[] visited){
        //mark the current point as visited
        visited[point] = true;

        //go through the neighbours
        for(int i = 0;i<getSurroundingCoordinates(boardPositions.get(point)).size();i++){
            Point currentPoint = getSurroundingCoordinates(boardPositions.get(point)).get(i);
            //if the coordinate isn't occupied by a piece, go to next
            //the piece that is moving is here seen as an empty space
            int neighbour;
            if(!boardPositions.contains(currentPoint) || currentPoint.equals(boardPositions.get(0))){
                continue;
            }
            else{
                neighbour = boardPositions.indexOf(currentPoint);
            }
            if(visited[neighbour] == false){
                DFS(neighbour,boardPositions,visited);
            }
        }
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



}
