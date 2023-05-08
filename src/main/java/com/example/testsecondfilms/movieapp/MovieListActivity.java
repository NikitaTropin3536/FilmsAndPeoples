package com.example.testsecondfilms.movieapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testsecondfilms.databinding.ActivityMovieListBinding;
import com.example.testsecondfilms.movieapp.adapters.MovieRecyclerViewAdapter;
import com.example.testsecondfilms.movieapp.adapters.OnMovieListener;
import com.example.testsecondfilms.movieapp.models.MovieModel;
import com.example.testsecondfilms.movieapp.request.SpecialService;
import com.example.testsecondfilms.movieapp.response.MovieSearchResponse;
import com.example.testsecondfilms.movieapp.utils.Credentials;
import com.example.testsecondfilms.movieapp.utils.MovieApi;
import com.example.testsecondfilms.movieapp.viewmodels.MovieListViewModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieListActivity extends AppCompatActivity implements OnMovieListener {

    private ActivityMovieListBinding binding;

    private MovieRecyclerViewAdapter adapter;

    // View Model
    private MovieListViewModel movieListViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMovieListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolBar);

        movieListViewModel = new ViewModelProvider(this).get(MovieListViewModel.class);

        ConfigureRercyclerView();
        ObserveAnyChange();
        SetupSearchView();
    }

    // Observing any data change
    private void ObserveAnyChange() {
        movieListViewModel.getMovies().observe(this,
                new Observer<List<MovieModel>>() {
                    @Override
                    public void onChanged(List<MovieModel> movieModels) {
                        // Observing for any data change

                        if(movieModels != null) {
                            for (MovieModel movieModel: movieModels) {
                                // Get the data in log
                                Log.v("Tag", "onChanged: " + movieModel.getTitle());

                                adapter.setmMovies(movieModels);

                            }
                        }
                    }
                });
    }

    // Initializing recyclerView & adding data to it
    private void ConfigureRercyclerView() {
        // Live Data can't be passed via the constructor
        adapter = new MovieRecyclerViewAdapter(this);
        binding.recyclerView.setAdapter(adapter); // ставим наш adapter
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this)); // layoutManager

        // RecyclerView Pagination
        // Loading next page of api response
        binding.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                if (!binding.recyclerView.canScrollVertically(1)) {
                    // Here we need to display the next search results on the next page of api
                    movieListViewModel.searchNextPage();


                }
            }
        });


    }

    @Override
    public void onMovieClick(int position) {
//        Toast.makeText(this, "The Posiion" + position,
//                Toast.LENGTH_SHORT).show();

        // We don't need position of the movie in recyclerView
        // WE NEED THE ID OF THE MOVIE IN ORDER TO GET ALL IT'S DETAILS

        Intent intent = new Intent(this,
                MovieDetailsActivity.class);

        intent.putExtra("movie", adapter.getSelectedMovie(position));
        startActivity(intent);
    }

    @Override
    public void onCategoryClick(String category) {

    }

    // get data from searchView & query the ap to get the results
    private void SetupSearchView() {
        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                movieListViewModel.searchMovieApi(
                        // The search string getted from searchView
                        query,
                        1

                );
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

//    private void getRetrofitResponse() {
//        MovieApi movieApi = SpecialService.getMovieApi();
//
//        Call<MovieSearchResponse> responseCall = movieApi.searchMovie(
//                Credentials.API_KEY,
//                "Jack Reacher",
//                "1");
//
//        // todo - enqueue()
//        responseCall.enqueue(new Callback<MovieSearchResponse>() {
//            @Override
//            public void onResponse(Call<MovieSearchResponse> call,
//                                   Response<MovieSearchResponse> response) {
//                if (response.code() == 200) {
//                    Log.v("Tag", "the response " + response.body().toString());
//
//                    List<MovieModel> movies = new ArrayList<>(response
//                            .body().getMovies());
//
//                    for (MovieModel movie : movies) {
//                        Log.v("Tag", "The release date" + movie.getReleaseDate());
//                    }
//                } else {
//                    try {
//                        Log.v("Tag", "Error" + response.errorBody().string());
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<MovieSearchResponse> call, Throwable t) {
//                t.printStackTrace();
//            }
//        });
//    }
//
//    private void getRetrofitResponseAccordingToID() {
//        MovieApi movieApi = SpecialService.getMovieApi();
//
//        Call<MovieModel> responseCall = movieApi.getMovie(
//                550, // id - шник фильма (в данном случае - Бойцовский клуб)
//                Credentials.API_KEY
//        );
//
//        responseCall.enqueue(new Callback<MovieModel>() {
//            @Override
//            public void onResponse(Call<MovieModel> call,
//                                   Response<MovieModel> response) {
//                if (response.code() == 200) {
//                    MovieModel movie = response.body();
//                    Log.v("Tag", "The Response " + movie.getTitle());
//                } else {
//                    try {
//                        Log.v("Tag", "Error" + response.errorBody().string());
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<MovieModel> call, Throwable t) {
//
//            }
//        });
//    }

}