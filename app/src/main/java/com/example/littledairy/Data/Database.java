package com.example.littledairy.Data;

import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

@android.arch.persistence.room.Database(entities = {DiaryObject.class , DiaryImage.class , DiaryPlace.class}, version = 3, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class Database extends RoomDatabase {
    public abstract DiaryDAO diaryDAO();
    private static Database database;

    public static Database getDatabase (Context context) {
        if (database == null){
            database = Room.databaseBuilder(context, Database.class, "Diary.db").fallbackToDestructiveMigration().build();
        }
        return database;
    }}
