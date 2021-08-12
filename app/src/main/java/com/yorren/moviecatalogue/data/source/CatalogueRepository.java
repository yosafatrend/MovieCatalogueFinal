package com.yorren.moviecatalogue.data.source;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.yorren.moviecatalogue.data.source.local.LocalDataSource;
import com.yorren.moviecatalogue.data.source.local.entity.MovieEntity;
import com.yorren.moviecatalogue.data.source.local.entity.TvShowsEntity;
import com.yorren.moviecatalogue.data.source.remote.ApiResponse;
import com.yorren.moviecatalogue.data.source.remote.RemoteDataSource;
import com.yorren.moviecatalogue.data.source.remote.response.ResponseDetailMovie;
import com.yorren.moviecatalogue.data.source.remote.response.ResponseDetailTv;
import com.yorren.moviecatalogue.data.source.remote.response.ResultsMovie;
import com.yorren.moviecatalogue.data.source.remote.response.ResultsTv;
import com.yorren.moviecatalogue.utils.AppExecutors;
import com.yorren.moviecatalogue.vo.Resource;

import java.util.ArrayList;
import java.util.List;

public class CatalogueRepository implements CatalogueDataSource {
    private volatile static CatalogueRepository INSTANCE = null;

    private final RemoteDataSource remoteDataSource;
    private final LocalDataSource localDataSource;
    private final AppExecutors appExecutors;

    public CatalogueRepository(@NonNull RemoteDataSource remoteDataSource, @NonNull LocalDataSource localDataSource, AppExecutors appExecutors) {
        this.remoteDataSource = remoteDataSource;
        this.localDataSource = localDataSource;
        this.appExecutors = appExecutors;
    }

    public static CatalogueRepository getInstance(RemoteDataSource remoteDataSource, LocalDataSource localDataSource, AppExecutors appExecutors) {
        if (INSTANCE == null) {
            INSTANCE = new CatalogueRepository(remoteDataSource, localDataSource, appExecutors);
        }
        return INSTANCE;
    }

    @Override
    public LiveData<Resource<PagedList<MovieEntity>>> getAllMovies(String apiKey, String language, String page) {
        return new NetworkBoundResource<PagedList<MovieEntity>, List<ResultsMovie>>(appExecutors){

            @Override
            protected LiveData<PagedList<MovieEntity>> loadFromDB() {
                PagedList.Config config = new PagedList.Config.Builder()
                        .setEnablePlaceholders(false)
                        .setInitialLoadSizeHint(20)
                        .setPageSize(20)
                        .build();
                return new LivePagedListBuilder<>(localDataSource.getAllMovies(), config).build();
            }

            @Override
            protected Boolean shouldFetch(PagedList<MovieEntity> data) {
                return (data == null) || (data.size() == 0);
            }

            @Override
            protected LiveData<ApiResponse<List<ResultsMovie>>> createCall() {
                return remoteDataSource.getAllMovies(apiKey, language, page);
            }

            @Override
            protected void saveCallResult(List<ResultsMovie> movies) {
                ArrayList<MovieEntity> movieEntityArrayList = new ArrayList<>();
                for (int i = 0; i < movies.size(); i++) {
                    ResultsMovie movieRemote = movies.get(i);
                    MovieEntity movieEntity = new MovieEntity(
                            movieRemote.getId(),
                            movieRemote.getTitle(),
                            "",
                            movieRemote.getReleaseDate(),
                            movieRemote.getVoteAverage(),
                            movieRemote.getBackdropPath(),
                            movieRemote.getLanguage(),
                            movieRemote.getPopularity(),
                            movieRemote.getOverview(),
                            movieRemote.getPosterPath(),
                            false
                    );
                    movieEntityArrayList.add(movieEntity);
                }
                localDataSource.insertMovies(movieEntityArrayList);
            }
        }.asLiveData();
    }

    @Override
    public LiveData<Resource<MovieEntity>> getDetailMovie(String movieId, String apiKey, String language) {
        return new NetworkBoundResource<MovieEntity, ResponseDetailMovie>(appExecutors){

            @Override
            protected LiveData<MovieEntity> loadFromDB() {
                return localDataSource.getMovieById(movieId);
            }

            @Override
            protected Boolean shouldFetch(MovieEntity data) {
                return (data == null);
            }

            @Override
            protected LiveData<ApiResponse<ResponseDetailMovie>> createCall() {
                return remoteDataSource.getDetailMovie(movieId, apiKey, language);
            }

            @Override
            protected void saveCallResult(ResponseDetailMovie detailMovie) {
                MovieEntity movieEntity = new MovieEntity(
                        detailMovie.getId(),
                        detailMovie.getTitle(),
                        detailMovie.getTagline(),
                        detailMovie.getReleaseDate(),
                        detailMovie.getBackdropPath(),
                        detailMovie.getLanguage(),
                        detailMovie.getPopularity(),
                        detailMovie.getVoteAverage(),
                        detailMovie.getOverview(),
                        detailMovie.getPosterPath(),
                        false
                );
                localDataSource.updateMovies(movieEntity);
            }
        }.asLiveData();
    }

    @Override
    public LiveData<PagedList<MovieEntity>> getFavoriteMovie() {
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(20)
                .setPageSize(20)
                .build();
        return new LivePagedListBuilder<>(localDataSource.getFavoriteMovies(), config).build();
    }

    @Override
    public void setMovieFavorite(MovieEntity movie, boolean state) {
        appExecutors.diskIO().execute(() -> localDataSource.setMovieFavorite(movie, state));
    }

    @Override
    public LiveData<Resource<PagedList<TvShowsEntity>>> getAllTvShows(String apiKey, String language, String page) {
        return new NetworkBoundResource<PagedList<TvShowsEntity>, List<ResultsTv>>(appExecutors){

            @Override
            protected LiveData<PagedList<TvShowsEntity>> loadFromDB() {
                PagedList.Config config = new PagedList.Config.Builder()
                        .setEnablePlaceholders(false)
                        .setInitialLoadSizeHint(20)
                        .setPageSize(20)
                        .build();
                return new LivePagedListBuilder<>(localDataSource.getAllTvShows(), config).build();
            }

            @Override
            protected Boolean shouldFetch(PagedList<TvShowsEntity> data) {
                return (data == null) || (data.size() == 0);
            }

            @Override
            protected LiveData<ApiResponse<List<ResultsTv>>> createCall() {
                return remoteDataSource.getAllTvShow(apiKey, language, page);
            }

            @Override
            protected void saveCallResult(List<ResultsTv> tvShows) {
                ArrayList<TvShowsEntity> tvShowsEntityArrayList = new ArrayList<>();
                for (int i = 0; i < tvShows.size(); i++) {
                    ResultsTv tvRemote = tvShows.get(i);
                    TvShowsEntity tvShowsEntity = new TvShowsEntity(
                            tvRemote.getId(),
                            tvRemote.getName(),
                            "",
                            tvRemote.getFirstAirDate(),
                            tvRemote.getUserScore(),
                            tvRemote.getBackdropPath(),
                            tvRemote.getLanguage(),
                            tvRemote.getPopularity(),
                            tvRemote.getOverview(),
                            tvRemote.getPosterPath(),
                            false
                    );
                    tvShowsEntityArrayList.add(tvShowsEntity);
                }
                localDataSource.insertTvShows(tvShowsEntityArrayList);
            }
        }.asLiveData();
    }

    @Override
    public LiveData<Resource<TvShowsEntity>> getDetailTvShow(String tvId, String apiKey, String language) {
        return new NetworkBoundResource<TvShowsEntity, ResponseDetailTv>(appExecutors){

            @Override
            protected LiveData<TvShowsEntity> loadFromDB() {
                return localDataSource.getTvShowsById(tvId);
            }

            @Override
            protected Boolean shouldFetch(TvShowsEntity data) {
                return (data == null);
            }

            @Override
            protected LiveData<ApiResponse<ResponseDetailTv>> createCall() {
                return remoteDataSource.getDetailTvShow(tvId, apiKey, language);
            }

            @Override
            protected void saveCallResult(ResponseDetailTv detailTvShow) {
                TvShowsEntity tvEntity = new TvShowsEntity(
                        detailTvShow.getId(),
                        detailTvShow.getName(),
                        detailTvShow.getTagline(),
                        detailTvShow.getFirstAirDate(),
                        detailTvShow.getVoteAverage(),
                        detailTvShow.getBackdropPath(),
                        detailTvShow.getLanguage(),
                        detailTvShow.getPopularity(),
                        detailTvShow.getOverview(),
                        detailTvShow.getPosterPath(),
                        false
                );
                localDataSource.updateTvShows(tvEntity);
            }
        }.asLiveData();
    }

    @Override
    public LiveData<PagedList<TvShowsEntity>> getFavoriteTv() {
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(20)
                .setPageSize(20)
                .build();
        return new LivePagedListBuilder<>(localDataSource.getFavoriteTvShows(), config).build();    }

    @Override
    public void setTvFavorite(TvShowsEntity tvshow, boolean state) {
        appExecutors.diskIO().execute(() -> localDataSource.setTvFavorite(tvshow, state));
    }
}
