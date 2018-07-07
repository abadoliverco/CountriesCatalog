package com.example.oliverabad.countriescatalog.di.component;

import com.example.oliverabad.countriescatalog.data.api.DataManager;
import com.example.oliverabad.countriescatalog.di.modules.ApiServiceModule;
import com.example.oliverabad.countriescatalog.di.modules.NetworkModule;
import com.example.oliverabad.countriescatalog.di.scope.PerDataManager;

import dagger.Component;

/**
 * Created by oliverabad on 7/7/18.
 */
@PerDataManager
@Component(dependencies = ApplicationComponent.class, modules = {ApiServiceModule.class, NetworkModule.class})
public interface DataManagerComponent {
    void inject(DataManager dataManager);
}
