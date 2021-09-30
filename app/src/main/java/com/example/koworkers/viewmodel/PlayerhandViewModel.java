package com.example.koworkers.viewmodel;

import androidx.lifecycle.ViewModel;

import com.example.koworkers.model.Hive;
import com.example.koworkers.model.pieces.IPiece;

import java.util.ArrayList;

public class PlayerhandViewModel extends ViewModel {
    private final Hive hive = Hive.getInstance();
    public ArrayList<IPiece> getPieces(){
        return hive.getCurrentPlayerHandPieces();
    }
}