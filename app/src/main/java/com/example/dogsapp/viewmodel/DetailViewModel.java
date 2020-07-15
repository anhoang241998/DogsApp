package com.example.dogsapp.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.dogsapp.model.DogBreed;

public class DetailViewModel extends ViewModel {

    public MutableLiveData<DogBreed> dogsLiveData = new MutableLiveData<DogBreed>();

    public void fetch() {
        DogBreed dog = new DogBreed("1", "corgi", "15 years", "", "companionship", "calm and friendly", "");
        dogsLiveData.setValue(dog);
    }

}
