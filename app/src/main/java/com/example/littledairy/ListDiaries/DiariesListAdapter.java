package com.example.littledairy.ListDiaries;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.littledairy.Data.Diary;
import com.example.littledairy.EditDiary.EditActivity;
import com.example.littledairy.MainActivity;
import com.example.littledairy.R;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

public class DiariesListAdapter extends RecyclerView.Adapter<DiariesListAdapter.listViewHolder>{
    Context context ;
    List<Diary> diaries;

    public DiariesListAdapter(Context context)
    {
     this.context = context;
    }

    public void setDiaries(List<Diary> diaryArrayList){
        diaries = diaryArrayList;
        notifyDataSetChanged();
    }
    public void setDiaries(Diary diary){
        diaries.clear();
        if(diary != null)
        diaries.add(diary);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public listViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.diary_card, parent, false);
        return new listViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DiariesListAdapter.listViewHolder holder, int position) {
        holder.onBind(diaries.get(position));
    }

    @Override
    public int getItemCount() {
        return diaries == null? 0:diaries.size();
    }

    class listViewHolder extends RecyclerView.ViewHolder {
        ImageView diaryImage , mapImage ;
        TextView diaryText , diaryDate;
        Button editButton;
        Diary diary;
       public listViewHolder (View view) {
           super(view);
           diaryImage = view.findViewById(R.id.diaryCard_imageView);
           mapImage = view.findViewById(R.id.diaryCard_map);
           diaryDate = view.findViewById(R.id.diaryCard_date);
           diaryText = view.findViewById(R.id.diaryCard_text);
           editButton = view.findViewById(R.id.diaryCard_editButton);
           view.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   Intent intent = new Intent(context, MainActivity.class);
                   intent.putExtra("diary",diary);
                   Log.i("OnClickView","view event");
                   context.startActivity(intent);
               }
           });
           editButton.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   Intent intent = new Intent(context, EditActivity.class);
                   intent.putExtra("diary",diary);
                   Log.i("onclickEditBtn", diary.getDailyText());
                   context.startActivity(intent);
               }
           });
       }
           void onBind(Diary diary){
               this.diary = diary;
               diaryDate.setText(diary.getDiaryDate().toString());
               if(diary.getDailyText() != null){
                   diaryText.setText(diary.getDailyText());
               }else{
                   diaryText.setText(context.getResources().getString(R.string.noText));
               }
               if(diary.getImagePath() != null){
                   Handler uiHandler = new Handler(Looper.getMainLooper());
                   uiHandler.post(new Runnable(){
                       @Override
                       public void run() {
                           File f=new File(diary.getImagePath());
                           Picasso.get().load(f).placeholder(R.drawable.ic_baseline_broken_image_24px).into(diaryImage);
                       }
                   });
               }
               if(diary.getPlaceName() == null){
                   mapImage.setVisibility(View.INVISIBLE);
               }
       }

    }
}
