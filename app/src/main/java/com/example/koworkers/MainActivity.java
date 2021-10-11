package com.example.koworkers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.koworkers.model.Hive;
import com.example.koworkers.model.Isubscriber;
import com.example.koworkers.view.WinScreenFragment;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){

        FragmentContainerView boardFragmentContainerView = findViewById(R.id.boardFragmentContainerView);
        Isubscriber boardFragment = boardFragmentContainerView.getFragment();
        Hive.getInstance().subscribe(boardFragment);

        FragmentContainerView playerHandFragmentContainerView = findViewById(R.id.playerHandFragmentContainerView);
        Isubscriber playerHandFragment = playerHandFragmentContainerView.getFragment();
        Hive.getInstance().subscribe(playerHandFragment);
    }

    public void showWinPopup(String winner){
        FragmentContainerView winScreenContainer = findViewById(R.id.winScreenContainer);
        winScreenContainer.setVisibility(View.VISIBLE);
        ((WinScreenFragment)winScreenContainer.getFragment()).setWinnerText(winner);
    }
}