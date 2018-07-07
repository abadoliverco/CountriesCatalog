package com.example.oliverabad.countriescatalog;

import android.app.Application;

import com.example.oliverabad.countriescatalog.di.component.ApplicationComponent;
import com.example.oliverabad.countriescatalog.di.component.DaggerApplicationComponent;
import com.example.oliverabad.countriescatalog.di.modules.ApplicationModule;

import timber.log.Timber;

/**
 * Created by oliverabad on 7/7/18.
 */
public class CountriesCatalogApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        //user timber for logs
        if (BuildConfig.DEBUG) Timber.plant(new Timber.DebugTree());
        initializeInjector();
    }


    private void initializeInjector() {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();

        getApplicationComponent().inject(this);
    }

    /**
     * Getter method to return ApplicationComponent, usually called at activities.
     *
     * @return ApplicationComponent
     */
    public final ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

}
