package com.example.marketproject.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import com.example.marketproject.R;
import com.example.marketproject.base.BaseFragment;
import com.example.marketproject.cart.fragment.CartFragment;
import com.example.marketproject.category.fragment.CategotyFragment;
import com.example.marketproject.communicate.fragment.CommunityFragment;
import com.example.marketproject.databinding.ActivityMainBinding;
import com.example.marketproject.home.fragment.HomeFragment;
import com.example.marketproject.user.fragment.UserFragment;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends FragmentActivity {

    /* https://developer.android.com/topic/libraries/view-binding#java */
    private ActivityMainBinding binding;

    private ArrayList<BaseFragment> fragments;

    private int position;

    /** 上次显示的fragment */
    private Fragment tempFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        /* select the button */
        /* I don't want to use findViewById anymore, because: ButterKnife
        I will use View Binding,以前总是要写很多findViewById来找到View对象，
        有了这个可以很轻松的省去这些步骤。对性能基本没有损失，因为View对象并不是
        在运行时反射的，而是在编译的时候生成新的class。 */
        // rg_main = (RadioGroup) findViewById(R.id.rg_main);
        initFragment();
        initListener();
    }

    private void initListener() {
        binding.rgMain.setOnCheckedChangeListener(
                new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        switch (checkedId) {
                            case R.id.rb_home:
                                position = 0;
                                break;

                            case R.id.rb_type:
                                position = 1;
                                break;

                            case R.id.rb_community:
                                position = 2;
                                break;

                            case R.id.rb_cart:
                                position = 3;
                                break;

                            case R.id.rb_user:
                                position = 4;
                                break;
                            default:
                                position = 0;
                                break;
                        }
                        BaseFragment baseFragment = getFragment(position);
                        switchFragment(tempFragment, baseFragment);
                    }
                });

        binding.rgMain.check(R.id.rb_home);
    }

    /**
     * The Sequence is important.
     */
    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new CategotyFragment());
        fragments.add(new CommunityFragment());
        fragments.add(new CartFragment());
        fragments.add(new UserFragment());
    }

    private BaseFragment getFragment(int position) {
        if (fragments != null && fragments.size() > 0) {
            BaseFragment baseFragment = fragments.get(position);
            return baseFragment;
        }
        return null;
    }

    private void switchFragment(Fragment fromFragment, BaseFragment nextFragment) {
        if (tempFragment != nextFragment) {
            tempFragment = nextFragment;
            if (nextFragment != null) {
                FragmentTransaction transaction =
                        getSupportFragmentManager().beginTransaction();
                //判断 nextFragment 是否添加
                if (!nextFragment.isAdded()) {
                    //隐藏当前 Fragment
                    if (fromFragment != null) {
                        transaction.hide(fromFragment);
                    }
                    transaction.add(R.id.frameLayout, nextFragment).commit();
                } else {
                    //隐藏当前 Fragment
                    if (fromFragment != null) {
                        transaction.hide(fromFragment);
                    }
                    transaction.show(nextFragment).commit();
                }
            }
        }
    }
}