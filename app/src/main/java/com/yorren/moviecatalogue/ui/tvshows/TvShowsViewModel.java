package com.yorren.moviecatalogue.ui.tvshows;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

import com.yorren.moviecatalogue.data.source.local.entity.TvShowsEntity;
import com.yorren.moviecatalogue.data.source.CatalogueRepository;
import com.yorren.moviecatalogue.vo.Resource;

import java.util.List;

public class TvShowsViewModel extends ViewModel {
    private CatalogueRepository catalogueRepository;

    public TvShowsViewModel(CatalogueRepository catalogueRepository) {
        this.catalogueRepository = catalogueRepository;
    }

    public LiveData<Resource<PagedList<TvShowsEntity>>> getTvShows(String apiKey, String language, String page) {
        return catalogueRepository.getAllTvShows(apiKey, language, page);
    }}
