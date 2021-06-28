package com.example.marketproject.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.widget.RadioGroup;

import com.example.marketproject.R;

public class MainActivity extends FragmentActivity {

    private RadioGroup rg_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* select the button */
        /* I don't want to use findViewById anymore, because: ButterKnife
        是一个专注于Android系统的View注入框架,以前总是要写很多findViewById来找到View对象，
        有了ButterKnife可以很轻松的省去这些步骤。是大神JakeWharton的力作，目前使用很广。
        最重要的一点，使用ButterKnife对性能基本没有损失，因为ButterKnife用到的注解并不是
        在运行时反射的，而是在编译的时候生成新的class。 */
        rg_main = (RadioGroup) findViewById(R.id.rg_main);
        rg_main.check(R.id.rb_home);

    }
}