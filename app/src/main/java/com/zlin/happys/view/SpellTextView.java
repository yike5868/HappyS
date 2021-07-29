package com.zlin.happys.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.zlin.happys.utils.ChineseCharacter2Spell;
import com.zlin.happys.utils.DisplayUtil;

@SuppressLint("AppCompatCustomView")
public class SpellTextView extends TextView {
    private String[] pinyin;
    private String[] chinese;

    private TextPaint textPaintSpell = new TextPaint(Paint.ANTI_ALIAS_FLAG);
    private TextPaint textPaintChinese = new TextPaint(Paint.ANTI_ALIAS_FLAG);

    private int fontSizeSpell = DisplayUtil.dp2px(getContext(),20);
    private int fontSizeChinese = DisplayUtil.dp2px(getContext(),20);

    private int colorSpell = Color.parseColor("#1b97d6");
    private int colorChinese = Color.parseColor("#000000");
    public SpellTextView(Context context) {
        super(context);
    }

    public SpellTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SpellTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initTextPaint();
    }

    public void initTextPaint() {
        float denity = getResources().getDisplayMetrics().density;
        textPaintSpell.setStrokeWidth(denity);
        textPaintChinese.setStrokeWidth(denity);
        textPaintSpell.setTextAlign(Paint.Align.LEFT);
        textPaintChinese.setTextAlign(Paint.Align.LEFT);
        //设置字体大小
        textPaintSpell.setTextSize(fontSizeSpell);
        textPaintChinese.setTextSize(fontSizeChinese);
        textPaintSpell.setColor(colorSpell);
        textPaintChinese.setColor(colorChinese);
    }
    int comlum;
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float widthMesure = 0f;
        comlum = 1;
        float pinyinWidth;
        if (pinyin != null && pinyin.length > 0) {
            for (int index = 0; index < pinyin.length; index++) {
                pinyinWidth = widthMesure + textPaintSpell.measureText(pinyin[index]);
                if (pinyinWidth > getWidth()) {
                    comlum++;
                    widthMesure = 0;
                }
                canvas.drawText(pinyin[index], widthMesure, (comlum * 2 - 1) * (textPaintChinese.getFontSpacing()), textPaintSpell);
                canvas.drawText(chinese[index],
                        widthMesure + (textPaintSpell.measureText(pinyin[index]) - textPaintChinese.measureText(chinese[index])) / 2,
                        (comlum * 2) * (textPaintChinese.getFontSpacing()), textPaintChinese);
                if (index + 1 < pinyin.length) {
                    widthMesure = widthMesure + textPaintSpell.measureText(pinyin[index] + 1);
                } else {
                    widthMesure = widthMesure + textPaintSpell.measureText(pinyin[index]);
                }
            }
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //获取宽高模式
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        //获取宽高
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        if (heightMode == MeasureSpec.AT_MOST) {
            //测量文字的高度
            Rect rect = new Rect();
            textPaintSpell.getTextBounds("pinyin", 0, 2, rect);
            heightSize = rect.height()*3 + getPaddingTop() + getPaddingBottom();
        }
        setMeasuredDimension(widthSize, heightSize);
    }

    //拼音和汉字的资源
    public void setSpellAndChinese(String[] pinYin, String[] chinese) {
        this.pinyin = pinYin;
        this.chinese = chinese;
    }

    //设置文字资源
    public void setStringResource(String string) {
        initTextPaint();
        String[] spellArray = ChineseCharacter2Spell.getPinyinString(string);
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : spellArray){
            stringBuilder.append(s);
            stringBuilder.append(" ");
        }

        char[] chars = string.toCharArray();
        String[] chineseArray = new String[chars.length];
        for (int i = 0; i < chars.length;i++){
            chineseArray[i] = String.valueOf(chars[i]);
        }
        setSpellAndChinese(spellArray,chineseArray);
    }

    //设置文字颜色
    public void setStringColor(int spellColor,int chineseColor) {
        textPaintSpell.setColor(spellColor);
        textPaintChinese.setColor(chineseColor);
    }

    //设置文字大小
    public void setFontSize(float spellFontSize,float chineseFontSize) {
        textPaintSpell.setTextSize(DisplayUtil.dip2px(getContext(),spellFontSize));
        textPaintChinese.setTextSize(DisplayUtil.dip2px(getContext(),chineseFontSize));
    }
}