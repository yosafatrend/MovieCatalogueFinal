package com.yorren.moviecatalogue.ui.movies;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

import com.yorren.moviecatalogue.data.source.local.entity.MovieEntity;
import com.yorren.moviecatalogue.data.source.CatalogueRepository;
import com.yorren.moviecatalogue.vo.Resource;

import java.util.List;

public class MoviesViewModel extends ViewModel {
    private CatalogueRepository catalogueRepository;

    public MoviesViewModel(CatalogueRepository catalogueRepository) {
        this.catalogueRepository = catalogueRepository;
    }

    public LiveData<Resource<PagedList<MovieEntity>>> getMovies(String apiKey, String language, String page) {
        return catalogueRepository.getAllMovies(apiKey, language, page);
    }
}
