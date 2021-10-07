package com.example.koworkers.model;


import com.example.koworkers.model.pieces.IPiece;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class Board implements IPublisher {

    private final HashMap<IPiece, Point> playedPieces = new HashMap<>(); //Hashmap med piece som key

    private IPiece blackQueen;
    private IPiece whiteQueen;
    private String winner;

    /**
     * Kollar var man kan placera ut en ny piece, om inga anna pieces är utplacerade kan man endast placera ut på 0,0, origo
     *
     * @return en lista med möjliga platser att placera ut en ny piece på
     */
    public ArrayList<Point> getPossibleplacements(Colour currentPlayersColour) {
        Collection<Point> colPlayedPoint = playedPieces.values();
        ArrayList<Point> playedPoints = new ArrayList<>(colPlayedPoint);

        ArrayList<Point> possiblePlacements = new ArrayList<>();

        if (playedPieces.size() == 0) {//Om det inte ligger några pieces ute hamnar första piecen som läggs ut på 0,0, origo
            possiblePlacements.add(new Point(0, 0));
        } else {
            Colour nemesisColour=Colour.WHITE;
            if (currentPlayersColour == Colour.WHITE) {
               nemesisColour = Colour.BLACK;
            }
            possiblePlacements=checkAdd(possiblePlacements, getSurroundPlayedPieces(currentPlayersColour));
            possiblePlacements = checkRemove(possiblePlacements, getSurroundPlayedPieces(nemesisColour));
            if (playedPieces.size() == 1) { //Om det ligger mer än 2 pieces ute får inte nästa piece läggas ut bredvid en av motståndarens pieces
                possiblePlacements.addAll(getSurroundPlayedPieces(nemesisColour));
            }
            possiblePlacements = checkRemove(possiblePlacements, playedPoints);

        }


        return possiblePlacements;
    }

    public static ArrayList<Point> checkRemove(ArrayList<Point> keep, ArrayList<Point> remove) {

        for (Point point : remove) {
            for (int i=0; i< keep.size(); i++) {
               Point keepPoint= keep.get(i);
                if (keepPoint.equals(point)) {
                    keep.remove(keepPoint);
                }
            }
        }
        return keep;
    }
    public static ArrayList<Point> checkAdd(ArrayList<Point> addTo, ArrayList<Point> addFrom) {

        for (Point point : addFrom) {
            Point addPoint=point;
            for (int i=0; i< addTo.size(); i++) {
                if (addTo.get(i).equals(point)) {
                    addTo.remove(addTo.get(i));
                }
            }
            addTo.add(addPoint);
        }
        return addTo;
    }

    private ArrayList<Point> getSurroundPlayedPieces(Colour playerColour) {
        ArrayList<Point> surroundingPlayedPieces = new ArrayList<>();

        for (IPiece pie : playedPieces.keySet()) { //går igenom alla utlagda pieces
            if (pie.getColour() == playerColour) {//kollar om pie är spelarens färg, pie är en piece i playedPieces

                for (Point surroundingPoint : pie.getSurroundingCoordinates(playedPieces.get(pie)))//Går igenom pies surrounding coordinates

                    if (!surroundingPlayedPieces.contains(surroundingPoint)) { //Om punkten inte finns läggs den till
                        surroundingPlayedPieces.add(surroundingPoint);
                    }
                }
        }
        return surroundingPlayedPieces;
    }



    /**
     * Kopplar en piece till en point i playedPieces som representerar de utlagda pieces
     *
     * @param piece piece som läggs ut
     * @param point point som den läggs på
     */
    public void movePiece(IPiece piece, Point point) {
        playedPieces.put(piece, point); //Tar en ny position och kopplar till piece å ändrar på så sätt piece-position.
        //kalla på boardViewModel.placement för att lägga till piecen i viewCoordinates
        notifySubscribers(); //notifierar BVM
      /*  if(isWinner()){
        }*/

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

    private final ArrayList<Isubscriber> subscribers = new ArrayList<>();

    //Board notifierar BVM
    @Override
    public void subscribe(Isubscriber isubscriber) {

        subscribers.add(isubscriber);
    }

    @Override
    public void notifySubscribers() {
        for (Isubscriber subscriber : subscribers) {
            //subscriber.update();
        }
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
     * Returns a string with the winner.
     * @return String with the winner.
     */
    public String getWinner() {
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
