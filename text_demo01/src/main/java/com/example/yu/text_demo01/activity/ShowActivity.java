package com.example.yu.text_demo01.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.yu.text_demo01.R;
import com.example.yu.text_demo01.adapter.ViewPagerAdapter;
import com.example.yu.text_demo01.bean.ProductBean;
import com.example.yu.text_demo01.fragment.DetailFragment;
import com.example.yu.text_demo01.fragment.ReviewFragment;
import com.example.yu.text_demo01.fragment.ViewPagerFragment;
import com.example.yu.text_demo01.presenter.IpresenterImpl;
import com.example.yu.text_demo01.view.Iview;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShowActivity extends AppCompatActivity{

    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        viewPager=findViewById(R.id.viewpager_detail);
        tabLayout=findViewById(R.id.tab_detail);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {

            String[] strings=new String[]{"商品","详情","评论"};

            @Override
            public Fragment getItem(int i) {
                switch (i){
                    case 0:
                        return new ViewPagerFragment();
                    case 1:
                        return new DetailFragment();
                    case 2:
                        return new ReviewFragment();
                    default:
                        return new ReviewFragment();
                }
            }

            @Override
            public int getCount() {
                return strings.length;
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return strings[position];
            }
        });
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
