package com.example.littledairy;

import android.app.IntentService;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.RemoteViews;

import com.example.littledairy.Data.Database;
import com.example.littledairy.Data.Diary;
import com.example.littledairy.Data.DiaryViewModel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Implementation of App Widget functionality.
 */
public class diarywidget extends AppWidgetProvider {

    DiaryWidgetService service =  new DiaryWidgetService();

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId,Diary diary) {

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.diarywidget);
        Intent intent = new Intent(context,MainActivity.class);

        if(diary != null){
            views.setTextViewText(R.id.appwidget_date_textV, diary.getDiaryDate());
            views.setTextViewText(R.id.appwidget_diary_textV, diary.getDailyText());
            intent.putExtra("diary",diary);
        }else{
            String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
            views.setTextViewText(R.id.appwidget_date_textV, date);
            views.setTextViewText(R.id.appwidget_diary_textV, "you did not write in the diary today");
        }

        PendingIntent pendingIntent = PendingIntent.getActivity(context,0,intent,0);
        // Instruct the widget manager to update the widget
        views.setOnClickPendingIntent(R.id.appwidget_layout,pendingIntent);
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
       service.setArgs(context , appWidgetManager , appWidgetIds);

    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

