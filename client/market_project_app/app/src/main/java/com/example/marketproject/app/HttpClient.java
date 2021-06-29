package com.example.marketproject.app;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

public class HttpClient {
    private final OkHttpClient okHttpClient;
    private static HttpClient instance;

    private HttpClient() {
        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                .build();
    }

    /**
     * One by one to get the instance
     */
    public synchronized static HttpClient getInstance() {
        if(instance == null) {
            instance = new HttpClient();
        }

        return instance;
    }

    public OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }
}
