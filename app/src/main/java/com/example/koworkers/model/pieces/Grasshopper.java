package com.example.koworkers.model.pieces;

import android.graphics.Point;

import com.example.koworkers.R;
import com.example.koworkers.model.Colour;

import java.util.ArrayList;

public class Grasshopper extends Piece {

    public Grasshopper(Colour colour){
        super(colour);
        if(colour == Colour.BLACK){
            this.setImageResource(R.drawable.black_grass_piece);
        }
        else{
            this.setImageResource(R.drawable.grass_piece);
        }
    }

    @Override
    public ArrayList<Point> getPossibleMoves(ArrayList<Point> boardPositions) {
        return null;
    }

}
