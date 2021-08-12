package com.yorren.moviecatalogue.ui.movies;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ShareCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.yorren.moviecatalogue.BuildConfig;
import com.yorren.moviecatalogue.R;
import com.yorren.moviecatalogue.data.source.local.entity.MovieEntity;
import com.yorren.moviecatalogue.databinding.FragmentMoviesBinding;
import com.yorren.moviecatalogue.data.source.remote.response.ResultsMovie;
import com.yorren.moviecatalogue.viewmodel.ViewModelFactory;

import java.util.List;

public class MoviesFragment extends Fragment implements MoviesFragmentCallback {
    private FragmentMoviesBinding fragmentMoviesBinding;
    private MoviesViewModel viewModel;

    public MoviesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentMoviesBinding = FragmentMoviesBinding.inflate(inflater, container, false);
        return fragmentMoviesBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getActivity() != null) {
            ViewModelFactory factory = ViewModelFactory.getInstance(getActivity());
            viewModel = new ViewModelProvider(this, factory).get(MoviesViewModel.class);
            fragmentMoviesBinding.progressBar.setVisibility(View.VISIBLE);

            MoviesAdapter adapter = new MoviesAdapter(this);

            viewModel.getMovies(BuildConfig.API_KEY, BuildConfig.LANGUAGE, BuildConfig.PAGE)
                    .observe(getActivity(), results -> {
                        if (results != null) {
                            switch (results.status) {
                                case LOADING:
                                    fragmentMoviesBinding.progressBar.setVisibility(View.VISIBLE);
                                    break;
                                case SUCCESS:
                                    fragmentMoviesBinding.progressBar.setVisibility(View.GONE);

                                    adapter.setListMovies(results.data);
                                    adapter.notifyDataSetChanged();

                                    fragmentMoviesBinding.rvMovies.setLayoutManager(new LinearLayoutManager(getContext()));
                                    fragmentMoviesBinding.rvMovies.setHasFixedSize(true);
                                    fragmentMoviesBinding.rvMovies.setAdapter(adapter);

                                    break;
                                case ERROR:
                                    fragmentMoviesBinding.progressBar.setVisibility(View.GONE);
                                    Toast.makeText(getContext(), "Terjadi kesalahan", Toast.LENGTH_SHORT).show();
                                    break;
                            }
                        }

                    });

        }
    }

    @Override
    public void onShareClick(MovieEntity resultsItem) {
        if (getActivity() != null) {
            String mimeType = "text/plain";
            ShareCompat.IntentBuilder
                    .from(getActivity())
                    .setType(mimeType)
                    .setChooserTitle("Bagikan aplikasi ini sekarang.")
                    .setText(getResources().getString(R.string.share_text, resultsItem.getTitle()))
                    .startChooser();
        }
    }

}