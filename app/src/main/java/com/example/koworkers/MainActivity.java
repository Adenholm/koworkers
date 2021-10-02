package com.example.koworkers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainerView;

import android.os.Bundle;

import com.example.koworkers.model.Hive;
import com.example.koworkers.model.Isubscriber;

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
}