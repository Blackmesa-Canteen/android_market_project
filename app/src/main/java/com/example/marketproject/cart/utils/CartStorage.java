package com.example.marketproject.cart.utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.SparseArray;

import com.example.marketproject.app.MyApplication;
import com.example.marketproject.home.bean.GoodsBean;
import com.example.marketproject.utils.CacheUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class CartStorage {
    public static final String JSON_CART = "json_cart";
    private static CartStorage instance = null;

    private Context mContext;

    private SparseArray<GoodsBean> sparseArray;

    private CartStorage() {
    }

    private CartStorage(Context context) {
        mContext = context;
        sparseArray = new SparseArray<>(100);

        listToSparseArray();
    }

    /**
     * 从本地读取的数据加入到sparseArray里
     */
    private void listToSparseArray() {
        List<GoodsBean> goodsBeanList = getAllData();
        // 把list树枝转换为sparseArray
        for (int i = 0; i < goodsBeanList.size(); i++) {
            GoodsBean goodsBean = goodsBeanList.get(i);
            sparseArray.put(Integer.parseInt(goodsBean.getProduct_id()), goodsBean);
        }
    }

    /**
     * 获取本地所有数据
     * @return
     */
    public List<GoodsBean> getAllData() {
        List<GoodsBean> goodsBeanList  = new ArrayList<>();
        //1.从本地获取
        String json = CacheUtils.getString(mContext, JSON_CART);
        //2.使用Gson转换成列表
        //判断不为空的时候执行
        if(!TextUtils.isEmpty(json)){
            //把String转换成List
            goodsBeanList = new Gson().fromJson(json,new TypeToken<List<GoodsBean>>(){}.getType());
        }
        return goodsBeanList;
    }

    /**
     * 添加数据
     * @param goodsBean
     */
    public void addData(GoodsBean goodsBean) {
        // 添加到内存中sparseArray
        // 如果数据已经存在，number++；
        GoodsBean tempData = sparseArray.get(Integer.parseInt(goodsBean.getProduct_id()));
        if (tempData != null) {
            // 内存中有了
            tempData.setNumber(tempData.getNumber() + 1);
        } else {
            tempData = goodsBean;
            tempData.setNumber(1);
        }
        // 存入内存
        sparseArray.put(Integer.parseInt(goodsBean.getProduct_id()), tempData);

        // 同步到本地sharedpreference
        saveLocal();
    }

    /**
     * 删除购物车的一项数据
     * @param goodsBean
     */
    public void deleteData(GoodsBean goodsBean) {
        // 内存中删除
        sparseArray.delete(Integer.parseInt(goodsBean.getProduct_id()));

        // 把内存的同步到本地
        saveLocal();
    }

    /**
     * 更新购物车商品数据
     * @param goodsBean
     */
    public void updateData(GoodsBean goodsBean) {
        // 内存中更新
        sparseArray.put(Integer.parseInt(goodsBean.getProduct_id()), goodsBean);

        // 把内存的同步到本地
        saveLocal();
    }

    /**
     * 数据持久化
     */
    private void saveLocal() {
        //把列表转换成String，把String数据保存
        // 用Gson
        List<GoodsBean> goodsBeanList = sparseToList();
        String json = new Gson().toJson(goodsBeanList);
        CacheUtils.saveString(mContext, JSON_CART, json);
    }

    private List<GoodsBean> sparseToList() {
        List<GoodsBean> goodsBeanList = new ArrayList<>();
        for (int i = 0; i < sparseArray.size(); i++) {
            GoodsBean goodsBean = sparseArray.valueAt(i);
            goodsBeanList.add(goodsBean);
        }

        return goodsBeanList;
    }

    public static synchronized CartStorage getInstance() {
        if (instance != null) {
            return instance;
        }
        instance = new CartStorage(MyApplication.getContext());

        return instance;
    }

}
