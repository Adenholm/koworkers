package com.example.koworkers.model.pieces;

import android.graphics.Point;

import com.example.koworkers.R;
import com.example.koworkers.model.Colour;

import java.util.ArrayList;

public class Queen extends Piece {

    public Queen(Colour colour){
        super(colour);
        if(colour == Colour.BLACK){
            this.setImageResource(R.drawable.black_queen_piece);
        }
        else{
            this.setImageResource(R.drawable.queen_piece);
        }
    }

    @Override
    public ArrayList<Point> getPossibleMoves(ArrayList<Point> boardPositions) {
        return null;
    }
}
