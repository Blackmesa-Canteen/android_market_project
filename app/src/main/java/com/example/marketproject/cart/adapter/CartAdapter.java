package com.example.marketproject.cart.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.marketproject.R;
import com.example.marketproject.cart.utils.CartStorage;
import com.example.marketproject.cart.view.AddSubView;
import com.example.marketproject.home.bean.GoodsBean;
import com.example.marketproject.utils.Constants;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    private final TextView tv_shopcart_total;
    private final CheckBox checkbox_all;
    private Context mContext;
    private List<GoodsBean> goodsBeanList;
    private OnItemClickListener onItemClickListener;

    public CartAdapter(Context mContext, List<GoodsBean> goodsBeanList, TextView tv_shopcart_total, CheckBox checkbox_all) {
        this.goodsBeanList = goodsBeanList;
        this.mContext = mContext;
        this.tv_shopcart_total = tv_shopcart_total;
        this.checkbox_all = checkbox_all;
        
        showTotalPrice();
        setListener();
    }

    private void setListener() {
        // 购物车商品单个点击
        setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                // 根据位置找到对应的，position 已经被下方的传入了
                GoodsBean goodsBean = goodsBeanList.get(position);
                // 选择状态取反
                goodsBean.setSelected(!goodsBean.isSelected());
                // 刷新adapter, 可能会重新调用对应的onBindViewHolder 方法
                notifyItemChanged(position);
                // 重新计算总价
                showTotalPrice();
            }
        });
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private CheckBox cb_gov;
        private ImageView iv_gov;
        private TextView tv_desc_gov;
        private TextView tv_price_gov;
        private AddSubView addSubView;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            cb_gov = (CheckBox) itemView.findViewById(R.id.cb_gov);
            iv_gov = (ImageView) itemView.findViewById(R.id.iv_gov);
            tv_desc_gov = (TextView) itemView.findViewById(R.id.tv_desc_gov);
            tv_price_gov = (TextView) itemView.findViewById(R.id.tv_price_gov);
            addSubView = (AddSubView) itemView.findViewById(R.id.addSubView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(getLayoutPosition());
                    }
                }
            });

        }
    }

    /**
     * 监听单个购物车物品
     */
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View itemView = View.inflate(mContext, R.layout.item_shop_cart, null);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        // 得到该position对应的bean
        GoodsBean goodsBean = goodsBeanList.get(position);
        // 设置数据
        holder.cb_gov.setChecked(goodsBean.isSelected());
        Glide.with(mContext).load(Constants.Base_URL_IMAGE + goodsBean.getFigure()).into(holder.iv_gov);
        holder.tv_desc_gov.setText(goodsBean.getName());
        holder.tv_price_gov.setText("¥" + goodsBean.getCover_price());
        holder.addSubView.setValue(goodsBean.getNumber());
        holder.addSubView.setMaxValue(8);
        holder.addSubView.setMinValue(1);

        // 应用自己定义的回吊函数
        holder.addSubView.setOnNumberChangeListener(new AddSubView.OnNumberChangeListener() {
            @Override
            public void onNumberChange(int value) {
                // 内存中和本地要更新数量
                // 刷新适配器
                // 再次计算总价格

                goodsBean.setNumber(value);
                CartStorage.getInstance().updateData(goodsBean);

                notifyItemChanged(position);

                showTotalPrice();
            }
        });
    }

    @Override
    public int getItemCount() {
        return goodsBeanList.size();
    }

    private void showTotalPrice() {
        tv_shopcart_total.setText("合计: ¥" + getTotalPrice());
    }

    private double getTotalPrice() {
        double totalPrice = 0.0;
        if(goodsBeanList != null && goodsBeanList.size() > 0) {
            for (GoodsBean bean : goodsBeanList) {
                if(bean.isSelected()) {
                    totalPrice += (double) bean.getNumber() * Double.parseDouble(bean.getCover_price());
                }
            }
        }

        return totalPrice;
    }
}
