package com.zlin.happys.ui.dashboard;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.zlin.happys.R;
import com.zlin.happys.model.DataEntity;
import com.zlin.happys.ui.classes.ChineseActivity;
import com.zlin.happys.view.TreeList.ListViewAdapter;
import com.zlin.happys.view.TreeList.Node;
import com.zlin.happys.view.TreeList.OnTreeNodeCheckedChangeListener;
import com.zlin.happys.view.TreeList.OnTreeNodeClickListener;

import java.util.ArrayList;
import java.util.List;

public class ClassListFragment2 extends Fragment {

    private static String ARG_PARAM = "param_key";
    private List<DataEntity>  dataEntityList=new ArrayList<>();
    private StudentExpandableAdapter  studentExpandableAdapter;

    ExpandableListView expandableListView;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_classlist2, container, false);
        expandableListView = root.findViewById(R.id.listview);
        initData();
        initAdapter();
        setOnClickEvent();
        return root;
    }

    private void initData() {

        for(int i=0;i<5;i++){
            List<DataEntity.ChildrenData> childrenData=new ArrayList<>();
            for(int j=0;j<8;j++){
                DataEntity.ChildrenData children=new DataEntity.ChildrenData("学生"+(j+1),false);
                childrenData.add(children);
            }
            DataEntity dataEntity=new DataEntity((i+1)+"班",childrenData);
            dataEntityList.add(dataEntity);
        }
    }

    private void setOnClickEvent() {

    }

    private void initAdapter() {
        studentExpandableAdapter=new StudentExpandableAdapter(getContext(),dataEntityList);
        expandableListView.setAdapter(studentExpandableAdapter);
        /**
         * 默认展开某个item
         * */
        //expandableListView.expandGroup(1);


    }

    public static ClassListFragment2 newInstance(String str) {
        ClassListFragment2 frag = new ClassListFragment2();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_PARAM, str);
        frag.setArguments(bundle);//设置参数
        return frag;
    }

    class StudentExpandableAdapter extends BaseExpandableListAdapter{

        private Context context;
        private List<DataEntity> dataEntity;

        public StudentExpandableAdapter(Context context, List<DataEntity> dataEntity) {
            this.context = context;
            this.dataEntity = dataEntity;
        }

        /**
         * 获取组的数目
         *
         * @return 返回一级列表组的数量
         */
        @Override
        public int getGroupCount() {
            return dataEntity == null ? 0 : dataEntity.size();
        }

        /**
         * 获取指定组中的子节点数量
         *
         * @param groupPosition 子元素组所在的位置
         * @return 返回指定组中的子数量
         */
        @Override
        public int getChildrenCount(int groupPosition) {
            return dataEntity.get(groupPosition).getChildrenDataList().size();
        }

        /**
         * 获取与给定组相关联的对象
         *
         * @param groupPosition 子元素组所在的位置
         * @return 返回指定组的子数据
         */
        @Override
        public Object getGroup(int groupPosition) {
            return dataEntity.get(groupPosition).getTitle();
        }


        /**
         * 获取与给定组中的给定子元素关联的数据
         *
         * @param groupPosition 子元素组所在的位置
         * @param childPosition 子元素的位置
         * @return 返回子元素的对象
         */
        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return dataEntity.get(groupPosition).getChildrenDataList().get(childPosition);
        }

        /**
         * 获取组在给定位置的ID（唯一的）
         *
         * @param groupPosition 子元素组所在的位置
         * @return 返回关联组ID
         */
        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }


        /**
         * 获取给定组中给定子元素的ID(唯一的)
         *
         * @param groupPosition 子元素组所在的位置
         * @param childPosition 子元素的位置
         * @return 返回子元素关联的ID
         */
        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        /**
         * @return 确定id 是否总是指向同一个对象
         */
        @Override
        public boolean hasStableIds() {
            return true;
        }

        /**
         * @return 返回指定组的对应的视图 （一级列表样式）
         */
        @Override
        public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            ParentHolder parentHolder;
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.tree_list_item, null);
                parentHolder = new ParentHolder();
                parentHolder.tvParent = convertView.findViewById(R.id.tv_body);
                parentHolder.img_right = convertView.findViewById(R.id.iv_title);
                convertView.setTag(parentHolder);
            } else {
                parentHolder = (ParentHolder) convertView.getTag();
            }
            parentHolder.tvParent.setText(dataEntity.get(groupPosition).getTitle());


            //共用一个右箭头，如果展开则顺时针旋转90°选择，否则不旋转
            if (isExpanded) parentHolder.img_right.setRotation(90F);
            else parentHolder.img_right.setRotation(0F);

            return convertView;
        }

        /**
         * @return 返回指定位置对应子视图的视图
         */
        @Override
        public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            final ChildrenHolder childrenHolder;
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.tree_list_item, null);
                childrenHolder = new ChildrenHolder();
                childrenHolder.tvChild = convertView.findViewById(R.id.tv_body);
                childrenHolder.ivChild = convertView.findViewById(R.id.iv_title);
                convertView.setTag(childrenHolder);
            } else {
                childrenHolder = (ChildrenHolder) convertView.getTag();
            }


            //Log.e("666","班级:"+dataEntity.get(groupPosition).getTitle()+"    学生："+dataEntity.get(groupPosition).getChildrenDataList().get(childPosition).getSubContent()+"   isChecked:"+dataEntity.get(groupPosition).getChildrenDataList().get(childPosition).isSelect());
//            childrenHolder.checkBox.setChecked(dataEntity.get(groupPosition).getChildrenDataList().get(childPosition).isSelect());
//            childrenHolder.tvChild.setText(dataEntity.get(groupPosition).getChildrenDataList().get(childPosition).getSubContent());
//            childrenHolder.checkBox.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    boolean isChecked = !dataEntity.get(groupPosition).getChildrenDataList().get(childPosition).isSelect();
//                    dataEntity.get(groupPosition).getChildrenDataList().get(childPosition).setSelect(!isChecked);
//                    Log.e("groupPosition:" + groupPosition, "childPosition:" + childPosition + " isChecked:" + isChecked);
//                    checkBoxListener.checkStateListener(groupPosition, childPosition, isChecked);
//                }
//            });


            return convertView;
        }

        /**
         * 指定位置的子元素是否可选
         *
         * @param groupPosition 子元素组所在的位置
         * @param childPosition 子元素的位置
         * @return 返回是否可选
         */

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }


        class ParentHolder {
            TextView tvParent;
            ImageView img_right;
        }


        class ChildrenHolder {
            TextView tvChild;
            ImageView ivChild;
        }


        /**
         * 用于提供对外复选框修改通知接口
         */
//        public interface CheckBoxListener {
//            void checkStateListener(int groupPosition, int childPosition, boolean isChecked);
//        }
//
//        public void setCheckBoxListener(CheckBoxListener checkBoxListener) {
//            this.checkBoxListener = checkBoxListener;
//        }


        /**
         * 用于刷新更新后的数据
         */
        public void reFreshData(List<DataEntity> dataEntity) {
            this.dataEntity = dataEntity;
            notifyDataSetChanged();
        }
    }
}
