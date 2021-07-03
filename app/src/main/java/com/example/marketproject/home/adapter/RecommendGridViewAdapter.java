package com.example.marketproject.home.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.marketproject.R;
import com.example.marketproject.home.bean.ResultBean;
import com.example.marketproject.utils.Constants;

import java.util.List;

public class RecommendGridViewAdapter extends BaseAdapter {
    private final Context mContext;
    private final List<ResultBean.ResultDTO.RecommendInfoDTO> data;

    public RecommendGridViewAdapter(Context mContext, List<ResultBean.ResultDTO.RecommendInfoDTO> recommendInfo) {
        this.mContext = mContext;
        data = recommendInfo;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_recommend_grid_view, null);
            viewHolder.iv_recommend = (ImageView) convertView.findViewById(R.id.iv_recommend);
            viewHolder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            viewHolder.tv_price = (TextView) convertView.findViewById(R.id.tv_price);

            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // 根据位置得到对应数据
        ResultBean.ResultDTO.RecommendInfoDTO recommendInfoDTO = data.get(position);
        Glide.with(mContext).load(Constants.Base_URL_IMAGE + recommendInfoDTO.getFigure()).into(viewHolder.iv_recommend);
        viewHolder.tv_name.setText(recommendInfoDTO.getName());
        viewHolder.tv_price.setText("¥" + recommendInfoDTO.getCoverPrice());

        return convertView;
    }

    static class ViewHolder {
        ImageView iv_recommend;
        TextView tv_name;
        TextView tv_price;
    }
}


