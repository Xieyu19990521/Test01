package com.example.yu.text_demo01.presenter;

import com.example.yu.text_demo01.callback.MyCallBack;
import com.example.yu.text_demo01.model.Imodelmpl;
import com.example.yu.text_demo01.view.Iview;

import java.util.Map;

public class IpresenterImpl implements Ipresenter{

    private Iview iview;
    private Imodelmpl imodelmpl;

    public IpresenterImpl(Iview iview) {
        this.iview=iview;
        imodelmpl=new Imodelmpl();
    }

    @Override
    public void onStart(String s, Map<String, String> map, Class clas) {
        imodelmpl.onStartRequest(s, map, clas, new MyCallBack() {
            @Override
            public void onSuccess(Object data) {
                iview.onSuccess(data);
            }

            @Override
            public void onFail(String e) {
                iview.onFail(e);
            }
        });
    }
}
