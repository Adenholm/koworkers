package com.example.koworkers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.example.koworkers.model.Colour;
import com.example.koworkers.model.PlayerHand;
import com.example.koworkers.model.Point;
import com.example.koworkers.model.pieces.Ant;
import com.example.koworkers.model.pieces.Beetle;
import com.example.koworkers.model.pieces.Grasshopper;
import com.example.koworkers.model.pieces.IPiece;
import com.example.koworkers.model.pieces.PieceFactory;
import com.example.koworkers.model.pieces.Queen;
import com.example.koworkers.model.pieces.Spider;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * test of the playerHand class
 *
 * @author Hanna Adenholm
 */
public class testPlayerHand {

    @Test
    public void testQueenHasBeenPayed(){
        PlayerHand playerHand = new PlayerHand(Colour.WHITE);
        assertFalse(playerHand.queenHasBeenPlayed());
        playerHand.removePiece(playerHand.getPieces().get(0));
        assertTrue(playerHand.queenHasBeenPlayed());
    }

    @Test
    public void testRemovePiece(){
        PlayerHand playerHand = new PlayerHand(Colour.WHITE);
        int numberOfPieces = playerHand.getPieces().size();
        IPiece pieceToBeRemoved = playerHand.getPieces().get(0);

        playerHand.removePiece(pieceToBeRemoved);

        assertEquals(numberOfPieces - 1, playerHand.getPieces().size());
        assertFalse(playerHand.getPieces().contains(pieceToBeRemoved));

        playerHand.removePiece(playerHand.getPieces().get(1));
        assertEquals(numberOfPieces - 2, playerHand.getPieces().size());
    }

    @Test
    public void testGetPieces(){
        PlayerHand playerHand = new PlayerHand(Colour.WHITE);
        assertTrue(playerHand.getPieces().get(0) instanceof Queen);
    }

    @Test
    public void testThisIsMyQueen(){
        PlayerHand playerHand = new PlayerHand(Colour.WHITE);
        assertTrue(playerHand.thisIsMyQueen(playerHand.getPieces().get(0)));
        assertFalse(playerHand.thisIsMyQueen(playerHand.getPieces().get(1)));
        assertFalse(playerHand.thisIsMyQueen(PieceFactory.createNewQueen(Colour.BLACK)));
    }
}