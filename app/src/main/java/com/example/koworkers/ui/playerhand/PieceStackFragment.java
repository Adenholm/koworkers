package com.example.koworkers.ui.playerhand;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.koworkers.R;
import com.example.koworkers.model.Colour;
import com.example.koworkers.model.pieces.Piece;
import com.example.koworkers.viewmodel.PieceStackViewModel;

public class PieceStackFragment extends Fragment {

    private PieceStackViewModel mViewModel;

    private ImageView hexagonImage;
    private ImageView insectImage;
    private TextView numberText;


    private final Piece piece;
    private int numberOfPieces;

    public PieceStackFragment(Piece piece, int numberOfPieces){
        this.piece = piece;
        this.numberOfPieces = numberOfPieces;
    }

    /*public static PieceStackFragment newInstance() {
        return new PieceStackFragment();
    }*/

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.piece_stack_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(PieceStackViewModel.class);

        hexagonImage = getView().findViewById(R.id.hexagonImage);
        insectImage = getView().findViewById(R.id.insectImage);
        numberText = getView().findViewById(R.id.numberText);

        if(piece.getColour().equals(Colour.WHITE)){
            hexagonImage.setImageResource(R.drawable.white_hexagon);
        }else{
            hexagonImage.setImageResource(R.drawable.white_hexagon); //TODO ändra till svart hexagon
        }

        //insectImage.setImageResource(piece.getImage());
        numberText.setText(numberOfPieces);
    }

}