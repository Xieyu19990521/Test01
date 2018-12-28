package com.example.yu.text_demo01.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.yu.text_demo01.R;
import com.example.yu.text_demo01.adapter.ViewPagerAdapter;
import com.example.yu.text_demo01.bean.MessageObject;
import com.example.yu.text_demo01.bean.ProductBean;
import com.example.yu.text_demo01.presenter.IpresenterImpl;
import com.example.yu.text_demo01.view.Iview;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewPagerFragment extends Fragment implements Iview {

    private String url="http://www.zhaoapi.cn/product/getProductDetail";
    private IpresenterImpl ipresenter;
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.price)
    TextView price;
    @BindView(R.id.title)
    TextView title;
    private int i=0;
    private ViewPagerAdapter adapter;
   private Handler handler=new Handler(){
       @Override
       public void handleMessage(Message msg) {
           super.handleMessage(msg);
           viewPager.setCurrentItem(i%list.size());
           i++;
           handler.sendEmptyMessageDelayed(0,4000);
       }
   };
    private List<String> list;
    @BindView(R.id.product_price)
    Button detail_price;
    @BindView(R.id.product_title)
    Button detail_title;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_viewpager,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this,view);
        initView();
        Intent intent=getActivity().getIntent();
        String pid = intent.getStringExtra("pid");
        Map<String,String> map=new HashMap<>();
        map.put("pid",pid);
        ipresenter.onStart(url,map,ProductBean.class);
    }

    private void initView() {
        ipresenter=new IpresenterImpl(this);
        adapter = new ViewPagerAdapter(getContext());
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onSuccess(Object data) {
        if(data instanceof ProductBean){
            final ProductBean productBean= (ProductBean) data;
            list = new ArrayList<>();
            String[] split = productBean.getData().getImages().split("\\|");
            for (String s:split){
                list.add(s);
            }
            adapter.setList(list);
            price.setText("¥"+productBean.getData().getPrice());
            title.setText(productBean.getData().getTitle());
            detail_title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MessageObject object=new MessageObject(productBean.getData().getTitle(),"title");
                    EventBus.getDefault().postSticky(object);
                }
            });
            detail_price.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MessageObject object=new MessageObject(productBean.getData().getPrice()+"","price");
                    EventBus.getDefault().postSticky(object);
                }
            });
            handler.sendEmptyMessageDelayed(0,2000);
        }
    }

    @Override
    public void onFail(String e) {
        Log.i("TAG",e);
    }
}
