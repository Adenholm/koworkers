package com.example.koworkers.model;

/**
 * An interface for classes that wants to get notified if a player wins
 *
 * @author Hanna Adenholm
 */
public interface IWinSubscriber {
    /**
     * Runs if a player wins.
     *
     * @param winningColour the colour of the winning player.
     */
    void playerWon(Colour winningColour);
}
