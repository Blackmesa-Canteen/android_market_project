package com.example.marketproject.app;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.example.marketproject.home.bean.ResultBean;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MyStringCallBack implements Callback {

    private ResultBean.ResultDTO result;

    @Override
    public void onFailure(@NotNull Call call, @NotNull IOException e) {
        Log.e("Network-ERROR", "联网失败: " + e.getMessage());
    }

    @Override
    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
        if (response != null) {
            Log.d("OK", "请求成功。");
            Log.d("OK", response.body().string());
            processData(response.body().string());
        }
    }

    private void processData(String json) {

        // using fastjson from Alibaba
        ResultBean resultBean = JSON.parseObject(json, ResultBean.class);
        result = resultBean.getResult();
    }

    public ResultBean.ResultDTO getResultData() {
        return result;
    }
}
