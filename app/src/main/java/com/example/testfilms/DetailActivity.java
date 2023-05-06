package com.example.testfilms;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    private ImageView moviePoster;

    private TextView movieTitle;
    private TextView movieRating;
    private TextView movieOverView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        moviePoster = findViewById(R.id.moviePoster);
        movieTitle = findViewById(R.id.movieTitle);
        movieRating = findViewById(R.id.movieRating);
        movieOverView = findViewById(R.id.movieOverView);

        Bundle bundle = getIntent().getExtras();

        String mPoster = bundle.getString("poster");
        Double mRating = bundle.getDouble("rating");
        String mMovieTitle = bundle.getString("title");
        String mOver = bundle.getString("overview");

        Glide.with(this).load(mPoster).into(moviePoster);
        movieTitle.setText(mMovieTitle);
        movieRating.setText(mRating.toString());
        movieOverView.setText(mOver);
    }
}