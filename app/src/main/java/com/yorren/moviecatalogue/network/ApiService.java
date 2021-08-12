package com.yorren.moviecatalogue.network;

import com.yorren.moviecatalogue.data.source.remote.response.ResponseDetailMovie;
import com.yorren.moviecatalogue.data.source.remote.response.ResponseDetailTv;
import com.yorren.moviecatalogue.data.source.remote.response.ResponseMovies;
import com.yorren.moviecatalogue.data.source.remote.response.ResponseTv;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @GET("movie/popular")
    Call<ResponseMovies> getMovie(@Query("api_key") String key,
                                  @Query("language") String lang,
                                  @Query("page") String page
    );

    @GET("tv/popular")
    Call<ResponseTv> getTvShows(@Query("api_key") String key,
                              @Query("language") String lang,
                              @Query("page") String page
    );

    @GET("movie/{id}")
    Call<ResponseDetailMovie> getDetailMovie(
            @Path("id") String id,
            @Query("api_key") String key,
            @Query("language") String lang
    );

    @GET("tv/{id}")
    Call<ResponseDetailTv> getDetailTv(
            @Path("id") String id,
            @Query("api_key") String key,
            @Query("language") String lang
    );


}
