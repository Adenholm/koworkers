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

    //TODO add method that checks if a piece is stuck, and therefore can't move
    protected boolean pieceIsStuck(ArrayList<Point> boardPositions, Point position){
        int occupiedNeighbours = 0;
        Point ownPosition = boardPositions.get(0);
        for(Point p: getSurroundingCoordinates(position)){
            if(isInList(p,boardPositions)){
                occupiedNeighbours++;
            }
        }
        //if a piece is surrounded på 5 or more pieces, it's always stuck
        if(occupiedNeighbours >= 5){
            return true;
        }
        else if(occupiedNeighbours == 4){
            ArrayList<Point> surroundPoints = getSurroundingCoordinates(ownPosition);
            int nextPoint;
            for(int i= 0;i<surroundPoints.size();i++){
                if(i+1 < surroundPoints.size()){
                    nextPoint = i+1;
                }
                else{
                    nextPoint = 0;
                }
                if(!isInList(surroundPoints.get(i),boardPositions) && !isInList(surroundPoints.get(nextPoint),boardPositions)){
                    return false;
                }
            }
            return true;
        }
        else{
            return false;
        }
    }

    //TODO add method that makes sure that a piece can't move if moving the piece means that the hive is not cohesive
    protected boolean isHiveCohesiveAfterMove(ArrayList<Point> boardPositions){
        boolean visited[] = new boolean[boardPositions.size()];
        //doesn't want to include first element of boardpositions, since that is the piece we want to move
        DFS(1,boardPositions,visited);

        //check if all pieces has been visited, if yes then the hive is cohesive
        int count =0;
        for(int i=1;i<visited.length;i++){
            if(visited[i]){
                count++;
            }
        }
        if(boardPositions.size()-1 == count){
            return true;
        }
        else{
            return false;
        }
    }

    //going through all of the pieces using depth-first search
    private void DFS(int point, ArrayList<Point> boardPositions, boolean[] visited){
        //mark the current point as visited
        visited[point] = true;

        //go through the neighbours
        for(int i = 0;i<getSurroundingCoordinates(boardPositions.get(point)).size();i++){
            Point currentPoint = getSurroundingCoordinates(boardPositions.get(point)).get(i);
            //if the coordinate isn't occupied by a piece, go to next
            //the piece that is moving is here seen as an empty space
            int neighbour;
            if(!isInList(currentPoint,boardPositions) || currentPoint.equals(boardPositions.get(0))){
                continue;
            }
            else{
                neighbour = indexOfPoint(boardPositions, currentPoint);
            }
            if(neighbour != -1 && visited[neighbour] == false){
                DFS(neighbour,boardPositions,visited);
            }
        }
    }

    private int indexOfPoint(ArrayList<Point> list, Point point){
        for(int i = 0;i<list.size();i++){
            if(list.get(i).equals(point)){
                return i;
            }
        }
        return -1;
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
