package com.example.littledairy.Data;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.List;


public class DiaryViewModel extends AndroidViewModel {
    private DiaryDAO dao;
    public DiaryViewModel(Application context){
        super(context);
        dao = Database.getDatabase(context.getApplicationContext()).diaryDAO();
    }

    public LiveData<List<Diary>> getAllDiary(){
        return dao.getAllDiaries();
    }
    public Diary getDiary(int id){
        return dao.getDiary(id);
    }
    public void insertDiary(Diary diary){
        DiaryImage diaryImage = new DiaryImage(diary.getImageBytes(), diary.getImageDescreption());
        DiaryPlace diaryPlace = new DiaryPlace(diary.getLatitudes(),diary.getLongitudes(),diary.getPlaceName());
        int imageId = (int) dao.insertImage(diaryImage);
        int placeId = (int) dao.insertPlace(diaryPlace);
        DiaryObject diaryObject = new DiaryObject(diary.getDairyDate(),diary.getDailyText(), imageId ,placeId);
        dao.insertDiary(diaryObject);
    }

    public void deleteImage(Diary diary){
        DiaryImage diaryImage = new DiaryImage(diary.getImageBytes(), diary.getImageDescreption(), diary.getImageId());
        dao.deleteImage(diaryImage);
    }
    public void updateImage(Diary diary){
        DiaryImage diaryImage = new DiaryImage(diary.getImageBytes(), diary.getImageDescreption(), diary.getImageId());
        dao.updateImage(diaryImage);
    }

    public void deletePlace(Diary diary){
        DiaryPlace diaryPlace = new DiaryPlace(diary.getLatitudes(),diary.getLongitudes(),diary.getPlaceName(), diary.getPlaceId());
        dao.deletePlace(diaryPlace);
    }
    public void updatePlace(Diary diary){
        DiaryPlace diaryPlace = new DiaryPlace(diary.getLatitudes(),diary.getLongitudes(),diary.getPlaceName(), diary.getPlaceId());
        dao.updateplace(diaryPlace);
    }

    public void deleteDiary(Diary diary){
        deleteImage(diary);
        deletePlace(diary);
        DiaryObject diaryObject = new DiaryObject(diary.getDairyDate(),diary.getDailyText(),
                                        diary.getImageId() ,diary.getPlaceId(),diary.getId());
        dao.deleteDiary(diaryObject);
    }
    public void updateDiary(Diary diary){
        updateImage(diary);
        updatePlace(diary);
        DiaryObject diaryObject = new DiaryObject(diary.getDairyDate(),diary.getDailyText(),
                diary.getImageId() ,diary.getPlaceId(),diary.getId());
        dao.updateDiary(diaryObject);
    }
}
