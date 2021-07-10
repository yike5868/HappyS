package com.zlin.happys.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RadioButton;

import androidx.appcompat.widget.AppCompatRadioButton;

/**
 * Created by zhanglin on 2016/11/26.
 */

public class FuRadioButton extends AppCompatRadioButton {
    public FuRadioButton(Context context) {
        super(context);
        setSize();
    }

    public FuRadioButton(Context context, AttributeSet attrs) {

        super(context,attrs);
        setSize();
    }

    public FuRadioButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setSize();
    }
    public void setSize(){
//        Resources r = getResources();
//        float size = r.getDimension(R.dimen.check_small);
//        setTextSize(size);
    }
}
