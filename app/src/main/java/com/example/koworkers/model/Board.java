package com.example.koworkers.model;


import com.example.koworkers.model.pieces.IPiece;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 * A class representing the board of the game. Holds the pieces and their position. Moves and places pieces on the board.
 * @Author Hanna Adenholm
 * @Author Stina Hansson
 * @Author Lisa Qwinth
 */
public class Board{

    private final HashMap<IPiece, Point> playedPieces = new HashMap<>(); //Hashmap med piece som key

    private IPiece blackQueen;
    private IPiece whiteQueen;
    private Colour winner;

    /**
     * Checks where it possible to place a new piece. The first piece is only able to be placed in origo
     *
     * @return a list with possible placements
     */
    public ArrayList<Point> getPossibleplacements(Colour currentPlayersColour) {
        Collection<Point> colPlayedPoint = playedPieces.values();
        ArrayList<Point> playedPoints = new ArrayList<>(colPlayedPoint);

        ArrayList<Point> possiblePlacements = new ArrayList<>();

        if (playedPieces.size() == 0) {
            possiblePlacements.add(new Point(0, 0));
        } else {
            Colour nemesisColour=Colour.WHITE;
            if (currentPlayersColour == Colour.WHITE) {
               nemesisColour = Colour.BLACK;
            }
            possiblePlacements=checkAdd(possiblePlacements, getSurroundOnePlayerPieces(currentPlayersColour));
            possiblePlacements = checkRemove(possiblePlacements, getSurroundOnePlayerPieces(nemesisColour));
            possiblePlacements = checkAdd(possiblePlacements,  checkedStackedPieces(possiblePlacements, currentPlayersColour));

            if (playedPieces.size() == 1) { //If there is less than three played pieces, you can place a piece beside your nemesis's piece.
                possiblePlacements.addAll(getSurroundOnePlayerPieces(nemesisColour));
            }
            possiblePlacements = checkRemove(possiblePlacements, playedPoints);

        }
        return possiblePlacements;
    }
    private ArrayList<Point> checkedStackedPieces(ArrayList<Point> placements, Colour currentPlayersColour){
        if (topStacked != null) {

            for (IPiece piece : topStacked) {
                if (piece.getColour() == currentPlayersColour) {
                    if (placements.contains(playedPieces.get(piece))) {
                        continue;
                    }
                    placements.add(playedPieces.get(piece));
                }
            }
        }
        return placements;
    }

    private ArrayList<IPiece> topStacked=new ArrayList<IPiece>();
    private boolean isStacked(IPiece playPiece, Point playPoint){
      Collection<Point> playedPoints=playedPieces.values();
      for (Point boardPoint:playedPoints){
          if (boardPoint.equals(playPoint)) {
              topStacked.add(playPiece);
              return true;
          }
      }
      return false;
    }

    /**
     * checks if the piece was on top of another piece and in that case remove it from the topStacked list
     * @param playPiece the played piece
     * @return true if it was on top of another piece
     */
    private boolean isUnStacked(IPiece playPiece){
        if (topStacked!= null && topStacked.contains(playPiece)){
            topStacked.remove(playPiece);
            return true;
        }
        return false;
    }

    /**
     * Checks if the elements of a list has the equal element as another list and in that case removes them
     * @param keep a list of the elements which will be removed from
     * @param remove a list of element which will be removed from keep
     * @return keep but without the elements in remove
     */
    private ArrayList<Point> checkRemove(ArrayList<Point> keep, ArrayList<Point> remove) {

        for (Point point : remove) {
            for (int i=0; i< keep.size(); i++) { //Shouldn't be replaced with an enhanced for-loop! :)
               Point keepPoint= keep.get(i);
                if (keepPoint.equals(point)) {
                    keep.remove(keepPoint);
                }
            }
        }
        return keep;
    }

    /**
     * A method which adds elements from one list to another if they doesn't exist in that list
     * @param addTo the list which will be added to
     * @param addFrom the list which will be added from
     * @return addTo with the elements from addFrom
     */
    private ArrayList<Point> checkAdd(ArrayList<Point> addTo, ArrayList<Point> addFrom) {

        for (Point point : addFrom) {
            for (int i=0; i< addTo.size(); i++) { //Shouldn't be replaced with an enhanced for-loop.
                if (addTo.get(i).equals(point)) {
                    addTo.remove(addTo.get(i));
                }
            }
            addTo.add(point);
        }
        return addTo;
    }

    /**
     * A method which gives the surrounding points for played pieces of one colour.
     * @param playerColour the colour of the pieces which SurroundingPlayedPieces will surround
     * @return a list of the surrounding points for one players played pieces.
     */
    private ArrayList<Point> getSurroundOnePlayerPieces(Colour playerColour) {
        ArrayList<Point> surroundingPlayedPieces = new ArrayList<>();

        for (IPiece pie : playedPieces.keySet()) {
            if (pie.getColour() == playerColour) {

                for (Point surroundingPoint : pie.getSurroundingCoordinates(playedPieces.get(pie)))//Goes through the surrounding coordinates for every piece on the board

                    if (!surroundingPlayedPieces.contains(surroundingPoint)) {
                        surroundingPlayedPieces.add(surroundingPoint);
                    }
                }
        }
        return surroundingPlayedPieces;
    }



    /**
     * Adds a piece as a key and a point as it's value in PlayedPieces
     *
     * @param piece the added piece
     * @param point the added point
     */
    public void movePiece(IPiece piece, Point point) {
        isUnStacked(piece);
        isStacked(piece,point);
        playedPieces.put(piece, point);
    }

    /**
     * Returns a list of Possible moves for the specified Piece.
     *
     * @param piece Piece to be moved.
     * @return A list of possible positions to move to,
     */
    public ArrayList<Point> getPossibleMoves(IPiece piece) {
        ArrayList<Point> boardPoints = new ArrayList<>(playedPieces.values());
        boardPoints.remove(playedPieces.get(piece));
        boardPoints.add(0, playedPieces.get(piece));
        return piece.getPossibleMoves(boardPoints);
    }


    /**
     * Returns the point where the provided piece is placed.
     *
     * @param piece Piece you want to know position of
     * @return The point where the provided piece is placed.
     */
    public Point getPoint(IPiece piece) {
        return playedPieces.get(piece);
    }


    /**
     * Checks if the queens surrounding coordinates exist in playedPieces.
     * @return true if one of the queens been surrounding.
     */
    boolean aQueenIsSurrounded (){
        int blackCount = 0;
        for (Point point:blackQueen.getSurroundingCoordinates(playedPieces.get(blackQueen))){
            for(Point boardPoint: playedPieces.values()){
                if(point.equals(boardPoint)){
                    blackCount ++;
                }
            }
        }
        if(blackCount == 6){
            winner = Colour.WHITE;
            return true;
        }
        int whiteCount = 0;
        for (Point point:whiteQueen.getSurroundingCoordinates(playedPieces.get(whiteQueen))){
            for(Point boardPoint: playedPieces.values()){
                if(point.equals(boardPoint)){
                    whiteCount ++;
                }
            }
        }
        if(whiteCount == 6){
            winner = Colour.BLACK;
            return true;
        }

       return false;
    }

    /**
     * Checks if the provided colour is able to make a move on the board
     * @param colour the colour of the pieces to be checked if able to move
     * @return true if any piece is able to move
     */
    public boolean playerCanMakeMove(Colour colour){
        for (IPiece piece: playedPieces.keySet()){
            if (piece.getColour().equals(colour) && !getPossibleMoves(piece).isEmpty()){
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the colour of the winner.
     * @return Colour of the winner.
     */
    public Colour getWinner() {
        return winner;
    }

    /**
     * Sets the black queen.
     * @param blackQueen the piece to be set as the black queen
     */
    public void setBlackQueen(IPiece blackQueen) {
        this.blackQueen = blackQueen;
    }

    /**
     * Sets the white queen.
     * @param whiteQueen the piece to be set as the white queen
     */
    public void setWhiteQueen(IPiece whiteQueen) {
        this.whiteQueen = whiteQueen;
    }
}
