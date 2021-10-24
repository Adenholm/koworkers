package com.example.koworkers;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.koworkers.model.Colour;
import com.example.koworkers.model.Hive;
import com.example.koworkers.model.IWinSubscriber;
import com.example.koworkers.view.BoardFragment;
import com.example.koworkers.view.PlayerhandFragment;
import com.example.koworkers.view.WinScreenFragment;
import com.example.koworkers.viewmodel.BoardViewModel;
import com.example.koworkers.viewmodel.PauseViewModel;
import com.example.koworkers.viewmodel.PlayerhandViewModel;
import com.example.koworkers.viewmodel.WinScreenViewModel;

public class MainActivity extends AppCompatActivity implements IWinSubscriber {

    private final Hive hive = new Hive();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){
        hive.subscribeWin(this);

        //initiate boardViewModel
        BoardViewModel boardViewModel = new ViewModelProvider(this).get(BoardViewModel.class);
        boardViewModel.init(hive);
        hive.subscribe(boardViewModel);

        //initiate PlayerHandViewModel
        PlayerhandViewModel playerhandViewModel = new ViewModelProvider(this).get(PlayerhandViewModel.class);
        playerhandViewModel.init(hive);
        hive.subscribe(playerhandViewModel);

        //initiate WinScreenViewModel
        WinScreenViewModel winScreenViewModel = new ViewModelProvider(this).get(WinScreenViewModel.class);
        winScreenViewModel.init(hive);

        //initiate PauseViewModel
        PauseViewModel pauseViewModel = new ViewModelProvider(this).get(PauseViewModel.class);
        pauseViewModel.init(hive);

        //Add a BordFragment and PlayerhandFragment to the activity
        BoardFragment boardFragment = new BoardFragment();
        PlayerhandFragment playerhandFragment = new PlayerhandFragment();

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

    public void hideWinPopup(){
        FragmentContainerView winScreenContainer = findViewById(R.id.winScreenContainer);
        winScreenContainer.setVisibility(View.GONE);
    }

    public void showPausePopup(View v){
        FragmentContainerView pauseFragmentContainer = findViewById(R.id.pauseFragmentContainer);
        pauseFragmentContainer.setVisibility(View.VISIBLE);
    }
    public void hidePausePopup(){
        FragmentContainerView pauseFragmentContainer = findViewById(R.id.pauseFragmentContainer);
        pauseFragmentContainer.setVisibility(View.GONE);
    }

    @Override
    public void playerWon(Colour winningColour) {
        showWinPopup(winningColour.toString());
    }
}