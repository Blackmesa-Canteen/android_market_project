package com.example.marketproject.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.marketproject.R;
import com.example.marketproject.app.GoodsInfoActivity;
import com.example.marketproject.home.bean.GoodsBean;
import com.example.marketproject.home.bean.ResultBean;
import com.example.marketproject.utils.Constants;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerClickListener;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnLoadImageListener;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

    private static final String GOODS_BEAN = "goodsBean";

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
     * 启动商品详情页
     * @param goodsBean
     */
    private void startGoodsInfoActivity(GoodsBean goodsBean) {
        Intent intent = new Intent(mContext, GoodsInfoActivity.class);
        intent.putExtra(GOODS_BEAN, goodsBean);
        mContext.startActivity(intent);
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
        }
        else if (viewType == CHANNEL) {
            return new ChannelViewHolder(mContext, mLayoutInflater.inflate(R.layout.channel_item, null));
        }
        else if (viewType == ACT)
        {
            return new ActViewHolder(mContext, mLayoutInflater.inflate(R.layout.act_item, null));
        }
        else if (viewType == SECKILL) {
            return new SeckillViewHolder(mContext, mLayoutInflater.inflate(R.layout.seckill_item, null));
        }
        else if(viewType == RECOMMEND ){
            return new RecommendViewHolder(mContext, mLayoutInflater.inflate(R.layout.recommend_item, null));
        }
        else if(viewType == HOT){
            return new HotViewHolder(mContext, mLayoutInflater.inflate(R.layout.hot_item, null));
        }
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
        else if (getItemViewType(position) == ACT) {
            ActViewHolder actViewHolder = (ActViewHolder) holder;
            actViewHolder.setData(resultBean.getActInfo());
        }
        else if(getItemViewType(position) == SECKILL){
            SeckillViewHolder seckillViewHolder = (SeckillViewHolder) holder;
            seckillViewHolder.setData(resultBean.getSeckillInfo());
        }
        else if(getItemViewType(position) == RECOMMEND){
            RecommendViewHolder recommendViewHolder = (RecommendViewHolder) holder;
            recommendViewHolder.setData(resultBean.getRecommendInfo());
        }
        else if(getItemViewType(position)==HOT){
            HotViewHolder hotViewHolder = (HotViewHolder) holder;
            hotViewHolder.setData(resultBean.getHotInfo());
        }
    }

    /**
     * Get total items
     * @return
     */
    @Override
    public int getItemCount() {
        // start from 1 -> 2 ... 6
        return 6;
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
            banner.setOnBannerClickListener(new OnBannerClickListener() {
                @Override
                public void OnBannerClick(int position) {
                    Toast.makeText(mContext, "position==" + position, Toast.LENGTH_SHORT).show();
//                    startGoodsInfoActivity(goodsBean);
                }
            });
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
            gvChannel.setOnItemClickListener((parent, view, position, id) -> Toast.makeText(mContext, "position" + position, Toast.LENGTH_SHORT).show());

        }

        public void setData(List<ResultBean.ResultDTO.ChannelInfoDTO> channelInfo) {
            adapter = new ChannelAdapter(mContext, channelInfo);
            gvChannel.setAdapter(adapter);
        }
    }


    class ActViewHolder extends RecyclerView.ViewHolder {
        private final Context mContext;
        private final ViewPager act_viewpager;

        public ActViewHolder(Context mContext, View itemView) {
            super(itemView);
            this.mContext = mContext;
            act_viewpager = (ViewPager) itemView.findViewById(R.id.act_viewpager);
        }

        public void setData(List<ResultBean.ResultDTO.ActInfoDTO> actInfo) {
            act_viewpager.setPageMargin(20);
            act_viewpager.setAdapter(new PagerAdapter() {
                @Override
                public int getCount() {
                    return actInfo.size();
                }

                /**
                 * 判断是不是一样的
                 * @param view 页面
                 * @param object instantiateItem()方法返回的值
                 * @return
                 */
                @Override
                public boolean isViewFromObject(@NonNull @NotNull View view, @NonNull @NotNull Object object) {
                    return view == object;
                }

                /**
                 *
                 * @param container ViewPager
                 * @param position 对应页面的位置
                 * @return
                 */
                @NonNull
                @NotNull
                @Override
                public Object instantiateItem(@NonNull @NotNull ViewGroup container, int position) {
                    ImageView imageView = new ImageView(mContext);
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);

                    // 加载图片
                    Glide.with(mContext).load(Constants.Base_URL_IMAGE + actInfo.get(position).getIconUrl()).into(imageView);
                    // 添加到容器中
                    container.addView(imageView);
                    imageView.setOnClickListener(v -> Toast.makeText(mContext, "position == " + position, Toast.LENGTH_SHORT).show());
                    return imageView;
                }

                @Override
                public void destroyItem(@NonNull @NotNull ViewGroup container, int position, @NonNull @NotNull Object object) {
                    container.removeView((View) object);
                }
            });


        }
    }

    /**
     * 相差多少毫秒
     */
    private long dt = 0;
    class SeckillViewHolder extends RecyclerView.ViewHolder {
        private final Context mContext;
        private TextView tv_time_seckill;
        private TextView tv_more_seckill;
        private RecyclerView rv_seckill;
        private SecKillRecyclerViewAdapter adapter;

        public SeckillViewHolder(Context mContext, View viewItem) {
            super(viewItem);
            this.mContext = mContext;
            tv_time_seckill = (TextView) viewItem.findViewById(R.id.tv_time_seckill);
            tv_more_seckill = (TextView) viewItem.findViewById(R.id.tv_more_seckill);
            rv_seckill = (RecyclerView) viewItem.findViewById(R.id.rv_seckill);
            
        }

        public void setData(ResultBean.ResultDTO.SeckillInfoDTO seckillInfo) {
            // 得到数据，设置数据（文本+recycleView的数据）
            adapter = new SecKillRecyclerViewAdapter(mContext, seckillInfo.getList());
            rv_seckill.setAdapter(adapter);
            // recycleView 设置布局管理器
            rv_seckill.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
            adapter.setOnSeckillRecyclerView(new SecKillRecyclerViewAdapter.OnSeckillRecyclerView() {
                @Override
                public void onItemClick(int position) {
                    GoodsBean goodsBean = new GoodsBean();
                    ResultBean.ResultDTO.SeckillInfoDTO.ListDTO listDTO = seckillInfo.getList().get(position);

                    // 秒杀商品信息类
                    goodsBean.setCover_price(listDTO.getCoverPrice());
                    goodsBean.setFigure(listDTO.getFigure());
                    goodsBean.setName(listDTO.getName());
                    goodsBean.setProduct_id(listDTO.getProductId());
                    startGoodsInfoActivity(goodsBean);
                }
            });
            

            // 秒杀的倒计时
            dt = Long.parseLong(seckillInfo.getEndTime()) - Long.parseLong(seckillInfo.getStartTime());
            SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
            tv_time_seckill.setText(format.format(new Date(dt)));

            handler.sendEmptyMessageDelayed(0, 1000);
        }

        private final Handler handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {
                dt = dt - 1000;
                SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
                String time = formatter.format(new Date(dt));
                tv_time_seckill.setText(time);

                handler.removeMessages(0);
                handler.sendEmptyMessageDelayed(0,1000);
                if(dt <=0){
                    //把消息移除
                    handler.removeCallbacksAndMessages(null);
                }

                return true;
            }
        });
    }

    class RecommendViewHolder extends RecyclerView.ViewHolder {
        private final Context mContext;
        private TextView tv_more_recommend;
        private GridView gv_recommend;
        private RecommendGridViewAdapter adapter;

        public RecommendViewHolder(Context mContext, View viewItem) {
            super(viewItem);
            this.mContext = mContext;

            tv_more_recommend = (TextView) viewItem.findViewById(R.id.tv_more_recommend);
            gv_recommend = (GridView) viewItem.findViewById(R.id.gv_recommend);
        }


        public void setData(List<ResultBean.ResultDTO.RecommendInfoDTO> recommendInfo) {
            adapter = new RecommendGridViewAdapter(mContext, recommendInfo);
            gv_recommend.setAdapter(adapter);

            gv_recommend.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    GoodsBean goodsBean = new GoodsBean();
                    ResultBean.ResultDTO.RecommendInfoDTO hotInfoDTO = recommendInfo.get(position);

                    // 推荐商品信息类
                    goodsBean.setCover_price(hotInfoDTO.getCoverPrice());
                    goodsBean.setFigure(hotInfoDTO.getFigure());
                    goodsBean.setName(hotInfoDTO.getName());
                    goodsBean.setProduct_id(hotInfoDTO.getProductId());
                    startGoodsInfoActivity(goodsBean);
                }
            });
        }
    }

    class HotViewHolder extends RecyclerView.ViewHolder {
        private Context mContext;
        private final TextView tv_more_hot;
        private final GridView gv_hot;
        private HotGridViewAdapter adapter;
        public HotViewHolder(Context mContext, View itemView) {
            super(itemView);
            this.mContext = mContext;
            tv_more_hot = (TextView) itemView.findViewById(R.id.tv_more_hot);
            gv_hot = (GridView) itemView.findViewById(R.id.gv_hot);


        }

        public void setData(List<ResultBean.ResultDTO.HotInfoDTO> hotInfo) {
            adapter = new HotGridViewAdapter(mContext, hotInfo);
            gv_hot.setAdapter(adapter);

            gv_hot.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    GoodsBean goodsBean = new GoodsBean();
                    ResultBean.ResultDTO.HotInfoDTO hotInfoDTO = hotInfo.get(position);

                    // 热卖商品信息类
                    goodsBean.setCover_price(hotInfoDTO.getCoverPrice());
                    goodsBean.setFigure(hotInfoDTO.getFigure());
                    goodsBean.setName(hotInfoDTO.getName());
                    goodsBean.setProduct_id(hotInfoDTO.getProductId());
                    startGoodsInfoActivity(goodsBean);
                }
            });
        }
    }
}
