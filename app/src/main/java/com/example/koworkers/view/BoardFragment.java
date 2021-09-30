package com.example.koworkers.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.koworkers.R;
import com.example.koworkers.model.Isubscriber;
import com.example.koworkers.model.Point;
import com.example.koworkers.model.pieces.IPiece;
import com.example.koworkers.model.pieces.Piece;


import java.util.ArrayList;
import com.example.koworkers.viewmodel.BoardViewModel;

public class BoardFragment extends Fragment implements Isubscriber {

    private FrameLayout boardFrame;

    private ArrayList<PieceImage> pieceImages;

    @Override
    public void update() {
        populate();
    }

    ArrayList <PieceFragment> pieceFragments =new ArrayList<>();
    private void populate(){
            for (Piece key: mViewModel.viewCoordinates.keySet()){//Gör om hashmapen av pieces å points till en arrayList av Piecefragments
                PieceFragment pf = PieceFragment.newInstance(key);
                //pf.setPoint(mViewModel.viewCoordinates.get(key)); //inte så OOP, mst förbättras
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

        boardFrame = getView().findViewById(R.id.boardFrame);

        initialize();

    }

    private void initialize(){
        int offset = 500 * (int) getContext().getResources().getDisplayMetrics().density;

        for(IPiece piece: mViewModel.getPiecesOnBoard()){
            pieceImages.add(new PieceImage(piece, getContext(), mViewModel.getCoordinates(piece), offset, 60, 2));
        }

        for(PieceImage pieceImage: pieceImages){
            boardFrame.addView(pieceImage.getImage());
        }
    }

    private void init(){
        for(IPiece piece: mViewModel.getPiecesOnBoard()){
            pieceFragments.add(PieceFragment.newInstance(piece));
        }

        int offset = 500 * (int) getContext().getResources().getDisplayMetrics().density;

        FragmentTransaction ft = getParentFragmentManager().beginTransaction();

        for(PieceFragment pieceFragment: pieceFragments){
            Point coordinates = mViewModel.getCoordinates(pieceFragment.getPiece());
            //setMargins(pieceFragment.getView(), coordinates.getX() + offset, coordinates.getY() + offset, 0,0);
            ft.add(boardFrame.getId(), pieceFragment);
        }
        ft.commit();
    }


}