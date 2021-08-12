package com.yorren.moviecatalogue.data.source.local.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.yorren.moviecatalogue.data.source.local.entity.MovieEntity;
import com.yorren.moviecatalogue.data.source.local.entity.TvShowsEntity;

@Database(entities={MovieEntity.class, TvShowsEntity.class},
        version=1,
        exportSchema=false)

public abstract class CatalogueDatabase extends RoomDatabase {
    public abstract CatalogueDao catalogueDao();

    private static volatile CatalogueDatabase INSTANCE;

    public static CatalogueDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (CatalogueDatabase.class) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        CatalogueDatabase.class, "Catalogue.db")
                        .build();
            }
        }
        return INSTANCE;
    }
}
