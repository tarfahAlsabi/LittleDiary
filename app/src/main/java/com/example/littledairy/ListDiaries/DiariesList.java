package com.example.littledairy.ListDiaries;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.littledairy.Data.DiaryViewModel;
import com.example.littledairy.R;

public class DiariesList extends AppCompatActivity {
private DiaryViewModel diaryViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diaries_list);
        diaryViewModel = ViewModelProviders.of(this).get(DiaryViewModel.class);
        RecyclerView recyclerView = findViewById(R.id.Diaries_list_RecyclerView);
        final DiariesListAdapter adapter = new DiariesListAdapter(this);
        diaryViewModel.getAllDiary().observe(this, (diaries) -> adapter.setDiaries(diaries));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

}
