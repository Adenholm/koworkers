package com.example.koworkers.view;

import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.koworkers.R;
import com.example.koworkers.model.Colour;
import com.example.koworkers.model.Point;
import com.example.koworkers.model.pieces.IPiece;
import com.example.koworkers.viewmodel.BoardViewModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The view that represents the board.
 *
 * @author Hanna Adenholm
 */
public class BoardFragment extends Fragment{

    private BoardViewModel mViewModel;

    private FrameLayout boardFrame;
    private HorizontalScrollView hScrollView;
    private ScrollView scrollview;
    private TextView playerTextView;

    private final Map<IPiece, ImageView> pieceMap = new HashMap<>();    //map of pieces and images
    private final Map<View, IPiece> imageMap = new HashMap<>();         //inverse pieceMap instead of a biMap

    private final Map<View, Point> possibleMovesMap = new HashMap<>();  //a Map for the possible moves images

    private ImageView selectImage;                                      // the image that shows which piece is selected

    private int topOffset = 500;                                        //the offset that the pieces have from the top of the board
    private int leftOffset = 400;                                       // the offset that the pieces have from the left of the board
    private final int dpiRatio = (int) Resources.getSystem().getDisplayMetrics().density; //the ratio to convert from pixels to dpi for the program to be portable to other devices
    private Boolean firstImage = true;

    private final View.OnClickListener possibleMovesListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mViewModel.handlePossibleMovesClick(possibleMovesMap.get(v));
        }
    };

    private final View.OnClickListener pieceListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(mViewModel.handlePieceClick(imageMap.get(v))){
                setLayout(selectImage, mViewModel.getPoint(imageMap.get(v)));
                boardFrame.addView(selectImage);
            }
        }
    };

    private final View.OnClickListener boardClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mViewModel.handleBoardClick();
        }
    };


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.board_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(getActivity()).get(BoardViewModel.class);

        boardFrame = getView().findViewById(R.id.boardFrame);
        boardFrame.setOnClickListener(boardClick);

        hScrollView = getView().findViewById(R.id.hScrollView);
        scrollview = getView().findViewById(R.id.scrollView);

        playerTextView = getView().findViewById(R.id.playerTextView);
        playerTextView.setText(Colour.WHITE.toString());
        playerTextView.setTextColor(getResources().getColor(R.color.white));
        playerTextView.setTextSize(30);
        playerTextView.setBackgroundColor(getResources().getColor(R.color.orangeBrown));
        playerTextView.setPadding(10,10,10,10);
        playerTextView.setBackgroundResource(R.drawable.rounded_corners);

        selectImage = new ImageView(getContext());
        selectImage.setImageResource(R.drawable.select_hexagon);

        mViewModel.getPieceIsSelected().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean pieceIsSelected) {
                if(pieceIsSelected){
                    displayPossibleMoves();
                }else{
                    removePossibleMoves();
                    boardFrame.removeView(selectImage);
                }
            }
        });

        mViewModel.getCurrentPlayer().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                playerTextView.setText(s);
                if(s.equals("White")){
                    playerTextView.setTextColor(getResources().getColor(R.color.white));
                }else{
                    playerTextView.setTextColor(getResources().getColor(R.color.black));
                }
            }
        });

        mViewModel.getPiecesOnBoard().observe(getViewLifecycleOwner(), new Observer<List<IPiece>>() {
            @Override
            public void onChanged(List<IPiece> pieces) {
                displayBoardPieces(pieces);
            }
        });
    }

    private void displayBoardPieces(List<IPiece> pieces){
        for(View v: imageMap.keySet()){
            boardFrame.removeView(v);
        }
        for(IPiece piece: pieces){
            displayBoardPiece(piece, mViewModel.getPoint(piece));
        }
    }

    private void displayBoardPiece(IPiece piece, Point point){
        ImageView image;
        if(pieceMap.containsKey(piece)){
            image = pieceMap.get(piece);
        }else{
            image = new ImageView(getContext());
            setPieceImage(image, piece);
            image.setOnClickListener(pieceListener);
            pieceMap.put(piece,image);
            imageMap.put(image, piece);
        }
        setLayout(image, point);
        boardFrame.addView(image);
        image.bringToFront();
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
        if(firstImage) {
            hScrollView.scrollTo((leftOffset + 100) * dpiRatio, 0);
            firstImage = false;
        }
    }

    private void removePossibleMoves(){
        for(View image: possibleMovesMap.keySet()){
            boardFrame.removeView(image);
        }
    }

    private void setLayout(View view, Point point){
        Point coordinate = mViewModel.getCoordinates(point);

        if(coordinate.getX() + leftOffset - 200 * dpiRatio < 0 ){
            leftOffset = leftOffset + 200;
            updateView();
            hScrollView.scrollTo(leftOffset -100, 0);
        }
        if(coordinate.getY() + topOffset - 200 * dpiRatio < 0 ){
            topOffset += 100;
            updateView();
            scrollview.scrollTo(0, topOffset - 400);
        }

        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(mViewModel.getPieceSize() * dpiRatio, mViewModel.getPieceSize() * dpiRatio);
        params.setMargins((coordinate.getX() + leftOffset)*dpiRatio, (coordinate.getY() + topOffset)*dpiRatio, 300 * dpiRatio, 400 * dpiRatio);
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

    private void setPieceImage(ImageView image, IPiece piece){
        String pkgName = getContext().getPackageName();
        if(piece.getColour() == Colour.WHITE){
            Uri path = Uri.parse("android.resource://"+pkgName+"/drawable/" + piece.getName() + "_piece");
            image.setImageURI(path);
        }else{
            Uri path = Uri.parse("android.resource://"+pkgName+"/drawable/black_" + piece.getName() + "_piece");
            image.setImageURI(path);
        }
    }
}