package com.example.testsecondfilms.movieapp.repositories;

import androidx.lifecycle.LiveData;

import com.example.testsecondfilms.movieapp.models.MovieModel;
import com.example.testsecondfilms.movieapp.request.MovieApiClient;

import java.util.List;

public class MovieRepository {
    // This class as acting as repositories

    private static MovieRepository instance;

    private MovieApiClient movieApiClient;

    private String mQuery;
    private int mPageNumber;

    public static MovieRepository getInstance() {
        if (instance == null) {
            instance = new MovieRepository();
        }
        return instance;
    }

    private MovieRepository() {
        movieApiClient = MovieApiClient.getInstance();
    }

    public LiveData<List<MovieModel>> getMovies() {
        return movieApiClient.getMovies();
    }

    // Calling the method
    public void searchMovieApi(String query, int pageNumber) {
        mQuery = query;
        mPageNumber = pageNumber;

        movieApiClient.searchMoviesApi(mQuery, mPageNumber);
    }

    public void searchNextPage() {
        searchMovieApi(mQuery, mPageNumber + 1);
    }
}