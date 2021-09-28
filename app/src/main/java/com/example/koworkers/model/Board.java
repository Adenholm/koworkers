package com.example.koworkers.model;



import com.example.koworkers.model.pieces.Piece;

import java.util.ArrayList;
import java.util.HashMap;

public class Board implements IPublisher{
    HashMap<Piece,Point> hive= new HashMap(); //Hashmap med piece som key
    public void movePiece(Piece piece, Point point){
        hive.put(piece, point); //Tar en ny position och kopplar till piece å ändrar på så sätt piece-position.
        //kalla på boardViewModel.placement för att lägga till piecen i viewCoordinates
        notifySubscribers(); //notifierar BVM

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
