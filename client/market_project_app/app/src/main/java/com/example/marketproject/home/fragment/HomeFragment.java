package com.example.marketproject.home.fragment;

import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.example.marketproject.base.BaseFragment;

public class HomeFragment extends BaseFragment {

    private TextView textView;

    @Override
    public View initView() {
        textView = new TextView(mContext);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(25);
        return textView;
    }

    @Override
    public void initData() {
        super.initData();

        textView.setText("home page");
    }
}
