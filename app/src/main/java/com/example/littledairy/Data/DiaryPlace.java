package com.example.littledairy.Data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class DiaryPlace {
    @PrimaryKey (autoGenerate = true)
    public int placeId;
    public float Latitudes;
    public float longitudes;
    public String placeName;

    public DiaryPlace(float latitudes , float longitudes , String placeName){
        this.Latitudes  = latitudes;
        this.longitudes = longitudes;
        this.placeName  = placeName;
    }

}
