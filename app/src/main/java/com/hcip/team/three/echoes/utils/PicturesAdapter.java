package com.hcip.team.three.echoes.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.hcip.team.three.echoes.EchoesApplication;
import com.hcip.team.three.echoes.R;

import java.util.ArrayList;

public class PicturesAdapter extends BaseAdapter {

    private final Context context;
    private final EchoesApplication echoesApplication;

    private final ArrayList<Drawable> allPictures;

    @SuppressLint("UseCompatLoadingForDrawables")
    public PicturesAdapter(Context context, EchoesApplication echoesApplication) {
        this.context = context;
        this.echoesApplication = echoesApplication;

        allPictures = new ArrayList<>();
        allPictures.add(context.getResources().getDrawable(R.drawable.drw_add_image_visual));
    }

    public void addDrawableImage(Drawable b64Image) {
        if (this.allPictures != null) {
            this.allPictures.add(b64Image);
        }
    }

    public void addDrawableImages(ArrayList<Drawable> b64Images) {
        if (this.allPictures != null) {
            this.allPictures.addAll(b64Images);
        }
    }

    @Override
    public int getCount() {
        return allPictures.size();
    }

    @Override
    public Object getItem(int i) {
        return allPictures.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ImageView picture = new ImageView(context);
        picture.setImageDrawable(allPictures.get(i));
        picture.setScaleType(ImageView.ScaleType.FIT_XY);
        picture.setAdjustViewBounds(true);

        if (i == 0) {
            openImageSelector(picture);
        } else {
            selectImage(picture);
        }

        return picture;
    }

    private void openImageSelector(ImageView picture) {

    }

    private void selectImage(ImageView picture) {

    }
}
