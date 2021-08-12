package com.yorren.moviecatalogue.ui.detail;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.yorren.moviecatalogue.R;
import com.yorren.moviecatalogue.data.source.local.entity.MovieEntity;
import com.yorren.moviecatalogue.data.source.local.entity.TvShowsEntity;
import com.yorren.moviecatalogue.databinding.ActivityDetailContentBinding;
import com.yorren.moviecatalogue.databinding.ContentDetailBinding;
import com.yorren.moviecatalogue.data.source.remote.response.GenresItem;
import com.yorren.moviecatalogue.data.source.remote.response.ResponseDetailMovie;
import com.yorren.moviecatalogue.data.source.remote.response.ResponseDetailTv;
import com.yorren.moviecatalogue.viewmodel.ViewModelFactory;

public class DetailContentActivity extends AppCompatActivity {
    public static final String TV_CONTENT = "tv_content";
    public static final String MOVIES_CONTENT = "movies_content";
    private String moviesId;
    private String tvId;

    private ContentDetailBinding contentDetailBinding;
    private ActivityDetailContentBinding activityDetailContentBinding;
    DetailContentViewModel viewModel;
    private Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityDetailContentBinding = ActivityDetailContentBinding.inflate(getLayoutInflater());
        contentDetailBinding = activityDetailContentBinding.detailContent;

        setContentView(activityDetailContentBinding.getRoot());

        setSupportActionBar(activityDetailContentBinding.toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        ViewModelFactory factory = ViewModelFactory.getInstance(this);
        viewModel = new ViewModelProvider(this, factory).get(DetailContentViewModel.class);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            moviesId = extras.getString(MOVIES_CONTENT);
            tvId = extras.getString(TV_CONTENT);
            if (moviesId != null) {
                viewModel.setMovieId(moviesId);
                viewModel.getMovieDetail.observe(this, movieEntityResource -> {
                    if (movieEntityResource != null) {
                        switch (movieEntityResource.status) {
                            case LOADING:
                                contentDetailBinding.progressBar.setVisibility(View.VISIBLE);
                                break;
                            case SUCCESS:
                                if (movieEntityResource.data != null) {
                                    contentDetailBinding.progressBar.setVisibility(View.GONE);
                                    populateMovies(movieEntityResource.data);
                                }
                                break;
                            case ERROR:
                                contentDetailBinding.progressBar.setVisibility(View.GONE);
                                break;
                        }
                    }
                });
            }
            if (tvId != null) {
                viewModel.setTvShowId(tvId);
                viewModel.getTvShowDetail.observe(this, tvShowsEntityResource -> {
                    if (tvShowsEntityResource != null) {
                        switch (tvShowsEntityResource.status) {
                            case LOADING:
                                contentDetailBinding.progressBar.setVisibility(View.VISIBLE);
                                break;
                            case SUCCESS:
                                if (tvShowsEntityResource.data != null) {
                                    contentDetailBinding.progressBar.setVisibility(View.GONE);
                                    populateTv(tvShowsEntityResource.data);
                                }
                                break;
                            case ERROR:
                                contentDetailBinding.progressBar.setVisibility(View.GONE);
                                break;
                        }
                    }
                });
            }

        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        this.menu = menu;
        if (moviesId != null){
            viewModel.getMovieDetail.observe(this, results -> {
                if (results != null) {
                    switch (results.status) {
                        case LOADING:
                            contentDetailBinding.progressBar.setVisibility(View.VISIBLE);
                            break;
                        case SUCCESS:
                            if (results.data != null) {
                                contentDetailBinding.progressBar.setVisibility(View.GONE);
                                boolean state = results.data.isFavorite();
                                setFavoriteState(state);
                            }
                            break;
                        case ERROR:
                            contentDetailBinding.progressBar.setVisibility(View.GONE);
                            Toast.makeText(getApplicationContext(), "Terjadi kesalahan", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            });
        }else if (tvId != null){
            viewModel.getTvShowDetail.observe(this, results -> {
                if (results != null) {
                    switch (results.status) {
                        case LOADING:
                            contentDetailBinding.progressBar.setVisibility(View.VISIBLE);
                            break;
                        case SUCCESS:
                            if (results.data != null) {
                                contentDetailBinding.progressBar.setVisibility(View.GONE);
                                boolean state = results.data.isFavorite();
                                setFavoriteState(state);
                            }
                            break;
                        case ERROR:
                            contentDetailBinding.progressBar.setVisibility(View.GONE);
                            Toast.makeText(getApplicationContext(), "Terjadi kesalahan", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            });
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_favorite:
                if (moviesId != null) {
                    viewModel.setFavoriteMovie();
                } else if (tvId != null) {
                    viewModel.setFavoriteTv();
                }
                return true;
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        contentDetailBinding = null;
        activityDetailContentBinding = null;
    }

    private void setFavoriteState(boolean state) {
        if (menu == null) return;
        MenuItem menuItem = menu.findItem(R.id.action_favorite);
        if (state) {
            menuItem.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_favorited_white));
        } else {
            menuItem.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_favorite_white));
        }
    }

    private void populateMovies(MovieEntity detailMovie) {
        contentDetailBinding.tvTitle.setText(detailMovie.getTitle());

        String lang = ((detailMovie.getLanguage() == null) ? "N/A" : detailMovie.getLanguage());
        String popular = ((detailMovie.getPopularity() == null) ? "N/A" : detailMovie.getPopularity());
        String userScore = ((detailMovie.getUserScore() == null || detailMovie.getUserScore().equals("0"))  ? "N/A" : detailMovie.getUserScore());
        String releaseDate = ((detailMovie.getReleaseDate() == null) ? "N/A" : detailMovie.getReleaseDate());
        String overview = ((detailMovie.getOverview() == null) ? "N/A" : detailMovie.getOverview());

        contentDetailBinding.tvLanguage.setText(lang);
        contentDetailBinding.tvPopular.setText(popular);
        contentDetailBinding.tvUserScore.setText(userScore);
        contentDetailBinding.tvReleaseDate.setText(releaseDate);
        contentDetailBinding.tvOverview.setText(overview);

        Glide.with(this)
                .load("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/" + detailMovie.getImagePath())
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                .fitCenter()
                .into(contentDetailBinding.imagePoster);

        Glide.with(this)
                .load("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/" + detailMovie.getBackdropPath())
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                .fitCenter()
                .into(contentDetailBinding.imgBackdrop);
    }

    private void populateTv(TvShowsEntity detailTv) {
        contentDetailBinding.tvTitle.setText(detailTv.getTitle());

        String lang = ((detailTv.getLanguage() == null) ? "N/A" : detailTv.getLanguage());
        String popular = ((detailTv.getPopularity() == null) ? "N/A" : detailTv.getPopularity());
        String userScore = ((detailTv.getUserScore() == null || detailTv.getUserScore().equals("0")) ? "N/A" : detailTv.getUserScore());
        String releaseDate = ((detailTv.getFirstAirDate() == null) ? "N/A" : detailTv.getFirstAirDate());
        String overview = ((detailTv.getOverview() == null) ? "N/A" : detailTv.getOverview());

        contentDetailBinding.tvLanguage.setText(lang);
        contentDetailBinding.tvPopular.setText(popular);
        contentDetailBinding.tvUserScore.setText(userScore);
        contentDetailBinding.tvReleaseDate.setText(releaseDate);
        contentDetailBinding.tvOverview.setText(overview);

        Glide.with(this)
                .load("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/" + detailTv.getImagePath())
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                .fitCenter()
                .into(contentDetailBinding.imagePoster);

        Glide.with(this)
                .load("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/" + detailTv.getBackdropPath())
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                .fitCenter()
                .into(contentDetailBinding.imgBackdrop);
    }

}