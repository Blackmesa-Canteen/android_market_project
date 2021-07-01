package com.example.marketproject.home.adapter;

import android.content.Context;
import android.util.Log;
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

/**
 * Channel adapter
 */
public class ChannelAdapter extends BaseAdapter {
    private final Context mContext;
    private final List<ResultBean.ResultDTO.ChannelInfoDTO> data;

    public ChannelAdapter(Context mContext, List<ResultBean.ResultDTO.ChannelInfoDTO> channelInfo) {
        this.data = channelInfo;
        this.mContext = mContext;
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
            convertView = View.inflate(mContext, R.layout.item_channel, null);
            viewHolder = new ViewHolder();
            viewHolder.iv_icon = (ImageView) convertView.findViewById(R.id.iv_channel);
            viewHolder.tv_title = (TextView) convertView.findViewById(R.id.tv_title);
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        ResultBean.ResultDTO.ChannelInfoDTO channelInfoDTO = data.get(position);
        Glide.with(mContext)
                .load(Constants.Base_URL_IMAGE + channelInfoDTO.getImage())
                .into(viewHolder.iv_icon);
        viewHolder.tv_title.setText(channelInfoDTO.getChannelName());
        return convertView;
    }

    static class ViewHolder {
        ImageView iv_icon;
        TextView tv_title;
    }
}
