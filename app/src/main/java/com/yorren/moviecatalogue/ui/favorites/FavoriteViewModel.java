package com.yorren.moviecatalogue.ui.favorites;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

import com.yorren.moviecatalogue.data.source.CatalogueRepository;
import com.yorren.moviecatalogue.data.source.local.entity.MovieEntity;
import com.yorren.moviecatalogue.data.source.local.entity.TvShowsEntity;

import java.util.List;

public class FavoriteViewModel extends ViewModel {
    private CatalogueRepository catalogueRepository;

    public FavoriteViewModel(CatalogueRepository mCatalogueRepository) {
        this.catalogueRepository = mCatalogueRepository;
    }

    public LiveData<PagedList<MovieEntity>> getFavoriteMovie() {
        return catalogueRepository.getFavoriteMovie();
    }

    public LiveData<PagedList<TvShowsEntity>> getFavoriteTvShow() {
        return catalogueRepository.getFavoriteTv();
    }

    public void setFavoriteMovie(MovieEntity movieEntity) {
        final boolean newState = !movieEntity.isFavorite();
        catalogueRepository.setMovieFavorite(movieEntity, newState);
    }

    public  void setFavoriteTvShow(TvShowsEntity tvShowsEntity) {
        final boolean newState = !tvShowsEntity.isFavorite();
        catalogueRepository.setTvFavorite(tvShowsEntity, newState);
    }
}
