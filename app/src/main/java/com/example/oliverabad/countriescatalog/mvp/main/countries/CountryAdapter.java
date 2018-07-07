package com.example.oliverabad.countriescatalog.mvp.main.countries;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.oliverabad.countriescatalog.R;
import com.example.oliverabad.countriescatalog.data.model.CountryModel;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.subjects.PublishSubject;

/**
 * Created by oliverabad on 7/7/18.
 */
public class CountryAdapter extends RecyclerView.Adapter<CountryViewHolder> {

    private List<CountryModel> countryList = new ArrayList<>();

    private final PublishSubject<CountryModel> onSelectedCountry = PublishSubject.create();

    public CountryAdapter(List<CountryModel> list) {
        this.countryList = list;
    }

    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_country, parent, false);
        return new CountryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder holder, final int position) {
        ((CountryViewHolder) holder).bindData(countryList.get(position));
        ((CountryViewHolder) holder).cardViewCountry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSelectedCountry.onNext(countryList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return countryList.size();
    }

    public Observable<CountryModel> getSelectedCountry() {
        return onSelectedCountry.asObservable();
    }
}
