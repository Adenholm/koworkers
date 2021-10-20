package com.example.koworkers.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.koworkers.model.Hive;
import com.example.koworkers.model.ISimpleSubscriber;
import com.example.koworkers.model.pieces.IPiece;

import java.util.ArrayList;
import java.util.List;

/**
 * The viewModel for the Player Hand view.
 * @author Hanna Adenholm
 */
public class PlayerhandViewModel extends ViewModel implements ISimpleSubscriber {

    private Hive hive;

    private final MutableLiveData<List<IPiece>> playerHandPieces = new MutableLiveData<>();
    private IPiece selectedPiece = null;

    /**
     * Sets hive and Initializes the LiveData.
     * @param hive the instance of hive to get data from
     */
    public void init(Hive hive){
        this.hive = hive;
        playerHandPieces.postValue(hive.getCurrentPlayerHandPieces());
    }

    /**
     * Returns the liveData of the current players pieces.
     * @return LiveData with list of the current players pieces.
     */
    public LiveData<List<IPiece>> getPlayerHandPieces() {
        return playerHandPieces;
    }

    /**
     * Returns the selected piece.
     * @return selected piece.
     */
    public IPiece getSelectedPiece() {
        return selectedPiece;
    }

    /**
     * Select the provided Piece.
     * @param piece Piece to be selected.
     */
    public void handlePieceClick(IPiece piece){
        hive.selectPiece(piece);
    }

    /**
     * Checks if the queen should be placed.
     * @return true if queen needs to be placed.
     */
    public boolean queenShouldBePlaced(){
        return hive.playersQueenShouldBePlaced();
    }


    @Override
    public void modelWasUpdated() {
        playerHandPieces.setValue(hive.getCurrentPlayerHandPieces());
    }

    @Override
    public void pieceWasSelected(IPiece piece) {
        selectedPiece = piece;
        playerHandPieces.setValue(hive.getCurrentPlayerHandPieces());
    }

    @Override
    public void pieceWasDeselected() {
        selectedPiece = null;
        playerHandPieces.setValue(hive.getCurrentPlayerHandPieces());
    }
}