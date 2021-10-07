package com.example.koworkers.viewmodel;

import static java.lang.Math.sqrt;

import androidx.lifecycle.ViewModel;

import com.example.koworkers.model.Hive;
import com.example.koworkers.model.IPublisher;
import com.example.koworkers.model.Isubscriber;
import com.example.koworkers.model.Point;
import com.example.koworkers.model.pieces.IPiece;

import java.util.ArrayList;

/**
 * The viewmodel for the board view
 *
 * @author Hanna Adenholm
 * @author Lisa Qwinth
 */
public class BoardViewModel extends ViewModel{

    private final Hive hive = Hive.getInstance();

    private final int PIECE_SIZE = 90;          // size of piece in dpi
    private final int RADIE = PIECE_SIZE/2;


    private  final int r=60; //från hexagonens mitt till hörn

    /*Metod som tar koordinat från hexagon-griden och placerar ut på skärmen. Punkten som tas fram är mitten av hexagonen och läggs sedan till i viewCoordinates*/
    public void placement(IPiece piece, Point point){
        Point viewCoordinate=new Point(0,0);
        double side=(2*r)/sqrt(3); // hexagonens sida, samband med r
        viewCoordinate.setX(point.getX()*r); //När x ökar flyttar viewcoordinate r i x-led...
        viewCoordinate.setY(point.getX()*2*r);//...och 2r i y-led
        viewCoordinate.setY(viewCoordinate.getY()+point.getY()*2*r); //När y ändras flyytas viewcoordinate enbart i y-led

        /* GAMMALT HEXAGONSYSTEM, kvar ifall att
        int y_offset=2*r;
        double x_offset=r+side;
        double xy_offset=r+(side/2); //offset i x-led vid ojämnt i hives y-led
        double yx_offset=r; //offset i y-led vid ojämnt i hives x-led
        viewCoordinate.setY(y_offset*point.getY());
        if (point.getX()%2==1){
            viewCoordinate.setX((point.getX()-1)*(int)x_offset+(int)xy_offset);
            viewCoordinate.setY(viewCoordinate.getY()+r);
        }
        else {
            viewCoordinate.setX(point.getX()*(int)x_offset);
        }



        viewCoordinates.put(piece, viewCoordinate);
        notifySubscribers();
        */
    }

    /**
     * Returns the calculated coordinates for placement on a view based on the hexagonsystems.
     *
     * @param point the point to be converted into a position on the view
     * @return the calculated coordinate of the position on the view
     */
    public Point getCoordinates(Point point){
        Point coordinate = new Point();

        coordinate.setX(point.getX()*2*RADIE); //När x ökar flyttar viewcoordinate 2r i x-led...
        coordinate.setY(point.getX()*RADIE);//...och r i y-led
        coordinate.setY(coordinate.getY()+point.getY()*2*RADIE); //När y ändras flyttas viewcoordinate enbart i y-led

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
    public void movePiece(Point point){
        hive.movePiece(point);
    }

    /**
     * Selects the provided piece and returns true if succeed.
     *
     * @param piece Piece to be selected.
     * @return True if the piece was able to be selected.
     */
    public boolean selectPiece(IPiece piece){
        return hive.selectPiece(piece);
    }

    /**
     * Runs the Deselect method in the Model.
     */
    public void deSelectPiece(){
        hive.deSelectPiece();
    }
}