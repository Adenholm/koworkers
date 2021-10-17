package com.example.koworkers;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainerView;

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
    public void showPausePopup(View v){
        FragmentContainerView pauseFragmentContainer = findViewById(R.id.pauseFragmentContainer);
        pauseFragmentContainer.setVisibility(View.VISIBLE);
    }
    public void hidePausePopup(){
        FragmentContainerView pauseFragmentContainer = findViewById(R.id.pauseFragmentContainer);
        pauseFragmentContainer.setVisibility(View.GONE);
    }

}