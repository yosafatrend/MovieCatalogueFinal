package com.yorren.moviecatalogue.data.source;

import android.graphics.Movie;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PagedList;

import com.yorren.moviecatalogue.BuildConfig;
import com.yorren.moviecatalogue.data.source.local.LocalDataSource;
import com.yorren.moviecatalogue.data.source.local.entity.MovieEntity;
import com.yorren.moviecatalogue.data.source.local.entity.TvShowsEntity;
import com.yorren.moviecatalogue.data.source.remote.RemoteDataSource;

import com.yorren.moviecatalogue.utils.AppExecutors;
import com.yorren.moviecatalogue.utils.DataDummy;
import com.yorren.moviecatalogue.utils.LiveDataTestUtil;
import com.yorren.moviecatalogue.utils.PagedListUtil;
import com.yorren.moviecatalogue.vo.Resource;

import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CatalogueRepositoryTest {
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private RemoteDataSource remote = mock(RemoteDataSource.class);
    private LocalDataSource local = mock(LocalDataSource.class);
    private AppExecutors appExecutors = mock(AppExecutors.class);

    private FakeCatalogueRepository catalogueRepository = new FakeCatalogueRepository(remote, local, appExecutors);

    private ArrayList<MovieEntity> movieResponse = DataDummy.generateDummyMovies();
    private ArrayList<TvShowsEntity> tvResponse = DataDummy.generateDummyTvShows();

    private String movieId = movieResponse.get(0).getMovieId();
    private String tvId = tvResponse.get(0).getTvId();


    @Test
    public void getAllMovies() {
        DataSource.Factory<Integer, MovieEntity> dataSourceFactory = mock(DataSource.Factory.class);
        when(local.getAllMovies()).thenReturn(dataSourceFactory);
        catalogueRepository.getAllMovies(BuildConfig.API_KEY, BuildConfig.LANGUAGE, BuildConfig.PAGE);

        Resource<PagedList<MovieEntity>> courseEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyMovies()));
        verify(local).getAllMovies();
        assertNotNull(courseEntities.data);
        assertEquals(movieResponse.size(), courseEntities.data.size());
    }

    @Test
    public void getDetailMovie() {
        MutableLiveData<MovieEntity> dummyEntity = new MutableLiveData<>();
        dummyEntity.setValue(DataDummy.generateDummyMovies().get(0));
        when(local.getMovieById(movieId)).thenReturn(dummyEntity);

        Resource<MovieEntity> movieEntities = LiveDataTestUtil.getValue(catalogueRepository.getDetailMovie(movieId, BuildConfig.API_KEY, BuildConfig.LANGUAGE));
        verify(local).getMovieById(movieId);
        assertNotNull(movieEntities.data);
        assertNotNull(movieEntities.data.getTitle());
        assertEquals(movieResponse.get(0).getTitle(), movieEntities.data.getTitle());
    }

    @Test
    public void getFavoritedMovies() {
        DataSource.Factory<Integer, MovieEntity> dataSourceFactory = mock(DataSource.Factory.class);
        when(local.getFavoriteMovies()).thenReturn(dataSourceFactory);
        catalogueRepository.getFavoriteMovie();

        Resource<PagedList<MovieEntity>> courseEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyMovies()));
        verify(local).getFavoriteMovies();
        assertNotNull(courseEntities);
        assertEquals(movieResponse.size(), courseEntities.data.size());
    }

    @Test
    public void getAllTvShows() {
        DataSource.Factory<Integer, TvShowsEntity> dataSourceFactory = mock(DataSource.Factory.class);
        when(local.getAllTvShows()).thenReturn(dataSourceFactory);
        catalogueRepository.getAllTvShows(BuildConfig.API_KEY, BuildConfig.LANGUAGE, BuildConfig.PAGE);

        Resource<PagedList<TvShowsEntity>> tvShowsEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyTvShows()));
        verify(local).getAllTvShows();
        assertNotNull(tvShowsEntities.data);
        assertEquals(tvResponse.size(), tvShowsEntities.data.size());
    }

    @Test
    public void getDetailTvShows() {
        MutableLiveData<TvShowsEntity> dummyEntity = new MutableLiveData<>();
        dummyEntity.setValue(DataDummy.generateDummyTvShows().get(0));
        when(local.getTvShowsById(tvId)).thenReturn(dummyEntity);

        Resource<TvShowsEntity> tvShowEntities = LiveDataTestUtil.getValue(catalogueRepository.getDetailTvShow(tvId, BuildConfig.API_KEY, BuildConfig.LANGUAGE));
        verify(local).getTvShowsById(tvId);
        assertNotNull(tvShowEntities.data);
        assertNotNull(tvShowEntities.data.getTitle());
        assertEquals(tvResponse.get(0).getTitle(), tvShowEntities.data.getTitle());
    }

    @Test
    public void getFavoritedTvShows() {
        DataSource.Factory<Integer, TvShowsEntity> dataSourceFactory = mock(DataSource.Factory.class);
        when(local.getFavoriteTvShows()).thenReturn(dataSourceFactory);
        catalogueRepository.getFavoriteTv();

        Resource<PagedList<TvShowsEntity>> tvShowsEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyTvShows()));
        verify(local).getFavoriteTvShows();
        assertNotNull(tvShowsEntities);
        assertEquals(tvResponse.size(), tvShowsEntities.data.size());
    }
}