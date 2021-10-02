package com.example.koworkers.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.koworkers.R;
import com.example.koworkers.model.Isubscriber;
import com.example.koworkers.model.Point;
import com.example.koworkers.model.pieces.IPiece;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.example.koworkers.viewmodel.BoardViewModel;

public class BoardFragment extends Fragment implements Isubscriber {

    private FrameLayout boardFrame;

    private final Map<IPiece, ImageView> pieceMap = new HashMap<>();
    private final Map<ImageView, IPiece> imageMap = new HashMap<>();



    private final int offset = 500;


    @Override
    public void update() {
        if(mViewModel.aPieceIsSelected()){
            mViewModel.getPossibleMoves();
        }
        populate();
    }


    private void populate(){
        ImageView image;
        for(IPiece piece: mViewModel.getPiecesOnBoard()){
            if(pieceMap.containsKey(piece)){
                image = pieceMap.get(piece);
            }else{
                image = new ImageView(getContext());
                image.setImageResource(piece.getImageResource());
                pieceMap.put(piece,image);
                imageMap.put(image, piece);
            }
            setLayout(image, mViewModel.getCoordinates(piece).getX() + offset, mViewModel.getCoordinates(piece).getY() + offset, offset, offset, 70, 2 );
            boardFrame.addView(image);
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

        for(IPiece piece: mViewModel.getPiecesOnBoard()){
            ImageView image = new ImageView(getContext());
            image.setImageResource(piece.getImageResource());
            setLayout(image, mViewModel.getCoordinates(piece).getX() + offset, mViewModel.getCoordinates(piece).getY() + offset, offset, offset, 70, 2 );
            pieceMap.put(piece,image);
            boardFrame.addView(image);
        }

    }

    public void setLayout(View view, int left, int top, int right, int bottom, int size, int dpiRatio){
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(size * dpiRatio, size * dpiRatio);
        params.setMargins(left*dpiRatio, top*dpiRatio, right*dpiRatio, bottom*dpiRatio);
        view.setLayoutParams(params);
    }

//    private void initialize(){
//        int offset = 500 * (int) getContext().getResources().getDisplayMetrics().density;
//
//        for(IPiece piece: mViewModel.getPiecesOnBoard()){
//            pieceImages.add(new PieceImage(piece, getContext(), mViewModel.getCoordinates(piece), offset, 60, 2));
//        }
//
//        for(PieceImage pieceImage: pieceImages){
//            boardFrame.addView(pieceImage.getImage());
//        }
//    }

//    private void init(){
//        for(IPiece piece: mViewModel.getPiecesOnBoard()){
//            pieceFragments.add(PieceFragment.newInstance(piece));
//        }
//
//        int offset = 500 * (int) getContext().getResources().getDisplayMetrics().density;
//
//        FragmentTransaction ft = getParentFragmentManager().beginTransaction();
//
//        for(PieceFragment pieceFragment: pieceFragments){
//            Point coordinates = mViewModel.getCoordinates(pieceFragment.getPiece());
//            //setMargins(pieceFragment.getView(), coordinates.getX() + offset, coordinates.getY() + offset, 0,0);
//            ft.add(boardFrame.getId(), pieceFragment);
//        }
//        ft.commit();
//    }
//

}