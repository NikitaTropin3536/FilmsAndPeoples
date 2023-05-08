package com.example.testsecondfilms.movieapp.response;

import com.example.testsecondfilms.movieapp.models.MovieModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

// This class is for getting multiple movies (Movies List) - popular movies
public class MovieSearchResponse {

    @SerializedName("results")
    @Expose()
    private int totalCount;

    @SerializedName("results")
    @Expose
    private List<MovieModel> movies;

    public int getTotalCount() {
        return totalCount;
    }

    public List<MovieModel> getMovies() {
        return movies;
    }

    @Override
    public String toString() {
        return "MovieSearchResponse{" +
                "totalCount=" + totalCount +
                ", movies=" + movies +
                '}';
    }
}