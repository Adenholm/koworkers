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
import com.example.koworkers.model.Isubscriber;
import com.example.koworkers.model.pieces.IPiece;
import com.example.koworkers.model.pieces.Piece;
import com.example.koworkers.viewmodel.BoardViewModel;

import java.util.ArrayList;

public class BoardFragment extends Fragment implements Isubscriber {
    //notifieras av BVM
    @Override
    public void update() {
        populate();
    }


    ArrayList <PieceFragment> pieceFragments =new ArrayList<>();
    private void populate(){
            for (IPiece key: mViewModel.viewCoordinates.keySet()){//Gör om hashmapen av pieces å points till en arrayList av Piecefragments
                PieceFragment pf = new PieceFragment();
                pf.setPiece(key);
                pf.setPoint(mViewModel.viewCoordinates.get(key)); //inte så OOP, mst förbättras
        }
    }

    private BoardViewModel mViewModel;

    public static BoardFragment newInstance() {
        return new BoardFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.board_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(BoardViewModel.class);
        // TODO: Use the ViewModel
    }

}