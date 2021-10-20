package com.example.koworkers.model;

import com.example.koworkers.model.pieces.IPiece;

/**
 * A interface with subsciber-methods. Will be used to make classes able to listen to a publisher-class.
 * @author Qwinth, Adenholm
 */
public interface Isubscriber {
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

    /**
     * Runs if a piece was moved.
     * @param piece piece that was moved.
     */
    void pieceWasMoved(IPiece piece);

    /**
     * Runs if Player was changed.
     * @param colour Colour of new player.
     */
    void playerWasChanged(Colour colour);

    /**
     * Runs when game is restarted.
     */
    void  gameWasRestarted();
}

