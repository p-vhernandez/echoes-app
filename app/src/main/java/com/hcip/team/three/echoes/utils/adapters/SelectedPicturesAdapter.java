package com.hcip.team.three.echoes.utils.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.hcip.team.three.echoes.EchoesApplication;
import com.hcip.team.three.echoes.R;
import com.hcip.team.three.echoes.fragments.creation.CameraFragment;
import com.hcip.team.three.echoes.utils.interfaces.PictureSelectedListener;

import java.util.ArrayList;

public class SelectedPicturesAdapter extends BaseAdapter {

    private final Context context;
    private final EchoesApplication echoesApplication;

    private final ArrayList<Drawable> allPictures;

    private final PictureSelectedListener pictureSelectedListener;

    @SuppressLint("UseCompatLoadingForDrawables")
    public SelectedPicturesAdapter(Context context, EchoesApplication echoesApplication, PictureSelectedListener pictureSelectedListener) {
        this.context = context;
        this.echoesApplication = echoesApplication;
        this.pictureSelectedListener = pictureSelectedListener;

        allPictures = new ArrayList<>();
        allPictures.add(context.getResources().getDrawable(R.drawable.drw_add_image_visual));
    }

    public void addDrawableImage(Drawable drawable) {
        if (this.allPictures != null) {
            this.allPictures.add(drawable);
        }

        notifyDataSetChanged();
    }

    public void addDrawableImages(ArrayList<Drawable> drawables) {
        if (this.allPictures != null) {
            this.allPictures.addAll(drawables);
        }

        notifyDataSetChanged();
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    public void clearDrawables() {
        allPictures.clear();
        allPictures.add(context.getResources().getDrawable(R.drawable.drw_add_image_visual));
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
    public View getView(int i, View view, ViewGroup parent) {
        if (i == 0) {
            ImageView picture = new ImageView(context);
            picture.setImageDrawable(allPictures.get(i));
            picture.setScaleType(ImageView.ScaleType.FIT_XY);
            picture.setAdjustViewBounds(true);

            picture.setOnClickListener(v -> openImageSelector());

            return picture;
        } else {
            LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            @SuppressLint("ViewHolder") View item = inflater.inflate(R.layout.custom_layout_picture_selection, parent, false);

            ImageView selectedImage = item.findViewById(R.id.available_image);
            selectedImage.setImageDrawable(allPictures.get(i));

            item.setOnClickListener(v -> {
                //TODO: check if image is selected
                // if so, and there are more selected images -> unselect
                // if so, and there are no more selected images -> unselect
                // if not, and there are more selected images -> select
                // if not, and there are no more selected images -> visualize
            });

            item.setOnLongClickListener(v -> {
                //TODO: if there are no more selected images -> select
                // if there are more selected images -> nothing
                return false;
            });

            return item;
        }
    }

    private void openImageSelector() {
        pictureSelectedListener.onOpenSelectorClicked();
    }

    private void visualizeImage() {
        //TODO: future sprint - preview of the image
    }

    private void selectImage(ImageView picture) {
        //TODO: select image and show bin icon for deletion
    }
}
