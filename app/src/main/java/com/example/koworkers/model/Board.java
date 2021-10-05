package com.example.koworkers.model;



import com.example.koworkers.model.pieces.IPiece;


import java.util.ArrayList;
import java.util.HashMap;

public class Board implements IPublisher {
    private IPiece newPiece;
    private Point newPoint;
    HashMap<IPiece, Point> playedPieces = new HashMap(); //Hashmap med piece som key

    /**
     * Kollar var man kan placera ut en ny piece, om inga anna pieces är utplacerade kan man endast placera ut på 0,0, origo
     *
     * @return en lista med möjliga platser att placera ut en ny piece på
     */
    public ArrayList<Point> getPossibleplacements() {
        ArrayList<Point> possiblePlacements = new ArrayList<Point>();

        if (playedPieces.size() == 0) {//Om det inte ligger några pieces ute hamnar första piecen som läggs ut på 0,0, origo
            possiblePlacements.add(new Point(0, 0));
        } else {

            for (IPiece pie : playedPieces.keySet()) { //går igenom alla utlagda pieces
                for (Point surroundingPoint : pie.getSurroundingCoordinates(playedPieces.get(pie)))//Går igenom pies surrounding coordinates
                    if (playedPieces.size() != 1) { //Om det ligger mer än 2 pieces ute får inte nästa piece läggas ut bredvid en av motståndarens pieces
                        if (pie.getColour() == Hive.getInstance().getCurrentPlayersColour()) {//kollar om pie är spelarens färg
                            if (!possiblePlacements.contains(surroundingPoint)) { //Om punkten inte finns läggs den till
                                possiblePlacements.add(surroundingPoint);
                            } else {
                                if (possiblePlacements.contains(surroundingPoint))
                                    //Om piecen är av ,otståndarens färg och pointenfinns med tas den bort
                                    possiblePlacements.remove(surroundingPoint);
                            }//det här går att skriva snyggare, värt att kolla på senare!
                        }
                    }
            }
        }
        return possiblePlacements;
    }


    public void movePiece(IPiece piece, Point point) {
        playedPieces.put(piece, point); //Tar en ny position och kopplar till piece å ändrar på så sätt piece-position.
        //kalla på boardViewModel.placement för att lägga till piecen i viewCoordinates
        notifySubscribers(); //notifierar BVM

    }


    public ArrayList<Point> getPossibleMoves(IPiece piece){
        return piece.getPossibleMoves(new ArrayList<>(playedPieces.values()));
    }

    private final ArrayList<Isubscriber> subscribers=new ArrayList<>();
    //Board notifierar BVM
    @Override
    public void subscribe(Isubscriber isubscriber) {

        subscribers.add(isubscriber);
    }

    @Override
    public void notifySubscribers() {
        for (Isubscriber subscriber : subscribers) {
            subscriber.update();
        }
    }

    public void placePiece(IPiece piece, Point point){
        playedPieces.put(piece, point);
    }

    public Point getPoint(IPiece piece){
        return playedPieces.get(piece);
    }

    public ArrayList<IPiece> getPiecesOnBoard(){
        ArrayList<IPiece> pieces = new ArrayList<>();
        pieces.addAll(playedPieces.keySet());
         return pieces;
    }

}
