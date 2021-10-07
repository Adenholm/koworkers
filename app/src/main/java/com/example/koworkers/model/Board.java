package com.example.koworkers.model;


import com.example.koworkers.model.pieces.IPiece;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 * A class representing the board of the game. Holds the pieces and their position. Moves and places pieces on the board.
 * @Author Qwinth, Adenholm, Hansson
 */
public class Board implements IPublisher {

    private final HashMap<IPiece, Point> playedPieces = new HashMap<>(); //Hashmap med piece som key

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
            if (playedPieces.size() == 1) { //If there is less than three played pieces, you can place a piece beside your nemesis's piece.
                possiblePlacements.addAll(getSurroundOnePlayerPieces(nemesisColour));
            }
            possiblePlacements = checkRemove(possiblePlacements, playedPoints);

        }


        return possiblePlacements;
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
        playedPieces.put(piece, point);
        notifySubscribers();
    }

    /**
     * Kallar på piece getPossibleMoves för att kolla var en piece kan flyttas
     *
     * @param piece piece som ska flyttas på
     * @return en arrayList av möjliga points att flytta till
     */
    public ArrayList<Point> getPossibleMoves(IPiece piece) {
        ArrayList<Point> boardPoints = new ArrayList<>(playedPieces.values());
        boardPoints.remove(playedPieces.get(piece));
        boardPoints.add(0, playedPieces.get(piece));
        return piece.getPossibleMoves(boardPoints);
    }

    private final ArrayList<Isubscriber> subscribers = new ArrayList<>();

    /**
     * adds a subscriber subscribers
     * @param isubscriber the subscriber to be added
     */
    @Override
    public void subscribe(Isubscriber isubscriber) {

        subscribers.add(isubscriber);
    }

    /**
     * Notifyes the subscribers in subscribers
     */
    @Override
    public void notifySubscribers() {
        for (Isubscriber subscriber : subscribers) {
            //subscriber.update();
        }
    }

    /**
     * Gives the point in which a piece is placed
     * @param piece the piece which position is given
     * @return the point in which the piece is placed in
     */
    public Point getPoint(IPiece piece) {
        return playedPieces.get(piece);
    }


    private IPiece blackQueen;
    private IPiece whiteQueen;
    private String winner;

    /**
     * Checks if the queens surrounding coordinates exist in playedPieces
     * @return true if one of the queens been surrounded
     */
    boolean aQueenIsSurrounded (){
        for (Point point:blackQueen.getSurroundingCoordinates(playedPieces.get(blackQueen))){
            if (!playedPieces.containsValue(point)) break;
            winner="Winner: Black";
            return true;
        }
        for (Point point:whiteQueen.getSurroundingCoordinates(playedPieces.get(whiteQueen))){
            if(!playedPieces.containsValue(point)) break;
            winner="Winner: White";
            return true;
        }

       return false;
    }

    /**
     * get who is the winner
     * @return a string with the winner written in it
     */
    public String getWinner() {
        return winner;
    }

    /**
     * Sets a piece in the blackQueen-variable (preferably the black Queen)
     * @param blackQueen the piece set to blackQueen
     */
    public void setBlackQueen(IPiece blackQueen) {
        this.blackQueen = blackQueen;
    }

    /**
     * Sets a piece in the whiteQueen-variable (preferably the white Queen)
     * @param whiteQueen the piece set to whiteQueen
     */
    public void setWhiteQueen(IPiece whiteQueen) {
        this.whiteQueen = whiteQueen;
    }
}
