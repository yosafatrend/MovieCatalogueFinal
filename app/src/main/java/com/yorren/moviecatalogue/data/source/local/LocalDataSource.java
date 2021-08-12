package com.yorren.moviecatalogue.data.source.local;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;

import com.yorren.moviecatalogue.data.source.local.entity.MovieEntity;
import com.yorren.moviecatalogue.data.source.local.entity.TvShowsEntity;
import com.yorren.moviecatalogue.data.source.local.room.CatalogueDao;

import java.util.List;

public class LocalDataSource {

    private static LocalDataSource INSTANCE;
    private final CatalogueDao mCatalogueDao;

    public LocalDataSource(CatalogueDao mCatalogueDao) {
        this.mCatalogueDao = mCatalogueDao;
    }

    public static LocalDataSource getInstance(CatalogueDao catalogueDao){
        if (INSTANCE == null){
            INSTANCE = new LocalDataSource(catalogueDao);
        }
        return INSTANCE;
    }

    //movies
    public DataSource.Factory<Integer, MovieEntity> getAllMovies(){
        return mCatalogueDao.getMovies();
    }

    public DataSource.Factory<Integer, MovieEntity> getFavoriteMovies(){
        return mCatalogueDao.getFavoriteMovie();
    }

    public LiveData<MovieEntity> getMovieById(String movieId){
        return mCatalogueDao.getMovieById(movieId);
    }

    public void insertMovies(List<MovieEntity> movies){
        mCatalogueDao.insertMovies(movies);
    }

    public void updateMovies(MovieEntity movies){
        mCatalogueDao.updateMovie(movies);
    }

    public void setMovieFavorite(MovieEntity movie, boolean newState) {
        movie.setFavorite(newState);
        mCatalogueDao.updateMovie(movie);
    }

    //tvshows
    public DataSource.Factory<Integer, TvShowsEntity> getAllTvShows(){
        return mCatalogueDao.getTvShows();
    }

    public DataSource.Factory<Integer, TvShowsEntity> getFavoriteTvShows(){
        return mCatalogueDao.getFavoriteTvShows();
    }

    public LiveData<TvShowsEntity> getTvShowsById(String tvId){
        return mCatalogueDao.getTvShowsById(tvId);
    }

    public void insertTvShows(List<TvShowsEntity> tvShows){
        mCatalogueDao.insertTvShows(tvShows);
    }

    public void updateTvShows(TvShowsEntity tvShow){
        mCatalogueDao.updateTvShows(tvShow);
    }

    public void setTvFavorite(TvShowsEntity tvShow, boolean newState) {
        tvShow.setFavorite(newState);
        mCatalogueDao.updateTvShows(tvShow);
    }
}
