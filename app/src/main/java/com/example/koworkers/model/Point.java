package com.example.koworkers.model;

/**
 * An immutable point. Has a x-coordinate and a y-coordinate.
 * @Author  Lisa Qwinth
 */
public class Point {
    private int x;
    private int y;

    /**
     * constructor. sets x to 0 and y to 0
     */
    public Point(){
        this(0,0);
    }

    /**
     * constructor
     * @param x sets x to provided x
     * @param y sets y to provided y
     */
    public Point(int x, int y){
        this.x=x;
        this.y=y;
    }

    /**
     * return the x-coordinate
     * @return the x-coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * return the y-coordinate
     * @return the y-coordinate
     */
    public int getY() {
        return y;
    }


    /**
     * Checks if point are equal to another point
     * @param point provided point to which the point is compared with
     * @return return true if provided point has the same x and y as the point
     */
    @Override
    public boolean equals(Object point){
        if(point instanceof Point){
            return ((Point) point).getX()==this.x && ((Point) point).getY()==this.y;
        }
        return false;
    }
}
