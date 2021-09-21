package com.example.koworkers.ui.piece;

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

public class PieceFragment extends superPieceFragment {


    public PieceFragment(Piece piece) {
        super(piece);
    }


}