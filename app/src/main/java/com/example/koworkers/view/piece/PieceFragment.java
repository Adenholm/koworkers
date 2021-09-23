package com.example.koworkers.view.piece;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.koworkers.R;
import com.example.koworkers.model.Colour;
import com.example.koworkers.model.pieces.Piece;
import com.example.koworkers.viewmodel.PieceViewModel;

public class PieceFragment extends Fragment {
    private PieceViewModel mViewModel;

    private ImageView hexagonImage;
    private ImageView insectImage;
    private final Piece piece;

    public PieceFragment(Piece piece){
        this.piece = piece;
    }


   /* public static PieceFragment newInstance() {
        return new PieceFragment();
    }*/

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.piece_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(PieceViewModel.class);

        hexagonImage = getView().findViewById(R.id.hexagonImage);
        insectImage = getView().findViewById(R.id.insectImage);

        if(piece.getColour().equals(Colour.WHITE)){                     //
            hexagonImage.setImageResource(R.drawable.white_hexagon);
        } else{
            hexagonImage.setImageResource(R.drawable.black_hexagon);
        }

        /*if(piece.getInsect().equals(Insect.ant)){
            insectImage.setImageResource(R.drawable.ant);
        }
        if(piece.getInsect().equals(Insect.spider)){
            insectImage.setImageResource(R.drawable.spider);
        }
        if(piece.getInsect().equals(Insect.queen)){
            insectImage.setImageResource(R.drawable.queen);
        }
        if(piece.getInsect().equals(Insect.beetle)){
            insectImage.setImageResource(R.drawable.beetle);

        if(piece.getInsect().equals(Insect.grasshopper)){
            insectImage.setImageResource(R.drawable.grasshopper);
        }*/



    }


}