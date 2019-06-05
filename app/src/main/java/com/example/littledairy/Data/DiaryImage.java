package com.example.littledairy.Data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
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
}
