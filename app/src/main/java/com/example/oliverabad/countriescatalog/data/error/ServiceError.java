package com.example.oliverabad.countriescatalog.data.error;

import java.lang.annotation.Annotation;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.HttpException;
import retrofit2.Response;
import retrofit2.Retrofit;
import timber.log.Timber;

/**
 * Created by oliverabad on 7/7/18.
 */

public enum  ServiceError {
    /* COMMON */
    TIMEOUT,
    NETWORK,
    UNAUTHORIZED, // 401
    FORBIDDEN, //403
    REQUEST_NOT_FOUND, // 404
    INTERNAL_SERVER_ERROR, // 500
    TECHNICAL,
    UNKNOWN;

    static public ServiceError error(Throwable error, Retrofit retrofit) {

        if (error instanceof HttpException) {
            HttpException httpException = (HttpException) error;
            Response response = httpException.response();

            try {
                Converter<ResponseBody, ServiceError> converter =
                        retrofit.responseBodyConverter(ServiceError.class, new Annotation[0]);
                ServiceError serviceError = converter.convert(response.errorBody());

                if (serviceError != null) {
                    return serviceError;
                }

                //Handling HTTP Code for cases when errorBody() is empty/null
                switch (httpException.code()) {
                    case 401:
                        return UNAUTHORIZED;
                    case 403:
                        return FORBIDDEN;
                    case 404:
                        return REQUEST_NOT_FOUND;
                    case 500:
                        return INTERNAL_SERVER_ERROR;
                }

            } catch (Exception e) {
                Timber.e(e.getMessage());
            }

        } else if (error instanceof ConnectException) {
            return NETWORK;

        } else if (error instanceof SocketTimeoutException) {
            return TIMEOUT;

        } else if (error instanceof UnknownHostException) {
            return TECHNICAL;
        }

        return UNKNOWN;
    }
}
