package com.zlin.happys.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.zlin.happys.R;

public class ClassListFragment extends Fragment {

    private static String ARG_PARAM = "param_key";
    private String mParam;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_classlist, container, false);
        TextView view = root.findViewById(R.id.tv_text);
        mParam = getArguments().getString( ARG_PARAM);
        view.setText(mParam);
        return root;
    }
    public static ClassListFragment newInstance(String str) {
        ClassListFragment frag = new ClassListFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_PARAM, str);
        frag.setArguments(bundle);//设置参数
        return frag;
    }
}
