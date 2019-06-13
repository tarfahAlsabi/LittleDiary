package com.example.littledairy.Data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.os.Parcel;
import android.os.Parcelable;

@Entity
public class Diary implements Parcelable {
    public int id;
    @ColumnInfo(name = "diaryDate")
    private String diaryDate;
    private String dailyText;
//Place
    private int placeId;
    private float Latitudes;
    private float longitudes;
    private String placeName;

//Image
    private int imageId;
    private String imagePath;

        public Diary(){
            super();
        }
    protected Diary(Parcel in) {
        id = in.readInt();
        diaryDate = in.readString();
        dailyText = in.readString();
        placeId = in.readInt();
        Latitudes = in.readFloat();
        longitudes = in.readFloat();
        placeName = in.readString();
        imageId = in.readInt();
        imagePath = in.readString();

    }

    public static final Creator<Diary> CREATOR = new Creator<Diary>() {
        @Override
        public Diary createFromParcel(Parcel in) {
            return new Diary(in);
        }

        @Override
        public Diary[] newArray(int size) {
            return new Diary[size];
        }
    };

    public String getImagePath() {
        return imagePath;
    }

    public String getDiaryDate() {
        return diaryDate;
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


    public String getPlaceName() {
        return placeName;
    }

    public void setDailyText(String dailyText) {
        this.dailyText = dailyText;
    }

    public void setDiaryDate(String diaryDate) {
        this.diaryDate = diaryDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setImagePath(String imagePath ) {
        this.imagePath = imagePath;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(diaryDate);
        dest.writeString(dailyText);
        dest.writeInt(placeId);
        dest.writeFloat(Latitudes);
        dest.writeFloat(longitudes);
        dest.writeString(placeName);
        dest.writeInt(imageId);
        dest.writeString(imagePath);
    }
}
