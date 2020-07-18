package com.example.dogsapp.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.example.dogsapp.R;
import com.example.dogsapp.model.DogBreed;
import com.example.dogsapp.util.Util;
import com.example.dogsapp.viewmodel.DetailViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailFragment extends Fragment {

    View v;
    @BindView(R.id.dogImage)
    ImageView dogImage;
    @BindView(R.id.dogName)
    TextView dogName;
    @BindView(R.id.dogPurpose)
    TextView dogPurpose;
    @BindView(R.id.dogTemperament)
    TextView dogTemperament;
    @BindView(R.id.dogLifespan)
    TextView dogLifespan;

    private int mDogUuid;
    private DetailViewModel mDetailViewModel;

    public DetailFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_detail, container, false);
        ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null) {
            mDogUuid = DetailFragmentArgs.fromBundle(getArguments()).getDogUuid();
        }

        mDetailViewModel = ViewModelProviders.of(this).get(DetailViewModel.class);
        mDetailViewModel.fetch(mDogUuid);

        observeViewModel();
    }

    private void observeViewModel() {
        mDetailViewModel.dogsLiveData.observe(this, dogs -> {
            if (dogs != null && dogs instanceof DogBreed) {
                dogName.setText(dogs.dogBreed);
                dogPurpose.setText(dogs.bredFor);
                dogTemperament.setText(dogs.temperament);
                dogLifespan.setText(dogs.lifeSpan);

            }

            if (dogs.imageUrl != null && getContext() != null) {
                Util.loadImages(dogImage, dogs.imageUrl, new CircularProgressDrawable(getContext()));

            }
        });

    }
}