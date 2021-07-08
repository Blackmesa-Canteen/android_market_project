package com.example.marketproject.app;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.marketproject.R;
import com.example.marketproject.cart.utils.CartStorage;
import com.example.marketproject.home.bean.GoodsBean;
import com.example.marketproject.utils.CacheUtils;
import com.example.marketproject.utils.Constants;

public class GoodsInfoActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton ibGoodInfoBack;
    private ImageButton ibGoodInfoMore;
    private ImageView ivGoodInfoImage;
    private TextView tvGoodInfoName;
    private TextView tvGoodInfoDesc;
    private TextView tvGoodInfoPrice;
    private TextView tvGoodInfoStore;
    private TextView tvGoodInfoStyle;
    private WebView wbGoodInfoMore;
    private LinearLayout llGoodsRoot;
    private TextView tvGoodInfoCallcenter;
    private TextView tvGoodInfoCollection;
    private TextView tvGoodInfoCart;
    private Button btnGoodInfoAddcart;
    private TextView tv_more_share;
    private TextView tv_more_search;
    private TextView tv_more_home;
    private Button btn_more;

    private static final String GOODS_BEAN = "goodsBean";
    private GoodsBean goodsBean;


    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2021-07-05 22:26:12 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        ibGoodInfoBack = (ImageButton)findViewById( R.id.ib_good_info_back );
        ibGoodInfoMore = (ImageButton)findViewById( R.id.ib_good_info_more );
        ivGoodInfoImage = (ImageView)findViewById( R.id.iv_good_info_image );
        tvGoodInfoName = (TextView)findViewById( R.id.tv_good_info_name );
        tvGoodInfoDesc = (TextView)findViewById( R.id.tv_good_info_desc );
        tvGoodInfoPrice = (TextView)findViewById( R.id.tv_good_info_price );
        tvGoodInfoStore = (TextView)findViewById( R.id.tv_good_info_store );
        tvGoodInfoStyle = (TextView)findViewById( R.id.tv_good_info_style );
        wbGoodInfoMore = (WebView)findViewById( R.id.wb_good_info_more );
        llGoodsRoot = (LinearLayout)findViewById( R.id.ll_goods_root );

        tvGoodInfoCallcenter = (TextView)findViewById( R.id.tv_good_info_callcenter );
        tvGoodInfoCollection = (TextView)findViewById( R.id.tv_good_info_collection );
        tvGoodInfoCart = (TextView)findViewById( R.id.tv_good_info_cart );
        btnGoodInfoAddcart = (Button)findViewById( R.id.btn_good_info_addcart );

        ibGoodInfoBack.setOnClickListener( this );
        ibGoodInfoMore.setOnClickListener( this );
        btnGoodInfoAddcart.setOnClickListener( this );

        tvGoodInfoCallcenter.setOnClickListener(this);
        tvGoodInfoCollection.setOnClickListener(this);
        tvGoodInfoCart.setOnClickListener(this);

        tv_more_share = (TextView) findViewById(R.id.tv_more_share);
        tv_more_search = (TextView) findViewById(R.id.tv_more_search);;
        tv_more_home = (TextView) findViewById(R.id.tv_more_home);
        btn_more = (Button) findViewById(R.id.btn_more);
    }

    /**
     * Handle button click events<br />
     * <br />
     * Auto-created on 2021-07-05 22:26:12 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    @Override
    public void onClick(View v) {
        if ( v == ibGoodInfoBack ) {
            // Handle clicks for ibGoodInfoBack
            finish();
        } else if ( v == ibGoodInfoMore ) {
            // Handle clicks for ibGoodInfoMore
            Toast.makeText(this, "more info", Toast.LENGTH_SHORT).show();
        } else if ( v == btnGoodInfoAddcart ) {
            // Handle clicks for btnGoodInfoAddcart
            CartStorage.getInstance().addData(goodsBean);
        } else if (v == tvGoodInfoCallcenter) {
            Toast.makeText(this, "客服", Toast.LENGTH_SHORT).show();
        } else if ( v == tvGoodInfoCollection) {
            Toast.makeText(this, "收藏", Toast.LENGTH_SHORT).show();
        } else if ( v == tvGoodInfoCart) {



        } else if ( v == tv_more_share) {
            Toast.makeText(this, "more share", Toast.LENGTH_SHORT).show();
        } else if (v == tv_more_search) {
            Toast.makeText(this, "more search", Toast.LENGTH_SHORT).show();
        } else if (v == tv_more_home) {
            Toast.makeText(this, "more home", Toast.LENGTH_SHORT).show();
        } else if (v == btn_more) {
            Toast.makeText(this, "btn more", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_info);
        findViews();

        // 接收数据
        goodsBean = (GoodsBean) getIntent().getSerializableExtra(GOODS_BEAN);
        if (goodsBean != null) {
            setDataForViews(goodsBean);
        }

    }

    /**
     * 设置view的数据，基于homeactivity传入的extra
     * @param goodsBean
     */
    private void setDataForViews(GoodsBean goodsBean) {
        Glide.with(this).load(Constants.Base_URL_IMAGE + goodsBean.getFigure()).into(ivGoodInfoImage);
        tvGoodInfoName.setText(goodsBean.getName());
        tvGoodInfoPrice.setText("¥" + goodsBean.getCover_price());

        setWebViewData(goodsBean.getProduct_id());
    }

    private void setWebViewData(String product_id) {
        if (product_id != null) {
            wbGoodInfoMore.loadUrl("https://www.bing.com/");
            // 设置
            WebSettings settings = wbGoodInfoMore.getSettings();
            // 设置双击变大
            settings.setUseWideViewPort(true);
            // 允许javascript
            settings.setJavaScriptEnabled(true);
            // 优先使用缓存
            settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
            wbGoodInfoMore.setWebViewClient(new WebViewClient() {

                // 高版本
                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                    view.loadUrl(request.getUrl().toString());

                    // 为true不会弹出外部浏览器
                    return true;
                }

                // 低版本

                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    view.loadUrl(url);
                    return true;
                }
            });
        }
    }
}