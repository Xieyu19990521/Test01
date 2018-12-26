package com.example.yu.demo02.bean2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.yu.demo02.R;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {

    //R.id.btn得和xml里面的一致,否则无效
    @BindView(id = R.id.but, click = true)
    private Button btn;

    @BindView(id = R.id.tv)
    private TextView tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        BindUtils.initBindView(this);
        //这里一定得放在setContentView()后
        tv.setText("绑定成功...");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.but:
                Log.i("TAG", "点击了....");
                break;

            default:
                break;
        }
    }
}
