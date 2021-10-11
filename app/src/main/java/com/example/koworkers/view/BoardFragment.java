package com.example.koworkers.view;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.koworkers.R;
import com.example.koworkers.model.Colour;
import com.example.koworkers.model.Isubscriber;
import com.example.koworkers.model.Point;
import com.example.koworkers.model.pieces.IPiece;
import com.example.koworkers.viewmodel.BoardViewModel;

import java.util.HashMap;
import java.util.Map;

/**
 * The view that represents the board.
 *
 * @author Hanna Adenholm
 */
public class BoardFragment extends Fragment implements Isubscriber {

    private BoardViewModel mViewModel;

    private FrameLayout boardFrame;
    private TextView playerTextView;

    private final Map<IPiece, ImageView> pieceMap = new HashMap<>();    //map of pieces and images
    private final Map<View, IPiece> imageMap = new HashMap<>();         //inverse pieceMap instead of a biMap

    private final Map<View, Point> possibleMovesMap = new HashMap<>();  //a Map for the possible moves images

    private ImageView selectImage;                                      // the image that shows which piece is selected

    private int topOffset = 400;                                        //the offset that the pieces have from the top of the board
    private int leftOffset = 150;                                       // the offset that the pieces have from the left of the board
    private final int dpiRatio = (int) Resources.getSystem().getDisplayMetrics().density; //the ratio to convert from pixels to dpi for the program to be portable to other devices

    private final View.OnClickListener possibleMovesListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mViewModel.movePiece(possibleMovesMap.get(v));
        }
    };

    private final View.OnClickListener pieceListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(mViewModel.selectPiece(imageMap.get(v))){
                setLayout(selectImage, mViewModel.getPoint(imageMap.get(v)));
                boardFrame.addView(selectImage);
            }
        }
    };

    private final View.OnClickListener boardClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mViewModel.deSelectPiece();
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
        mViewModel = new ViewModelProvider(this).get(BoardViewModel.class);

        boardFrame = getView().findViewById(R.id.boardFrame);
        boardFrame.setOnClickListener(boardClick);

        playerTextView = getView().findViewById(R.id.playerTextView);
        playerTextView.setText(Colour.WHITE.toString());
        playerTextView.setTextColor(getResources().getColor(R.color.white));
        playerTextView.setTextSize(20);
        playerTextView.setBackgroundColor(getResources().getColor(R.color.orangeBrown));
        playerTextView.setBackgroundResource(R.drawable.rounded_corners);

        selectImage = new ImageView(getContext());
        selectImage.setImageResource(R.drawable.select_hexagon);
    }

    /**
     * Displays the possible moves for the selected piece.
     *
     * @param piece The selected piece
     */
    @Override
    public void pieceWasSelected(IPiece piece) {
        displayPossibleMoves();
    }

    /**
     * Removes the possible moves and removes the selection.
     */
    @Override
    public void pieceWasDeselected() {
        removePossibleMoves();
        boardFrame.removeView(selectImage);
   }

    /**
     * Moves the provided piece on the board to the provided point.
     *
     * @param piece The piece to be moved.
     * @param point The point where the piece should be moved.
     */
    @Override
    public void pieceWasMoved(IPiece piece, Point point){
        displayBoardPiece(piece, point);
    }

    /**
     * Changes the playerTextView to show which players turn it is.
     *
     * @param colour The colour of the current player.
     */
    @Override
    public void playerWasChanged(Colour colour) {
        playerTextView.setText(colour.toString());

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
    }

    private void removePossibleMoves(){
        for(View image: possibleMovesMap.keySet()){
            boardFrame.removeView(image);
        }
    }

    private void setLayout(View view, Point point){
        Point coordinate = mViewModel.getCoordinates(point);

        if(coordinate.getX() + leftOffset - 200 < 0 ){
            leftOffset += 200;
            updateView();
        }
        if(coordinate.getY() + topOffset - 200 < 0 ){
            topOffset += 100;
            updateView();
        }

        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(mViewModel.getPieceSize() * dpiRatio, mViewModel.getPieceSize() * dpiRatio);
        params.setMargins((coordinate.getX() + leftOffset)*dpiRatio, (coordinate.getY() + topOffset)*dpiRatio, 100, 400);
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