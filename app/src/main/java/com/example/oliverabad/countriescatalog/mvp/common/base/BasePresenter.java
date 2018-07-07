package com.example.oliverabad.countriescatalog.mvp.common.base;

import android.content.Context;
import android.support.annotation.CallSuper;

import com.example.oliverabad.countriescatalog.CountriesCatalogApplication;
import com.example.oliverabad.countriescatalog.data.error.ServiceError;
import com.example.oliverabad.countriescatalog.di.component.ApplicationComponent;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Single;
import rx.SingleSubscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by oliverabad on 7/7/18.
 */

public abstract class BasePresenter<T extends MvpView> implements Presenter<T> {

    protected BasePresenterInjection injectionContainer;
    protected List<Subscription> subscriptions = new ArrayList<>();
    private T mvpView;
    private Context context;


    public BasePresenter(Context context) {
        this.context = context;
        injectionContainer = new BasePresenterInjection();
    }

    protected BasePresenterInjection getInjectionContainer() {
        return injectionContainer;
    }

    protected ApplicationComponent getApplicationComponent() {
        return ((CountriesCatalogApplication) getContext().getApplicationContext()).getApplicationComponent();
    }

    public T getView() {
        return mvpView;
    }

    public Context getContext() {
        return context;
    }

    @Override
    @CallSuper
    public void attachView(T mvpView) {
        this.mvpView = mvpView;
        //Inject don't work with heritage. Each instance must call inject method.
        injectComponent(getApplicationComponent());
        getApplicationComponent().inject(injectionContainer);
        loadData();
    }

    @Override
    @CallSuper
    public void detachView() {
        mvpView = null;
        // Unsubscribe
        int size = subscriptions.size();
        Subscription subscription;
        for (int i = 0; i < size; i++) {
            subscription = subscriptions.remove(0);
            if (subscription != null && !subscription.isUnsubscribed()) {
                subscription.unsubscribe();
            }
        }
    }

    /**
     * Consume observable.
     *
     * @param observable Observable
     * @param subscriber Observer
     */
    protected final <V> void singleSubscribe(final Observable<V> observable, final SingleSubscriber<V> subscriber) {
        subscriptions.add(observable.subscribeOn(Schedulers.io())
                .toSingle()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber));
    }

    /**
     * Consume observable with error with default handling
     *
     * @param observable Observable for call
     * @param
     */
    protected final <T> void baseSubscribe(final Observable<T> observable, final BaseSubscriber<T> subscriber) {

        SingleSubscriber<T> singleSubscriber = new SingleSubscriber<T>() {
            @Override
            public void onSuccess(T value) {
                if (subscriber != null) {
                    subscriber.onComplete();
                    subscriber.onSuccess(value);
                }
            }

            @Override
            public void onError(Throwable error) {
                ServiceError serviceError = ServiceError.error(error, injectionContainer.getRetrofit());

                if (subscriber != null) {
                    subscriber.onComplete();
                    subscriber.onError(serviceError);
                }
            }
        };

        subscriptions.add(observable.subscribeOn(Schedulers.io())
                .toSingle()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(singleSubscriber));
    }


    public interface BaseSubscriber<T> {
        /**
         * Notifies the SingleSubscriber with a single item and that the {@link Single} has finished sending
         * push-based notifications.
         * <p>
         * The {@link Single} will not call this method if it calls {@link #onError}.
         *
         * @param t the item emitted by the Single
         */
        void onSuccess(T t);

        /**
         * Notifies the SingleSubscriber that the {@link Single} has experienced an error condition.
         * <p>
         * If the {@link Single} calls this method, it will not thereafter call {@link #onSuccess}.
         *
         * @param error the service error encountered by the Single
         */
        void onError(ServiceError error);

        /**
         * Notifies the SingleSubscriber that the {@link Single} has finished and sent a push-based notifications or experienced an error condition
         * <p>
         * If the {@link Single} call this method one (and only one) of {@link #onSuccess(Object)} or {@link #onError(ServiceError)} method is also called.
         */
        void onComplete();
    }
}
