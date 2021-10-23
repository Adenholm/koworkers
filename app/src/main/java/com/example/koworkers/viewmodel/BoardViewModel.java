package com.example.koworkers.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.koworkers.model.Colour;
import com.example.koworkers.model.Hive;
import com.example.koworkers.model.Isubscriber;
import com.example.koworkers.model.Point;
import com.example.koworkers.model.pieces.IPiece;

import java.util.ArrayList;
import java.util.List;

/**
 * The viewModel for the board view
 *
 * Implements the Isubscriber interface to be able to observe changes from the model. Contains LiveData that the view can observe.
 *
 * @author Hanna Adenholm
 * @author Lisa Qwinth
 */
public class BoardViewModel extends ViewModel implements Isubscriber {

    private Hive hive;

    private final MutableLiveData<List<IPiece>> piecesOnBoard = new MutableLiveData<>();
    private final MutableLiveData<Boolean> pieceIsSelected = new MutableLiveData<>();
    private final MutableLiveData<String> currentPlayer = new MutableLiveData<>();

    private final int PIECE_SIZE = 80;          // size of piece in dp
    private final int RADIUS = PIECE_SIZE/2;

    /**
     * Sets the hive and Initializes the liveData
     * @param hive the instance of hive to get data from
     */
    public void init(Hive hive){
        this.hive = hive;
        piecesOnBoard.postValue(new ArrayList<>());
        pieceIsSelected.postValue(false);
        currentPlayer.postValue("White");
    }

    /**
     * Returns the LiveData of a list with the pieces on the board
     * @return LiveData with list of pieces on board
     */
    public LiveData<List<IPiece>> getPiecesOnBoard(){
        return piecesOnBoard;
    }

    /**
     * Returns LiveData of a boolean that says whether a piece is selected
     * @return LiveData of a boolean that says whether a piece is selected
     */
    public LiveData<Boolean> getPieceIsSelected() {
        return pieceIsSelected;
    }

    /**
     * Returns LiveData of String containing the current player
     * @return String of currentPlayer
     */
    public LiveData<String> getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Returns the calculated coordinates for placement on a view based on the hexagon systems.
     *
     * @param point the point to be converted into a position on the view
     * @return the calculated coordinate of the position on the view
     */
    public Point getCoordinates(Point point){
        int x, y;

        x = point.getX()*2* RADIUS;
        y = point.getX()* RADIUS;
        y = y + point.getY()*2* RADIUS;

        return new Point(x,y);
    }

    /**
     * Returns the position on the board that the provided piece has.
     *
     * @param piece The piece you want position of.
     * @return Position of the provided piece.
     */
    public Point getPoint(IPiece piece){ return hive.getPoint(piece);}

    /**
     * Returns a list with points of possible moves for the currently selected piece.
     *
     * @return List of possible moves for selected piece
     */
    public ArrayList<Point> getPossibleMoves(){
        return hive.getPossibleMoves();
    }

    /**
     * Returns the size of the piece in dpi.
     * @return Size of piece.
     */
    public int getPieceSize() {
        return PIECE_SIZE;
    }

    /**
     * Moves the selected piece to the provided point in Hive.
     *
     * @param point Point where the selected piece is to be moved.
     */
    public void handlePossibleMovesClick(Point point){
        hive.movePiece(point);
    }

    /**
     * Selects the provided piece and returns true if succeed.
     *
     * @param piece Piece to be selected.
     * @return True if the piece was able to be selected.
     */
    public boolean handlePieceClick(IPiece piece){
        return hive.selectPiece(piece);
    }

    /**
     * Handles the boardClick by running the deselect method in the model
     */
    public void handleBoardClick(){
        hive.deSelectPiece();
    }

    @Override
    public void update(){
        pieceIsSelected.setValue(hive.aPieceIsSelected());
        piecesOnBoard.setValue(hive.getPiecesOnBoard());
        currentPlayer.setValue((hive.getCurrentPlayerColour()).toString());
    }
}