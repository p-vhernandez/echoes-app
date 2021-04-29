package com.hcip.team.three.echoes.utils.adapters;

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

        picture.setOnClickListener(v -> {
            if (i == 0) {
                openImageSelector(picture);
            } else {
                //TODO: check if image is selected
                // if so, and there are more selected images -> unselect
                // if so, and there are no more selected images -> unselect
                // if not, and there are more selected images -> select
                // if not, and there are no more selected images -> visualize
            }
        });

        picture.setOnLongClickListener(v -> {
            if (i != 0) {
                //TODO: if there are no more selected images -> select
                // if there are more selected images -> nothing
                selectImage(picture);
            }

            return false;
        });

        return picture;
    }

    private void openImageSelector(ImageView picture) {
        //TODO: create custom selector OR open default system selector
    }

    private void visualizeImage() {
        //TODO: preview of the image
    }

    private void selectImage(ImageView picture) {
        //TODO: select image and show bin icon for deletion
    }
}
