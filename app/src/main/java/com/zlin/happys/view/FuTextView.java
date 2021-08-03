package com.zlin.happys.view;

import android.content.Context;
import android.graphics.Canvas;
import android.text.Html;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatTextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by zhanglin on 16/8/12.
 */

public class FuTextView extends AppCompatTextView {

    public FuTextView(Context context) {
        super(context);
    }

    public FuTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


    @Override
    public void setText(CharSequence text, BufferType type) {
        super.setText(text, type);
    }


    public void setText(Date date) {
        if (date == null)
            return;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date_str = simpleDateFormat.format(date);
        setTag(date);
        setText(date_str);
    }


    public void setText(Integer i) {
        if (i != null)
            setText(i + "");
    }

    public void setText(Integer i, Integer j) {
        if (i != null)
            setText(i + "");
        if (j != null)
            setText(j + "");
    }

    public void setText(Double i, Double j) {
        if (i != null)
            setText(i + "");
        if (j != null)
            setText(j + "");
    }

    public void setText(Double i) {
        if (i != null)
            setText(i + "");
    }

    public void setText(Integer i, String j) {
        if (i != null)
            setText(i + "");
        if (j != null)
            setText(j);
    }

    public void setText(String i, String j) {
        if (i != null)
            setText(i);
        if (j != null)
            setText(j);
    }

    public Date getDate() {
        if (getTag() == null)
            return null;
        else {
            return (Date) getTag();
        }
    }

    public int getInt() {
        String str_int = getText().toString().trim();
        if (isNumeric(str_int))
            return Integer.parseInt(str_int);
        else
            return 0;
    }

    public Double getDouble() {

        String str_int = getText().toString().trim();
        if (isNumeric(str_int))
            return Double.parseDouble(str_int);
        else
            return null;
    }

    public boolean isNumeric(String str) {
        return str.matches("-?[0-9]+.*[0-9]*");
    }
}
