package com.example.moviego;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;


public class DetailActivity extends AppCompatActivity {


    Button btn_like;
    int i = 0;
    DBmanager  dbManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);



        Intent intent = getIntent();
        final String title = intent.getStringExtra("title");
        final String original_title = intent.getStringExtra("original_title");
        final String poster_path = intent.getStringExtra("poster_path");
        final String overview = intent.getStringExtra("overview");
        final String release_date = intent.getStringExtra("release_date");

        TextView textView_title = (TextView) findViewById(R.id.tv_title);
        textView_title.setText(title);
        TextView textView_original_title = (TextView) findViewById(R.id.tv_original_title);
        textView_original_title.setText(original_title);
        ImageView imageView_poster = (ImageView) findViewById(R.id.iv_poster);
        Glide.with(this)
                .load(poster_path)
                .centerCrop()
                .crossFade()
                .into(imageView_poster);

        TextView textView_overview = (TextView) findViewById(R.id.tv_overview);
        textView_overview.setText(overview);
        TextView textView_release_date = (TextView) findViewById(R.id.tv_release_date);
        textView_release_date.setText(release_date);


        btn_like = (Button) findViewById(R.id.btn_like);
        btn_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = 1 - i;

                if(i==0){

                    btn_like.setBackgroundResource(R.drawable.ic_grade_black_24dp);

                    ContentValues contentValues = new ContentValues();
                    contentValues.put("title", title);
                    contentValues.put("original_title", original_title);
                    contentValues.put("overview", overview);
                    contentValues.put("release_date", release_date);
                    contentValues.put("poster_path", poster_path);

                    long insertId  = dbManager.insert(contentValues);


                }
                else{
                    btn_like.setBackgroundResource(R.drawable.ic_star_border_black_24dp);

                    int deleteCnt = dbManager.delete("_id>1", null);

                }
            }
        });
    }
}
