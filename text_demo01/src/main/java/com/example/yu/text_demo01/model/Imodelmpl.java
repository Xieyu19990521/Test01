package com.example.yu.text_demo01.model;

import com.example.yu.text_demo01.callback.MyCallBack;
import com.example.yu.text_demo01.okhttp.OkHttpUtils;
import com.example.yu.text_demo01.okhttp.RetrofitManager;
import com.google.gson.Gson;

import java.util.Map;

import okhttp3.RequestBody;

public class Imodelmpl implements Imodel{
    @Override
    public void onStartRequest(String s, Map<String, String> params, final Class clas, final MyCallBack callBack) {

        Map<String, RequestBody> map = RetrofitManager.getInstance().generateRequestBody(params);
        RetrofitManager.getInstance().postFormBody(s, map).result(new RetrofitManager.HttpListener() {
            @Override
            public void onSuccess(String data) {
                try{
                    Object o = new Gson().fromJson(data, clas);
                    if(callBack != null){
                        callBack.onSuccess(o);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    if(callBack != null){
                        callBack.onFail(e.getMessage());
                    }
                }
            }

            @Override
            public void onFail(String error) {
                if(callBack != null){
                    callBack.onFail(error);
                }
            }
        });

        /*OkHttpUtils.getIntance().postEnqueu(s, map, clas, new MyCallBack() {
            @Override
            public void onSuccess(Object data) {
                callBack.onSuccess(data);
            }

            @Override
            public void onFail(Exception e) {
                callBack.onFail(e);
            }
        });*/
    }
}
