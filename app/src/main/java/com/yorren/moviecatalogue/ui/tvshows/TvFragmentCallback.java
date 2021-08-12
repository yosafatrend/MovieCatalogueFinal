package com.yorren.moviecatalogue.ui.tvshows;

import com.yorren.moviecatalogue.data.source.local.entity.TvShowsEntity;
import com.yorren.moviecatalogue.data.source.remote.response.ResultsTv;

public interface TvFragmentCallback {
    void onShareClick(TvShowsEntity resultsTv);
}
