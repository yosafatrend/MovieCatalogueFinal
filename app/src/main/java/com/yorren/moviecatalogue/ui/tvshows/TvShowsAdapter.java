package com.yorren.moviecatalogue.ui.tvshows;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.yorren.moviecatalogue.R;
import com.yorren.moviecatalogue.data.source.local.entity.MovieEntity;
import com.yorren.moviecatalogue.data.source.local.entity.TvShowsEntity;
import com.yorren.moviecatalogue.databinding.ItemsBinding;
import com.yorren.moviecatalogue.data.source.remote.response.ResultsTv;
import com.yorren.moviecatalogue.ui.detail.DetailContentActivity;

import java.util.ArrayList;
import java.util.List;

public class TvShowsAdapter extends PagedListAdapter<TvShowsEntity, TvShowsAdapter.TvShowsHolder> {
    private final List<TvShowsEntity> listTvShows = new ArrayList<>();
    private final TvFragmentCallback callback;

    public TvShowsAdapter(TvFragmentCallback callback) {
        super(DIFF_CALLBACK);
        this.callback = callback;
    }

    private static DiffUtil.ItemCallback<TvShowsEntity> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<TvShowsEntity>() {
                @Override
                public boolean areItemsTheSame(@NonNull TvShowsEntity oldItem, @NonNull TvShowsEntity newItem) {
                    return oldItem.getTvId().equals(newItem.getTvId());
                }

                @SuppressLint("DiffUtilEquals")
                @Override
                public boolean areContentsTheSame(@NonNull TvShowsEntity oldItem, @NonNull TvShowsEntity newItem) {
                    return oldItem.equals(newItem);
                }
            };

    public void setListTvShows(List<TvShowsEntity> listTvShows) {
        if (listTvShows == null) return;
        this.listTvShows.clear();
        this.listTvShows.addAll(listTvShows);
    }

    public TvShowsEntity getSwipedData(int swipedPosition) {
        return listTvShows.get(swipedPosition);
    }

    @NonNull
    @Override
    public TvShowsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemsBinding binding = ItemsBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new TvShowsHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TvShowsHolder holder, int position) {
        TvShowsEntity tvShows = listTvShows.get(position);
        holder.bind(tvShows);
    }

    @Override
    public int getItemCount() {
        return listTvShows.size();
    }

    public class TvShowsHolder extends RecyclerView.ViewHolder {
        private final ItemsBinding binding;

        public TvShowsHolder(@NonNull ItemsBinding binding) {
            super(binding.getRoot());

            this.binding = binding;
        }

        void bind(TvShowsEntity tvShows) {
            binding.tvItemTitle.setText(tvShows.getTitle());
            binding.tvItemDate.setText(tvShows.getFirstAirDate());
            binding.tvItemDesc.setText(tvShows.getOverview());

            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(itemView.getContext(), DetailContentActivity.class);
                intent.putExtra(DetailContentActivity.TV_CONTENT, tvShows.getTvId());
                itemView.getContext().startActivity(intent);
            });

            Glide.with(itemView.getContext())
                    .load("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/" + tvShows.getImagePath())
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                    .centerCrop()
                    .into(binding.imagePoster);
            binding.imgShare.setOnClickListener(v -> callback.onShareClick(tvShows));
        }
    }
}
