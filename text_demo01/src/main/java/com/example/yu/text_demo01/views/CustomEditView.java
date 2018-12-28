package com.example.yu.text_demo01.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.yu.text_demo01.R;
import com.example.yu.text_demo01.bean.MessageObject;

import org.greenrobot.eventbus.EventBus;


public class CustomEditView extends LinearLayout {

    private ImageView imageView;
    private EditText editText;

    public CustomEditView(Context context) {
        super(context);
        init(context);
    }

    public CustomEditView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CustomEditView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void init(Context context){
        View view=inflate(context, R.layout.custom_editview,null);
        imageView=view.findViewById(R.id.custom_image);
        editText=view.findViewById(R.id.custom_edit);
        addView(view);
        //EventBus.getDefault().register(this);
        imageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = editText.getText().toString();
                MessageObject object=new MessageObject(s,"search");
                EventBus.getDefault().post(object);
            }
        });
    }
}
