package com.yorren.moviecatalogue.ui.movies;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.paging.PagedList;

import com.yorren.moviecatalogue.BuildConfig;
import com.yorren.moviecatalogue.data.source.local.entity.MovieEntity;
import com.yorren.moviecatalogue.data.source.CatalogueRepository;
import com.yorren.moviecatalogue.utils.DataDummy;
import com.yorren.moviecatalogue.vo.Resource;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MoviesViewModelTest {
    private MoviesViewModel viewModel;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Mock
    private CatalogueRepository catalogueRepository;

    @Mock
    private Observer<Resource<PagedList<MovieEntity>>> observer;

    @Mock
    private PagedList<MovieEntity> pagedList;

    @Before
    public void setUp() {
        viewModel = new MoviesViewModel(catalogueRepository);
    }

    @Test
    public void getMovies() {
        Resource<PagedList<MovieEntity>> dummyMovies = Resource.success(pagedList);
        when(dummyMovies.data.size()).thenReturn(20);
        MutableLiveData<Resource<PagedList<MovieEntity>>> movies = new MutableLiveData<>();
        movies.setValue(dummyMovies);

        when(catalogueRepository.getAllMovies(BuildConfig.API_KEY,
                BuildConfig.LANGUAGE, BuildConfig.PAGE)).thenReturn(movies);
        List<MovieEntity> movieEntities = viewModel.getMovies(BuildConfig.API_KEY,
                BuildConfig.LANGUAGE, BuildConfig.PAGE).getValue().data;
        verify(catalogueRepository).getAllMovies(BuildConfig.API_KEY,
                BuildConfig.LANGUAGE, BuildConfig.PAGE);
        assertNotNull(movieEntities);
        assertEquals(20, movieEntities.size());

        viewModel.getMovies(BuildConfig.API_KEY, BuildConfig.LANGUAGE, BuildConfig.PAGE).observeForever(observer);
        verify(observer).onChanged(dummyMovies);
    }
}