package com.example.oliverabad.countriescatalog.di.component;

import android.content.Context;

import com.example.oliverabad.countriescatalog.CountriesCatalogApplication;
import com.example.oliverabad.countriescatalog.data.api.DataManager;
import com.example.oliverabad.countriescatalog.di.modules.ApplicationModule;
import com.example.oliverabad.countriescatalog.mvp.common.base.BasePresenterInjection;
import com.example.oliverabad.countriescatalog.mvp.common.injectable.InjectableActivity;
import com.example.oliverabad.countriescatalog.mvp.main.countries.CountriesListPresenter;
import com.example.oliverabad.countriescatalog.mvp.main.details.CountryDetailsPresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by oliverabad on 7/7/18.
 */
@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    Context getContext();

    DataManager getDataManager();

    void inject(CountriesCatalogApplication giroApplication);

    void inject(BasePresenterInjection basePresenterInjection);

    void inject(InjectableActivity injectableActivity);

    void inject(CountriesListPresenter countriesListPresenter);

    void inject(CountryDetailsPresenter countryDetailsPresenter);
}