package com.example.oliverabad.countriescatalog.mvp.main.details;

import com.example.oliverabad.countriescatalog.data.model.CountryModel;
import com.example.oliverabad.countriescatalog.mvp.common.base.MvpView;

/**
 * Created by oliverabad on 7/7/18.
 */
public interface CountryDetailsMvpView extends MvpView {
    void showDetails(CountryModel countryModel);
}
