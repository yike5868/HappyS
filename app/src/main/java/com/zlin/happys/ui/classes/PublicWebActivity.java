package com.zlin.happys.ui.classes;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.Nullable;

import com.zlin.happys.R;
import com.zlin.happys.base.BaseActivity;
import com.zlin.happys.utils.StaticString;
import com.zlin.happys.utils.StringUtils;

public class PublicWebActivity extends BaseActivity {
    WebView webview;
    String newword;
    String publicurl;
    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_public_web);
        webview = findViewById(R.id.webview);
        newword = getIntent().getExtras().getString(StaticString.NEW_WORD,"");
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        publicurl = bundle.getString(StaticString.WEB_URL,"");
        initweb();
    }

    public void initweb(){
        WebSettings settings = webview.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setAllowFileAccess(true);
        settings.setDefaultTextEncodingName("UTF-8");
        webview.setBackgroundColor(0);
        webview.addJavascriptInterface(new AndroidForJs(PublicWebActivity.this),"AndroidView");
        webview.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                if(StringUtils.isEmpty(publicurl)) {
                    webview.loadUrl("javascript:setword('" + newword + "')");
                }
                super.onPageFinished(view, url);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }



        });
        if(StringUtils.isEmpty(publicurl)){
            webview.loadUrl("file:///android_asset/index.html");
        }else {
            webview.loadUrl(publicurl);
        }
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
            if(webview.canGoBack()){
                webview.goBack();
                return true;
            }
        }

        return super.onKeyUp(keyCode, event);
    }
}
