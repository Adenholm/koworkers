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
 * The viewmodel for the board view
 *
 * @author Hanna Adenholm
 * @author Lisa Qwinth
 */
public class BoardViewModel extends ViewModel implements Isubscriber {

    private Hive hive;

    private final int PIECE_SIZE = 90;          // size of piece in dp
    private final int RADIUS = PIECE_SIZE/2;

    private MutableLiveData<List<IPiece>> piecesOnBoard = new MutableLiveData<>();
    private MutableLiveData<Boolean> pieceIsSelected = new MutableLiveData<>();
    private MutableLiveData<String> currentPlayer = new MutableLiveData<>();

    public void init(Hive hive){
        this.hive = hive;
        piecesOnBoard.postValue(new ArrayList<>());
        pieceIsSelected.postValue(false);
        currentPlayer.postValue("White");
    }

    public LiveData<List<IPiece>> getPiecesOnBoard(){
        return piecesOnBoard;
    }

    public LiveData<Boolean> getPieceIsSelected() {
        return pieceIsSelected;
    }

    public LiveData<String> getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Returns the calculated coordinates for placement on a view based on the hexagonsystems.
     *
     * @param point the point to be converted into a position on the view
     * @return the calculated coordinate of the position on the view
     */
    public Point getCoordinates(Point point){
        Point coordinate = new Point();

        coordinate.setX(point.getX()*2* RADIUS); //När x ökar flyttar viewcoordinate 2r i x-led...
        coordinate.setY(point.getX()* RADIUS);//...och r i y-led
        coordinate.setY(coordinate.getY()+point.getY()*2* RADIUS); //När y ändras flyttas viewcoordinate enbart i y-led

        return coordinate;
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

    public void handleBoardClick(){
        hive.deSelectPiece();
    }

    @Override
    public void pieceWasSelected(IPiece piece) {
        pieceIsSelected.postValue(true);
    }

    @Override
    public void pieceWasDeselected() {
        pieceIsSelected.postValue(false);
    }

    @Override
    public void pieceWasMoved(IPiece piece) {
        List<IPiece> updatedList = piecesOnBoard.getValue();
        if(updatedList.contains(piece)){
            updatedList.remove(piece);
        }
        updatedList.add(piece);
        piecesOnBoard.postValue(updatedList);
    }

    @Override
    public void playerWasChanged(Colour colour) {
        currentPlayer.postValue(colour.toString());
    }

    @Override
    public void gameWasRestarted() {
        piecesOnBoard.postValue(new ArrayList<>());
        pieceIsSelected.postValue(false);
        currentPlayer.postValue("White");
    }
}