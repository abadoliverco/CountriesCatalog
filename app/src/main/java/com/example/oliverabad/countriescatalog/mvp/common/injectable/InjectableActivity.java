package com.example.oliverabad.countriescatalog.mvp.common.injectable;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.oliverabad.countriescatalog.CountriesCatalogApplication;
import com.example.oliverabad.countriescatalog.di.component.ActivitiesComponent;
import com.example.oliverabad.countriescatalog.di.component.ApplicationComponent;
import com.example.oliverabad.countriescatalog.di.component.DaggerActivitiesComponent;
import com.example.oliverabad.countriescatalog.di.modules.PresentersModule;
import com.example.oliverabad.countriescatalog.mvp.common.base.BaseActivity;

/**
 * Created by oliverabad on 7/7/18.
 */
public abstract class InjectableActivity extends BaseActivity {

    private ActivitiesComponent activitiesComponent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getApplicationComponent().inject(this);
    }
    protected ActivitiesComponent getActivityComponent() {
        if (activitiesComponent == null) {
            activitiesComponent = DaggerActivitiesComponent.builder()
                    .applicationComponent(getApplicationComponent())
                    .presentersModule(new PresentersModule(this))
                    .build();
        }
        return activitiesComponent;
    }

    private ApplicationComponent getApplicationComponent() {
        return ((CountriesCatalogApplication) getApplication()).getApplicationComponent();
    }
}
