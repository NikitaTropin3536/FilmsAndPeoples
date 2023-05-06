package com.example.testfilms;

// класс фильма
public class Movie {

    private String title;
    private String poster;
    private String overView;
    private Double rating;

    public Movie(String title, Double rating,
                 String overView, String poster) {
        this.title = title;
        this.poster = poster;
        this.overView = overView;
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public String getPoster() {
        return poster;
    }

    public String getOverView() {
        return overView;
    }

    public Double getRating() {
        return rating;
    }
}