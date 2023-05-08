package com.example.testsecondfilms.movieapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.testsecondfilms.R;
import com.example.testsecondfilms.movieapp.models.MovieModel;

import java.util.List;

import retrofit2.http.POST;

public class MovieRecyclerViewAdapter extends RecyclerView.Adapter<MovieRecyclerViewAdapter.MovieViewHolder> {

    private List<MovieModel> mMovies;
    private OnMovieListener onMovieListener;

    public MovieRecyclerViewAdapter(OnMovieListener onMovieListener) {
        this.onMovieListener = onMovieListener;
    }

    @NonNull
    @Override
    public MovieRecyclerViewAdapter.MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_item,
                parent, false);
        return new MovieViewHolder(view, onMovieListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieRecyclerViewAdapter.MovieViewHolder holder,
                                 int position) {
        ((MovieViewHolder)holder).title.setText(mMovies.get(position).getTitle());
        ((MovieViewHolder)holder).releaseDate.setText(mMovies.get(position).getReleaseDate());

        // This is an error in runtime duration
        // Let's fix this error
        ((MovieViewHolder)holder).duration.setText(mMovies.get(position).getOriginal_language());

        // vote average id over 10, and our rating bar is over 5 stars: dividing by 2
        ((MovieViewHolder)holder).ratingBar.setRating((mMovies.get(position).getVoteAverage()) / 2);

        // ImageView: Using Glide Library
        Glide.with(holder.itemView.getContext())
                .load("https://image.tmdb.org/t/p/w500/" +
                        mMovies.get(position))
                .into(((MovieViewHolder)holder).imageView);
    }

    @Override
    public int getItemCount() {
        if (mMovies != null) {
            return mMovies.size();
        }
        return 0;
    }

    public void setmMovies(List<MovieModel> mMovies) {
        this.mMovies = mMovies;
        notifyDataSetChanged();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView title, releaseDate, duration;
        private ImageView imageView;
        private RatingBar ratingBar;

        private OnMovieListener onMovieListener;

        public MovieViewHolder(@NonNull View itemView, OnMovieListener onMovieListener) {
            super(itemView);

            this.onMovieListener = onMovieListener;

            title = itemView.findViewById(R.id.movieTitle);
            releaseDate = itemView.findViewById(R.id.movieCategory);
            duration = itemView.findViewById(R.id.movieDuration);
            imageView = itemView.findViewById(R.id.movieImg);
            ratingBar = itemView.findViewById(R.id.ratingBar);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onMovieListener.onMovieClick(getAdapterPosition());
        }
    }

    // Getting the id of the movie clicked
    public MovieModel getSelectedMovie(int position) {
        if (mMovies != null) {
            if (mMovies.size() > 0) {
                return mMovies.get(position);
            }
        }
        return null;
    }
}