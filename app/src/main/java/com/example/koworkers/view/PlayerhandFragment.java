package com.example.koworkers.view;

import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.koworkers.R;
import com.example.koworkers.model.Isubscriber;
import com.example.koworkers.model.pieces.IPiece;
import com.example.koworkers.viewmodel.PlayerhandViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PlayerhandFragment extends Fragment implements Isubscriber {

    //private final Map<String, PieceStackFragment> pieceStackMap = new HashMap<>(); //TODO
    private LinearLayout handLinearLayout;


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

//        TextView textView1 = new TextView(getContext());
//        textView1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
//                LinearLayout.LayoutParams.WRAP_CONTENT));
//        textView1.setPadding(20, 20, 20, 20);// in pixels (left, top, right, bottom)
//        handLinearLayout.addView(textView1);

        populate();
    }

    @Override
    public void update() {
        //populateHand();
    }

    private Map<ImageView, Integer> numberImageMap = new HashMap<>();
    private final Map<ImageView, IPiece> pieceImageMap = new HashMap<>();

    /**
     * creates stacks of pieces and adds to the linear layout
     */
    private void populate(){
        ArrayList<ImageView> images = new ArrayList<>();
        boolean stackAlreadyExist = false;
        for (IPiece piece: mViewModel.getPieces()){
            for(ImageView image: images){
                if (pieceImageMap.get(image).getImageResource() == piece.getImageResource()) {
                   numberImageMap.put(image, numberImageMap.get(image) + 1);
                   stackAlreadyExist = true;
                   if(!images.contains(image)){
                       images.add(image);
                   }
                   break;
                }
            }
            if(!stackAlreadyExist){
                ImageView newImage = new ImageView(getContext());
                newImage.setImageResource(piece.getImageResource());
                pieceImageMap.put(newImage, piece);
                numberImageMap.put(newImage, 1);
                images.add(newImage);
            }
            stackAlreadyExist = false;
        }
        addToLinearLayout(images);
    }

    private void addToLinearLayout(ArrayList<ImageView> images){
        for(ImageView image: images){
            setLayout(image, 20,0,  0,0,  80, 2);
            handLinearLayout.addView(image);

            TextView textView1 = new TextView(getContext());
            textView1.setText(numberImageMap.get(image) + "");
            textView1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            textView1.setPadding(20, 80, 20, 20);// in pixels (left, top, right, bottom)
            handLinearLayout.addView(textView1);
        }
    }

    public void setLayout(View view, int left, int top, int right, int bottom, int size, int dpiRatio){
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(size * dpiRatio, size * dpiRatio);
        params.setMargins(left*dpiRatio, top*dpiRatio, right*dpiRatio, bottom*dpiRatio);
        view.setLayoutParams(params);
    }



//    /**
//     * creates stacks of pieces and adds to the linear layout
//     */
//    private void populateHand() {
//        boolean stackAlreadyExists = false;
//        for (IPiece piece : mViewModel.getPieces()) {
//            for (PieceStack pieceStack : ) {
//                if (pieceStack.getPiece().getImageResource() == piece.getImageResource()) {
//                    pieceStack.incNumberOfPieces();
//                    stackAlreadyExists = true;
//                    break;
//                }
//            }
//            if (!stackAlreadyExists) {
//                pieceStacks.add(PieceStackFragment.newInstance(piece));
//            }
//            stackAlreadyExists = false;
//        }
//    }

//    /**
//     * adds the provided list of PieceStackFragments to the Linear Layout and removes the prevoius list
//     *
//     * @param pieceStacks to be added to Linear Layout
//     */
//    private void addToLinearLayout(ArrayList<PieceStackFragment> pieceStacks) {
//        FragmentTransaction ft = getParentFragmentManager().beginTransaction();
//        for (PieceStackFragment pieceStack : pieceStacksInHand) {
//            ft.remove(pieceStack);
//        }
//        int i=0;
//        for (PieceStackFragment pieceStack : pieceStacks) {
//            ft.add(R.id.handLinearLayout, pieceStack, ""+ i);
//            i++;
//        }
//        ft.commit();
//
//        pieceStacksInHand.clear();
//        pieceStacksInHand.addAll(pieceStacks);
//    }

}