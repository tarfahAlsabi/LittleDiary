package com.example.littledairy;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.example.littledairy.Data.Database;
import com.example.littledairy.Data.Diary;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DiaryWidgetService extends IntentService {
    AppWidgetManager appWidgetManager;
    int[] appWidgetIds;
    Context context;
    public DiaryWidgetService(){
        super("DiaryWidgetService");
    }

    void setArgs(Context context,AppWidgetManager appWidgetManager, int[] appWidgetIds){
        this.context = context;
        this.appWidgetManager = appWidgetManager;
        this.appWidgetIds = appWidgetIds;
        getDiary();
    }

    private void getDiary(){
        Database db = Database.getDatabase(context);
        String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Diary diary = db.diaryDAO().getDiary(date);
                for (int appWidgetId : appWidgetIds) {
                    diarywidget.updateAppWidget(context, appWidgetManager, appWidgetId, diary);
                }
            }
        });
        thread.start();
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

    }
}
