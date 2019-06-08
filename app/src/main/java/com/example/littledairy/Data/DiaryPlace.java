package com.example.littledairy.Data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

@Entity (indices = {@Index("placeId")})
public class DiaryPlace {
    @PrimaryKey (autoGenerate = true)
    public int placeId;
    public float Latitudes;
    public float longitudes;
    public String placeName;

    public DiaryPlace (float Latitudes , float longitudes , String placeName){
        this.Latitudes  = Latitudes;
        this.longitudes = longitudes;
        this.placeName  = placeName;
    }
    @Ignore
    public DiaryPlace(float latitudes , float longitudes , String placeName, int placeId){
        this.Latitudes  = latitudes;
        this.longitudes = longitudes;
        this.placeName  = placeName;
        this.placeId    = placeId;
    }

}
