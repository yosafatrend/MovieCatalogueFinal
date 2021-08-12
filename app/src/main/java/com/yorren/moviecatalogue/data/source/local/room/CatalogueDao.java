package com.yorren.moviecatalogue.data.source.local.room;

import androidx.annotation.WorkerThread;
import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.yorren.moviecatalogue.data.source.local.entity.MovieEntity;
import com.yorren.moviecatalogue.data.source.local.entity.TvShowsEntity;

import java.util.List;

@Dao
public interface CatalogueDao {
    //movies
    @Query("SELECT * FROM movies")
    DataSource.Factory<Integer, MovieEntity> getMovies();

    @Query("SELECT * FROM movies WHERE is_favorite = 1")
    DataSource.Factory<Integer, MovieEntity> getFavoriteMovie();

    @Query("SELECT * FROM movies WHERE id = :movieId")
    LiveData<MovieEntity> getMovieById(String movieId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMovies(List<MovieEntity> movies);

    @Update
    void updateMovie(MovieEntity movie);

    //tvshows
    @Query("SELECT * FROM tvshows")
    DataSource.Factory<Integer, TvShowsEntity> getTvShows();

    @Query("SELECT * FROM tvshows WHERE is_favorite = 1")
    DataSource.Factory<Integer, TvShowsEntity> getFavoriteTvShows();

    @Query("SELECT * FROM tvshows WHERE id = :tvId")
    LiveData<TvShowsEntity> getTvShowsById(String tvId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTvShows(List<TvShowsEntity> tvShows);

    @Update
    void updateTvShows(TvShowsEntity tvShows);
}
