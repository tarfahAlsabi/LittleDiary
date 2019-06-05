package com.example.littledairy.Data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import java.util.Date;

@Entity (foreignKeys = {@ForeignKey( entity = DiaryImage.class , parentColumns = "imageId" ,childColumns = "imageId")
, @ForeignKey(entity = DiaryPlace.class , parentColumns = "placeId", childColumns = "placeId")})
public class DiaryObject {
    @PrimaryKey (autoGenerate = true)
    public int id;
    public int imageId;
    public int placeId;
    @TypeConverters(Converters.class)
    public Date diaryDate;
    public String dailyText;

    public DiaryObject (Date diaryDate , String dailyText){
        this.diaryDate = diaryDate;
        this.dailyText = dailyText;
    }
}


