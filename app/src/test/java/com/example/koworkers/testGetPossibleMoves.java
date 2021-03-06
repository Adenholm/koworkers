package com.example.koworkers;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.example.koworkers.model.Colour;
import com.example.koworkers.model.Point;
import com.example.koworkers.model.pieces.IPiece;
import com.example.koworkers.model.pieces.PieceFactory;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class testGetPossibleMoves {

    @Test
    public void getSurroundingCoordinates_isCorrect(){
        IPiece ant = PieceFactory.createNewAnt(Colour.BLACK);
        ArrayList<Point> surroundingCoordinates = ant.getSurroundingCoordinates(new Point(1,-1));
        ArrayList<Point> predictedCoordinates = new ArrayList<>(Arrays.asList(new Point[]{new Point(0,0),new Point(0,-1),new Point(1,0),new Point(1,-2),new Point(2,-1),new Point(2,-2)}));
        for(int i =0;i<surroundingCoordinates.size();i++){
            System.out.println("Point"+i);
            System.out.println(surroundingCoordinates.get(i).getX());
            System.out.println(surroundingCoordinates.get(i).getY());
        }
        if (surroundingCoordinates.containsAll(predictedCoordinates) && predictedCoordinates.containsAll(surroundingCoordinates) && (surroundingCoordinates.size() == predictedCoordinates.size())) {
            assertTrue(true);
        } else {
            assertFalse(false);
        }
    }


    @Test
    public void beetlePossibleMoves_isCorrect() {
        ArrayList<Point> boardPositions = new ArrayList<>(Arrays.asList(new Point(0, 0), new Point(1, -1)));
        IPiece beetle = PieceFactory.createNewBeetle(Colour.BLACK);
        ArrayList<Point> predictedMoves = new ArrayList<>(Arrays.asList(new Point[]{new Point(1, 0), new Point(1, -1), new Point(0, -1)}));
        ArrayList<Point> actualMoves = beetle.getPossibleMoves(boardPositions);
        if (actualMoves.containsAll(predictedMoves) && predictedMoves.containsAll(actualMoves) && (actualMoves.size() == predictedMoves.size())) {
            assertTrue(true);
        } else {
            assertFalse(false);
        }
    }

    @Test
    public void beetleIsStuckPossibleMoves_isCorrect(){
        ArrayList<Point> boardPositions = new ArrayList<>(Arrays.asList(new Point(0, 0),new Point(-1, 1),new Point(1, 0),new Point(-1, 0),new Point(1, -1),new Point(0, -1)));
        IPiece beetle = PieceFactory.createNewBeetle(Colour.BLACK);
        ArrayList<Point> actualMoves = beetle.getPossibleMoves(boardPositions);
        ArrayList<Point> predictedMoves = new ArrayList<>(Arrays.asList(new Point(-1, 1),new Point(1, 0),new Point(-1, 0),new Point(1, -1),new Point(0, -1)));
        if (actualMoves.containsAll(predictedMoves) && predictedMoves.containsAll(actualMoves) && (actualMoves.size() == predictedMoves.size())) {
            assertTrue(true);
        } else {
            assertFalse(false);
        }
    }

   @Test
    public void beetleIsStackedPossibleMoves_isCorrect(){
        ArrayList<Point> boardPositions = new ArrayList<>(Arrays.asList(new Point(0, 0),new Point(0, 0)));
        IPiece beetle = PieceFactory.createNewBeetle(Colour.BLACK);
        ArrayList<Point> actualMoves = beetle.getPossibleMoves(boardPositions);
        ArrayList<Point> predictedMoves = new ArrayList<>(Arrays.asList(new Point(-1, 1),new Point(1, 0),new Point(-1, 0),new Point(1, -1),new Point(0, -1),new Point(0, 1)));
        if (actualMoves.containsAll(predictedMoves) && predictedMoves.containsAll(actualMoves) && (actualMoves.size() == predictedMoves.size())) {
            assertTrue(true);
        } else {
            assertFalse(false);
        }
    }


    @Test
    public void grassHopperMoves_isCorrect(){
        ArrayList<Point> boardPositions = new ArrayList<>(Arrays.asList(new Point(0,0), new Point(1,0),new Point(2,0)));
        IPiece grasshopper = PieceFactory.createNewGrasshopper(Colour.BLACK);
        ArrayList<Point> predictedMoves = new ArrayList<>(Arrays.asList(new Point[]{new Point(3,0)}));
        ArrayList<Point> actualMoves = grasshopper.getPossibleMoves(boardPositions);
        if (actualMoves.containsAll(predictedMoves) && predictedMoves.containsAll(actualMoves) && (actualMoves.size() == predictedMoves.size())) {
            assertTrue(true);
        } else {
            assertFalse(false);
        }
    }

    @Test
    public void antPossibleMoves_isCorrect(){
        ArrayList<Point> boardPositions = new ArrayList<>(Arrays.asList(new Point(0,0),new Point(1,-1)));
        IPiece ant = PieceFactory.createNewAnt(Colour.BLACK);
        ArrayList<Point> predictedMoves = new ArrayList<>(Arrays.asList(new Point[]{new Point(1,0),new Point(2,-1),new Point(2,-2),new Point(1,-2),new Point(0,-1)}));
        ArrayList<Point> actualMoves = ant.getPossibleMoves(boardPositions);
        for(int i =0;i<ant.getPossibleMoves(boardPositions).size();i++){
            System.out.println("Point"+i);
            System.out.println(ant.getPossibleMoves(boardPositions).get(i).getX());
            System.out.println(ant.getPossibleMoves(boardPositions).get(i).getY());
        }
        for(int i =0;i<predictedMoves.size();i++){
            System.out.println("Point"+i);
            System.out.println(predictedMoves.get(i).getX());
            System.out.println(predictedMoves.get(i).getY());
        }

        if(actualMoves.containsAll(predictedMoves) && predictedMoves.containsAll(actualMoves) && (actualMoves.size()==predictedMoves.size())){
            assertTrue(true);
        }
        else{
            assertFalse(false);
        }


    }

    @Test
    public void queenPossibleMoves_isCorrect(){
        ArrayList<Point> boardPositions = new ArrayList<>(Arrays.asList(new Point(0, 0), new Point(1, -1)));
        IPiece queen = PieceFactory.createNewQueen(Colour.BLACK);
        ArrayList<Point> predictedMoves = new ArrayList<>(Arrays.asList(new Point[]{new Point(1, 0),new Point(0, -1)}));
        ArrayList<Point> actualMoves = queen.getPossibleMoves(boardPositions);
        if (actualMoves.containsAll(predictedMoves) && predictedMoves.containsAll(actualMoves) && (actualMoves.size() == predictedMoves.size())) {
            assertTrue(true);
        } else {
            assertFalse(false);
        }
    }

    @Test
    public void spiderPossibleMoves_isCorrect(){
        ArrayList<Point> boardPositions = new ArrayList<>(Arrays.asList(new Point(0, 0), new Point(1, -1),new Point(2,-2)));
        IPiece spider = PieceFactory.createNewSpider(Colour.BLACK);
        ArrayList<Point> predictedMoves = new ArrayList<>(Arrays.asList(new Point[]{new Point(2, -3),new Point(2, -2)}));
        ArrayList<Point> actualMoves = spider.getPossibleMoves(boardPositions);
        if (actualMoves.containsAll(predictedMoves) && predictedMoves.containsAll(actualMoves) && (actualMoves.size() == predictedMoves.size())) {
            assertTrue(true);
        } else {
            assertFalse(false);
        }
    }

}