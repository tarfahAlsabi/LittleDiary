package com.example.littledairy.EditDiary;


import android.Manifest;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.littledairy.Data.Diary;
import com.example.littledairy.Data.DiaryViewModel;
import com.example.littledairy.MainActivity;
import com.example.littledairy.R;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.text.SimpleDateFormat;
import java.util.Date;


public class EditActivity extends AppCompatActivity {
    Diary diary;
    EditImageFragment ImageFragment;
    TextView textDate ,textPlaceInfo;
    EditText diaryText;
    DiaryViewModel viewModel;
    AppCompatActivity edit = this;
    Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        ImageFragment =(EditImageFragment) getSupportFragmentManager().findFragmentById(R.id.ImageFragment);
        ImageFragment.setContext(this);
        textPlaceInfo = findViewById(R.id.edit_place_result);
        Button saveBtn = findViewById(R.id.EditActivity_save_btn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveDiary();
            }
        });
        Button cancelBtn = findViewById(R.id.EditActivity_cancel_btn);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelDiary();
            }
        });
        Intent intent = getIntent();
        textDate = findViewById(R.id.EditActivity_date);
        diaryText = findViewById(R.id.EditActivity_editText);
        if(intent.hasExtra("diary")){
            diary = intent.getParcelableExtra("diary");
            if(diary != null) {
                diaryText.setText(diary.getDailyText());
                setDate(diary.getDiaryDate());
                if (diary.getImagePath() != null) {
                    ImageFragment.setImage(diary.getImagePath());
                }
                if(diary.getPlaceName() != null){
                    textPlaceInfo.setText(diary.getPlaceName());
                }
            }else{
                Date date = new Date();
                String fDate = new SimpleDateFormat("dd-MM-yyyy").format(date);
                setDate(fDate);
                diary = new Diary();
                diary.setDiaryDate(fDate);
            }
            ImageFragment.setDate(diary.getDiaryDate());
        }else{
            finish();
        }

        Button locationBtn = findViewById(R.id.editPlaceBtn);
        locationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ContextCompat.checkSelfPermission(EditActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    Intent intent1 = new Intent(context, EditPlaceActivity.class);
                    startActivityForResult(intent1, 25);
                }else{
                    getLocationPermission();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            Log.i("on Edit Activity","RESULT_OK");
           diary.setPlaceName(data.getStringExtra("name"));
           diary.setLatitudes((float)data.getDoubleExtra("lat",-1));
           diary.setLongitudes((float) data.getDoubleExtra("lng",-1));
           String result = diary.getPlaceName() + "\n" +diary.getLatitudes() + "\n" + diary.getLongitudes();
           Log.i("intentResult",result);
           textPlaceInfo.setText(result);
        }
    }

    public void saveDiary(){
        diary.setImagePath(ImageFragment.getImagePath());
        diary.setDailyText(diaryText.getText().toString());
        Thread thread = new Thread() {
            @Override
            public void run() {
                viewModel = ViewModelProviders.of(edit).get(DiaryViewModel.class);
                if(diary.getId() != 0)
                {viewModel.updateDiary(diary);}
                else
                { viewModel.insertDiary(diary);}
//                Toast.makeText(getApplicationContext(), "diary Saved!", Toast.LENGTH_SHORT).show();

                Intent intent= new Intent(context, MainActivity.class );
                intent.putExtra("diary",diary);
                startActivity(intent);
            }
        };
        thread.start();
   }
    public void cancelDiary(){
            finish();
    }
    private void setDate(String date) {
        textDate.setText("edit " + date);
    }

    private  void getLocationPermission(){
        Dexter.withActivity(EditActivity.this)
                .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        Intent intent1 = new Intent(context, EditPlaceActivity.class);
                        startActivityForResult(intent1, 25);
                    }
                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {
                        if(response.isPermanentlyDenied()){
                            AlertDialog.Builder builder = new AlertDialog.Builder(EditActivity.this);
                            builder.setTitle("Permission Denied")
                                    .setMessage("Permission to access device location is permanently denied. you need to go to setting to allow the permission.")
                                    .setNegativeButton("Cancel", null)
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Intent intent = new Intent();
                                            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                            intent.setData(Uri.fromParts("package", getPackageName(), null));
                                        }
                                    })
                                    .show();
                        } else {
                            Toast.makeText(EditActivity.this, "Permission Denied", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                })
                .check();
    }
}
