package com.zlin.happys.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.zlin.happys.R;

public class FuHanzi extends RelativeLayout {

    FuTextView tv_py;
    FuTextView tv_hz;

    public FuHanzi(Context context) {
        super(context);
    }

    public FuHanzi(Context context, AttributeSet attrs) {
        super(context, attrs);
        // 加载布局
        LayoutInflater.from(context).inflate(R.layout.view_hanzi, this);
        // 获取控件
        tv_py = findViewById(R.id.tv_py);
        tv_hz = findViewById(R.id.tv_hz);
    }

    public void setText(String py,String hz){
        tv_py.setText(py);
        tv_hz.setText(hz);
    }

    public void setpy(String py){
        tv_py.setText(py);
    }

    public void sethz(String hz){

    }

    public void hideHz(boolean b){
        if(b){
            tv_hz.setVisibility(GONE);
        }else{
            tv_hz.setVisibility(VISIBLE);
        }
    }
}
