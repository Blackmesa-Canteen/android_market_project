package com.example.marketproject.app;

import android.util.Log;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MyStringCallBack implements Callback {

    @Override
    public void onFailure(@NotNull Call call, @NotNull IOException e) {
        Log.e("Network-ERROR", "联网失败" + e.getMessage());
    }

    @Override
    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

    }
}
