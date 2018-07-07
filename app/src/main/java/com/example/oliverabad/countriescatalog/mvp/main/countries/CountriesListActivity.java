package com.example.oliverabad.countriescatalog.mvp.main.countries;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.oliverabad.countriescatalog.R;
import com.example.oliverabad.countriescatalog.data.model.CountryModel;
import com.example.oliverabad.countriescatalog.mvp.common.injectable.InjectableActivity;
import com.example.oliverabad.countriescatalog.mvp.main.details.CountryDetailsActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import timber.log.Timber;

/**
 * Created by oliverabad on 7/7/18.
 */

public class CountriesListActivity extends InjectableActivity implements CountriesListMvpView{


    @BindView(R.id.countries_list)
    RecyclerView countriesList;

    @Inject
    CountriesListPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivityComponent().inject(this);
        presenter.attachView(this);
        recyclerViewSetup();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_countries_list;
    }

    @Override
    protected boolean hasBackButton() {
        return false;
    }

    @Override
    protected String setHeaderTitle() {
        return getString(R.string.title_country_list);
    }

    @Override
    public void showListOfCountries(List<CountryModel> list) {
        CountryAdapter adapter = new CountryAdapter(list);
        countriesList.setAdapter(adapter);
        presenter.onSelectedCountry(adapter);
    }

    private void recyclerViewSetup() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        countriesList.setHasFixedSize(true);
        countriesList.setLayoutManager(layoutManager);
    }

    @Override
    public void showCountryDetailsScreen(CountryModel countryModel) {
        Timber.e("show");
        CountryDetailsActivity.start(this, countryModel);
    }
}
