package com.yorren.moviecatalogue.ui.favorites.tab;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ShareCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.snackbar.Snackbar;
import com.yorren.moviecatalogue.R;
import com.yorren.moviecatalogue.data.source.local.entity.MovieEntity;
import com.yorren.moviecatalogue.data.source.local.entity.TvShowsEntity;
import com.yorren.moviecatalogue.databinding.FragmentFavoriteBinding;
import com.yorren.moviecatalogue.databinding.FragmentMoviesBinding;
import com.yorren.moviecatalogue.ui.favorites.FavoriteViewModel;
import com.yorren.moviecatalogue.ui.movies.MoviesAdapter;
import com.yorren.moviecatalogue.ui.movies.MoviesFragmentCallback;
import com.yorren.moviecatalogue.ui.tvshows.TvFragmentCallback;
import com.yorren.moviecatalogue.ui.tvshows.TvShowsAdapter;
import com.yorren.moviecatalogue.viewmodel.ViewModelFactory;

public class FavMovieFragment extends Fragment implements MoviesFragmentCallback {
    private FragmentMoviesBinding fragmentFavMovieBinding;
    private FavoriteViewModel viewModel;
    private MoviesAdapter adapter;

    public FavMovieFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentFavMovieBinding = FragmentMoviesBinding.inflate(inflater);
        return fragmentFavMovieBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        itemTouchHelper.attachToRecyclerView(fragmentFavMovieBinding.rvMovies);

        if (getActivity() != null) {

            ViewModelFactory factory = ViewModelFactory.getInstance(getActivity());
            viewModel = new ViewModelProvider(this, factory).get(FavoriteViewModel.class);

            adapter = new MoviesAdapter(this);
            fragmentFavMovieBinding.progressBar.setVisibility(View.VISIBLE);
            viewModel.getFavoriteMovie().observe(getViewLifecycleOwner(), results -> {
                fragmentFavMovieBinding.progressBar.setVisibility(View.GONE);

                adapter.setListMovies(results);
                adapter.notifyDataSetChanged();

            });
            fragmentFavMovieBinding.rvMovies.setLayoutManager(new LinearLayoutManager(getContext()));
            fragmentFavMovieBinding.rvMovies.setHasFixedSize(true);
            fragmentFavMovieBinding.rvMovies.setAdapter(adapter);
        }

    }

    private ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
        @Override
        public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
            return makeMovementFlags(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        }
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return true;
        }
        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            if (getView() != null) {
                int swipedPosition = viewHolder.getAdapterPosition();
                Log.e("TESTES", String.valueOf(swipedPosition));
                MovieEntity movieEntity = adapter.getSwipedData(swipedPosition);
                viewModel.setFavoriteMovie(movieEntity);
                Snackbar snackbar = Snackbar.make(getView(), R.string.message_undo, Snackbar.LENGTH_LONG);
                snackbar.setAction(R.string.message_ok, v -> viewModel.setFavoriteMovie(movieEntity));
                snackbar.show();
            }
        }
    });

    @Override
    public void onShareClick(MovieEntity resultsTv) {
        if (getActivity() != null) {
            String mimeType = "text/plain";
            ShareCompat.IntentBuilder
                    .from(getActivity())
                    .setType(mimeType)
                    .setChooserTitle("Bagikan aplikasi ini sekarang.")
                    .setText(getResources().getString(R.string.share_text, resultsTv.getTitle()))
                    .startChooser();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        fragmentFavMovieBinding = null;
    }
}