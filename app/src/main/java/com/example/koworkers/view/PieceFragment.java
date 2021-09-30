package com.example.koworkers.view;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.koworkers.R;
import com.example.koworkers.model.Colour;
import com.example.koworkers.model.pieces.IPiece;
import com.example.koworkers.viewmodel.PieceViewModel;
import com.example.koworkers.model.pieces.Piece;
import com.example.koworkers.model.Point;

public class PieceFragment extends Fragment {

    //private Point point;
    private final IPiece piece;

    private PieceViewModel mViewModel;

    private PieceFragment(IPiece piece){
        this.piece = piece;
    }

    public static PieceFragment newInstance(IPiece piece) {
        return new PieceFragment(piece);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.piece_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(PieceViewModel.class);

        ImageView hexagonImage = getView().findViewById(R.id.hexagonImage);
        ImageView insectImage = getView().findViewById(R.id.insectImage);

        if(piece.getColour().equals(Colour.WHITE)){
            hexagonImage.setImageResource(R.drawable.white_hexagon);
        } else{
            hexagonImage.setImageResource(R.drawable.black_hexagon);
        }
        insectImage.setImageResource(piece.getImageResource());
    }

    public IPiece getPiece() {
        return piece;
    }
}