package com.example.littledairy.ListDiaries;

import android.app.DatePickerDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.littledairy.Data.Diary;
import com.example.littledairy.Data.DiaryViewModel;
import com.example.littledairy.MainActivity;
import com.example.littledairy.OnSwipeTouchListener;
import com.example.littledairy.R;

import java.util.Calendar;

public class DiariesList extends AppCompatActivity {
private DiaryViewModel diaryViewModel;
private Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diaries_list);
        LinearLayout linearLayout = findViewById(R.id.DiariesList_Linear_layout);
        linearLayout.setOnTouchListener(new OnSwipeTouchListener(this){
            @Override
            public void onSwipeLeft() {
                super.onSwipeLeft();
                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
            }
        });
        diaryViewModel = ViewModelProviders.of(this).get(DiaryViewModel.class);
        RecyclerView recyclerView = findViewById(R.id.Diaries_list_RecyclerView);
        final DiariesListAdapter adapter = new DiariesListAdapter(this);
        diaryViewModel.getAllDiary().observe(this, (diaries) -> adapter.setDiaries(diaries));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);


        ImageButton search = findViewById(R.id.Diaries_list_imageButton);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                DatePickerDialog datePickerDialog = new DatePickerDialog(DiariesList.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                Thread thread = new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        String day , month;
                                        if(dayOfMonth < 10)
                                            day = "0" + dayOfMonth;
                                        else
                                            day = dayOfMonth+"";
                                        if((monthOfYear+1) < 10)
                                            month = "0"+(monthOfYear + 1);
                                        else
                                            month = (monthOfYear + 1)+"";

                                        String date = day + "-" + month + "-" + year ;
                                        Log.i("date result ",date);
                                        Diary foundDiary = diaryViewModel.getDiary(date);
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                adapter.setDiaries(foundDiary);
                                            }
                                        });
                                    }
                                });
                                thread.start();
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }

        });
    }

}
