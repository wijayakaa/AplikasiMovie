package com.example.apiddb;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class detailPage extends AppCompatActivity {

    Intent i;
    movieModel MovieModel;
    private TextView tvname, deskripsi;
    private ImageView imageview;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_page);

        tvname = findViewById(R.id.tvname);
        deskripsi = findViewById(R.id.tvdeskripsi);
        imageview = findViewById(R.id.movieimage);

        i = getIntent();
        MovieModel = i.getParcelableExtra("mymovie");

        if (MovieModel != null) {
            tvname.setText(MovieModel.getFilmName());
            deskripsi.setText(MovieModel.getDeskripsi());
            Glide.with(this).load("https://image.tmdb.org/t/p/w500" + MovieModel.getLogoFilm()).into(imageview);
        }
    }
}
