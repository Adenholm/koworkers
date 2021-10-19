package com.example.koworkers.viewmodel;
import androidx.lifecycle.ViewModel;

import com.example.koworkers.model.Hive;

/**
 *
 */
public class PauseViewModel extends ViewModel {
    private Hive hive;

    public void init(Hive hive){
        this.hive = hive;
    }

    public void restart(){
        hive.restart();
    }
}
