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

    private Map<ImageView, Integer> numberImageMap = new HashMap<>();
    private final Map<ImageView, IPiece> pieceImageMap = new HashMap<>();

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

        populate();
    }

    @Override
    public void update() {
        populate();
    }

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
                setLayout(newImage, 20,0,  0,0,  90, 2);
                pieceImageMap.put(newImage, piece);
                numberImageMap.put(newImage, 1);
                images.add(newImage);
            }
            stackAlreadyExist = false;
        }
        handLinearLayout.removeAllViews();
        addToLinearLayout(images);
    }

    private void addToLinearLayout(ArrayList<ImageView> images){
        for(ImageView image: images){
            handLinearLayout.addView(image);

            TextView textView1 = new TextView(getContext());
            textView1.setText(numberImageMap.get(image) + "");
            textView1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            textView1.setPadding(20, 110, 20, 20);// in pixels (left, top, right, bottom)
            handLinearLayout.addView(textView1);
        }
    }

    public void setLayout(View view, int left, int top, int right, int bottom, int size, int dpiRatio){
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(size * dpiRatio, size * dpiRatio);
        params.setMargins(left*dpiRatio, top*dpiRatio, right*dpiRatio, bottom*dpiRatio);
        view.setLayoutParams(params);
    }

}