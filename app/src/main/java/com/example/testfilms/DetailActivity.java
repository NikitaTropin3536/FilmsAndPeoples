package com.example.testfilms;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    private ImageView moviePoster;
    private TextView movieTitle;
    private TextView rating_tv;
    private TextView overview_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        moviePoster = findViewById(R.id.moviePoster);
        movieTitle = findViewById(R.id.movieTitle);
        rating_tv = findViewById(R.id.rating_tv);
        overview_tv = findViewById(R.id.description);

        Bundle bundle = getIntent().getExtras();

        String mMovieTitle = bundle.getString("title");
        String mPoster = bundle.getString("poster");
        String mOver = bundle.getString("overview");
        Double mRating = bundle.getDouble("rating");

        Glide.with(this).load(mPoster).into(moviePoster);
        movieTitle.setText(mMovieTitle);
        rating_tv.setText(mRating.toString());
        overview_tv.setText(mOver);
    }
}