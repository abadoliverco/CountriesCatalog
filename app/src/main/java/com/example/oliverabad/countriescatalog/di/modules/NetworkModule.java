package com.example.oliverabad.countriescatalog.di.modules;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.oliverabad.countriescatalog.BuildConfig;
import com.example.oliverabad.countriescatalog.di.scope.PerDataManager;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

/**
 * Created by oliverabad on 7/7/18.
 */

@Module
public class NetworkModule {
    private static final int TIMEOUT_DURATION = 30;

    @Provides
    @PerDataManager
    final GsonConverterFactory provideGsonConverterFactory() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        return GsonConverterFactory.create(gsonBuilder.create());
    }

    /**
     * Provides Cookie management instance.
     *
     * @return CookieManager cookie manager for OkHttpClient.
     */
    @Provides
    @PerDataManager
    final CookieManager provideCookieManager() {
        CookieManager cookieManager = new CookieManager();
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
        return cookieManager;
    }

    @Provides
    @PerDataManager
    SharedPreferences providesSharedPreferences(Application application) {
        return PreferenceManager.getDefaultSharedPreferences(application);
    }


    /**
     * Provides OkHttp client.
     *
     * @return OkHttpClient OkHttp client instance.
     */
    @Provides
    @PerDataManager
    final OkHttpClient provideOkHttpClient(CookieManager cookieManager) {
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
        okHttpClientBuilder.connectTimeout(TIMEOUT_DURATION, TimeUnit.SECONDS);
        okHttpClientBuilder.readTimeout(TIMEOUT_DURATION, TimeUnit.SECONDS);
        okHttpClientBuilder.cookieJar(new JavaNetCookieJar(cookieManager));

        //Take 3 re-try just to be sure
        okHttpClientBuilder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Response response = chain.proceed(request);

                int count = 1;
                while (!response.isSuccessful() && count < 3) {
                    Timber.e("Request Retry - " + count);
                    count++;
                    response = chain.proceed(request);
                }
                return response;
            }
        });


        /** enable logging on dev-only */
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            okHttpClientBuilder.addInterceptor(logging);
        }

        return okHttpClientBuilder.build();
    }

    /**
     * Provides RxJava call adaptor instance.
     *
     * @return RxJavaCallAdapter RxJava call adapter instance.
     */
    @Provides
    @PerDataManager
    final RxJavaCallAdapterFactory provideRxJavaCallAdapterFactory() {
        return RxJavaCallAdapterFactory.create();
    }


    /**
     * Provides Retrofit instance for API call.
     *
     * @param client           OkHttpClient Inject OkHttpClient from dependcy tree.
     * @param converterFactory GsonConverterFactory Inject GsonConverterFactory from dependency
     *                         tree.
     * @param adapterFactory   RxJavaCallAdapterFactory Inject RxJava call adapter factory from
     *                         dependency tree.
     * @return Retrofit Retrofit client instance.
     */
    @Provides
    @PerDataManager
    final Retrofit provideRetrofit(
            final OkHttpClient client,
            final GsonConverterFactory converterFactory,
            final RxJavaCallAdapterFactory adapterFactory
    ) {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.API_BASE_URL)
                .addConverterFactory(converterFactory)
                .addCallAdapterFactory(adapterFactory)
                .client(client)
                .build();
    }
}
