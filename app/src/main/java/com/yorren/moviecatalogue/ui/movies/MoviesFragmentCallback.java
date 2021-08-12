package com.yorren.moviecatalogue.ui.movies;

import com.yorren.moviecatalogue.data.source.local.entity.MovieEntity;
import com.yorren.moviecatalogue.data.source.remote.response.ResultsMovie;

public interface MoviesFragmentCallback {
    void onShareClick(MovieEntity resultsItem);
}
