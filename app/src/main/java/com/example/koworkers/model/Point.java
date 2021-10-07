package com.example.koworkers.model;

/**
 * A point. Has a x-coordinate and a y-coordinate.
 * @Author Qwinth
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
     * to get x
     * @return the x-coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * to get y
     * @return the y-coordinate
     */
    public int getY() {
        return y;
    }

    /**
     * sets x to the provided x
     * @param x the provided x which x is set to
     */
    public void setX(int x) {
        this.x = x;
    }
    /**
     * sets y to the provided y
     * @param y the provided y which y is set to
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Checks if point are equal to another point
     * @param point provided point to which the point is compared with
     * @return return true if provided point has the same x and y as the point
     */
    public boolean equals(Point point){
        return point.x==this.x && point.y==this.y;
    }
}
