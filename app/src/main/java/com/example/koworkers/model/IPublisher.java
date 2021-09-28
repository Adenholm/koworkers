package com.example.koworkers.model;

public interface IPublisher { //ev onödigt med interface
    //en lista m subscribers

    void subscribe(Isubscriber isubscriber);

    void notifySubscribers();
    //eventuellt en unsubscribe oxå men för tillfället finns det ingen väg tillbaka :)
}
