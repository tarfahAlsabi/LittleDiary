package com.example.littledairy;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.littledairy.EditDiary.EditActivity;

public class MainActivity extends AppCompatActivity {
Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button Mapsbutton = findViewById(R.id.Mapsbutton);
        Mapsbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(context, MapsActivity.class );
                startActivity(intent);
            }
        });
        Button edit= findViewById(R.id.EditButton);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(context, EditActivity.class );
                startActivity(intent);
            }
        });
    }
}
