package com.yorren.moviecatalogue.data.source.local.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;
import com.yorren.moviecatalogue.data.source.remote.response.GenresItem;

import java.util.List;

@Entity(tableName = "movies")
public class MovieEntity {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private String movieId;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "tagline")
    private String tagline;

    @Ignore
    private List<GenresItem> genre;

    @ColumnInfo(name = "release_date")
    private String releaseDate;

    @ColumnInfo(name = "user_score")
    private String userScore;

    @ColumnInfo(name = "backdrop_path")
    private String backdropPath;

    @ColumnInfo(name = "original_language")
    private String language;

    @ColumnInfo(name = "popularity")
    private String popularity;

    @ColumnInfo(name = "description")
    private String overview;

    @ColumnInfo(name = "image_path")
    private String imagePath;

    @ColumnInfo(name = "is_favorite")
    private boolean favorite;

    @Ignore
    public MovieEntity() {
    }

    public MovieEntity(@NonNull String movieId, String title, String tagline, String releaseDate, String userScore, String backdropPath, String language, String popularity, String overview, String imagePath, boolean favorite) {
        this.movieId = movieId;
        this.title = title;
        this.tagline = tagline;
        this.releaseDate = releaseDate;
        this.userScore = userScore;
        this.backdropPath = backdropPath;
        this.language = language;
        this.popularity = popularity;
        this.overview = overview;
        this.imagePath = imagePath;
        this.favorite = favorite;
    }

    @Ignore
    public MovieEntity(String movieId, String title, String tagline, List<GenresItem> genre, String releaseDate, String userScore, String overview, String imagePath) {
        this.movieId = movieId;
        this.title = title;
        this.tagline = tagline;
        this.genre = genre;
        this.releaseDate = releaseDate;
        this.userScore = userScore;
        this.overview = overview;
        this.imagePath = imagePath;
    }

    @Ignore
    public MovieEntity(String movieId, String title, String releaseDate, String overview, String imagePath) {
        this.movieId = movieId;
        this.title = title;
        this.releaseDate = releaseDate;
        this.overview = overview;
        this.imagePath = imagePath;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public List<GenresItem> getGenre() {
        return genre;
    }

    public void setGenre(List<GenresItem> genre) {
        this.genre = genre;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getUserScore() {
        return userScore;
    }

    public void setUserScore(String userScore) {
        this.userScore = userScore;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }
}
