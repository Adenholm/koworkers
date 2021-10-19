package com.example.koworkers.viewmodel;
import androidx.lifecycle.ViewModel;

import com.example.koworkers.model.Hive;

/**
 *
 */
public class PauseViewModel extends ViewModel {
    private final Hive hive = Hive.getInstance();
    public void restart(){
        hive.restart();
    }
}
