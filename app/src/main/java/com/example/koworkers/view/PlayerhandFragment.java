package com.example.koworkers.view;

import androidx.lifecycle.ViewModelProvider;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.koworkers.R;
import com.example.koworkers.model.Colour;
import com.example.koworkers.model.Isubscriber;
import com.example.koworkers.model.Point;
import com.example.koworkers.model.pieces.IPiece;
import com.example.koworkers.viewmodel.PlayerhandViewModel;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * The view that represents the PlayerHand
 *
 * @author Hanna Adenholm
 */
public class PlayerhandFragment extends Fragment implements Isubscriber {

    private final Map<ImageView, Integer> numberImageMap = new HashMap<>(); // HashMap with the number of pieces there are in each stack
    private final Map<View, IPiece> imagePieceMap = new HashMap<>();        // HashMap of images with the piece that are "on top" of the stack

    private ImageView selectImage;

    private IPiece selectedPiece;

    private LinearLayout handLinearLayout;

    private PlayerhandViewModel mViewModel;

    private final int dpiRatio = (int) Resources.getSystem().getDisplayMetrics().density;

    private final View.OnClickListener stackListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mViewModel.selectPiece(imagePieceMap.get(v));
        }
    };

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

        selectImage = new ImageView(getContext());
        selectImage.setImageResource(R.drawable.select_hexagon);
        setLayout(selectImage, -90, 0,0,0,90);

        populate();
    }

    @Override
    public void pieceWasSelected(IPiece piece) {
        selectedPiece = piece;
        populate();
    }

    @Override
    public void pieceWasDeselected() {
        selectedPiece = null;
        populate();
    }

    @Override
    public void pieceWasMoved(IPiece piece, Point point) {
        populate();
    }

    @Override
    public void playerWasChanged(Colour colour) {
        populate();
    }

    @Override
    public void playerWon(Colour winningColour) {

    }


    /**
     * Creates stacks of pieces and adds to the linear layout.
     */
    private void populate(){

        ArrayList<ImageView> images = new ArrayList<>();
        boolean stackAlreadyExist = false;
        for (IPiece piece: mViewModel.getPieces()){
            for(ImageView image: images){
                if (imagePieceMap.get(image).getName().equals(piece.getName())) { //checks if stack already exists
                   numberImageMap.put(image, numberImageMap.get(image) + 1);
                   imagePieceMap.put(image, piece);
                   stackAlreadyExist = true;
                   if(!images.contains(image)){ //om stacken inte redan finns i imageslistan, lägg till den
                       images.add(image);
                   }
                   break;
                }
            }
            if(!stackAlreadyExist){
                ImageView newImage = new ImageView(getContext());
                //newImage.setImageResource(piece.getImageResource());

                setPieceImage(newImage, piece);

                newImage.setOnClickListener(stackListener);
                setLayout(newImage, 20,0,  0,0,  90);
                imagePieceMap.put(newImage, piece);
                numberImageMap.put(newImage, 1);
                images.add(newImage);
            }
            stackAlreadyExist = false;
        }

        if(mViewModel.queenShouldBePlaced()){
            for (int i = 1; i < images.size(); i++) {
                images.get(i).setClickable(false);
                images.get(i).setAlpha(0.5f);
            }
        }else{
            for (ImageView image : images) {
                image.setClickable(true);
                image.setAlpha(1f);
            }
        }

        handLinearLayout.removeAllViews();
        addToLinearLayout(images);
    }


    private void addToLinearLayout(ArrayList<ImageView> images){
        for(ImageView image: images){
            handLinearLayout.addView(image);
            if(imagePieceMap.get(image) == selectedPiece){
                handLinearLayout.addView(selectImage);
            }
            TextView textView1 = new TextView(getContext());
            textView1.setText(numberImageMap.get(image) + "");
            textView1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            textView1.setPadding(20, 110, 20, 20);// in pixels (left, top, right, bottom)
            handLinearLayout.addView(textView1);
        }
    }

    private void setLayout(View view, int left, int top, int right, int bottom, int size){
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(size * dpiRatio, size * dpiRatio);
        params.setMargins(left*dpiRatio, top*dpiRatio, right*dpiRatio, bottom*dpiRatio);
        view.setLayoutParams(params);
    }

    private void setPieceImage(ImageView image, IPiece piece){

        String pkgName = getContext().getPackageName();
        if(piece.getColour() == Colour.WHITE){
            Uri path = Uri.parse("android.resource://"+pkgName+"/drawable/" + piece.getName() + "_piece");
            image.setImageURI(path);
        }else{
            Uri path = Uri.parse("android.resource://"+pkgName+"/drawable/black_" + piece.getName() + "_piece");
            image.setImageURI(path);
        }

    }

}