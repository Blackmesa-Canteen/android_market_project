package com.example.marketproject.home.fragment;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.example.marketproject.R;
import com.example.marketproject.home.bean.ResultBean;
import com.example.marketproject.utils.Constants;
import com.example.marketproject.app.HttpClient;
import com.example.marketproject.app.MyStringCallBack;
import com.example.marketproject.base.BaseFragment;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HomeFragment extends BaseFragment {

    private static final String TAG =
            HomeFragment.class.getSimpleName();
    private RecyclerView rvHome;
    private ImageView ib_top;
    private TextView tv_search_home;
    private TextView tv_message_home;
    private ResultBean.ResultDTO result;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_home, null);
        rvHome = (RecyclerView) view.findViewById(R.id. rv_home);
        ib_top = (ImageView) view.findViewById(R.id.ib_top);
        tv_search_home = (TextView) view.findViewById(R.id.tv_search_home);
        tv_message_home = (TextView) view.findViewById(R.id.tv_message_home);
        initListener();
        return view;
    }

    private void initListener() {
        ib_top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //回到顶部
                rvHome.scrollToPosition(0);
            }
        });

        //搜素的监听
        tv_search_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "搜索", Toast.LENGTH_SHORT).show();
            }
        });

        //消息的监听
        tv_message_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "进入消息中心", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void initData() {
        super.initData();

        // get data from server
        getDataFromNetwork();
    }

    private void getDataFromNetwork() {
        String url = "http：//10.0.2.2:8080";
        OkHttpClient okHttpClient = HttpClient.getInstance().getOkHttpClient();
        MyStringCallBack myStringCallBack = new MyStringCallBack();

        Request request = new Request.Builder()
                .url(Constants.HOME_URL)
                .build();

        okHttpClient
        .newCall(request)
        .enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.e("Network-ERROR", "联网失败: " + e.getMessage());

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response != null) {
                    Log.d("OK", "请求成功。");
                    processData(response.body().string());
                }
            }
        });
    }

    private void processData(String json) {
        // using fastjson from Alibaba
        ResultBean resultBean = JSON.parseObject(json, ResultBean.class);
        result = resultBean.getResult();
        if(result != null) {
            Log.d("OK", "成功解析: " + result.getHotInfo().get(0).getName());
            /* set up an adapter */

        }
        else
        {

        }

    }
}
