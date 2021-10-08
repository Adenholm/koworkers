package com.example.koworkers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.example.koworkers.model.Colour;
import com.example.koworkers.model.Hive;
import com.example.koworkers.model.PlayerHand;
import com.example.koworkers.model.Point;
import com.example.koworkers.model.pieces.IPiece;
import com.example.koworkers.model.pieces.PieceFactory;
import com.example.koworkers.model.pieces.Queen;

import org.junit.Test;

/**
 * test of the Hive class
 *
 * @author Hanna Adenholm
 */
public class testHive {

    private Hive initTestBoard(){
        Hive hive = new Hive();
        hive.selectPiece(hive.getCurrentPlayerHandPieces().get(1));
        hive.movePiece(new Point(0,0));
        hive.selectPiece(hive.getCurrentPlayerHandPieces().get(1));
        hive.movePiece(new Point(1,-1));
        hive.selectPiece(hive.getCurrentPlayerHandPieces().get(1));
        return hive;
    }

    @Test
    public void testSelectPiece(){
        Hive hive = new Hive();
        assertFalse(hive.aPieceIsSelected());
        hive.selectPiece(hive.getCurrentPlayerHandPieces().get(1));
        assertTrue(hive.aPieceIsSelected());
    }

    @Test
    public void testSelectPieceWrongColour(){
        Hive hive = new Hive();
        assertFalse(hive.aPieceIsSelected());
        hive.selectPiece(PieceFactory.createNewAnt(Colour.BLACK));
        assertFalse(hive.aPieceIsSelected());
    }

    @Test
    public void testSelectPieceWhenQueenHasToBePlayed(){
        Hive hive = initTestBoard();

        //play another round so the queen needs to be placed
        hive.selectPiece(hive.getCurrentPlayerHandPieces().get(1));
        hive.movePiece(new Point(0,0));
        hive.selectPiece(hive.getCurrentPlayerHandPieces().get(1));
        hive.movePiece(new Point(1,-1));

        assertFalse(hive.aPieceIsSelected());
        hive.selectPiece(hive.getCurrentPlayerHandPieces().get(1)); //try selecting another piece that is not the queen
        assertFalse(hive.aPieceIsSelected());

        hive.selectPiece(hive.getCurrentPlayerHandPieces().get(0)); //try selecting the queen
        assertTrue(hive.aPieceIsSelected());
    }

    @Test
    public void testPlayersQueenShouldBePlaced(){
        Hive hive = initTestBoard();

        assertFalse(hive.playersQueenShouldBePlaced()); // false since its only round 2

        //play another round
        hive.selectPiece(hive.getCurrentPlayerHandPieces().get(1));
        hive.movePiece(new Point(0,0));
        hive.selectPiece(hive.getCurrentPlayerHandPieces().get(1));
        hive.movePiece(new Point(1,-1));

        assertTrue(hive.playersQueenShouldBePlaced()); //true since its round 3
    }

    @Test
    public void testPlayersQueenShouldBePlacedWhenQueenIsPlaced(){
        Hive hive = initTestBoard();

        assertFalse(hive.playersQueenShouldBePlaced()); // false since its only round 2

        //play another round
        hive.selectPiece(hive.getCurrentPlayerHandPieces().get(0));
        hive.movePiece(new Point(0,0));
        hive.selectPiece(hive.getCurrentPlayerHandPieces().get(0));
        hive.movePiece(new Point(1,-1));

        assertFalse(hive.playersQueenShouldBePlaced()); //false since queens are already placed
    }

    @Test
    public void testDeSelectPiece(){
        Hive hive = new Hive();

        hive.selectPiece(PieceFactory.createNewAnt(Colour.WHITE));
        assertTrue(hive.aPieceIsSelected());

        hive.deSelectPiece();
        assertFalse(hive.aPieceIsSelected());
    }

    @Test
    public void testMovePieceFromHand(){
        Hive hive = new Hive();

        IPiece pieceToBePlaced = hive.getCurrentPlayerHandPieces().get(0);
        Point pointToPlacePiece = new Point(0,0);

        hive.selectPiece(pieceToBePlaced);
        hive.movePiece(new Point(0,0));

        assertTrue(pointToPlacePiece.equals(hive.getPoint(pieceToBePlaced)));

        //Do another turn in order to get the original player back
        hive.selectPiece(PieceFactory.createNewBeetle(Colour.BLACK));
        hive.movePiece(new Point(0,0));

        //Make sure the placed piece isn't still in the hand
        assertFalse(hive.getCurrentPlayerHandPieces().contains(pieceToBePlaced));
    }


}