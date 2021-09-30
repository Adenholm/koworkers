package com.example.koworkers.model;



import com.example.koworkers.model.pieces.IPiece;
import com.example.koworkers.model.pieces.Piece;


import java.util.ArrayList;
import java.util.HashMap;

public class Board implements IPublisher{
    private HashMap<IPiece,Point> hive= new HashMap(); //Hashmap med piece som key

    public void movePiece(IPiece piece, Point point){
        hive.put(piece, point); //Tar en ny position och kopplar till piece å ändrar på så sätt piece-position.
        //kalla på boardViewModel.placement för att lägga till piecen i viewCoordinates
        notifySubscribers(); //notifierar BVM

    }

    private final ArrayList<Isubscriber> subscribers=new ArrayList<>();
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

    public void placePiece(IPiece piece, Point point){
        hive.put(piece, point);
    }

    public Point getPoint(IPiece piece){
        return hive.get(piece);
    }

    public ArrayList<IPiece> getPiecesOnBoard(){
        ArrayList<IPiece> pieces = new ArrayList<>();
         for(IPiece piece: hive.keySet()){
             pieces.add(piece);
         }
         return pieces;
    }

}
