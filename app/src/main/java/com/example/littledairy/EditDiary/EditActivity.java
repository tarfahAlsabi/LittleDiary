package com.example.littledairy.EditDiary;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.littledairy.R;


public class EditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        EditImageFragment fragment =(EditImageFragment) getSupportFragmentManager().findFragmentById(R.id.ImageFragment);
        fragment.setContext(this);
    }



}
