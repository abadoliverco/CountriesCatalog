package com.example.oliverabad.countriescatalog.mvp.main.countries;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.oliverabad.countriescatalog.R;
import com.example.oliverabad.countriescatalog.data.model.CountryModel;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by oliverabad on 7/7/18.
 */
public class CountryViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.country_flag)
    ImageView countryFlag;

    @BindView(R.id.country_name)
    TextView countryName;

    @BindView(R.id.country_region)
    TextView countryRegion;

    @BindView(R.id.cardview_country)
    CardView cardViewCountry;

    public CountryViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bindData(final CountryModel country) {
        countryName.setText(country.getName());
        countryRegion.setText(country.getRegion());
        Glide.with(itemView).load(country.getFlagPng()).into(countryFlag);
    }
}
