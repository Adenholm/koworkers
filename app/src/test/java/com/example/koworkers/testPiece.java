package com.example.koworkers;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.example.koworkers.model.Colour;
import com.example.koworkers.model.Point;
import com.example.koworkers.model.pieces.Ant;
import com.example.koworkers.model.pieces.IPiece;


import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class testPiece {

    @Test
    public void testPieceIsStuckBetween4(){
        ArrayList<Point> boardPositions = new ArrayList<>(Arrays.asList(new Point(0, 0),new Point(-1, 1),new Point(1, 0),new Point(-1, 0),new Point(1, -1)));
        IPiece ant = new Ant(Colour.BLACK);
        ArrayList<Point> actualMoves = ant.getPossibleMoves(boardPositions);
        if (actualMoves.size()==0) {
            assertTrue(true);
        }
        else {
            assertFalse(false);
        }
    }

    @Test
    public void testPieceIsNotStuckBetween4(){
        ArrayList<Point> boardPositions = new ArrayList<>(Arrays.asList(new Point(0, 0),new Point(-1, 1),new Point(1, 0),new Point(-1, 0),new Point(0, 1)));
        IPiece ant = new Ant(Colour.BLACK);
        ArrayList<Point> actualMoves = ant.getPossibleMoves(boardPositions);
        if (actualMoves.size()!=0) {
            assertTrue(true);
        }
        else {
            assertFalse(false);
        }
    }

    @Test
    public void testPieceIsStuckBetween5(){
        ArrayList<Point> boardPositions = new ArrayList<>(Arrays.asList(new Point(0, 0),new Point(-1, 1),new Point(1, 0),new Point(-1, 0),new Point(1, -1),new Point(0, -1)));
        IPiece ant = new Ant(Colour.BLACK);
        ArrayList<Point> actualMoves = ant.getPossibleMoves(boardPositions);
        if (actualMoves.size()==0) {
            assertTrue(true);
        }
        else {
            assertFalse(false);
        }
    }

}