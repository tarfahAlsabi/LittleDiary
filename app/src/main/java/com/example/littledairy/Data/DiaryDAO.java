package com.example.littledairy.Data;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface DiaryDAO {
    @Insert
    long insertImage(DiaryImage diaryImage);
    @Insert
    long insertPlace(DiaryPlace diaryPlace);
    @Insert
    long insertDiary(DiaryObject diary);

    @Query("SELECT * FROM DiaryObject INNER JOIN DiaryPlace ON DiaryObject.placeId = DiaryPlace.placeId " +
            "INNER JOIN DiaryImage ON DiaryImage.imageId =  DiaryObject.imageId")
    LiveData<List<Diary>> getAllDiaries();

    @Query("SELECT * FROM DiaryObject INNER JOIN DiaryPlace ON DiaryObject.placeId = DiaryPlace.placeId " +
            "INNER JOIN DiaryImage ON DiaryImage.imageId =  DiaryObject.imageId WHERE id = :id")
    Diary getDiary(int id);

    @Delete
    void deleteImage (DiaryImage diaryImage);
    @Delete
    void deletePlace(DiaryPlace diaryPlace);
    @Delete
    void deleteDiary(DiaryObject diary);

    @Update
    void updateDiary(DiaryObject diary);
    @Update
    void updateImage(DiaryImage diaryImage);
    @Update
    void updateplace(DiaryPlace diaryPlace);

}
