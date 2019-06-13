package com.example.littledairy;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.littledairy.Data.Diary;
import com.example.littledairy.Data.DiaryViewModel;
import com.example.littledairy.EditDiary.EditActivity;
import com.example.littledairy.ListDiaries.DiariesList;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity  implements OnMapReadyCallback {
    Context context = this;
    DiaryViewModel diaryViewModel;
    AppCompatActivity Main = this;
    Diary today;
    private GoogleMap mMap;
    float lat , lng;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(this, getResources().getString(R.string.google_ads_key));
        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        ConstraintLayout constraintLayout = findViewById(R.id.MainActivity_Layout);
        constraintLayout.setOnTouchListener(new OnSwipeTouchListener(this){
            @Override
            public void onSwipeRight() {
                super.onSwipeLeft();
                toListActivity();
            }
        });

        Button listBtn = findViewById(R.id.MainActivity_list_btn);
        listBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toListActivity();
            }
        });

        FloatingActionButton edit= findViewById(R.id.MAinActivity_floatingActionButton);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(context, EditActivity.class );
                intent.putExtra("diary",today);
                startActivity(intent);
            }
        });
        Intent intent = getIntent();
        if(intent.hasExtra("diary")){
            today = intent.getParcelableExtra("diary");
            setDiary(today);
            setDate(today.getDiaryDate());

        }else {
            Date todayDate = new Date();
            String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(todayDate);
            setDate(date);
            Thread thread = new Thread() {
                @Override
                public void run() {
                    diaryViewModel = ViewModelProviders.of(Main).get(DiaryViewModel.class);
                    today = diaryViewModel.getDiary(date);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            setDiary(today);

                        }
                    });
                }
            };
            thread.start();
        }
    }

    private void setDate(String date) {
        TextView dateTV = findViewById(R.id.MainActivity_diary_date);
        dateTV.setText(date);
    }

    private void setDiary(Diary today) {
        RelativeLayout relativeLayout = findViewById(R.id.MainActivity_map_layout);
        TextView diaryText= findViewById(R.id.MainActivity_diary_text);
        ImageView diaryImage = findViewById(R.id.MainActivity_diary_image);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.MainActivity_diary_map);
        getSupportFragmentManager().beginTransaction().hide(mapFragment).commit();
        diaryImage.setVisibility(View.GONE);
        if (today == null){
            diaryText.setText(getResources().getString(R.string.noText));
        }else{
            if(today.getDailyText() != null){
                diaryText.setText(today.getDailyText());
            }
            String imagePath = today.getImagePath();
            if(imagePath != null) {
                Handler uiHandler = new Handler(Looper.getMainLooper());
                uiHandler.post(new Runnable(){
                    @Override
                    public void run() {
                        File f=new File(imagePath);
                        Picasso.get().load(f).placeholder(R.drawable.ic_baseline_broken_image_24px).into(diaryImage);
                        diaryImage.setVisibility(View.VISIBLE);
                    }
                });
            }
            if(today.getLatitudes() != 0 && today.getLongitudes() != 0 ){
                lat= today.getLatitudes();
                lng = today.getLongitudes();
                mapFragment.getMapAsync(this);
                getSupportFragmentManager().beginTransaction().show(mapFragment).commit();
                relativeLayout.setVisibility(View.VISIBLE);
            }
        }
    }

    public void toListActivity(){
        Intent intent = new Intent(context, DiariesList.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap =googleMap;
        LatLng placeLocation = new LatLng(lat,lng);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(placeLocation,18));
    }
}
