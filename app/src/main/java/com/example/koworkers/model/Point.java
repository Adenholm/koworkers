package com.example.koworkers.model;

/**
 * A point. has a x-coordinate and a y-coordinate.
 * @Author Qwinth
 */
public class Point {
    private int x;
    private int y;

    public Point(){
        this(0,0);
    }

    public Point(int x, int y){
        this.x=x;
        this.y=y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean equals(Point point){
        return point.x==this.x && point.y==this.y;
    }
}
