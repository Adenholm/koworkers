package com.example.koworkers.model;

/**
 * The colour of the players and pieces
 * @author Hanna Adenholm
 */
public enum Colour {
    /**
     * The colour white
     */
    WHITE{
        @Override
        public String toString() {
            return "White";
        }
    },
    /**
     * The colour black
     */
    BLACK{
        @Override
        public String toString() {
            return "Black";
        }
    }
}
