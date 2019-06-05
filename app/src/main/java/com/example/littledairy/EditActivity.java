package com.example.littledairy;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


public class EditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        EditImageFragment fragment =(EditImageFragment) getSupportFragmentManager().findFragmentById(R.id.ImageFragment);
        fragment.setContext(this);
    }



}
