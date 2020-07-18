package com.example.dogsapp.model;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Observable;


@Dao
public interface DogDao {
    @Insert
    List<Long> insertAll(DogBreed... dogs);

//    @Query("SELECT * FROM dogbreed")
//    Observable<List<DogBreed>> getAllDog();

    @Query("SELECT * FROM dogbreed")
    List<DogBreed> getAllDog();

    @Query("SELECT * FROM dogbreed WHERE uuid = :dogID")
    DogBreed getDog(int dogID);

    @Query("DELETE FROM dogbreed")
    void deleteAllDogs();

}
