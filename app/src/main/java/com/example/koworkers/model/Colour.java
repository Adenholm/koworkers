package com.example.koworkers.model;

/**
 * The colour of the players and pieces
 */
public enum Colour {
    WHITE{
        @Override
        public String toString() {
            return "White";
        }
    },
    BLACK{
        @Override
        public String toString() {
            return "Black";
        }
    }
}
