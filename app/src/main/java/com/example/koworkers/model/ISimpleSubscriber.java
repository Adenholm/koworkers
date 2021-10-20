package com.example.koworkers.model;

import com.example.koworkers.model.pieces.IPiece;

public interface ISimpleSubscriber {

    /**
     * Runs if there is any change in the model
     */
    void modelWasUpdated();

    /**
     * Runs if piece was selected.
     *
     * @param piece The selected Piece
     */
    void pieceWasSelected(IPiece piece);

    /**
     * Runs if a piece was deselected.
     */
    void pieceWasDeselected();
}
