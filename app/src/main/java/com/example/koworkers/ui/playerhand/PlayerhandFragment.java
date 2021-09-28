package com.example.koworkers.ui.playerhand;

import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.koworkers.R;
import com.example.koworkers.model.pieces.IPiece;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PlayerhandFragment extends Fragment {

    //private final Map<String, PieceStackFragment> pieceStackMap = new HashMap<>(); //TODO
    private LinearLayout handLinearLayout;

    private final ArrayList<PieceStackFragment> pieceStacksInHand = new ArrayList<>();

    private PlayerhandViewModel mViewModel;


    public static PlayerhandFragment newInstance() {
        return new PlayerhandFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.playerhand_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(PlayerhandViewModel.class);

        handLinearLayout = getView().findViewById(R.id.handLinearLayout);

        populateHand();
    }

    public void update() {
        populateHand();
    }

    /**
     * creates stacks of pieces and adds to the linear layout
     */
    private void populateHand() {
        ArrayList<PieceStackFragment> pieceStacks = new ArrayList<>();
        boolean stackAlreadyExists = false;
        for (IPiece piece : mViewModel.getPieces()) {
            for (PieceStackFragment pieceStack : pieceStacks) {
                if (pieceStack.getPiece().getImageResource() == piece.getImageResource()) {
                    pieceStack.incNumberOfPieces();
                    stackAlreadyExists = true;
                    break;
                }
            }
            if (!stackAlreadyExists) {
                pieceStacks.add(PieceStackFragment.newInstance(piece));
            }
            stackAlreadyExists = false;
        }
        addToLinearLayout(pieceStacks);
    }

    /**
     * adds the provided list of PieceStackFragments to the Linear Layout and removes the prevoius list
     *
     * @param pieceStacks to be added to Linear Layout
     */
    private void addToLinearLayout(ArrayList<PieceStackFragment> pieceStacks) {
        FragmentTransaction ft = getParentFragmentManager().beginTransaction();
        for (PieceStackFragment pieceStack : pieceStacksInHand) {
            ft.remove(pieceStack);
        }
        for (PieceStackFragment pieceStack : pieceStacks) {
            ft.add(R.id.handLinearLayout, pieceStack);
        }
        ft.commit();

        pieceStacksInHand.clear();
        pieceStacksInHand.addAll(pieceStacks);
    }

}