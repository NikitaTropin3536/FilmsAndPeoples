package com.example.testsecondfilms.movieapp.utils;

import com.example.testsecondfilms.movieapp.models.MovieModel;
import com.example.testsecondfilms.movieapp.response.MovieSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieApi {

    // Search for movies
    // https://api.themoviedb.org/3/search/movie?api_key={api_key}&query=Jack+Reacher
    @GET
    Call<MovieSearchResponse> searchMovie(
            @Query("api_key") String key,
            @Query("query") String query, // запрос - конкретный фильм
            @Query("page") String page // страничка
    );

    // todo прочитать все про аннотации
    // Making Search with ID
    // https://api.themoviedb.org/3/movie/550?api_key=9b959e6f21d1e770c408fb15df65b56f
    // Remember that movie_id = 550 is for Jack Reacher
    @GET("3/movie/{movie_id}?")
    Call<MovieModel> getMovie(
            @Path("movie_id") int movie_id, // id фильма
            @Query("api_key") String api_key
    );
}