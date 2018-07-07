package com.example.oliverabad.countriescatalog.mvp.common.base;

import com.example.oliverabad.countriescatalog.data.api.DataManager;

import javax.inject.Inject;

import retrofit2.Retrofit;

/**
 * Created by oliverabad on 7/7/18.
 */

public class BasePresenterInjection {
    @Inject
    DataManager dataManager;

    public Retrofit getRetrofit() {
        return dataManager.getRetrofit();
    }

    public DataManager getDataManager() {
        return dataManager;
    }
}
