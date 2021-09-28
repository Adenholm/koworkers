package com.example.koworkers.ui.playerhand;

import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.koworkers.R;
import com.example.koworkers.model.pieces.IPiece;
import com.example.koworkers.ui.piece.PieceFragment;

public class PieceStackFragment extends Fragment {

    private PieceStackViewModel mViewModel;

    private TextView numberText;
    private PieceFragment pieceFragment;


    private final IPiece piece;
    private int numberOfPieces;

    public PieceStackFragment(IPiece piece){
        this.piece = piece;
        numberOfPieces = 1;
    }

    public static PieceStackFragment newInstance(IPiece piece) {
        return new PieceStackFragment(piece);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.piece_stack_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(PieceStackViewModel.class);

        numberText = getView().findViewById(R.id.numberText);

        //adds the pieceFragment
        FragmentTransaction ft = getParentFragmentManager().beginTransaction();
        pieceFragment = new PieceFragment();
        ft.add(R.id.pieceFrame, pieceFragment);
        ft.commit();

        numberText.setText(numberOfPieces);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        pieceFragment.onDestroy();
    }

    public void incNumberOfPieces(){
        numberOfPieces++;
        numberText.setText(numberOfPieces);
    }

    public IPiece getPiece() {
        return piece;
    }
}