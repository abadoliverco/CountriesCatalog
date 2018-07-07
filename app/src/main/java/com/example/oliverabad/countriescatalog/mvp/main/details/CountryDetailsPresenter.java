package com.example.oliverabad.countriescatalog.mvp.main.details;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.oliverabad.countriescatalog.data.model.CountryModel;
import com.example.oliverabad.countriescatalog.di.component.ApplicationComponent;
import com.example.oliverabad.countriescatalog.mvp.common.base.BasePresenter;

import static com.example.oliverabad.countriescatalog.mvp.main.details.CountryDetailsActivity.BUNDLE_COUNTRY_MODEL;

/**
 * Created by oliverabad on 7/7/18.
 */
public class CountryDetailsPresenter extends BasePresenter<CountryDetailsMvpView> {

    public CountryDetailsPresenter(Context context) {
        super(context);
    }

    private CountryModel country;

    @Override
    public void loadData() {
        getView().showDetails(country);
    }

    @Override
    public void injectComponent(ApplicationComponent applicationComponent) {
        applicationComponent.inject(this);
    }

    public void setBundle(Intent intent) {
        Bundle b = intent.getExtras();
        if (b != null) {
            country = (CountryModel) intent.getParcelableExtra(BUNDLE_COUNTRY_MODEL);
        }
    }
}
