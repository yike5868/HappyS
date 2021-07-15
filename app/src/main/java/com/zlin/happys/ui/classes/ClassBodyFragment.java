package com.zlin.happys.ui.classes;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.zlin.happys.R;
import com.zlin.happys.model.ClassBody;
import com.zlin.happys.view.SpellTextView;
import com.zlin.happys.view.TreeList.ListViewAdapter;
import com.zlin.happys.view.TreeList.Node;
import com.zlin.happys.view.TreeList.OnTreeNodeCheckedChangeListener;

import java.util.ArrayList;
import java.util.List;

public class ClassBodyFragment extends Fragment {
    private static String ARG_PARAM = "param_key";
    private String mParam;
    SpellTextView stv_body,stv_title;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_classbody, container, false);
        stv_body = root.findViewById(R.id.stv_body);
        stv_title = root.findViewById(R.id.stv_title);

        stv_body.setStringResource(ClassBody.body);
        stv_title.setStringResource(ClassBody.title);

        stv_body.setMovementMethod(ScrollingMovementMethod.getInstance());
//        TextView view = root.findViewById(R.id.tv_body);
//        mParam = getArguments().getString( ARG_PARAM);
//        view.setText(mParam);
        return root;
    }
    public static ClassBodyFragment newInstance(String str) {
        ClassBodyFragment frag = new ClassBodyFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_PARAM, str);
        frag.setArguments(bundle);//设置参数
        return frag;
    }
}
