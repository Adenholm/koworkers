package com.example.koworkers.ui.board;

import static java.lang.Math.sqrt;

import android.graphics.Point;

import androidx.lifecycle.ViewModel;

public class BoardViewModel extends ViewModel {
    // TODO: Implement the ViewModel

    private  final int r=15; //från hexagonens mitt till hörn


    private Point placement(Point hiveCoordinate){
        Point p=new Point(0,0);
        double side=(2*r)/sqrt(3); // hexagonens sida, samband med r
        int y_offset=2*r;
        double x_offset=2*r+side;
        double xy_offset=r+(side/2); //offset i x-led vid ojämnt i hives y-led
        double yx_offset=r; //offset i y-led vid ojämnt i hives x-led
        p.y=y_offset*hiveCoordinate.y;
        if (hiveCoordinate.x%2==1){
            p.x=(hiveCoordinate.x-1)*(int)x_offset+(int)xy_offset;
            p.y=p.y+r;
        }
        else {
            p.x=hiveCoordinate.x*(int)x_offset;
        }
        return p;
    }

}