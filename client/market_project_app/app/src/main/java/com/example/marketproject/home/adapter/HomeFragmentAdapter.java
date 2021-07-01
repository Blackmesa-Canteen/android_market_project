package com.example.marketproject.home.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.marketproject.R;
import com.example.marketproject.home.bean.ResultBean;
import com.example.marketproject.utils.Constants;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerClickListener;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnLoadImageListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class HomeFragmentAdapter extends RecyclerView.Adapter {

    // 广告条幅
    public static final int BANNER = 0;
    // 频道
    public static final int CHANNEL = 1;
    // 活动
    public static final int ACT = 2;
    // 新品秒杀
    public static final int SECKILL = 3;
    // 推荐
    public static final int RECOMMEND = 4;
    // 热卖
    public static final int HOT = 5;

    /**
     * 总共6种类型：人为定义的
     */
    private int currentType = BANNER;

    private final Context mContext;
    private final ResultBean.ResultDTO resultBean;
    /**
     * 用来初始化布局
     */
    private LayoutInflater mLayoutInflater;

    public HomeFragmentAdapter(Context context, ResultBean.ResultDTO resultBean) {
        this.mContext = context;
        this.resultBean = resultBean;
        mLayoutInflater = LayoutInflater.from(mContext);
    }
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
        if (viewType == BANNER) {
            return new BannerViewHolder(mContext, mLayoutInflater.inflate(R.layout.banner_viewpager, null));
        } else if (viewType == CHANNEL) {
            return new ChannelViewHolder(mContext, mLayoutInflater.inflate(R.layout.channel_item, null));
        }
//        else if (viewType == ACT)
//        {
//            return new ActViewHolder(mContext, mLayoutInflater.inflate(R.layout.act_item, null));
//        }else if (viewType == SECKILL) {
//            return new SeckillViewHolder(mContext, mLayoutInflater.inflate(R.layout.seckill_item, null));
//        }else if(viewType == RECOMMEND ){
//            return new RecommendViewHolder(mContext, mLayoutInflater.inflate(R.layout.recommend_item, null));
//        }else if(viewType == HOT){
//            return new HotViewHolder(mContext, mLayoutInflater.inflate(R.layout.hot_item, null));
//        }
        return null;
    }

    /**
     * 绑定数据
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == BANNER) {
            BannerViewHolder bannerViewHolder = (BannerViewHolder) holder;
            bannerViewHolder.setData(resultBean.getBannerInfo());
        } else if (getItemViewType(position) == CHANNEL) {
            ChannelViewHolder channelViewHolder = (ChannelViewHolder) holder;
            channelViewHolder.setData(resultBean.getChannelInfo());
        }
//        else if (getItemViewType(position) == ACT) {
//            ActViewHolder actViewHolder = (ActViewHolder) holder;
//            actViewHolder.setData(resultBean.getAct_info());
//        }else if(getItemViewType(position) == SECKILL){
//            SeckillViewHolder seckillViewHolder = (SeckillViewHolder) holder;
//            seckillViewHolder.setData(resultBean.getSeckill_info());
//        }else if(getItemViewType(position) == RECOMMEND){
//            RecommendViewHolder recommendViewHolder = (RecommendViewHolder) holder;
//            recommendViewHolder.setData(resultBean.getRecommend_info());
//        }else if(getItemViewType(position)==HOT){
//            HotViewHolder hotViewHolder = (HotViewHolder) holder;
//            hotViewHolder.setData(resultBean.getHot_info());
//        }
    }

    /**
     * Get total items
     * @return
     */
    @Override
    public int getItemCount() {
        // start from 1 -> 2 ... 6
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case BANNER:
                currentType = BANNER;
                break;
            case CHANNEL:
                currentType = CHANNEL;
                break;
            case ACT:
                currentType = ACT;
                break;
            case SECKILL:
                currentType = SECKILL;
                break;
            case RECOMMEND:
                currentType = RECOMMEND;
                break;
            case HOT:
                currentType = HOT;
                break;
            default:
                break;
        }
        return currentType;
    }

    class BannerViewHolder extends  RecyclerView.ViewHolder {

        private Context mContext;
        private Banner banner;

        public BannerViewHolder(Context mContext, View itemView) {
            super(itemView);
            this.mContext = mContext;
            this.banner = (Banner) itemView.findViewById(R.id.banner);
        }

        @Override
        public String toString() {
            return super.toString();
        }

        /**
         * 绑定数据到banner上
         * @param bannerInfo
         */
        public void setData(List<ResultBean.ResultDTO.BannerInfoDTO> bannerInfo) {
            //设置Banner的数据
            //得到图片集合地址
            List<String> imagesUrl = new ArrayList<>();
            for (int i = 0; i < bannerInfo.size(); i++) {
                String imageUrl = bannerInfo.get(i).getImage();
                imagesUrl.add(imageUrl);
            }

            //设置循环指示点
            banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
            //设置手风琴效果
            banner.setBannerAnimation(Transformer.Accordion);
            banner.setImages(imagesUrl, new OnLoadImageListener() {
                @Override
                public void OnLoadImage(ImageView view, Object url) {

                    //联网请求图片-Glide
                    Glide.with(mContext).load(Constants.Base_URL_IMAGE + url).into(view);

                }
            });

            //设置item的点击事件
            banner.setOnBannerClickListener(position -> Toast.makeText(mContext, "position==" + position, Toast.LENGTH_SHORT).show());
        }
    }

    class ChannelViewHolder extends RecyclerView.ViewHolder {

        private Context mContext;
        private GridView gvChannel;

        // GrkdView 适配器
        private ChannelAdapter adapter;

        public ChannelViewHolder(Context mContext, @NonNull @NotNull View itemView) {
            super(itemView);
            this.mContext = mContext;
            gvChannel = (GridView) itemView.findViewById(R.id.gv_channel);

            //设置item的点击事件
            gvChannel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView parent, View view, int position, long id) {
                    Toast.makeText(mContext, "position" + position, Toast.LENGTH_SHORT).show();
                }
            });

        }

        public void setData(List<ResultBean.ResultDTO.ChannelInfoDTO> channelInfo) {
            adapter = new ChannelAdapter(mContext, channelInfo);
            gvChannel.setAdapter(adapter);
        }
    }


}
