package com.example.littledairy.Data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

@Entity (indices = {@Index("imageId")})
public class DiaryImage {
    @PrimaryKey (autoGenerate = true)
        public int imageId;
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
        public byte[] imageBytes;
        public String imageDescreption;

    public DiaryImage( byte[] imageBytes , String imageDescreption){
        this.imageBytes = imageBytes;
        this.imageDescreption = imageDescreption;
    }
@Ignore
    public DiaryImage( byte[] imageBytes , String imageDescreption, int imageId){
        this.imageBytes = imageBytes;
        this.imageDescreption = imageDescreption;
        this.imageId = imageId;
    }
}
