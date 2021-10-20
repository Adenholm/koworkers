package com.example.koworkers.view;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.koworkers.MainActivity;
import com.example.koworkers.R;
import com.example.koworkers.viewmodel.WinScreenViewModel;

public class WinScreenFragment extends Fragment {

    private WinScreenViewModel mViewModel;

    public static WinScreenFragment newInstance() {
        return new WinScreenFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.win_screen_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(getActivity()).get(WinScreenViewModel.class);

        Button newGameButton = getView().findViewById(R.id.newGameButton);
        newGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.handleNewGameButtonClick();
                ((MainActivity)getActivity()).hideWinPopup();
            }
        });
    }

    public void setWinnerText(String winner){
        TextView winnerText = getView().findViewById(R.id.winnerColourText);
        winnerText.setText(winner);
    }
}