package com.zlin.happys.ui.classes;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListAdapter;

import com.zlin.happys.R;
import com.zlin.happys.base.BaseFragment;
import com.zlin.happys.model.Hanzi;
import com.zlin.happys.view.FuHanzi;
import com.zlin.happys.view.FuTextView;

import java.util.ArrayList;
import java.util.List;

public class DictationFragment extends BaseFragment implements View.OnClickListener {
    private static String ARG_PARAM = "param_key";
    private  List<Hanzi> hanziList;
    Button btn_play,btn_show;
    private boolean isShow = true;
    GvAdapter gvadapter;
    private GridView gview;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_dictation, container, false);
        gview = root.findViewById(R.id.gview);
        btn_play = root.findViewById(R.id.btn_play);
        btn_show = root.findViewById(R.id.btn_show);
        btn_show.setOnClickListener(this);
        btn_play.setOnClickListener(this);
        hanziList = new ArrayList<>();
        hanziList.add(new Hanzi());
        hanziList.add(new Hanzi());
        hanziList.add(new Hanzi());
        hanziList.add(new Hanzi());
        hanziList.add(new Hanzi());
        gvadapter =  new GvAdapter(getContext());
        gview.setAdapter(gvadapter);
        return root;
    }

    public static DictationFragment newInstance(String str) {
        DictationFragment frag = new DictationFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_PARAM, str);
        frag.setArguments(bundle);//设置参数
        return frag;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_play:
                break;
            case R.id.btn_show:
                if(isShow == true){
                    isShow = false;
                    btn_show.setText("隐藏");
                }else{
                    isShow = true;
                    btn_show.setText("显示");
                }
                gvadapter.notifyDataSetChanged();
                break;
        }
    }

    class GvAdapter extends BaseAdapter{
        private LayoutInflater layoutInflater;
        public GvAdapter(Context context){
            layoutInflater = LayoutInflater.from(context);
        }
        @Override
        public int getCount() {
            return hanziList.size();
        }

        @Override
        public Object getItem(int i) {
            return hanziList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View v = layoutInflater.inflate(R.layout.item_gv_hz,null);
            FuHanzi tv_py = v.findViewById(R.id.fu_hanzi);
            tv_py.setText("han","汉");
            tv_py.hideHz(isShow);
            return v;
        }
    }
}
