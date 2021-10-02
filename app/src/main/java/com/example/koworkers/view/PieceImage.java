package com.example.koworkers.view;

import android.content.Context;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.koworkers.model.Point;
import com.example.koworkers.model.pieces.IPiece;

public class PieceImage {
    private final ImageView image;
    private final IPiece piece;

    public PieceImage(IPiece piece, Context context, Point coordinates, int offset, int size, int dpiRatio){
        this.piece = piece;

        image = new ImageView(context);
        image.setImageResource(piece.getImageResource());
        //setLayout(coordinates.getX() + offset, coordinates.getY() + offset, 0, 0, size, dpiRatio);

    }

    public void setLayout(int left, int top, int right, int bottom, int size, int dpiRatio){
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(size * dpiRatio, size * dpiRatio);
        params.setMargins(left*dpiRatio, top*dpiRatio, right*dpiRatio, bottom*dpiRatio);
        image.setLayoutParams(params);
    }

    public ImageView getImage() {
        return image;
    }

    public IPiece getPiece() {
        return piece;
    }
}
