package com.example.oliverabad.countriescatalog.mvp.main.countries;

import android.content.Context;

import com.example.oliverabad.countriescatalog.data.api.DataManager;
import com.example.oliverabad.countriescatalog.data.error.ServiceError;
import com.example.oliverabad.countriescatalog.data.error.ServiceErrorUtil;
import com.example.oliverabad.countriescatalog.data.model.CountryModel;
import com.example.oliverabad.countriescatalog.data.wrapper.ListWrapper;
import com.example.oliverabad.countriescatalog.di.component.ApplicationComponent;
import com.example.oliverabad.countriescatalog.mvp.common.base.BasePresenter;

import javax.inject.Inject;

import rx.Observer;

/**
 * Created by oliverabad on 7/7/18.
 */
public class CountriesListPresenter extends BasePresenter<CountriesListMvpView> {


    @Inject
    DataManager dataManager;

    public CountriesListPresenter(Context context) {
        super(context);
    }

    @Override
    public void loadData() {
        getView().showLoader();

        this.baseSubscribe(dataManager.getCountriesList(), new BaseSubscriber<ListWrapper<CountryModel>>() {
            @Override
            public void onSuccess(ListWrapper<CountryModel> countriesModelListWrapper) {
                getView().showListOfCountries(countriesModelListWrapper.getData());
            }

            @Override
            public void onError(ServiceError error) {
                ServiceErrorUtil.showErrorDialog(getView(), error);
            }

            @Override
            public void onComplete() {
                getView().hideLoader();
            }
        });
    }

    @Override
    public void injectComponent(ApplicationComponent applicationComponent) {
        applicationComponent.inject(this);
    }

    public void onSelectedCountry(CountryAdapter adapter) {
        adapter.getSelectedCountry().subscribe(new Observer<CountryModel>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(CountryModel countryModel) {
                getView().showCountryDetailsScreen(countryModel);
            }
        });
    }
}
