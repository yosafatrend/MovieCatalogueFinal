package com.yorren.moviecatalogue.di;

import android.content.Context;

import com.yorren.moviecatalogue.data.source.CatalogueRepository;
import com.yorren.moviecatalogue.data.source.local.LocalDataSource;
import com.yorren.moviecatalogue.data.source.local.room.CatalogueDatabase;
import com.yorren.moviecatalogue.data.source.remote.RemoteDataSource;
import com.yorren.moviecatalogue.utils.AppExecutors;

public class Injection {
    public static CatalogueRepository provideRepository(Context context) {

        CatalogueDatabase database = CatalogueDatabase.getInstance(context);

        RemoteDataSource remoteDataSource = RemoteDataSource.getInstance();
        LocalDataSource localDataSource = LocalDataSource.getInstance(database.catalogueDao());
        AppExecutors appExecutors = new AppExecutors();

        return CatalogueRepository.getInstance(remoteDataSource, localDataSource, appExecutors);
    }
}
