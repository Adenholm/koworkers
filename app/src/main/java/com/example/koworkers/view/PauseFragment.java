package com.example.koworkers.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.koworkers.MainActivity;
import com.example.koworkers.R;
import com.example.koworkers.viewmodel.PauseViewModel;


/**
 * @author Lisa Qwinth
 */
public class PauseFragment extends Fragment {

    private PauseViewModel mViewModel;

    private Button restartButton;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pause, container, false);
    }

    /**
     * sets pausePopup to gone
     */
    public void closePausePopup(){
        ((MainActivity)getActivity()).hidePausePopup();
    }
    private final View.OnClickListener restartListener= new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            restartGame(view);
        }
    };

    /**
     * restarts the game when the restart-button is clicked
     * @param view
     */
    public void restartGame(View view) {
        mViewModel.restart();
        closePausePopup();
    }
    private  final View.OnClickListener pauseListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            ((MainActivity)getActivity()).hidePausePopup();
        }
    };

    /**
     * when created, adds listeners
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(getActivity()).get(PauseViewModel.class);
        restartButton =getView().findViewById(R.id.btn_restart);
        ImageView imageView = getView().findViewById(R.id.closePauseButton);
        imageView.setOnClickListener(pauseListener);
        Button restartButton =getView().findViewById(R.id.btn_restart);
        restartButton.setOnClickListener(restartListener);
    }

}