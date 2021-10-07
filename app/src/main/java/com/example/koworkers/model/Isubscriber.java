package com.example.koworkers.model;

import com.example.koworkers.model.pieces.IPiece;

/**
 * A interface with subsciber-methods. Will be used to make classes able to listen to a publisher-class.
 * @author Qwinth, Adenholm
 */
public interface Isubscriber {
    void selectPiece(IPiece piece);
    void deselectPiece();
    void movePiece(IPiece piece, Point point);
    void switchPlayer(Colour colour);
}
