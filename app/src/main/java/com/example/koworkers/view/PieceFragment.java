package com.example.koworkers.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.koworkers.R;
import com.example.koworkers.model.Point;
import com.example.koworkers.model.pieces.IPiece;
import com.example.koworkers.viewmodel.PieceViewModel;

public class PieceFragment extends Fragment {
    private Point point;
    private IPiece piece;

    public void setPoint(Point point){
        this.point=point;
    }
    public void setPiece(IPiece piece){
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