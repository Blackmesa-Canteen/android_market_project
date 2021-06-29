package com.example.marketproject.home.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

public class HomeFragmentAdapter extends RecyclerView.Adapter {

    public static final int BANNER = 0;

    /**
     * 总共6种类型：人为定义的
     */
    private int currentType = 0;

    /**
     * 相当于getView
     * @param parent
     * @param viewType 当前类型
     * @return
     */
    @NonNull
    @NotNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return null;
    }

    /**
     * 绑定数据
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder holder, int position) {

    }

    /**
     * Get total items
     * @return
     */
    @Override
    public int getItemCount() {
        // start from 1 -> 2
        return 6;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }
}
