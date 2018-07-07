package com.example.oliverabad.countriescatalog.di.component;

import com.example.oliverabad.countriescatalog.di.modules.PresentersModule;
import com.example.oliverabad.countriescatalog.di.scope.PerActivity;
import com.example.oliverabad.countriescatalog.mvp.common.base.BaseActivity;
import com.example.oliverabad.countriescatalog.mvp.main.countries.CountriesListActivity;
import com.example.oliverabad.countriescatalog.mvp.main.details.CountryDetailsActivity;

import dagger.Component;

/**
 * Created by oliverabad on 7/7/18.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = PresentersModule.class)
public interface ActivitiesComponent {

    //Activities
    void inject(BaseActivity baseActivity);
    void inject(CountriesListActivity countriesListActivity);
    void inject(CountryDetailsActivity countryDetailsActivity);

    //Fragments
}
