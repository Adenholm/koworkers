package com.example.koworkers.model;

/**
 * A interface with subsciber-methods. Will be used to make classes able to listen to a publisher-class.
 * @author Qwinth, Adenholm
 */
public interface Isubscriber {
    /**
     * Runs if the model was updated
     */
    void update();
}

