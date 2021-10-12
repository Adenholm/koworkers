package com.example.koworkers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.example.koworkers.model.Hive;
import com.example.koworkers.model.Isubscriber;
import com.example.koworkers.view.BoardFragment;
import com.example.koworkers.view.PlayerhandFragment;
import com.example.koworkers.view.WinScreenFragment;

public class MainActivity extends AppCompatActivity {

    private final Hive hive = new Hive();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){

        BoardFragment boardFragment = new BoardFragment();
        FrameLayout boardFrame = findViewById(R.id.boardFrame);
        hive.subscribe(boardFragment);

        PlayerhandFragment playerhandFragment = new PlayerhandFragment();
        FrameLayout playerhandFrame = findViewById(R.id.playerhandFrame);
        hive.subscribe(playerhandFragment);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.boardFrame, boardFragment);
        ft.add(R.id.playerhandFrame, playerhandFragment);
        ft.commit();

    }

    public void showWinPopup(String winner){
        FragmentContainerView winScreenContainer = findViewById(R.id.winScreenContainer);
        winScreenContainer.setVisibility(View.VISIBLE);
        ((WinScreenFragment)winScreenContainer.getFragment()).setWinnerText(winner);
    }
}