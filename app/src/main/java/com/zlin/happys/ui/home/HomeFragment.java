package com.zlin.happys.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.zlin.happys.R;
import com.zlin.happys.base.BaseFragment;
import com.zlin.happys.databinding.FragmentHomeBinding;
import com.zlin.happys.ui.classes.AndroidForJs;
import com.zlin.happys.ui.classes.PublicWebActivity;
import com.zlin.happys.utils.HpWebClient;

public class HomeFragment extends BaseFragment {

    private FragmentHomeBinding binding;
    WebView wb_home;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        wb_home = root.findViewById(R.id.wb_home);
        initwebview();
        return root;
    }

    public void initwebview(){
        WebSettings settings = wb_home.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setAllowFileAccess(true);
        settings.setDefaultTextEncodingName("UTF-8");
        wb_home.setBackgroundColor(0);
        wb_home.loadUrl("file:///android_asset/notices.html");
        wb_home.setWebViewClient(new HpWebClient());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}