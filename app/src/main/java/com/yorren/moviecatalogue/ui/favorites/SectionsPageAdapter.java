package com.yorren.moviecatalogue.ui.favorites;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.yorren.moviecatalogue.ui.favorites.tab.FavMovieFragment;
import com.yorren.moviecatalogue.ui.favorites.tab.FavTvFragment;

public class SectionsPageAdapter extends FragmentPagerAdapter {
    public SectionsPageAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new FavMovieFragment();
            case 1:
                return new FavTvFragment();
            default:
        }
        return null;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return "Movies";
        }
        return "Tv Shows";
    }

    @Override
    public int getCount() {
        return 2;
    }
}
