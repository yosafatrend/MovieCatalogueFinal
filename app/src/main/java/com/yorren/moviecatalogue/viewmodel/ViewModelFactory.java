package com.yorren.moviecatalogue.viewmodel;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.yorren.moviecatalogue.data.source.CatalogueRepository;
import com.yorren.moviecatalogue.di.Injection;
import com.yorren.moviecatalogue.ui.detail.DetailContentViewModel;
import com.yorren.moviecatalogue.ui.favorites.FavoriteViewModel;
import com.yorren.moviecatalogue.ui.movies.MoviesViewModel;
import com.yorren.moviecatalogue.ui.tvshows.TvShowsViewModel;

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private static volatile ViewModelFactory INSTANCE;

    private final CatalogueRepository catalogueRepository;

    public ViewModelFactory(CatalogueRepository catalogueRepository) {
        this.catalogueRepository = catalogueRepository;
    }

    public static ViewModelFactory getInstance(Context context){
        if (INSTANCE == null){
            synchronized (ViewModelFactory.class){
                if (INSTANCE == null){
                    INSTANCE = new ViewModelFactory(Injection.provideRepository(context));
                }
            }
        }
        return INSTANCE;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(DetailContentViewModel.class)) {
            return (T) new DetailContentViewModel(catalogueRepository);
        } else if (modelClass.isAssignableFrom(MoviesViewModel.class)) {
            return (T) new MoviesViewModel(catalogueRepository);
        } else if (modelClass.isAssignableFrom(TvShowsViewModel.class)) {
            return (T) new TvShowsViewModel(catalogueRepository);
        } else if (modelClass.isAssignableFrom(FavoriteViewModel.class)) {
            return (T) new FavoriteViewModel(catalogueRepository);
        }
        throw new IllegalArgumentException("ViewModel " + modelClass.getName() + " notfound.");
    }

}
