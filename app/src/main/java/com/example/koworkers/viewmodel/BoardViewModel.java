package com.example.koworkers.viewmodel;

import static java.lang.Math.sqrt;

import androidx.lifecycle.ViewModel;

import com.example.koworkers.model.IPublisher;
import com.example.koworkers.model.Isubscriber;
import com.example.koworkers.model.Point;
import com.example.koworkers.model.pieces.Piece;

import java.util.ArrayList;
import java.util.HashMap;

public class BoardViewModel extends ViewModel implements Isubscriber, IPublisher {
    // TODO: Implement the ViewModel
    ArrayList<Isubscriber> subscribers=new ArrayList<>();
    //BVM notifierar boardfragment
    @Override
    public void subscribe(Isubscriber isubscriber) {
        subscribers.add(isubscriber);
    }
    @Override
    public void notifySubscribers() {
        for (Isubscriber subscriber:subscribers) {
            subscriber.update();
        }
    }
    //BVM notifieras av board
    @Override
    public void update() {
        //placement();
    }


    public HashMap<Piece, Point> viewCoordinates = new HashMap<>();



    private  final int r=15; //från hexagonens mitt till hörn

    /*Metod som tar koordinat från hexagon-griden och placerar ut på skärmen. Punkten som tas fram är mitten av hexagonen och läggs sedan till i viewCoordinates*/
    public void placement(Piece piece, Point point){
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
         */


        viewCoordinates.put(piece, viewCoordinate);
        notifySubscribers();

    }

}