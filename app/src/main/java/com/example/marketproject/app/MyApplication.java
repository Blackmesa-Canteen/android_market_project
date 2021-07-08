package com.example.marketproject.app;

import android.app.Application;
import android.content.Context;

public class MyApplication extends Application {
    public static Context getContext() {
        return mContext;
    }

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }
}
