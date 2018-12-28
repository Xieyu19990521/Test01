package com.example.yu.text_demo01.activity;

import android.database.sqlite.SQLiteDatabase;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.util.Property;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yu.text_demo01.NetUtils;
import com.example.yu.text_demo01.R;
import com.example.yu.text_demo01.adapter.RecyclerAdapter;
import com.example.yu.text_demo01.bean.BookBean;
import com.example.yu.text_demo01.bean.GreenDaoBean;
import com.example.yu.text_demo01.bean.MessageObject;
import com.example.yu.text_demo01.greendao.DaoMaster;
import com.example.yu.text_demo01.greendao.DaoSession;
import com.example.yu.text_demo01.greendao.GreenDaoBeanDao;
import com.example.yu.text_demo01.presenter.IpresenterImpl;
import com.example.yu.text_demo01.view.Iview;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements Iview ,View.OnClickListener {

    private ViewPager viewPager;
    private String url="http://www.zhaoapi.cn/product/searchProducts";
    private IpresenterImpl ipresenter;
    private int mPage=1;
    private int mSort=0;
    private XRecyclerView xRecyclerView;
    private RecyclerAdapter adapter;
    private GridLayoutManager layoutManager;
    private DaoMaster.DevOpenHelper helper;
    private SQLiteDatabase database;
    private DaoMaster master;
    private DaoSession session;
    private GreenDaoBeanDao daoBeanDao;
    private RadioButton zong,price,sale;
    private Map<String, String> map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);
        initView();
        xRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mPage=1;
                mSort=0;
                getData();
            }


            @Override
            public void onLoadMore() {
                getData();
            }
        });
        setDataBase();
        getData();
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getSearch(MessageObject messageObject){
        Object object = messageObject.getObject();
        String s= (String) object;
        map.put("keywords",s);
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
        ipresenter.onStart(url, map,BookBean.class);
    }

    private void initView() {
        price=findViewById(R.id.price);
        sale=findViewById(R.id.sale);
        zong=findViewById(R.id.zong);
        findViewById(R.id.price).setOnClickListener(this);
        findViewById(R.id.sale).setOnClickListener(this);
        findViewById(R.id.zong).setOnClickListener(this);
        ipresenter=new IpresenterImpl(this);
        xRecyclerView=findViewById(R.id.xrecycler);
        layoutManager = new GridLayoutManager(this,2);
        adapter = new RecyclerAdapter(this);
        xRecyclerView.setLayoutManager(layoutManager);
        xRecyclerView.setAdapter(adapter);
        map = new HashMap<>();
    }

    public void getData(){
        boolean b = NetUtils.hasNetwork(this);
        if(b){
            map.put("keywords","笔记本");
            map.put("page",mPage+"");
            map.put("sort",mSort+"");
            ipresenter.onStart(url, map,BookBean.class);
        }else{
            List<BookBean.DataBean> beans=new ArrayList<>();
            List<GreenDaoBean> list = daoBeanDao.queryBuilder().list();
            for(int i=0;i<list.size();i++){
                BookBean.DataBean dataBean = new BookBean.DataBean();
                dataBean.setPid((int) list.get(i).getPid());
                dataBean.setBargainPrice(list.get(i).getBargainPrice());
                dataBean.setCreatetime(list.get(i).getCreatetime());
                dataBean.setDetailUrl(list.get(i).getDetailUrl());
                dataBean.setTitle(list.get(i).getTitle());
                dataBean.setSubhead(list.get(i).getSubhead());
                dataBean.setSalenum(list.get(i).getSalenum());
                dataBean.setSellerid(list.get(i).getSellerid());
                dataBean.setImages(list.get(i).getImages());
                dataBean.setItemtype(list.get(i).getItemtype());
                dataBean.setPrice(list.get(i).getPrice());
                dataBean.setPscid(list.get(i).getPscid());
                beans.add(dataBean);
            }
            adapter.setMlist(beans);
        }

    }

    public void setDataBase(){
        helper = new DaoMaster.DevOpenHelper(this, "sport-db", null);
        database = helper.getWritableDatabase();
        master = new DaoMaster(database);
        session = master.newSession();
        daoBeanDao = session.getGreenDaoBeanDao();
    }

    @Override
    public void onSuccess(Object data) {
        if(data instanceof BookBean){
            BookBean bookBean= (BookBean) data;
            List<BookBean.DataBean> beanData = bookBean.getData();
            if(mPage==1){
                adapter.setMlist(bookBean.getData());

            }else{

                adapter.addMlist(bookBean.getData());
            }
            mPage++;
            xRecyclerView.refreshComplete();
            xRecyclerView.loadMoreComplete();
            for (int i=0;i<beanData.size();i++){
                GreenDaoBean daoBean=new GreenDaoBean();
                daoBean.setPid(beanData.get(i).getPid());
                daoBean.setBargainPrice(beanData.get(i).getBargainPrice());
                daoBean.setCreatetime(beanData.get(i).getCreatetime());
                daoBean.setDetailUrl(beanData.get(i).getDetailUrl());
                daoBean.setTitle(beanData.get(i).getTitle());
                daoBean.setSubhead(beanData.get(i).getSubhead());
                daoBean.setSalenum(beanData.get(i).getSalenum());
                daoBean.setSellerid(beanData.get(i).getSellerid());
                daoBean.setImages(beanData.get(i).getImages());
                daoBean.setItemtype(beanData.get(i).getItemtype());
                daoBean.setPrice(beanData.get(i).getPrice());
                daoBean.setPscid(beanData.get(i).getPscid());
                daoBeanDao.insertOrReplace(daoBean);
            }
        }
    }

    @Override
    public void onFail(String e) {
        Log.i("TAG",e);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.zong:
                mPage=1;
                mSort=0;
                zong.setChecked(true);
                getData();
                break;
            case R.id.price:
                mPage=1;
                mSort=2;
                price.setChecked(true);
                getData();
                break;
            case R.id.sale:
                mPage=1;
                mSort=1;
                sale.setChecked(true);
                getData();
                break;
            case R.id.xuan:
                break;
            default:break;
        }
    }
}
