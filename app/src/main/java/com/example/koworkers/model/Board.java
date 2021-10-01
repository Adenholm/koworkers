package com.example.koworkers.model;



import com.example.koworkers.model.pieces.IPiece;


import java.util.ArrayList;
import java.util.HashMap;

public class Board implements IPublisher{
    public IPiece newPiece;
    public Point newPoint;
    HashMap<IPiece,Point> playedPieces= new HashMap(); //Hashmap med piece som key

    public void movePiece(IPiece piece, Point point){
        playedPieces.put(piece, point); //Tar en ny position och kopplar till piece å ändrar på så sätt piece-position.
        //kalla på boardViewModel.placement för att lägga till piecen i viewCoordinates
        notifySubscribers(); //notifierar BVM

    }


    /**
     * get the possible moves for a specific piece
     * @param piece the piece that the possible moves is wanted for
     * @return a list of the coordinates of the possible moves
     */
    public ArrayList<Point> getPossibleMoves(IPiece piece){
         return piece.getPossibleMoves(new ArrayList<>(playedPieces.values()));
    }

    ArrayList<Isubscriber> subscribers=new ArrayList<>();
    //Board notifierar BVM
    @Override
    public void subscribe(Isubscriber isubscriber) {

            subscribers.add(isubscriber);
        }
    @Override
    public void notifySubscribers() {
        for (Isubscriber subscriber:subscribers) {
            subscriber.update();
        }
    }



}
