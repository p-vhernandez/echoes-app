package com.hcip.team.three.echoes.fragments.creation;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.hcip.team.three.echoes.EchoesApplication;
import com.hcip.team.three.echoes.R;
import com.hcip.team.three.echoes.activities.CreationActivity;
import com.hcip.team.three.echoes.utils.adapters.AvailablePicturesAdapter;
import com.hcip.team.three.echoes.utils.adapters.SelectedPicturesAdapter;
import com.hcip.team.three.echoes.utils.interfaces.PictureSelectedListener;

import java.util.ArrayList;
import java.util.Objects;

public class CameraFragment extends Fragment implements PictureSelectedListener {

    private EchoesApplication echoesApplication;

    private View fragmentView;
    private GridView alreadySelectedGridView;
    private GridView availablePicturesGridView;

    private RelativeLayout imageSelection;

    private ImageView btnCloseSelection;
    private Button btnAddImages;

    private SelectedPicturesAdapter selectedPicturesAdapter;
    private AvailablePicturesAdapter availablePicturesAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup parent, @Nullable Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.fragment_creation_pictures, parent, false);
        echoesApplication = (EchoesApplication) Objects.requireNonNull(getActivity()).getApplication();

        initialize();

        return fragmentView;
    }

    private void initialize() {
        alreadySelectedGridView = fragmentView.findViewById(R.id.picture_grid);
        imageSelection = fragmentView.findViewById(R.id.image_selection);
        btnCloseSelection = fragmentView.findViewById(R.id.close_selection_button);
        availablePicturesGridView = fragmentView.findViewById(R.id.picture_grid_selection);
        btnAddImages = fragmentView.findViewById(R.id.add_images_button);

        setUpAdapter();
        setUpListeners();
    }

    private void setUpAdapter() {
        selectedPicturesAdapter = new SelectedPicturesAdapter(requireContext(), echoesApplication, this);
        alreadySelectedGridView.setAdapter(selectedPicturesAdapter);

        availablePicturesAdapter = new AvailablePicturesAdapter(requireContext(), echoesApplication);
        availablePicturesGridView.setAdapter(availablePicturesAdapter);
    }

    private void setUpListeners() {
        btnAddImages.setOnClickListener(view -> {
            ArrayList<Drawable> selectedImages = availablePicturesAdapter.getSelectedImages();
            selectedPicturesAdapter.clearDrawables();
            selectedPicturesAdapter.addDrawableImages(selectedImages);
            echoesApplication.saveEchoPictures(getEncodedPictures());

            closeImageSelection();
            showBottomMenu();
//            enableNavbarOptions();
        });

        btnCloseSelection.setOnClickListener(v -> {
            closeImageSelection();
            showBottomMenu();
//            enableNavbarOptions();
        });
    }

    private void openImageSelector() {
        imageSelection.setVisibility(View.VISIBLE);
    }

    private void closeImageSelection() {
        imageSelection.setVisibility(View.GONE);
    }

    private void hideBottomMenu() {
        ((CreationActivity) Objects.requireNonNull(getActivity())).hideBottomMenu();
    }

    private void showBottomMenu() {
        ((CreationActivity) Objects.requireNonNull(getActivity())).showBottomMenu();
    }

    private void disableNavbarOptions() {
        ((CreationActivity) Objects.requireNonNull(getActivity())).disableNavbarOptions();
    }

    private void enableNavbarOptions() {
        ((CreationActivity) Objects.requireNonNull(getActivity())).enableNavbarOptions();
    }

    @Override
    public void onOpenSelectorClicked() {
        hideBottomMenu();
        openImageSelector();
//        disableNavbarOptions();
    }

    @Override
    public void onSelectedPictureClicked() {

    }

    public ArrayList<String> getEncodedPictures() {
        ArrayList<String> selected = new ArrayList<>();
        for (Drawable drawable : availablePicturesAdapter.getSelectedImages()) {
            selected.add(echoesApplication.imageEncoder(drawable, false));
        }

        return selected;
    }
}
