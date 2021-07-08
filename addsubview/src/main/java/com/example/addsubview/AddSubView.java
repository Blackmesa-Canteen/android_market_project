package com.example.addsubview;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class AddSubView extends LinearLayout implements View.OnClickListener {

    private Context context;
    private ImageView iv_sub;
    private ImageView iv_add;
    private TextView tv_value;

    private int value = 1;

    private int minValue = 1;
    private int maxValue = 5;

    private OnNumberChangeListener onNumberChangeListener;

    public AddSubView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;

        // 把布局文件实例化，并且加载到AddSubView类中
        View.inflate(context, R.layout.add_sub_view, AddSubView.this);

        iv_sub = (ImageView) findViewById(R.id.iv_sub);
        iv_add = (ImageView) findViewById(R.id.iv_add);
        tv_value = (TextView) findViewById(R.id.tv_value);

        int value = getValue();
        setValue(value);

        iv_sub.setOnClickListener(this);
        iv_add.setOnClickListener(this);
    }

    public int getValue() {
        String valueStr = tv_value.getText().toString().trim();
        if(!TextUtils.isEmpty(valueStr)) {
            value = Integer.parseInt(valueStr);
        }
        return value;
    }

    public void setValue(int value) {
        this.value = value;
        tv_value.setText(String.valueOf(value));
    }

    public int getMinValue() {
        return minValue;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    @Override
    public void onClick(View v) {

            if ( v == iv_sub ) {
                subNumber();
            } else if (v == iv_add) {
                addNumber();
            }


    }

    private void addNumber() {
        if (value < maxValue) {
            value++;
        }
        setValue(value);
        if (onNumberChangeListener != null) {
            onNumberChangeListener.onNumberChange(value);
        }
    }

    private void subNumber() {
        if (value > minValue) {
            value--;
        }
        setValue(value);

        if (onNumberChangeListener != null) {
            onNumberChangeListener.onNumberChange(value);
        }
    }

    /**
     * 当产品数量发生变化的回掉
     */
    public interface OnNumberChangeListener {
        /**
         * 当数据发生变化
         * @param value
         */
        public void onNumberChange(int value);
    }

    public void setOnNumberChangeListener(OnNumberChangeListener onNumberChangeListener) {
        this.onNumberChangeListener = onNumberChangeListener;
    }
}
