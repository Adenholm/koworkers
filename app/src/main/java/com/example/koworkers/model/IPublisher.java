package com.example.koworkers.model;

import java.util.ArrayList;

public interface IPublisher { //ev onödigt med interface
    //en lista m subscribers
    void subscribe ();
    void notifySubscribers();
    //eventuellt en unsubscribe oxå men för tillfället finns det ingen väg tillbaka :)
}
