package com.example.koworkers.view;

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
import android.widget.TextView;

import com.example.koworkers.R;
import com.example.koworkers.model.pieces.IPiece;
import com.example.koworkers.viewmodel.PlayerhandViewModel;

import java.util.ArrayList;

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

//        FragmentTransaction ft = getParentFragmentManager().beginTransaction();
//        PieceStackFragment pieceFragment = PieceStackFragment.newInstance(mViewModel.getPieces().get(0));
//        ft.add(R.id.handLinearLayout, pieceFragment);
//        ft.commit();

//        FragmentTransaction ft = getParentFragmentManager().beginTransaction();
//        PieceFragment pieceFragment = PieceFragment.newInstance(mViewModel.getPieces().get(0));
//        ft.add(R.id.handLinearLayout, pieceFragment);
//        ft.commit();

        TextView textView1 = new TextView(getContext());
        textView1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        textView1.setText("TextView");
        textView1.setBackgroundColor(0xff66ff66); // hex color 0xAARRGGBB
        textView1.setPadding(20, 20, 20, 20);// in pixels (left, top, right, bottom)
        handLinearLayout.addView(textView1);

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
        int i=0;
        for (PieceStackFragment pieceStack : pieceStacks) {
            ft.add(R.id.handLinearLayout, pieceStack, ""+ i);
            i++;
        }
        ft.commit();

        pieceStacksInHand.clear();
        pieceStacksInHand.addAll(pieceStacks);
    }

}