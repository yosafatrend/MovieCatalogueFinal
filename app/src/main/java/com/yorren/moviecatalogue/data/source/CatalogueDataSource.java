package com.yorren.moviecatalogue.data.source;

import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

import com.yorren.moviecatalogue.data.source.local.entity.MovieEntity;
import com.yorren.moviecatalogue.data.source.local.entity.TvShowsEntity;
import com.yorren.moviecatalogue.vo.Resource;

public interface CatalogueDataSource {
    LiveData<Resource<PagedList<MovieEntity>>> getAllMovies(String apiKey, String language, String page);
    LiveData<Resource<MovieEntity>> getDetailMovie(String movieId, String apiKey, String language);
    LiveData<PagedList<MovieEntity>> getFavoriteMovie();

    void setMovieFavorite(MovieEntity movie, boolean state);

    LiveData<Resource<PagedList<TvShowsEntity>>> getAllTvShows(String apiKey, String language, String page);
    LiveData<Resource<TvShowsEntity>> getDetailTvShow(String tvShowId, String apiKey, String language);
    LiveData<PagedList<TvShowsEntity>> getFavoriteTv();

    void setTvFavorite(TvShowsEntity tvshow, boolean state);
}
