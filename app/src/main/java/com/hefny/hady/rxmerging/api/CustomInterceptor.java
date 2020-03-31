package com.hefny.hady.rxmerging.api;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class CustomInterceptor implements Interceptor {

    @NotNull
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Headers headers = new Headers.Builder()
                .add("X-Auth-Token", "12faf50c4edc47d2bdc76add010fc0c1")
                .build();

        Request newRequest = request.newBuilder()
                .cacheControl(CacheControl.FORCE_CACHE)
                .headers(headers)
                .method(request.method(), request.body())
                .build();

        Response response = chain.proceed(newRequest);

        return response;
    }
}
