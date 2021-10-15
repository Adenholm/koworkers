package com.example.koworkers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.example.koworkers.model.Colour;
import com.example.koworkers.model.Hive;
import com.example.koworkers.model.Isubscriber;
import com.example.koworkers.model.PlayerHand;
import com.example.koworkers.model.Point;
import com.example.koworkers.model.pieces.IPiece;
import com.example.koworkers.model.pieces.PieceFactory;
import com.example.koworkers.model.pieces.Queen;

import org.junit.Test;

import java.util.ArrayList;

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

        assertTrue(hive.playersQueenShouldBePlaced()); //true since its round 3 and queen isn't placed
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
        hive.movePiece(new Point(-1,1));

        //Make sure the placed piece isn't still in the hand
        assertFalse(hive.getCurrentPlayerHandPieces().contains(pieceToBePlaced));
    }

    @Test
    public void testMovePieceOnBoard(){
        Hive hive = initTestBoard();

        IPiece pieceToMove = hive.getCurrentPlayerHandPieces().get(1);
        hive.selectPiece(pieceToMove);
        hive.movePiece(new Point(0,1));
        //Do another turn in order to get the original player back
        hive.selectPiece(PieceFactory.createNewBeetle(Colour.BLACK));
        hive.movePiece(new Point(2,-2));

        Point pointToMoveTo = new Point(3,-3);
        hive.selectPiece(pieceToMove);
        hive.movePiece(pointToMoveTo);

        //assertTrue(hive.getPoint(pieceToMove).equals(pointToMoveTo));
    }

    @Test
    public void testPossibleMovesForPieceInHand(){
        Hive hive = new Hive();

        hive.selectPiece(hive.getCurrentPlayerHandPieces().get(1));
        ArrayList<Point> possibleMoves = hive.getPossibleMoves();
        assertEquals(1, possibleMoves.size());
        hive.movePiece(possibleMoves.get(0));

        hive.selectPiece(hive.getCurrentPlayerHandPieces().get(1));
        possibleMoves = hive.getPossibleMoves();
        assertEquals(6, possibleMoves.size());
        hive.movePiece(possibleMoves.get(0));

        hive.selectPiece(hive.getCurrentPlayerHandPieces().get(1));
        possibleMoves = hive.getPossibleMoves();
        assertEquals(3, possibleMoves.size());
    }

    @Test
    public void testPossibleMovesForPieceOnBoard(){
        Hive hive = initTestBoard();

        IPiece pieceToGetPossibleMoves = hive.getCurrentPlayerHandPieces().get(0);
        hive.selectPiece(pieceToGetPossibleMoves);
        hive.movePiece(new Point(-1,1));

        //Do another turn in order to get the original player back
        hive.selectPiece(PieceFactory.createNewBeetle(Colour.BLACK));
        hive.movePiece(new Point(2,-2));

        hive.selectPiece(pieceToGetPossibleMoves);
        ArrayList<Point> possibleMoves = hive.getPossibleMoves();
        assertEquals(2, possibleMoves.size());
    }

    @Test
    public void testWhenPlayerCantMove(){
        Hive hive = new Hive();
        //hive.subscribe(new testSubscriber());

        IPiece whitePiece = hive.getCurrentPlayerHandPieces().get(0);

        //place first piece in 0,0
        hive.selectPiece(whitePiece);
        hive.movePiece(new Point());

        //place all but the last pieces in a line
        int max = hive.getCurrentPlayerHandPieces().size()-1;
        int i;
        for (i = 0; i < max; i++) {
            hive.selectPiece(hive.getCurrentPlayerHandPieces().get(0));
            hive.movePiece(new Point(i+1, 0));
            hive.selectPiece(hive.getCurrentPlayerHandPieces().get(0));
            hive.movePiece(new Point(-i-1, 0));
        }

        //place the last piece at the furthest end of the white side of the line to make white unable to make move
        hive.selectPiece(hive.getCurrentPlayerHandPieces().get(0));
        hive.movePiece(new Point(-i-1,0));

        //try selecting a white piece to see if it is still blacks turn
        hive.selectPiece(whitePiece);
        assertFalse(hive.aPieceIsSelected());
    }

    @Test
    public void testWinConditionBlackWins(){
        Hive hive = initTestBoard();
        hive.subscribe(new testSubscriber());

        //place white queen in spot 0,-1
        hive.selectPiece(hive.getCurrentPlayerHandPieces().get(0));
        hive.movePiece(new Point(0, -1));

        //surround the white queen with other pieces
        hive.selectPiece(hive.getCurrentPlayerHandPieces().get(0));
        hive.movePiece(new Point(-1,0));

        hive.selectPiece(hive.getCurrentPlayerHandPieces().get(0));
        hive.movePiece(new Point(-1,-1));

        hive.selectPiece(hive.getCurrentPlayerHandPieces().get(0));
        hive.movePiece(new Point(0,-2));

        hive.selectPiece(hive.getCurrentPlayerHandPieces().get(0));
        hive.movePiece(new Point(1,-2));
    }

    @Test
    public void testSubscriber(){
        Hive hive = new Hive();
        hive.subscribe(new testSubscriber());

        hive.selectPiece(hive.getCurrentPlayerHandPieces().get(0));
        hive.movePiece(hive.getPossibleMoves().get(0));

        hive.selectPiece(hive.getCurrentPlayerHandPieces().get(1));
        hive.movePiece(hive.getPossibleMoves().get(0));
    }

    class testSubscriber implements Isubscriber{

        @Override
        public void pieceWasSelected(IPiece piece) {
            System.out.println(piece.getColour().toString() + " " + piece.getName() + " was selected");
        }

        @Override
        public void pieceWasDeselected() {
            System.out.println("Piece was deselected");
        }

        @Override
        public void pieceWasMoved(IPiece piece, Point point) {
            System.out.println("Piece: " + piece.getColour().toString() + " " + piece.getName() + " was moved to point " + point.getX() + ", " + point.getY());
        }

        @Override
        public void playerWasChanged(Colour colour) {
            System.out.println("Player was changed to " + colour.toString());
        }

        @Override
        public void playerWon(Colour winningColour) {
            System.out.println(winningColour.toString() + " won!");
            assertEquals(winningColour, Colour.BLACK);
        }
    }
}