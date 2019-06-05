package com.example.littledairy.Data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

@Dao
public interface DiaryDAO {
    @Insert
    DiaryImage insertImage(DiaryImage diaryImage);

    @Insert
    DiaryPlace insertPlace(DiaryPlace diaryPlace);

    @Insert
    DiaryObject insertDiary(DiaryObject diary);

    @Query("SELECT * FROM DiaryObject INNER JOIN DiaryPlace ON DiaryObject.placeId = DiaryPlace.placeId " +
            "INNER JOIN DiaryImage ON DiaryImage.imageId =  DiaryObject.imageId")
    Diary[] getAllDiaries();

    @Query("SELECT * FROM DiaryObject INNER JOIN DiaryPlace ON DiaryObject.placeId = DiaryPlace.placeId " +
            "INNER JOIN DiaryImage ON DiaryImage.imageId =  DiaryObject.imageId WHERE id = :id")
    Diary getDiary(int id);


}
