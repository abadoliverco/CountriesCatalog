package com.example.oliverabad.countriescatalog.mvp.main.details;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.oliverabad.countriescatalog.R;
import com.example.oliverabad.countriescatalog.data.model.CountryModel;
import com.example.oliverabad.countriescatalog.mvp.common.injectable.InjectableActivity;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by oliverabad on 7/7/18.
 */
public class CountryDetailsActivity extends InjectableActivity implements CountryDetailsMvpView{

    public static final String BUNDLE_COUNTRY_MODEL = "BUNDLE_COUNTRY_MODEL";

    @BindView(R.id.country_name)
    TextView countryName;

    @BindView(R.id.currency_code_value)
    TextView currencyCodeValue;

    @BindView(R.id.currency_name_value)
    TextView currencyNameValue;

    @BindView(R.id.latitude_value)
    TextView latitudeValue;

    @BindView(R.id.longitude_value)
    TextView longitudeValue;

    @BindView(R.id.region_value)
    TextView regionValue;

    @BindView(R.id.sub_region_value)
    TextView subRegionValue;

    @BindView(R.id.native_language_value)
    TextView nativeLanguageValue;

    @BindView(R.id.native_name_value)
    TextView nativeNameValue;

    @BindView(R.id.country_flag)
    ImageView countryFlag;

    private String selectedCountry;



    @Inject
    CountryDetailsPresenter presenter;

    public static void start(Activity activity, CountryModel countryModel) {
        Intent intent = new Intent(activity, CountryDetailsActivity.class);
        intent.putExtra(BUNDLE_COUNTRY_MODEL, countryModel);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivityComponent().inject(this);
        presenter.setBundle(getIntent());
        presenter.attachView(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_country_details;
    }

    @Override
    protected boolean hasBackButton() {
        return true;
    }

    @Override
    protected String setHeaderTitle() {
        return getString(R.string.title_country_details);
    }

    @Override
    public void showDetails(CountryModel countryModel) {
        this.selectedCountry = countryModel.getName();

        Glide.with(this).load(countryModel.getFlagPng()).into(countryFlag);
        countryName.setText(countryModel.getName());
        currencyCodeValue.setText(countryModel.getCurrencyCode());
        currencyNameValue.setText(countryModel.getCurrencyName());
        latitudeValue.setText(countryModel.getLatitude());
        longitudeValue.setText(countryModel.getLongitude());
        regionValue.setText(countryModel.getRegion());
        subRegionValue.setText(countryModel.getSubRegion());
        nativeLanguageValue.setText(countryModel.getNativeLanguage());
        nativeNameValue.setText(countryModel.getNativeName());
    }
}
