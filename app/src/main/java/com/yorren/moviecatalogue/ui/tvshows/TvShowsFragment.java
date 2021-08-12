package com.yorren.moviecatalogue.ui.tvshows;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ShareCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.yorren.moviecatalogue.BuildConfig;
import com.yorren.moviecatalogue.R;
import com.yorren.moviecatalogue.data.source.local.entity.MovieEntity;
import com.yorren.moviecatalogue.data.source.local.entity.TvShowsEntity;
import com.yorren.moviecatalogue.databinding.FragmentTvShowsBinding;
import com.yorren.moviecatalogue.data.source.remote.response.ResultsTv;
import com.yorren.moviecatalogue.ui.movies.MoviesAdapter;
import com.yorren.moviecatalogue.ui.movies.MoviesViewModel;
import com.yorren.moviecatalogue.viewmodel.ViewModelFactory;

import java.util.List;

public class TvShowsFragment extends Fragment implements TvFragmentCallback {
    private FragmentTvShowsBinding fragmentTvShowsBinding;
    private TvShowsViewModel viewModel;

    public TvShowsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentTvShowsBinding = FragmentTvShowsBinding.inflate(inflater, container, false);
        return fragmentTvShowsBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getActivity() != null) {
            ViewModelFactory factory = ViewModelFactory.getInstance(getActivity());
            viewModel = new ViewModelProvider(this, factory).get(TvShowsViewModel.class);
            fragmentTvShowsBinding.progressBar.setVisibility(View.VISIBLE);

            TvShowsAdapter adapter = new TvShowsAdapter(this);

            viewModel.getTvShows(BuildConfig.API_KEY, BuildConfig.LANGUAGE, BuildConfig.PAGE)
                    .observe(getActivity(), results -> {
                        if (results != null) {
                            switch (results.status) {
                                case LOADING:
                                    fragmentTvShowsBinding.progressBar.setVisibility(View.VISIBLE);
                                    break;
                                case SUCCESS:
                                    fragmentTvShowsBinding.progressBar.setVisibility(View.GONE);

                                    adapter.setListTvShows(results.data);
                                    adapter.notifyDataSetChanged();

                                    fragmentTvShowsBinding.rvTvshows.setLayoutManager(new LinearLayoutManager(getContext()));
                                    fragmentTvShowsBinding.rvTvshows.setHasFixedSize(true);
                                    fragmentTvShowsBinding.rvTvshows.setAdapter(adapter);

                                    break;
                                case ERROR:
                                    fragmentTvShowsBinding.progressBar.setVisibility(View.GONE);
                                    Toast.makeText(getContext(), "Terjadi kesalahan", Toast.LENGTH_SHORT).show();
                                    break;
                            }
                        }

                    });

        }
    }

    @Override
    public void onShareClick(TvShowsEntity resultsTv) {
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
}