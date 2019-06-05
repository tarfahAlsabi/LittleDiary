package com.example.littledairy.Data;

import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@android.arch.persistence.room.Database(entities = {DiaryObject.class , DiaryImage.class , DiaryPlace.class}, version = 1, exportSchema = false)
public abstract class Database extends RoomDatabase {
    public abstract DiaryDAO diaryDAO();
    private static Database database;

    public static Database getDatabase (Context context) {
        if (database == null){
            database = Room.databaseBuilder(context, Database.class, "Diary.db").fallbackToDestructiveMigration().build();
        }
        return database;
    }}
