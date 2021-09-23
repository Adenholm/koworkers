package com.example.koworkers.ui.board;

import static java.lang.Math.sqrt;

import com.example.koworkers.model.IPublisher;
import com.example.koworkers.model.Isubscriber;
import com.example.koworkers.model.Point;

import androidx.lifecycle.ViewModel;

import com.example.koworkers.model.pieces.Piece;

import java.util.ArrayList;
import java.util.HashMap;

public class BoardViewModel extends ViewModel implements Isubscriber {
    // TODO: Implement the ViewModel


    @Override
    public void update() {
        //TODO:
    }

    public void notifySubscriber() {

    }

    HashMap<Piece, Point> viewCoordinates = new HashMap<>();

    private  final int r=15; //från hexagonens mitt till hörn

    /*Metod som tar koordinat från hexagon-griden och placerar ut på skärmen. Punkten som tas fram är mitten av hexagonen och läggs sedan till i viewCoordinates*/
    public void placement(Piece piece, Point point){
        Point viewCoordinate=new Point(0,0);
        double side=(2*r)/sqrt(3); // hexagonens sida, samband med r
        int y_offset=2*r;
        double x_offset=2*r+side;
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
        notifySubscriber();

    }

}