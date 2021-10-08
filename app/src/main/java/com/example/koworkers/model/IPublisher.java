package com.example.koworkers.model;

/**
 * A interface with publisher-methods. Will be used to notify other classes without too much dependencies
 * @Author Qwinth
 */
public interface IPublisher {
    /**
     * add a subscriber to the subsriberlist
     * @param isubscriber the subscriber to be added
     */
    void subscribe(Isubscriber isubscriber);

    void notifySubscribers();
}
