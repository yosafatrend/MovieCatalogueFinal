package com.yorren.moviecatalogue.ui.movies;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagedList;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.yorren.moviecatalogue.R;
import com.yorren.moviecatalogue.data.source.local.entity.MovieEntity;
import com.yorren.moviecatalogue.databinding.ItemsBinding;
import com.yorren.moviecatalogue.data.source.remote.response.ResultsMovie;
import com.yorren.moviecatalogue.ui.detail.DetailContentActivity;
import com.yorren.moviecatalogue.ui.tvshows.TvFragmentCallback;

import java.util.ArrayList;
import java.util.List;

public class MoviesAdapter extends PagedListAdapter<MovieEntity, MoviesAdapter.MoviesViewHolder> {
    private final List<MovieEntity> listResult = new ArrayList<>();
    private final MoviesFragmentCallback callback;

    public MoviesAdapter(MoviesFragmentCallback callback) {
        super(DIFF_CALLBACK);
        this.callback = callback;
    }
    private static DiffUtil.ItemCallback<MovieEntity> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<MovieEntity>() {
                @Override
                public boolean areItemsTheSame(@NonNull MovieEntity oldItem, @NonNull MovieEntity newItem) {
                    return oldItem.getMovieId().equals(newItem.getMovieId());
                }
                @SuppressLint("DiffUtilEquals")
                @Override
                public boolean areContentsTheSame(@NonNull MovieEntity oldItem, @NonNull MovieEntity newItem) {
                    return oldItem.equals(newItem);
                }
            };


    public void setListMovies(List<MovieEntity> listResult) {
        if (listResult == null){
            return;
        }
        this.listResult.clear();
        this.listResult.addAll(listResult);
    }

    @NonNull
    @Override
    public MoviesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemsBinding binding = ItemsBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MoviesViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesViewHolder holder, int position) {
        MovieEntity results = listResult.get(position);
        holder.bind(results);
    }

    public MovieEntity getSwipedData(int swipedPosition) {
        return listResult.get(swipedPosition);
    }

    @Override
    public int getItemCount() {
        return listResult.size();
    }

    public class MoviesViewHolder extends RecyclerView.ViewHolder {
        private final ItemsBinding binding;

        public MoviesViewHolder(ItemsBinding binding) {
            super(binding.getRoot());

            this.binding = binding;
        }

        void bind(MovieEntity resultsItem) {
            binding.tvItemTitle.setText(resultsItem.getTitle());
            binding.tvItemDate.setText(((resultsItem.getReleaseDate() == null) ? "N/A" : resultsItem.getReleaseDate()));
            binding.tvItemDesc.setText(resultsItem.getOverview());
            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(itemView.getContext(), DetailContentActivity.class);
                intent.putExtra(DetailContentActivity.MOVIES_CONTENT, resultsItem.getMovieId());
                itemView.getContext().startActivity(intent);
            });
            Glide.with(itemView.getContext())
                    .load("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/"+resultsItem.getImagePath())
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                    .centerCrop()
                    .into(binding.imagePoster);
            binding.imgShare.setOnClickListener(v -> callback.onShareClick(resultsItem));
        }
    }
}
