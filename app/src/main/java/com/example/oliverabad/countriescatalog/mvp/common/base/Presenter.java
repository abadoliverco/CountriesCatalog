package com.example.oliverabad.countriescatalog.mvp.common.base;

import com.example.oliverabad.countriescatalog.di.component.ApplicationComponent;

/**
 * Created by oliverabad on 7/7/18.
 */

public interface Presenter<V extends MvpView> {
    void attachView(V mvpView);

    void detachView();

    void loadData();

    void injectComponent(ApplicationComponent applicationComponent);
}
