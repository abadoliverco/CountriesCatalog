package com.example.oliverabad.countriescatalog.data.api;

import android.content.Context;

import com.example.oliverabad.countriescatalog.CountriesCatalogApplication;
import com.example.oliverabad.countriescatalog.data.main.MainControllerApi;
import com.example.oliverabad.countriescatalog.data.model.CountryModel;
import com.example.oliverabad.countriescatalog.data.wrapper.ListWrapper;
import com.example.oliverabad.countriescatalog.di.component.DaggerDataManagerComponent;

import javax.inject.Inject;

import retrofit2.Retrofit;
import rx.Observable;

/**
 * Created by oliverabad on 7/7/18.
 */
public class DataManager {

    private Context context;

    @Inject
    MainControllerApi mainService;

    @Inject
    Retrofit retrofit;

    public DataManager(Context con) {
        this.context = con;
        injectDependencies();
    }

    private void injectDependencies() {
        DaggerDataManagerComponent.builder()
                .applicationComponent(((CountriesCatalogApplication) context).getApplicationComponent())
                .build()
                .inject(this);
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

    public Observable<ListWrapper<CountryModel>> getCountriesList() {
        return mainService.getCountriesList();
    }
}
