package com.example.testsecondfilms.movieapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MovieModel implements Parcelable {

    /**
     * Parcelable - interface for transmitting objects
     * (интерфейс для передачи объектов)
     */
    // Model class for our movies

    /**
     * todo добавить в поля ссылку на сайт,
     * todo где можно этот фильм посмотреть
     */

    private String title;
    private String posterPath;
    private String releaseDate;

    @SerializedName("id")
    private int movieId;
    private float voteAverage; // rating

    @SerializedName("overView")
    @Expose
    private String movieOverView;

    private String original_language;

    public MovieModel(String title, String posterPath,
                      String releaseDate, int movieId,
                      float voteAverage, String movieOverView,
                      String original_language) {
        this.title = title;
        this.posterPath = posterPath;
        this.releaseDate = releaseDate;
        this.movieId = movieId;
        this.voteAverage = voteAverage;
        this.movieOverView = movieOverView;
        this.original_language = original_language;
    }

    protected MovieModel(Parcel in) {
        // получаем данные из Parcel

        title = in.readString();
        posterPath = in.readString();
        releaseDate = in.readString();
        movieId = in.readInt();
        voteAverage = in.readFloat();
        movieOverView = in.readString();
        original_language = in.readString();
    }

    // статическое поле CREATOR, которое представляет объект Creator<MovieModel>
    public static final Creator<MovieModel> CREATOR = new Creator<MovieModel>() {

        // объект CREATOR реализует 2 метода

        // создает из Parcel новый объект типа MovieModel
        @Override
        public MovieModel createFromParcel(Parcel in) {
            return new MovieModel(in);
        }

        // создает масси объект MovieModel
        @Override
        public MovieModel[] newArray(int size) {
            return new MovieModel[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public int getMovieId() {
        return movieId;
    }

    public float getVoteAverage() {
        return voteAverage;
    }

    public String getMovieOverView() {
        return movieOverView;
    }

    public String getOriginal_language() {
        return original_language;
    }

    /**
     * метод описывает объекты, которые описывают интерфейс
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * упаковывает объект для передачи
     */
    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(posterPath);
        dest.writeString(releaseDate);
        dest.writeInt(movieId);
        dest.writeFloat(voteAverage);
        dest.writeString(movieOverView);
        dest.writeString(original_language);
    }

    @Override
    public String toString() {
        return "MovieModel{" +
                "title='" + title + '\'' +
                ", posterPath='" + posterPath + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", movieId=" + movieId +
                ", voteAverage=" + voteAverage +
                ", movieOverView='" + movieOverView + '\'' +
                ", original_language='" + original_language + '\'' +
                '}';
    }
}