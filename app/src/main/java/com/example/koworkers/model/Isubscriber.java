package com.example.koworkers.model;

import com.example.koworkers.model.pieces.IPiece;

public interface Isubscriber {
    void selectPiece(IPiece piece);
    void deselectPiece(IPiece piece);
    void movePiece(IPiece piece, Point point);
    void switchPlayer(Colour colour);
}
