package com.zlin.happys.ui.classes;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zlin.happys.R;
import com.zlin.happys.base.BaseFragment;
import com.zlin.happys.model.ClassBody;

public class NewWordFragment extends BaseFragment {
    private static String ARG_PARAM = "param_key";
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_classbody, container, false);
//        TextView view = root.findViewById(R.id.tv_body);
//        mParam = getArguments().getString( ARG_PARAM);
//        view.setText(mParam);
        return root;
    }
    public static NewWordFragment newInstance(String str) {
        NewWordFragment frag = new NewWordFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_PARAM, str);
        frag.setArguments(bundle);//设置参数
        return frag;
    }
}
