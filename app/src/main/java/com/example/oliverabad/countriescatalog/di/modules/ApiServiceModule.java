package com.example.oliverabad.countriescatalog.di.modules;

import com.example.oliverabad.countriescatalog.data.main.MainControllerApi;
import com.example.oliverabad.countriescatalog.di.scope.PerDataManager;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by oliverabad on 7/7/18.
 */
@Module
public class ApiServiceModule {

    @Provides
    @PerDataManager
    final MainControllerApi provideMainControllerApi(final Retrofit retrofit) {
        return retrofit.create(MainControllerApi.class);
    }
}

