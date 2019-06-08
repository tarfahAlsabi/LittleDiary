package com.example.littledairy.Data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.TypeConverters;

import java.util.Date;
@Entity
public class Diary {
    public int id;
    @TypeConverters(Converters.class)
    @ColumnInfo(name = "diaryDate")
    private Date dairyDate;
    private String dailyText;
//Place
    private int placeId;
    private float Latitudes;
    private float longitudes;
    private String placeName;

//Image
    private int imageId;
    private byte[] imageBytes;
    private String imageDescreption;


    public byte[] getImageBytes() {
        return imageBytes;
    }

    public Date getDairyDate() {
        return dairyDate;
    }

    public float getLatitudes() {
        return Latitudes;
    }

    public float getLongitudes() {
        return longitudes;
    }

    public String getDailyText() {
        return dailyText;
    }

    public int getId() {
        return id;
    }

    public int getImageId() {
        return imageId;
    }

    public int getPlaceId() {
        return placeId;
    }

    public String getImageDescreption() {
        return imageDescreption;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setDailyText(String dailyText) {
        this.dailyText = dailyText;
    }

    public void setDairyDate(Date dairyDate) {
        this.dairyDate = dairyDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setImageBytes(byte[] imageBytes) {
        this.imageBytes = imageBytes;
    }

    public void setImageDescreption(String imageDescreption) {
        this.imageDescreption = imageDescreption;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public void setLatitudes(float latitudes) {
        Latitudes = latitudes;
    }

    public void setLongitudes(float longitudes) {
        this.longitudes = longitudes;
    }

    public void setPlaceId(int placeId) {
        this.placeId = placeId;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }
}
