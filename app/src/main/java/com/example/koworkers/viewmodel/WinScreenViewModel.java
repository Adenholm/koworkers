package com.example.koworkers.viewmodel;

import androidx.lifecycle.ViewModel;

import com.example.koworkers.model.Hive;

public class WinScreenViewModel extends ViewModel {
    private Hive hive;

    public void init(Hive hive){
        this.hive = hive;
    }

    public void handleRestartButtonClick(){
        hive.restart();
    }
}
