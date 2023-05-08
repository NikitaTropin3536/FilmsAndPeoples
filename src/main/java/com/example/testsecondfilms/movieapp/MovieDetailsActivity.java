package com.example.testsecondfilms.movieapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.example.testsecondfilms.R;
import com.example.testsecondfilms.databinding.ActivityMovieDetailsBinding;
import com.example.testsecondfilms.movieapp.models.MovieModel;

public class MovieDetailsActivity extends AppCompatActivity {

    private ActivityMovieDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMovieDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



    }

    private void GetDataFromIntent() {
        if (getIntent().hasExtra("movie")) {
            MovieModel movieModel = getIntent().getParcelableExtra("movie");
            Log.v("Tag", "incoming intent" + movieModel.getTitle());

            binding.textViewTitleDetails.setText(movieModel.getTitle());

            binding.textViewDescription.setText(movieModel.getMovieOverView());

            binding.ratingBar.setRating(movieModel.getVoteAverage());

            Log.v("Tag", "" + movieModel.getMovieOverView());

            Glide.with(this)
                    .load("https://image.tmdb.org/t/p/w500/"
                    + movieModel.getPosterPath()).into(binding.imageViewDetails);
        }
    }
}

// Now let's make our app look more professional
// editing the layout od details activity