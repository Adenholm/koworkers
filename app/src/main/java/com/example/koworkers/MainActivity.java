package com.example.koworkers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.koworkers.model.PlayerHand;
import com.example.koworkers.ui.playerhand.PlayerhandFragment;

public class MainActivity extends AppCompatActivity {

    private LinearLayout linearLayout;      //playerhandlistan

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       /* if (savedInstanceState == null){


            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.playerHand, PlayerhandFragment.class, null)
                    .commit();
        }*/
        setContentView(R.layout.activity_main);
        setContentView(R.layout.piece_fragment);




        //linearLayout=findViewById(R.id.handLinearLayout);
    }
}