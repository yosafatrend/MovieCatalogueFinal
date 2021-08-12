package com.yorren.moviecatalogue.ui.detail;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.yorren.moviecatalogue.BuildConfig;
import com.yorren.moviecatalogue.data.source.local.entity.MovieEntity;
import com.yorren.moviecatalogue.data.source.local.entity.TvShowsEntity;
import com.yorren.moviecatalogue.data.source.CatalogueRepository;
import com.yorren.moviecatalogue.utils.DataDummy;
import com.yorren.moviecatalogue.vo.Resource;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DetailContentViewModelTest {
    private DetailContentViewModel viewModel;
    private MovieEntity dummyMovies = DataDummy.generateDummyMovies().get(0);
    private TvShowsEntity dummyTvShows = DataDummy.generateDummyTvShows().get(0);
    private String moviesId = dummyMovies.getMovieId();
    private String tvshowsId = dummyTvShows.getTvId();

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Mock
    private CatalogueRepository catalogueRepository;

    @Mock
    private Observer<Resource<MovieEntity>> movieObserver;

    @Mock
    private Observer<Resource<TvShowsEntity>> tvObserver;

    @Before
    public void setUp() {
        viewModel = new DetailContentViewModel(catalogueRepository);
        viewModel.setMovieId(moviesId);
        viewModel.setTvShowId(tvshowsId);
    }

    @Test
    public void getMovies() {
        Resource<MovieEntity> movieEntityDummy = Resource.success(DataDummy.generateDummyMovies().get(0));
        MutableLiveData<Resource<MovieEntity>> movie = new MutableLiveData<>();
        movie.setValue(movieEntityDummy);
        when(catalogueRepository.getDetailMovie(moviesId, BuildConfig.API_KEY, BuildConfig.LANGUAGE)).thenReturn(movie);
        viewModel.getMovieDetail.observeForever(movieObserver);
        verify(movieObserver).onChanged(movieEntityDummy);
    }

    @Test
    public void getTvShows() {
        Resource<TvShowsEntity> tvShowEntityDummy = Resource.success(DataDummy.generateDummyTvShows().get(0));
        MutableLiveData<Resource<TvShowsEntity>> tvshow = new MutableLiveData<>();
        tvshow.setValue(tvShowEntityDummy);
        when(catalogueRepository.getDetailTvShow(tvshowsId, BuildConfig.API_KEY, BuildConfig.LANGUAGE)).thenReturn(tvshow);
        viewModel.getTvShowDetail.observeForever(tvObserver);
        verify(tvObserver).onChanged(tvShowEntityDummy);
    }
}