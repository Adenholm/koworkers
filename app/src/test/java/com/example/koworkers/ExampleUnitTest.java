package com.example.koworkers;

import org.junit.Test;

import static org.junit.Assert.*;



import com.example.koworkers.model.Colour;
import com.example.koworkers.model.Point;
import com.example.koworkers.model.pieces.Ant;
import com.example.koworkers.model.pieces.Piece;

import java.util.ArrayList;
import java.util.Arrays;


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void antMoves_isCorrect(){
        Piece ant = new Ant(Colour.BLACK);
        ArrayList<Point> board = new ArrayList<>(Arrays.asList(new Point(0,0),new Point(0,1),new Point(0,2)));
        Point[] pointArray = new Point[] {new Point(-1,1),new Point(0,2),new Point(1,1),new Point(0,3),new Point(1,3),new Point(2,2)};
        System.out.print(ant.getPossibleMoves(board).toArray());
        assertArrayEquals(pointArray,ant.getPossibleMoves(board).toArray());
    }
}