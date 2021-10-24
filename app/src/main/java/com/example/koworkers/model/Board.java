package com.example.koworkers.model;


import com.example.koworkers.model.pieces.IPiece;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 * A class representing the board of the game. Holds the pieces and their position. Moves and places pieces on the board.
 *
 * @Author Hanna Adenholm
 * @Author Stina Hansson
 * @Author Lisa Qwinth
 */
class Board {

    private final HashMap<IPiece, Point> playedPieces = new HashMap<>(); //Hashmap med piece som key

    private IPiece blackQueen;
    private IPiece whiteQueen;
    private Colour winner;
    private final ArrayList<IPiece> topStacked = new ArrayList<>();

    /**
     * Checks where it possible to place a new piece. The first piece is only able to be placed in origo
     *
     * @return a list with possible placements
     */
    public ArrayList<Point> getPossiblePlacements(Colour currentPlayersColour) {
        Collection<Point> colPlayedPoint = playedPieces.values();
        ArrayList<Point> playedPoints = new ArrayList<>(colPlayedPoint);

        ArrayList<Point> possiblePlacements = new ArrayList<>();

        if (playedPieces.size() == 0) {
            possiblePlacements.add(new Point(0, 0));
        } else {
            Colour nemesisColour = Colour.WHITE;
            if (currentPlayersColour == Colour.WHITE) {
                nemesisColour = Colour.BLACK;
            }
            possiblePlacements = checkAdd(possiblePlacements, getSurroundOnePlayerPieces(currentPlayersColour, false));
            possiblePlacements = checkRemove(possiblePlacements, getSurroundOnePlayerPieces(nemesisColour, true));//Removes points which touches pieces of nemesis's colour, if there isn't a piece of playerColour stacked on top.
            if (playedPieces.size() == 1) { //If there is less than three played pieces, you can place a piece beside your nemesis's piece.
                possiblePlacements.addAll(getSurroundOnePlayerPieces(nemesisColour, false));
            }
            possiblePlacements = checkRemove(possiblePlacements, playedPoints);//You can never place a piece if there already is a piece in that point
            checkRemove(possiblePlacements, playedPoints);

        }
        return possiblePlacements;
    }

    /**
     * Checks if a stack has occurred
     *
     * @param playPoint the checked point
     * @return true if a stack has occurred
     */
    private boolean isStacked(Point playPoint) {
        Collection<Point> playedPoints = playedPieces.values();
        for (Point boardPoint : playedPoints) {
            if (boardPoint.equals(playPoint)) {

                return true;
            }
        }
        return false;
    }

    /**
     * checks if the piece was on top of another piece and in that case remove it from the topStacked list
     *
     * @param playPiece the played piece
     * @return true if it was on top of another piece
     */
    private boolean isUnStacked(IPiece playPiece) {
        return topStacked.size() != 0 && topStacked.contains(playPiece);
    }


    /**
     * Checks if the elements of a list has the equal element as another list and in that case removes them
     *
     * @param keep   a list of the elements which will be removed from
     * @param remove a list of element which will be removed from keep
     * @return keep but without the elements in remove
     */
    private ArrayList<Point> checkRemove(ArrayList<Point> keep, ArrayList<Point> remove) {

        for (Point point : remove) {
            for (int i = 0; i < keep.size(); i++) { //Shouldn't be replaced with an enhanced for-loop! :)
                Point keepPoint = keep.get(i);
                if (keepPoint.equals(point)) {
                    keep.remove(keepPoint);
                }
            }
        }
        return keep;
    }

    /**
     * A method which adds elements from one list to another if they doesn't exist in that list
     *
     * @param addTo   the list which will be added to
     * @param addFrom the list which will be added from
     * @return addTo with the elements from addFrom
     */
    private ArrayList<Point> checkAdd(ArrayList<Point> addTo, ArrayList<Point> addFrom) {
        ArrayList<Point> addedTo = new ArrayList<>();
        addedTo.addAll(addTo);
        for (Point point : addFrom) {
            for (int i = 0; i < addTo.size(); i++) { //Shouldn't be replaced with an enhanced for-loop.
                if (addTo.get(i).equals(point)) {
                    addedTo.remove(addTo.get(i));
                }
            }
            addedTo.add(point);
        }
        return addedTo;
    }

    /**
     * A method which gives the surrounding points for played pieces of one colour.
     * @param playerColour the colour of the pieces which SurroundingPlayedPieces will surround
     * @return a list of the surrounding points for one players played pieces.
     */

    /**
     * Get all points surrounding all pieces of a colour
     *
     * @param playerColour the colour the points will surrounding
     * @param withStack    if the operation needs to consider stacked pieces
     * @return a list of all surrounding points around pieces of playerColour
     */
    private ArrayList<Point> getSurroundOnePlayerPieces(Colour playerColour, boolean withStack) {
        ArrayList<Point> surroundingPlayedPieces = new ArrayList<>();
        if (topStacked.size() == 0) {//If there isn't any stacked pieces, we treat it as we would without stack-conditions
            withStack = false;
        }
        for (IPiece pie : playedPieces.keySet()) {
            if (!withStack) {
                surroundingPlayedPieces.addAll(getSurroundingPlayedPoints(pie, playerColour));
            }
            else {
                for (IPiece piece : topStacked) {
                    if (!playedPieces.get(pie).equals(playedPieces.get(piece)) || pie.equals(piece)) { //Adds point to surroundingPlayedPieces if the point has the same coordinate as a topStacked piece, but isn't the topStacked piece, aka a bottomStacked piece
                        surroundingPlayedPieces.addAll(getSurroundingPlayedPoints(pie, playerColour));
                    }
                }
            }


        }
        return surroundingPlayedPieces;
    }

    /**
     * Checks the surrounding points for a piece. if it doesn't exist in surroundingPlayedPoints, adds it.
     *
     * @param pie          the piece it checks around.
     * @param playerColour the colour it checks surrounding for
     * @return
     */
    private ArrayList<Point> getSurroundingPlayedPoints(IPiece pie, Colour playerColour) {
        ArrayList<Point> surroundingPlayedPoints = new ArrayList<>();
        if (pie.getColour() == playerColour) {

            for (Point surroundingPoint : pie.getSurroundingCoordinates(playedPieces.get(pie))) {//Goes through the surrounding coordinates for every piece on the board

                if (!surroundingPlayedPoints.contains(surroundingPoint)) {
                    surroundingPlayedPoints.add(surroundingPoint);
                }
            }
        }
        return surroundingPlayedPoints;
    }


    /**
     * Adds a piece as a key and a point as it's value in PlayedPieces
     *
     * @param piece the added piece
     * @param point the added point
     */
    public void movePiece(IPiece piece, Point point) {
        if (isUnStacked(piece)) {
            topStacked.remove(piece);
        }
        if (isStacked(point)) {
            topStacked.add(piece);
        }
        for (IPiece playedPiece : playedPieces.keySet()) { //Less dublicates
            if (playedPieces.get(playedPiece).equals(point)) {
                point = playedPieces.get(playedPiece);
            }
        }
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
     * Returns the pieces on the board
     * @return Arraylist with pieces on the board
     */
    public ArrayList<IPiece> getPiecesOnBoard(){
        ArrayList<IPiece> pieces = new ArrayList<>();
        pieces.addAll(playedPieces.keySet());
        return pieces;
    }


    /**
     * Checks if the queens surrounding coordinates exist in playedPieces.
     *
     * @return true if one of the queens been surrounding.
     */
    boolean aQueenIsSurrounded() {
        int blackCount = 0;
        for (Point point : blackQueen.getSurroundingCoordinates(playedPieces.get(blackQueen))) {
            for (Point boardPoint : playedPieces.values()) {
                if (point.equals(boardPoint)) {
                    blackCount++;
                }
            }
        }
        if (blackCount == 6) {
            winner = Colour.WHITE;
            return true;
        }
        int whiteCount = 0;
        for (Point point : whiteQueen.getSurroundingCoordinates(playedPieces.get(whiteQueen))) {
            for (Point boardPoint : playedPieces.values()) {
                if (point.equals(boardPoint)) {
                    whiteCount++;
                }
            }
        }
        if (whiteCount == 6) {
            winner = Colour.BLACK;
            return true;
        }

        return false;
    }

    /**
     * Checks if the provided colour is able to make a move on the board
     *
     * @param colour the colour of the pieces to be checked if able to move
     * @return true if any piece is able to move
     */
    public boolean playerCanMakeMove(Colour colour) {
        for (IPiece piece : playedPieces.keySet()) {
            if (piece.getColour().equals(colour) && !getPossibleMoves(piece).isEmpty()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the colour of the winner.
     *
     * @return Colour of the winner.
     */
    public Colour getWinner() {
        return winner;
    }

    /**
     * Sets the black queen.
     *
     * @param blackQueen the piece to be set as the black queen
     */
    public void setBlackQueen(IPiece blackQueen) {
        this.blackQueen = blackQueen;
    }

    /**
     * Sets the white queen.
     *
     * @param whiteQueen the piece to be set as the white queen
     */
    public void setWhiteQueen(IPiece whiteQueen) {
        this.whiteQueen = whiteQueen;
    }
}
