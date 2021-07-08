package com.example.marketproject.cart.fragment;

import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.marketproject.R;
import com.example.marketproject.base.BaseFragment;
import com.example.marketproject.cart.utils.CartStorage;
import com.example.marketproject.home.bean.GoodsBean;

import java.util.List;

public class CartFragment extends BaseFragment implements View.OnClickListener {

    private TextView tvShopcartEdit;
    private RecyclerView recyclerview;
    private LinearLayout llCheckAll;
    private CheckBox checkboxAll;
    private TextView tvShopcartTotal;
    private Button btnCheckOut;
    private LinearLayout llDelete;
    private CheckBox cbAll;
    private Button btnDelete;
    private Button btnCollection;

    private ImageView ivEmpty;
    private TextView tvEmptyCartTobuy;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_cart, null);
        tvShopcartEdit = (TextView)view.findViewById( R.id.tv_shopcart_edit );
        recyclerview = (RecyclerView)view.findViewById( R.id.recyclerview );
        llCheckAll = (LinearLayout)view.findViewById( R.id.ll_check_all );
        checkboxAll = (CheckBox)view.findViewById( R.id.checkbox_all );
        tvShopcartTotal = (TextView)view.findViewById( R.id.tv_shopcart_total );
        btnCheckOut = (Button)view.findViewById( R.id.btn_check_out );
        llDelete = (LinearLayout)view.findViewById( R.id.ll_delete );
        cbAll = (CheckBox)view.findViewById( R.id.cb_all );
        btnDelete = (Button)view.findViewById( R.id.btn_delete );
        btnCollection = (Button)view.findViewById( R.id.btn_collection );

        ivEmpty = (ImageView)view.findViewById( R.id.iv_empty );
        tvEmptyCartTobuy = (TextView)view.findViewById( R.id.tv_empty_cart_tobuy );

        btnCheckOut.setOnClickListener( this );
        btnDelete.setOnClickListener( this );
        btnCollection.setOnClickListener( this );
        return view;
    }

    /**
     * Handle button click events
     */
    @Override
    public void onClick(View v) {
        if ( v == btnCheckOut ) {
            // Handle clicks for btnCheckOut
        } else if ( v == btnDelete ) {
            // Handle clicks for btnDelete
        } else if ( v == btnCollection ) {
            // Handle clicks for btnCollection
        }
    }

    @Override
    public void initData() {
        super.initData();

        List<GoodsBean> allData = CartStorage.getInstance().getAllData();
        for (GoodsBean goodsBean : allData) {
            Toast.makeText(mContext, "data got", Toast.LENGTH_SHORT).show();
        }
    }
}
