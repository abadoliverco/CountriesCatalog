package com.example.oliverabad.countriescatalog.data.error;

import com.example.oliverabad.countriescatalog.mvp.common.base.MvpView;

import timber.log.Timber;

/**
 * Created by oliverabad on 7/7/18.
 */


public class ServiceErrorUtil {

    public static void showErrorDialog(MvpView getView, ServiceError error) {

        switch (error) {
            case NETWORK:
                //TODO: show network error dialog
                Timber.e("no network connection");
                break;

            case TIMEOUT:
                //TODO: show timeout error dialog
                Timber.e("network timeout");
                break;

            case TECHNICAL:
            case FORBIDDEN:
            case UNAUTHORIZED:
            case REQUEST_NOT_FOUND:
            case INTERNAL_SERVER_ERROR:
                //TODO: show server error dialog
                Timber.e("server error");
                break;

            default:
                //TODO: show technical error dialog
                Timber.e("technical error");
        }
    }
}
