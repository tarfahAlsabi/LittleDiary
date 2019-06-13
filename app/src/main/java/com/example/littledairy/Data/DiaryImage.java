package com.example.littledairy.Data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

@Entity (indices = {@Index("imageId")})
public class DiaryImage {
    @PrimaryKey (autoGenerate = true)
        public int imageId;
        public String imagePath;

    public DiaryImage( String imagePath){
        this.imagePath = imagePath;
    }
@Ignore
    public DiaryImage(String imagePath, int imageId){
        this.imagePath = imagePath;
        this.imageId = imageId;
    }
}
