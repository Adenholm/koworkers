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
 * A simple {@link Fragment} subclass.
 * Use the {@link PauseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PauseFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private Button restartButton;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    /**
     * constructor
     */
    public PauseFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Pause.
     */
    // TODO: Rename and change types and number of parameters
    public static PauseFragment newInstance(String param1, String param2) {
        PauseFragment fragment = new PauseFragment();
        return fragment;
    }


    private PauseViewModel mViewModel;



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment

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
        mViewModel = new ViewModelProvider(this).get(PauseViewModel.class);
        restartButton =getView().findViewById(R.id.btn_restart);
        ImageView imageView = getView().findViewById(R.id.closePauseButton);
        imageView.setOnClickListener(pauseListener);
        Button restartButton =getView().findViewById(R.id.btn_restart);
        restartButton.setOnClickListener(restartListener);
    }

}