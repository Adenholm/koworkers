package com.example.koworkers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.example.koworkers.model.Colour;
import com.example.koworkers.model.Hive;
import com.example.koworkers.model.IWinSubscriber;
import com.example.koworkers.model.Isubscriber;
import com.example.koworkers.model.Point;
import com.example.koworkers.model.pieces.IPiece;
import com.example.koworkers.model.pieces.PieceFactory;

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
        //hive.subscribe(new testSubscriber(hive));

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
        IPiece lastPiece = hive.getCurrentPlayerHandPieces().get(0);
        hive.selectPiece(lastPiece);
        hive.movePiece(new Point(-i-1,0));

        //Make sure its still black turns since white cant make a move
        assertEquals(Colour.BLACK, hive.getCurrentPlayerColour());

        hive.selectPiece(lastPiece);
        hive.movePiece(new Point(i+1, 0));

        //Make sure white can play again when black moved it's piece
        assertEquals(Colour.WHITE, hive.getCurrentPlayerColour());
    }

    @Test
    public void testRestart(){
        Hive hive = initTestBoard();

        hive.restart();

        assertEquals(11, hive.getCurrentPlayerHandPieces().size());
        assertEquals(Colour.WHITE, hive.getCurrentPlayerHandPieces().get(0).getColour());
    }

    @Test
    public void testSubscriber(){
        Hive hive = new Hive();
        hive.subscribe(new testSubscriber(hive));

        hive.selectPiece(hive.getCurrentPlayerHandPieces().get(0));
        hive.movePiece(hive.getPossibleMoves().get(0));

        hive.selectPiece(hive.getCurrentPlayerHandPieces().get(1));
        hive.movePiece(hive.getPossibleMoves().get(0));

        hive.restart();
    }

    class testSubscriber implements Isubscriber{
        private final Hive hive;

        public testSubscriber(Hive hive){
            this.hive = hive;
        }

        @Override
        public void update(){
            System.out.println("Model Updated!");
            System.out.println("Current player is " + hive.getCurrentPlayerColour().toString());
            if(hive.aPieceIsSelected()){
                System.out.println("The selected Piece is: " + hive.getSelectedPiece().getColour().toString() + " " + hive.getSelectedPiece().getName());
            }else{
                System.out. println("There is no selected piece");
            }
            System.out.println("Pieces on board:");
            for(IPiece piece: hive.getPiecesOnBoard()){
                System.out.println(piece.getColour().toString() + " " + piece.getName() + ": " + hive.getPoint(piece).getX() + ", " + hive.getPoint(piece).getY());
            }
            System.out.println();
        }
    }

    @Test
    public void testWinConditionBlackWins(){
        Hive hive = initTestBoard();
        hive.subscribeWin(new testBlackWinsSubscriber());

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

    class testBlackWinsSubscriber implements IWinSubscriber {
        @Override
        public void playerWon(Colour winningColour) {
            System.out.println(winningColour.toString() + " won!");
            assertEquals(winningColour, Colour.BLACK);
        }
    }

    @Test
    public void testWinConditionWhiteWins(){
        Hive hive = initTestBoard();
        hive.subscribeWin(new testWhiteWinsSubscriber());

        hive.selectPiece(hive.getCurrentPlayerHandPieces().get(0));
        hive.movePiece(new Point(0,1));

        //place black queen in spot 0,-1
        hive.selectPiece(hive.getCurrentPlayerHandPieces().get(0));
        hive.movePiece(new Point(0, -1));

        //surround the black queen with other pieces
        hive.selectPiece(hive.getCurrentPlayerHandPieces().get(0));
        hive.movePiece(new Point(-1,0));

        hive.selectPiece(hive.getCurrentPlayerHandPieces().get(0));
        hive.movePiece(new Point(-1,-1));

        hive.selectPiece(hive.getCurrentPlayerHandPieces().get(0));
        hive.movePiece(new Point(0,-2));

        hive.selectPiece(hive.getCurrentPlayerHandPieces().get(0));
        hive.movePiece(new Point(1,-2));
    }

    class testWhiteWinsSubscriber implements IWinSubscriber {
        @Override
        public void playerWon(Colour winningColour) {
            System.out.println(winningColour.toString() + " won!");
            assertEquals(winningColour, Colour.WHITE);
        }
    }
}