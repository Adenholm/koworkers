package com.example.koworkers.model;



import com.example.koworkers.model.pieces.Piece;
import com.example.koworkers.ui.board.BoardViewModel;

import java.util.HashMap;

public class Board {
    HashMap<Piece,Point> hive= new HashMap(); //Hashmap med piece som key
    public void movePiece(Piece piece, Point point){
        hive.put(piece, point); //Tar en ny position och kopplar till piece å ändrar på så sätt piece-position.
        //kalla på boardViewModel.placement för att lägga till piecen i viewCoordinates

    }
    void notifySubsciber(){

    }
}
