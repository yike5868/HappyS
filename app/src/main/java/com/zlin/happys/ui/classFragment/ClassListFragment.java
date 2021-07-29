package com.zlin.happys.ui.classFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.zlin.happys.R;
import com.zlin.happys.ui.classes.ChineseActivity;
import com.zlin.happys.view.TreeList.ListViewAdapter;
import com.zlin.happys.view.TreeList.Node;
import com.zlin.happys.view.TreeList.OnTreeNodeClickListener;

import java.util.ArrayList;
import java.util.List;

public class ClassListFragment extends Fragment {

    private static String ARG_PARAM = "param_key";

    private List<Node> dataList = new ArrayList<>();
    private ListViewAdapter mAdapter;

    Button btn;
    ListView listView;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_classlist, container, false);
        listView = root.findViewById(R.id.listview);
        initData();
        btn = root.findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"asdfasdfasdf",Toast.LENGTH_LONG).show();
            }
        });

        //第二个参数 上下文
        //第三个参数 数据集
        //第四个参数 默认展开层级数 0为不展开
        //第五个参数 展开的图标
        //第六个参数 闭合的图标
        mAdapter = new ListViewAdapter(listView, getContext(), dataList,
                0, R.mipmap.jiia, R.mipmap.jianhao);

        listView.setAdapter(mAdapter);

        //获取所有节点
        final List<Node> allNodes = mAdapter.getAllNodes();
        for (Node allNode : allNodes) {
            //Log.e("xyh", "onCreate: " + allNode.getName());
        }
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
////                Log.e("onItemClick","onItemClickonItemClickonItemClick");
//            }
//        });
        listView.getParent().requestDisallowInterceptTouchEvent(true);

        mAdapter.setOnTreeNodeClickListener(new OnTreeNodeClickListener() {
            @Override
            public void onClick(Node node, int position) {
                if(node.isLeaf()){
                    Intent intent = new Intent(getContext(), ChineseActivity.class);
                    startActivity(intent);
                }
            }
        });


        return root;
    }
    
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        mAdapter.notifyDataSetChanged();
    }

    public static ClassListFragment newInstance(String str) {
        ClassListFragment frag = new ClassListFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_PARAM, str);
        frag.setArguments(bundle);//设置参数
        return frag;
    }

    private void initData() {
        //根节点
        dataList.add(new Node<>("1", "-1", "第一单元"));
        dataList.add(new Node<>("2", "-1", "第二单元"));
        dataList.add(new Node<>("3", "-1", "第三单元"));
        dataList.add(new Node<>("4", "-1", "第四单元"));

        //根节点1的二级节点
        dataList.add(new Node<>("3", "1", "第一课"));
        dataList.add(new Node<>("4", "1", "第二课"));
        dataList.add(new Node<>("5", "1", "第三课"));

        //根节点2的二级节点
        dataList.add(new Node<>("6", "2", "第一课"));
        dataList.add(new Node<>("7", "2", "第二课"));
        dataList.add(new Node<>("8", "2", "第三课"));

        //根节点3的二级节点
        dataList.add(new Node<>("9", "3", "第一课"));
        dataList.add(new Node<>("10", "3", "第二课"));
        dataList.add(new Node<>("11", "4", "第三课"));

    }
}
