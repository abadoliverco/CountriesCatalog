package com.example.oliverabad.countriescatalog.di.modules;

import android.content.Context;

import com.example.oliverabad.countriescatalog.CountriesCatalogApplication;
import com.example.oliverabad.countriescatalog.data.api.DataManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by oliverabad on 7/7/18.
 */
@Module
public class ApplicationModule {

    private final CountriesCatalogApplication application;


    public ApplicationModule(CountriesCatalogApplication app) {
        application = app;
    }

    @Provides
    @Singleton
    CountriesCatalogApplication providesApplication() {
        return application;
    }


    @Provides
    @Singleton
    Context providesContext() {
        return application.getApplicationContext();
    }


    @Provides
    @Singleton
    DataManager providesDataManager(Context context) {
        return new DataManager(context);
    }



}