package com.example.koworkers;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.koworkers.model.Hive;
import com.example.koworkers.model.Isubscriber;
import com.example.koworkers.view.BoardFragment;
import com.example.koworkers.view.PlayerhandFragment;
import com.example.koworkers.view.WinScreenFragment;
import com.example.koworkers.viewmodel.BoardViewModel;
import com.example.koworkers.viewmodel.PlayerhandViewModel;

public class MainActivity extends AppCompatActivity {

    private final Hive hive = new Hive();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){
        BoardViewModel boardViewModel = new ViewModelProvider(this).get(BoardViewModel.class);
        boardViewModel.setHive(hive);
        PlayerhandViewModel playerhandViewModel = new ViewModelProvider(this).get(PlayerhandViewModel.class);
        playerhandViewModel.setHive(hive);

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
    public void showPausePopup(View v){
        FragmentContainerView pauseFragmentContainer = findViewById(R.id.pauseFragmentContainer);
        pauseFragmentContainer.setVisibility(View.VISIBLE);
    }
    public void hidePausePopup(){
        FragmentContainerView pauseFragmentContainer = findViewById(R.id.pauseFragmentContainer);
        pauseFragmentContainer.setVisibility(View.GONE);
    }

}