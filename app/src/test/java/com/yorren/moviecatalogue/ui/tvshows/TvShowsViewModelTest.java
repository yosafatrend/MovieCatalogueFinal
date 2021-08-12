package com.yorren.moviecatalogue.ui.tvshows;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.paging.PagedList;

import com.yorren.moviecatalogue.BuildConfig;
import com.yorren.moviecatalogue.data.source.local.entity.TvShowsEntity;
import com.yorren.moviecatalogue.data.source.CatalogueRepository;
import com.yorren.moviecatalogue.vo.Resource;

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
public class TvShowsViewModelTest {
    private TvShowsViewModel viewModel;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Mock
    private CatalogueRepository catalogueRepository;

    @Mock
    private Observer<Resource<PagedList<TvShowsEntity>>> observer;

    @Mock
    private PagedList<TvShowsEntity> pagedList;

    @Before
    public void setUp() {
        viewModel = new TvShowsViewModel(catalogueRepository);
    }

    @Test
    public void getTvShows() {
        Resource<PagedList<TvShowsEntity>> dummyTvShows = Resource.success(pagedList);
        when(dummyTvShows.data.size()).thenReturn(20);
        MutableLiveData<Resource<PagedList<TvShowsEntity>>> tvshows = new MutableLiveData<>();
        tvshows.setValue(dummyTvShows);

        when(catalogueRepository.getAllTvShows(BuildConfig.API_KEY,
                BuildConfig.LANGUAGE, BuildConfig.PAGE)).thenReturn(tvshows);
        List<TvShowsEntity> tvShowEntities = viewModel.getTvShows(BuildConfig.API_KEY,
                BuildConfig.LANGUAGE, BuildConfig.PAGE).getValue().data;
        verify(catalogueRepository).getAllTvShows(BuildConfig.API_KEY,
                BuildConfig.LANGUAGE, BuildConfig.PAGE);
        assertNotNull(tvShowEntities);
        assertEquals(20, tvShowEntities.size());

        viewModel.getTvShows(BuildConfig.API_KEY, BuildConfig.LANGUAGE, BuildConfig.PAGE).observeForever(observer);
        verify(observer).onChanged(dummyTvShows);
    }
}