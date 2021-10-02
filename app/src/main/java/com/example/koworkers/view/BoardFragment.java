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
import androidx.lifecycle.ViewModelProvider;

import com.example.koworkers.R;
import com.example.koworkers.model.Isubscriber;
import com.example.koworkers.model.Point;
import com.example.koworkers.model.pieces.IPiece;


import java.util.HashMap;
import java.util.Map;

import com.example.koworkers.viewmodel.BoardViewModel;

public class BoardFragment extends Fragment implements Isubscriber {

    private BoardViewModel mViewModel;

    private FrameLayout boardFrame;

    private final Map<IPiece, ImageView> pieceMap = new HashMap<>();
    private final Map<View, IPiece> imageMap = new HashMap<>();

    private final Map<View, Point> possibleMovesMap = new HashMap<>();

    private final int OFFSET = 500;
    private final int dpiRatio = 2;

    private final View.OnClickListener possibleMovesListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mViewModel.movePiece(possibleMovesMap.get(v));
        }
    };

    private final View.OnClickListener pieceListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mViewModel.selectPiece(imageMap.get(v));
        }
    };

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

        //dpiRatio = (int) getContext().getResources().getDisplayMetrics().density;

        update();
    }

    @Override
    public void update() {
        boardFrame.removeAllViews();
        displayBoardPieces();
        if(mViewModel.aPieceIsSelected()){
            displayPossibleMoves();
        }
    }

    private void displayBoardPieces(){
        ImageView image;
        for(IPiece piece: mViewModel.getPiecesOnBoard()){
            if(pieceMap.containsKey(piece)){
                image = pieceMap.get(piece);
            }else{
                image = new ImageView(getContext());
                image.setImageResource(piece.getImageResource());
                image.setOnClickListener(pieceListener);
                pieceMap.put(piece,image);
                imageMap.put(image, piece);
            }
            setLayout(image, mViewModel.getCoordinates(piece).getX() + OFFSET, mViewModel.getCoordinates(piece).getY() + OFFSET, OFFSET, OFFSET, mViewModel.getPieceSize());
            boardFrame.addView(image);
        }
    }

    private void displayPossibleMoves(){
        possibleMovesMap.clear();
        for(Point point: mViewModel.getPossibleMoves()){
            ImageView image = new ImageView(getContext());
            image.setImageResource(R.drawable.possible_move_hexagon);
            image.setOnClickListener(possibleMovesListener);
            setLayout(image, mViewModel.getCoordinates(point).getX() + OFFSET, mViewModel.getCoordinates(point).getY() + OFFSET, OFFSET, OFFSET, mViewModel.getPieceSize());
            possibleMovesMap.put(image, point);
            boardFrame.addView(image);
        }
    }


    public void setLayout(View view, int left, int top, int right, int bottom, int size){
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(size * dpiRatio, size * dpiRatio);
        params.setMargins(left*dpiRatio, top*dpiRatio, right*dpiRatio, bottom*dpiRatio);
        view.setLayoutParams(params);
    }

}