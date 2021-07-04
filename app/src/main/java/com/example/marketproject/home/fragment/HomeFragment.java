package com.example.marketproject.home.fragment;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.example.marketproject.R;
import com.example.marketproject.home.adapter.HomeFragmentAdapter;
import com.example.marketproject.home.bean.ResultBean;
import com.example.marketproject.utils.Constants;
import com.example.marketproject.app.HttpClient;
import com.example.marketproject.app.MyStringCallBack;
import com.example.marketproject.base.BaseFragment;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

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

    private HomeFragmentAdapter adapter;

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
        String url = Constants.HOME_URL;
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new StringCallback() {
                    /**
                     * 当请求失败的时候回调
                     * @param call
                     * @param e
                     * @param id
                     */
                    @Override
                    public void onError(Call call, Exception e, int id) {

                        Log.e(TAG,"首页请求失败=="+e.getMessage());
                    }

                    /**
                     * 当联网成功的时候回调
                     * @param response 请求成功的数据
                     * @param id
                     */
                    @Override
                    public void onResponse(String response, int id) {
                        // Log.d(TAG,"首页请求成功=="+response);
                        //解析数据
                        processData(response);
                    }


                });
    }

    private void processData(String json) {
        ResultBean resultBeanData = JSON.parseObject(json,ResultBean.class);
        ResultBean.ResultDTO bean = resultBeanData.getResult();
        if(bean != null){
            //有数据
            //设置适配器
            adapter = new HomeFragmentAdapter(mContext,bean);
            Log.d("OK", "适配器已经配置");
            rvHome.setAdapter(adapter);
            GridLayoutManager manager =  new GridLayoutManager(mContext,1);
            //设置跨度大小监听， GridLayout能设置跨度大小的监听器
            manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    if(position <= 3){
                        //隐藏
                        ib_top.setVisibility(View.GONE);
                    }else{
                        //显示
                        ib_top.setVisibility(View.VISIBLE);
                    }
                    //只能返回1
                    return 1;
                }
            });

            //设置布局管理
            rvHome.setLayoutManager(manager);

        }else{
            //没有数据
        }
        Log.d(TAG,"解析成功=="+bean.getHotInfo().get(0).getName());
    }
}
