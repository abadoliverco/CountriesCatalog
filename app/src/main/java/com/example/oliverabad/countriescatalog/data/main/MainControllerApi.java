package com.example.oliverabad.countriescatalog.data.main;

import com.example.oliverabad.countriescatalog.data.model.CountryModel;
import com.example.oliverabad.countriescatalog.data.wrapper.ListWrapper;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by oliverabad on 7/7/18.
 */
public interface MainControllerApi {

    @GET("getCountries")
    Observable<ListWrapper<CountryModel>> getCountriesList();

}