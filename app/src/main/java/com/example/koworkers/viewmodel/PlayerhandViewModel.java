package com.example.koworkers.viewmodel;

import androidx.lifecycle.ViewModel;

import com.example.koworkers.model.pieces.IPiece;

import java.util.ArrayList;

public class PlayerhandViewModel extends ViewModel {
    public ArrayList<IPiece> getPieces(){
        return new ArrayList<>();
    }
}