package com.example.yu.bufferknife;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    //绑定控件
    @BindView(R.id.btn)
    Button button;
    @BindView(R.id.tv)
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //一定记住在 onCreate() 中调用 ButterKnife.bind(this);, 否则不起作用
        ButterKnife.bind(this);
    }

    //绑定事件
    @OnClick(R.id.tv)
    void tvOnClick(View view){
        String s = ((TextView) view).getText().toString();
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.btn)
    void btnOnClick(){
        Toast.makeText(this, "123456", Toast.LENGTH_SHORT).show();
        textView.setText("123456");
    }
}
