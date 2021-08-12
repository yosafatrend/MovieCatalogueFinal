package com.yorren.moviecatalogue.ui.detail;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.yorren.moviecatalogue.BuildConfig;
import com.yorren.moviecatalogue.data.source.local.entity.MovieEntity;
import com.yorren.moviecatalogue.data.source.local.entity.TvShowsEntity;
import com.yorren.moviecatalogue.data.source.CatalogueRepository;
import com.yorren.moviecatalogue.vo.Resource;

import static android.content.ContentValues.TAG;

public class DetailContentViewModel extends ViewModel {
    private MovieEntity mMovieEntity;
    private TvShowsEntity mTvEntity;

    private MutableLiveData<String> tvShowId = new MutableLiveData<>();
    private MutableLiveData<String> movieId = new MutableLiveData<>();

    private CatalogueRepository catalogueRepository;

    public DetailContentViewModel(CatalogueRepository catalogueRepository) {
        this.catalogueRepository = catalogueRepository;
    }

    public String getTvShowId(){
        return tvShowId.getValue();
    }

    public void setTvShowId(String tvShowId) {
        this.tvShowId.setValue(tvShowId);
    }

    public String getMovieId(){
        return movieId.getValue();
    }

    public void setMovieId(String movieId) {
        this.movieId.setValue(movieId);
    }

    public LiveData<Resource<MovieEntity>> getMovieDetail = Transformations.switchMap(movieId,
            mMovieId -> catalogueRepository.getDetailMovie(mMovieId, BuildConfig.API_KEY, BuildConfig.LANGUAGE));

    public LiveData<Resource<TvShowsEntity>> getTvShowDetail = Transformations.switchMap(tvShowId,
            mTvShowId -> catalogueRepository.getDetailTvShow(mTvShowId, BuildConfig.API_KEY, BuildConfig.LANGUAGE));

    void setFavoriteMovie(){
        if (getMovieDetail.getValue() != null) {
            MovieEntity movieEntity = getMovieDetail.getValue().data;
            Log.e(TAG, "setFavorited: " + movieEntity );

            assert movieEntity != null;
            final boolean newState = !movieEntity.isFavorite();
            Log.e(TAG, "setFavoritedState: "+newState);

            catalogueRepository.setMovieFavorite(movieEntity, newState);
        }
    }

    void setFavoriteTv(){
        if (getTvShowDetail.getValue() != null) {
            TvShowsEntity tvShowsEntity = getTvShowDetail.getValue().data;
            Log.e(TAG, "setFavorited: " + tvShowsEntity );

            assert tvShowsEntity != null;
            final boolean newState = !tvShowsEntity.isFavorite();
            Log.e(TAG, "setFavoritedState: "+newState);

            catalogueRepository.setTvFavorite(tvShowsEntity, newState);
        }
    }
}
