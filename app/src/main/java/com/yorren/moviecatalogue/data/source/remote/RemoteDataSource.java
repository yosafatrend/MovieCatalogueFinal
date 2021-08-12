package com.yorren.moviecatalogue.data.source.remote;

import android.os.Handler;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.yorren.moviecatalogue.data.source.local.entity.MovieEntity;
import com.yorren.moviecatalogue.data.source.remote.response.ResponseDetailMovie;
import com.yorren.moviecatalogue.data.source.remote.response.ResponseDetailTv;
import com.yorren.moviecatalogue.data.source.remote.response.ResponseMovies;
import com.yorren.moviecatalogue.data.source.remote.response.ResponseTv;
import com.yorren.moviecatalogue.data.source.remote.response.ResultsMovie;
import com.yorren.moviecatalogue.data.source.remote.response.ResultsTv;
import com.yorren.moviecatalogue.network.ApiConfig;
import com.yorren.moviecatalogue.utils.EspressoIdlingResource;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RemoteDataSource {
    private static RemoteDataSource INSTANCE;
    private final long SERVICE_LATENCY_IN_MILIS = 2000;

    public static RemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RemoteDataSource();
        }
        return INSTANCE;
    }

    public LiveData<ApiResponse<List<ResultsMovie>>> getAllMovies(String apiKey, String language, String page){
        EspressoIdlingResource.increment();

        MutableLiveData<ApiResponse<List<ResultsMovie>>> resultsMovie = new MutableLiveData<>();
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            Call<ResponseMovies> call = ApiConfig.getApiService().getMovie(apiKey, language, page);
            call.enqueue(new Callback<ResponseMovies>() {
                @Override
                public void onResponse(Call<ResponseMovies> call, Response<ResponseMovies> response) {

                    resultsMovie.setValue(ApiResponse.success(response.body().getResults()));
                    EspressoIdlingResource.decrement();
                }

                @Override
                public void onFailure(Call<ResponseMovies> call, Throwable t) {
                    EspressoIdlingResource.decrement();
                }
            });
        }, SERVICE_LATENCY_IN_MILIS);
        return resultsMovie;
    }

    public LiveData<ApiResponse<ResponseDetailMovie>> getDetailMovie(String movieId, String apiKey, String language) {
        EspressoIdlingResource.increment();
        MutableLiveData<ApiResponse<ResponseDetailMovie>> detailMovie = new MutableLiveData<>();
        Handler handler = new Handler();
        Log.d("ASDF", "detailMovie.toString()");
        handler.postDelayed(() -> {
            Call<ResponseDetailMovie> call = ApiConfig.getApiService().getDetailMovie(movieId, apiKey, language);
            call.enqueue(new Callback<ResponseDetailMovie>() {
                @Override
                public void onResponse(Call<ResponseDetailMovie> call, Response<ResponseDetailMovie> response) {
                    detailMovie.setValue(ApiResponse.success(response.body()));
                    EspressoIdlingResource.decrement();
                }

                @Override
                public void onFailure(Call<ResponseDetailMovie> call, Throwable t) {
                    EspressoIdlingResource.decrement();
                }
            });
        }, SERVICE_LATENCY_IN_MILIS);
        return detailMovie;
    }

    public LiveData<ApiResponse<List<ResultsTv>>> getAllTvShow(String apiKey, String language, String page) {
        EspressoIdlingResource.increment();
        MutableLiveData<ApiResponse<List<ResultsTv>>> resultsTv = new MutableLiveData<>();
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            Call<ResponseTv> call = ApiConfig.getApiService().getTvShows(apiKey, language, page);
            call.enqueue(new Callback<ResponseTv>() {
                @Override
                public void onResponse(Call<ResponseTv> call, Response<ResponseTv> response) {
                    resultsTv.setValue(ApiResponse.success(response.body().getResults()));
                    EspressoIdlingResource.decrement();
                }

                @Override
                public void onFailure(Call<ResponseTv> call, Throwable t) {
                    EspressoIdlingResource.decrement();
                }
            });
        }, SERVICE_LATENCY_IN_MILIS);
        return resultsTv;
    }

    public LiveData<ApiResponse<ResponseDetailTv>> getDetailTvShow(String tvShowId, String apiKey, String language) {
        EspressoIdlingResource.increment();
        MutableLiveData<ApiResponse<ResponseDetailTv>> detailTv = new MutableLiveData<>();
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            Call<ResponseDetailTv> call = ApiConfig.getApiService().getDetailTv(tvShowId, apiKey, language);
            call.enqueue(new Callback<ResponseDetailTv>() {
                @Override
                public void onResponse(Call<ResponseDetailTv> call, Response<ResponseDetailTv> response) {
                    detailTv.setValue(ApiResponse.success(response.body()));
                    EspressoIdlingResource.decrement();
                }

                @Override
                public void onFailure(Call<ResponseDetailTv> call, Throwable t) {
                    EspressoIdlingResource.decrement();
                }
            });
        }, SERVICE_LATENCY_IN_MILIS);
        return detailTv;
    }

    public interface LoadMoviesCallback {
        void onMoviesReceived(List<ResultsMovie> movies);
        void onMoviesReceivedd(List<MovieEntity> movies);

        void onMoviesFailedReceived();
    }

    public interface LoadDetailMovieCallback {
        void onDetailMovieReceived(ResponseDetailMovie detailMovie);

        void onDetailMovieFailedReceived();
    }

    public interface LoadTvShowsCallback {
        void onTvShowsReceived(List<ResultsTv> tvShows);

        void onTvShowsFailedReceived();
    }

    public interface LoadDetailTvShowCallback {
        void onDetailTvShowReceived(ResponseDetailTv detailTvShow);

        void onDetailTvShowsFailedReceived();
    }
}
