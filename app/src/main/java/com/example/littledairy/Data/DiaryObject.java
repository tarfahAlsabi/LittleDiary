package com.example.littledairy.Data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import java.util.Date;

@Entity (indices = {@Index("id")},foreignKeys = {@ForeignKey( entity = DiaryImage.class , parentColumns = "imageId" ,childColumns = "imageId")
, @ForeignKey(entity = DiaryPlace.class , parentColumns = "placeId", childColumns = "placeId")})
public class DiaryObject {
    @PrimaryKey (autoGenerate = true)
    public int id;
    public int imageId;
    public int placeId;
    public String diaryDate;
    public String dailyText;

    public DiaryObject (String diaryDate , String dailyText , int imageId , int placeId){
        this.diaryDate = diaryDate;
        this.dailyText = dailyText;
        this.imageId   = imageId;
        this.placeId   = placeId;
    }
    @Ignore
    public DiaryObject (String diaryDate , String dailyText , int imageId , int placeId, int id){
        this.diaryDate = diaryDate;
        this.dailyText = dailyText;
        this.imageId   = imageId;
        this.placeId   = placeId;
        this.id        = id;
    }
}


