package com.example.dogsapp.repository;

import android.content.Context;

import com.example.dogsapp.model.DogBreed;
import com.example.dogsapp.model.DogDao;
import com.example.dogsapp.model.DogDatabase;

import java.util.List;

import io.reactivex.Observable;

public class DatabaseRomRepository {
    private DogDao mDogDao;
    private static DatabaseRomRepository mDatabaseRomRepository = null;

//    public DatabaseRomRepository(Context context) {
//        mDogDao = DogDatabase.getInstance(context).dogDao();
//    }
//
//    public static DatabaseRomRepository getInstance(Context context){
//        if (mDatabaseRomRepository == null){
//            mDatabaseRomRepository = new DatabaseRomRepository(context);
//        }
//        return mDatabaseRomRepository;
//    }
//
//    public List<Long> insertDatabase(DogBreed... dogBreeds ){
//        return mDogDao.insertAll(dogBreeds);
//    }
//
//    public Observable<List<DogBreed>> getAll(){
//        return  mDogDao.getAllDog();
//    }

}
