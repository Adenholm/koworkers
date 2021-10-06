package com.example.koworkers.view;

import android.content.res.Resources;
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
import com.example.koworkers.model.Colour;
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

    private ImageView selectImage;

    private int topOffset = 200;
    private int leftOffset = 150;
    private final int dpiRatio = (int) Resources.getSystem().getDisplayMetrics().density;

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
            if(mViewModel.aPieceIsSelected()){
                selectImage.setVisibility(View.VISIBLE);
                selectImage.setLayoutParams(v.getLayoutParams());
            }
        }
    };

    private final View.OnClickListener boardClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mViewModel.deSelectPiece();
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
        boardFrame.setOnClickListener(boardClick);

        selectImage = new ImageView(getContext());
        selectImage.setImageResource(R.drawable.possible_move_hexagon); //TODO change picture
    }


    @Override
    public void selectPiece(IPiece piece) {
        displayPossibleMoves();
    }

    @Override
    public void deselectPiece(IPiece piece) {
        removePossibleMoves();
        selectImage.setVisibility(View.GONE);
   }

    @Override
    public void movePiece(IPiece piece, Point point){
        displayBoardPiece(piece, point);
    }

    @Override
    public void switchPlayer(Colour colour) {

    }

    private void displayBoardPiece(IPiece piece, Point point){
        ImageView image;
        if(pieceMap.containsKey(piece)){
            image = pieceMap.get(piece);
            setLayout(image, point);
        }else{
            image = new ImageView(getContext());
            image.setImageResource(piece.getImageResource());
            image.setOnClickListener(pieceListener);
            pieceMap.put(piece,image);
            imageMap.put(image, piece);
            setLayout(image, point);
            boardFrame.addView(image);
        }
    }

    private void displayPossibleMoves(){
        possibleMovesMap.clear();
        for(Point point: mViewModel.getPossibleMoves()){
            ImageView image = new ImageView(getContext());
            image.setImageResource(R.drawable.possible_move_hexagon);
            image.setOnClickListener(possibleMovesListener);
            setLayout(image, point);
            possibleMovesMap.put(image, point);
            boardFrame.addView(image);
        }
    }

    private void removePossibleMoves(){
        for(View image: possibleMovesMap.keySet()){
            boardFrame.removeView(image);
        }
    }

    private void setLayout(View view, Point point){
        Point coordinate = mViewModel.getCoordinates(point);

        if(coordinate.getX() + leftOffset - 100 < 0 ){
            leftOffset += 200;
            updateView();
        }
        if(coordinate.getY() + topOffset - 100 < 0 ){
            topOffset += 100;
            updateView();
        }

        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(mViewModel.getPieceSize() * dpiRatio, mViewModel.getPieceSize() * dpiRatio);
        params.setMargins((coordinate.getX() + leftOffset)*dpiRatio, (coordinate.getY() + topOffset)*dpiRatio, 0, 0);
        view.setLayoutParams(params);
    }

    private void updateView(){
        for(View image: imageMap.keySet()){
            setLayout(image, mViewModel.getPoint(imageMap.get(image)));
        }
        for(View image: possibleMovesMap.keySet()){
            setLayout(image, possibleMovesMap.get(image));
        }
    }

}