package com.example.koworkers.ui.piece;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.koworkers.R;
import com.example.koworkers.model.pieces.Piece;
import com.example.koworkers.ui.board.Point;

public class PieceFragment extends Fragment {
    private Point point;
    private Piece piece;

    public void setPoint(Point point){
        this.point=point;
    }
    public void setPiece(Piece piece){
        this.piece=piece;
    }

    private PieceViewModel mViewModel;

    public static PieceFragment newInstance() {
        return new PieceFragment();
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
        // TODO: Use the ViewModel
    }

}