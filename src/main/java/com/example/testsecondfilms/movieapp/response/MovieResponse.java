package com.example.testsecondfilms.movieapp.response;

import com.example.testsecondfilms.movieapp.models.MovieModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

// this class is for single movie request
public class MovieResponse {
    // 1 - Finding the movie object

    @SerializedName("results") // todo что делает эта аннотация
    // results - массив с данными, которые приходит в результате запроса
    @Expose // todo что делает эта аннотация

    private MovieModel movie;

    public MovieModel getMovie() {
        return movie;
    }

    @Override
    public String toString() {
        return "MovieResponse{" +
                "movie=" + movie +
                '}';
    }
}