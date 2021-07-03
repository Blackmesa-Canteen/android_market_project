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

public class HotGridViewAdapter extends BaseAdapter {
    private final Context mContext;
    private final List<ResultBean.ResultDTO.HotInfoDTO> data;

    public HotGridViewAdapter(Context mContext, List<ResultBean.ResultDTO.HotInfoDTO> hotInfo) {
        this.mContext = mContext;
        this.data = hotInfo;
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
        ViewHolder viewHolder;

        if(convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_hot_grid_view, null);
            viewHolder = new ViewHolder();
            viewHolder.iv_hot = (ImageView) convertView.findViewById(R.id.iv_hot);
            viewHolder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            viewHolder.tv_price = (TextView) convertView.findViewById(R.id.tv_price);
            convertView.setTag(viewHolder);

        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        ResultBean.ResultDTO.HotInfoDTO hotInfoDTO = data.get(position);
        Glide.with(mContext).load(Constants.Base_URL_IMAGE + hotInfoDTO.getFigure()).into(viewHolder.iv_hot);
        viewHolder.tv_name.setText(hotInfoDTO.getName());
        viewHolder.tv_price.setText("¥" + hotInfoDTO.getCoverPrice());

        return convertView;
    }

    static class ViewHolder {
        ImageView iv_hot;
        TextView tv_name;
        TextView tv_price;
    }
}
