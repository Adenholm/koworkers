package com.example.koworkers.model;

/**
 * A interface with publisher-methods. Will be used to notify other classes without too much dependencies
 * @Author Qwinth
 */
public interface IPublisher {
    void subscribe(Isubscriber isubscriber);

    void notifySubscribers();
}
