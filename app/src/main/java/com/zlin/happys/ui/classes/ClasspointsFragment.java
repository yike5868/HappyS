package com.zlin.happys.ui.classes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.zlin.happys.R;
import com.zlin.happys.base.BaseFragment;
import com.zlin.happys.model.Classpoints;
import com.zlin.happys.model.ClasspointsDao;
import java.util.List;

public class ClasspointsFragment extends BaseFragment {
    private static String ARG_PARAM = "param_key";
    WebView webview;
    Classpoints classpoints;
    String lessonId;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_class_points, container, false);
        lessonId = getArguments().getString( ARG_PARAM);

        webview= root.findViewById(R.id.webview);
        loadFlash();
        return root;
    }

    public void loadFlash() {
        List<Classpoints> classpointsList = getDaoSession().getClasspointsDao().queryBuilder().where(ClasspointsDao.Properties.Lessonid.eq(lessonId)).list();
        if(classpointsList.size()>0){
            classpoints = classpointsList.get(0);
        }
        WebSettings settings = webview.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setAllowFileAccess(true);
        settings.setDefaultTextEncodingName("UTF-8");
        webview.setBackgroundColor(0);
        webview.addJavascriptInterface(new AndroidForJs(getContext()),"AndroidView");

//        webview.loadUrl("file:///android_asset/piny.html");
        if(classpoints!=null){
            webview.loadData(classpoints.getPbody(),"text/html", "UTF-8");
        }
    }



    public static ClasspointsFragment newInstance(String str) {
        ClasspointsFragment frag = new ClasspointsFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_PARAM, str);
        frag.setArguments(bundle);//设置参数
        return frag;
    }

}
