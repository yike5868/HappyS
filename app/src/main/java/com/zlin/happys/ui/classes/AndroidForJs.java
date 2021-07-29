package com.zlin.happys.ui.classes;

import android.content.Context;
import android.content.Intent;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

public class AndroidForJs {
    private Context context;

    public AndroidForJs(Context context) {
        this.context = context;
    }

    //api17以后，只有public且添加了 @JavascriptInterface 注解的方法才能被调用
    @JavascriptInterface
    public void goToBH(String word) {
        Intent intent = new Intent(context,PublicWebActivity.class);
        intent.putExtra("newword",word);
        context.startActivity(intent);
    }
}
