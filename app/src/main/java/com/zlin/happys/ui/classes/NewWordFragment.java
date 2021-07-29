package com.zlin.happys.ui.classes;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import com.zlin.happys.R;
import com.zlin.happys.base.BaseFragment;
import com.zlin.happys.model.Classbody;
import com.zlin.happys.model.ClassbodyDao;
import com.zlin.happys.model.Classpoints;
import com.zlin.happys.model.ClasspointsDao;

import java.io.File;
import java.util.List;

public class NewWordFragment extends BaseFragment {
    private static String ARG_PARAM = "param_key";
    String lessonId;
    WebView webview;
   Classpoints classpoints;
    Classbody classbody;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_new_word, container, false);
        webview = root.findViewById(R.id.webview);
        lessonId = getArguments().getString( ARG_PARAM);
        loadnewword();

        return root;
    }


    public void loadnewword(){
        classbody = getDaoSession().getClassbodyDao().queryBuilder().where(ClassbodyDao.Properties.Lessonid.eq(lessonId)).unique();
        if(classbody!=null){
            WebSettings settings = webview.getSettings();
            settings.setJavaScriptEnabled(true);
            settings.setAllowFileAccess(true);
            settings.setDefaultTextEncodingName("UTF-8");
            webview.setBackgroundColor(0);
            webview.addJavascriptInterface(new AndroidForJs(getContext()),"AndroidView");

//        webview.loadUrl("file:///android_asset/piny.html");
            if(classbody!=null){
                webview.loadData(classbody.getNewword(),"text/html", "UTF-8");
            }
        }
    }




    public static NewWordFragment newInstance(String str) {
        NewWordFragment frag = new NewWordFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_PARAM, str);
        frag.setArguments(bundle);//设置参数
        return frag;
    }
}
