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
                //????????????
                rvHome.scrollToPosition(0);
            }
        });

        //???????????????
        tv_search_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "??????", Toast.LENGTH_SHORT).show();
            }
        });

        //???????????????
        tv_message_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "??????????????????", Toast.LENGTH_SHORT).show();
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
                     * ??????????????????????????????
                     * @param call
                     * @param e
                     * @param id
                     */
                    @Override
                    public void onError(Call call, Exception e, int id) {

                        Log.e(TAG,"??????????????????=="+e.getMessage());
                    }

                    /**
                     * ??????????????????????????????
                     * @param response ?????????????????????
                     * @param id
                     */
                    @Override
                    public void onResponse(String response, int id) {
                        // Log.d(TAG,"??????????????????=="+response);
                        //????????????
                        processData(response);
                    }


                });
    }

    private void processData(String json) {
        ResultBean resultBeanData = JSON.parseObject(json,ResultBean.class);
        ResultBean.ResultDTO bean = resultBeanData.getResult();
        if(bean != null){
            //?????????
            //???????????????
            adapter = new HomeFragmentAdapter(mContext,bean);
            Log.d("OK", "?????????????????????");
            rvHome.setAdapter(adapter);
            GridLayoutManager manager =  new GridLayoutManager(mContext,1);
            //??????????????????????????? GridLayout?????????????????????????????????
            manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    if(position <= 3){
                        //??????
                        ib_top.setVisibility(View.GONE);
                    }else{
                        //??????
                        ib_top.setVisibility(View.VISIBLE);
                    }
                    //????????????1
                    return 1;
                }
            });

            //??????????????????
            rvHome.setLayoutManager(manager);

        }else{
            //????????????
        }
        Log.d(TAG,"????????????=="+bean.getHotInfo().get(0).getName());
    }
}
