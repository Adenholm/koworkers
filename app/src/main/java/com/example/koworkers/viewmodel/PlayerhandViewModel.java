package com.example.koworkers.viewmodel;

import androidx.lifecycle.ViewModel;

import com.example.koworkers.model.Hive;
import com.example.koworkers.model.pieces.IPiece;

import java.util.ArrayList;

/**
 * The viewModel for the Player Hand view.
 * @author Hanna Adenholm
 */
public class PlayerhandViewModel extends ViewModel {

    private final Hive hive = Hive.getInstance();

    /**
     * Returns a list with the current Players pieces in hand.
     * @return list of current players pieces in hand.
     */
    public ArrayList<IPiece> getPieces(){
        return hive.getCurrentPlayerHandPieces();
    }

    /**
     * Select the provided Piece.
     * @param piece Piece to be selected.
     */
    public void selectPiece(IPiece piece){
        hive.selectPiece(piece);
    }

    /**
     * Checks if the queen should be placed.
     * @return true if queen needs to be placed.
     */
    public boolean queenShouldBePlaced(){
        return hive.playersQueenShouldBePlaced();
    }
}