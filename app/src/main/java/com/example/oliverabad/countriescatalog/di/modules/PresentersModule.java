package com.example.oliverabad.countriescatalog.di.modules;

import android.content.Context;

import com.example.oliverabad.countriescatalog.di.scope.PerActivity;
import com.example.oliverabad.countriescatalog.mvp.main.countries.CountriesListPresenter;
import com.example.oliverabad.countriescatalog.mvp.main.details.CountryDetailsPresenter;

import java.lang.ref.WeakReference;

import dagger.Module;
import dagger.Provides;

/**
 * Created by oliverabad on 7/7/18.
 */

@Module
public class PresentersModule {

    private WeakReference<Context> contextRef;

    public PresentersModule(Context context) {
        contextRef = new WeakReference<>(context);
    }

    @Provides
    @PerActivity
    CountriesListPresenter providesCountriesListPresenter() {
        return new CountriesListPresenter(contextRef.get());
    }

    @Provides
    @PerActivity
    CountryDetailsPresenter providesCountryDetailsPresenter() {
        return new CountryDetailsPresenter(contextRef.get());
    }
}
