package com.yorren.moviecatalogue.ui.favorites;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.paging.PagedList;

import com.yorren.moviecatalogue.data.source.CatalogueRepository;
import com.yorren.moviecatalogue.data.source.local.entity.MovieEntity;
import com.yorren.moviecatalogue.data.source.local.entity.TvShowsEntity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FavoriteViewModelTest {

    private FavoriteViewModel viewModel;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Mock
    private CatalogueRepository catalogueRepository;

    @Mock
    private Observer<PagedList<MovieEntity>> movieObserver;

    @Mock
    private Observer<PagedList<TvShowsEntity>> tvObserver;

    @Mock
    private PagedList<MovieEntity> pagedListMovie;

    @Mock
    private PagedList<TvShowsEntity> pagedListTvShow;

    @Before
    public void setUp() {
        viewModel = new FavoriteViewModel(catalogueRepository);
    }

    @Test
    public void getFavoriteMovie() {
        PagedList<MovieEntity> dummyMovie = pagedListMovie;
        when(dummyMovie.size()).thenReturn(5);
        MutableLiveData<PagedList<MovieEntity>> movies = new MutableLiveData<>();
        movies.setValue(dummyMovie);

        when(catalogueRepository.getFavoriteMovie()).thenReturn(movies);
        List<MovieEntity> movieEntities = viewModel.getFavoriteMovie().getValue();
        verify(catalogueRepository).getFavoriteMovie();
        assertNotNull(movieEntities);
        assertEquals(5, movieEntities.size());

        viewModel.getFavoriteMovie().observeForever(movieObserver);
        verify(movieObserver).onChanged(dummyMovie);
    }

    @Test
    public void getFavoriteTvShow() {
        PagedList<TvShowsEntity> dummyTvShow = pagedListTvShow;
        when(dummyTvShow.size()).thenReturn(5);
        MutableLiveData<PagedList<TvShowsEntity>> tvshow = new MutableLiveData<>();
        tvshow.setValue(dummyTvShow);

        when(catalogueRepository.getFavoriteTv()).thenReturn(tvshow);
        List<TvShowsEntity> tvShowEntities = viewModel.getFavoriteTvShow().getValue();
        verify(catalogueRepository).getFavoriteTv();
        assertNotNull(tvShowEntities);
        assertEquals(5, tvShowEntities.size());

        viewModel.getFavoriteTvShow().observeForever(tvObserver);
        verify(tvObserver).onChanged(dummyTvShow);
    }
}