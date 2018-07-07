package com.example.oliverabad.countriescatalog.mvp.main.countries;

import com.example.oliverabad.countriescatalog.data.model.CountryModel;
import com.example.oliverabad.countriescatalog.mvp.common.base.MvpView;

import java.util.List;

/**
 * Created by oliverabad on 7/7/18.
 */
public interface CountriesListMvpView extends MvpView {

    void showListOfCountries(List<CountryModel> list);
    void showCountryDetailsScreen(CountryModel countryModel);

}
