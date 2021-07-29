package com.zlin.happys.ui.classes;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.zlin.happys.R;
import com.zlin.happys.base.BaseFragment;
import com.zlin.happys.model.Classexercises;
import com.zlin.happys.model.ClassexercisesDao;
import com.zlin.happys.model.Classpoints;
import com.zlin.happys.model.ClasspointsDao;
import com.zlin.happys.ui.classFragment.ClassListFragment2;
import com.zlin.happys.view.TreeList.ListViewAdapter;
import com.zlin.happys.view.TreeList.Node;

import java.util.ArrayList;
import java.util.List;

public class ClassExplainFragment extends BaseFragment {
    private static String ARG_PARAM = "param_key";
    String lessonId;
    List<Classexercises> classexercisesList;
    StudentExpandableAdapter studentExpandableAdapter;
    ExpandableListView expandableListView;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_class_explain, container, false);
        lessonId = getArguments().getString( ARG_PARAM);
        expandableListView = root.findViewById(R.id.listview);
        loadFlash();
        return root;
    }

    public void loadFlash() {
        ClassexercisesDao classexercisesDao = getDaoSession().getClassexercisesDao();
        classexercisesList = classexercisesDao.queryBuilder().where(ClassexercisesDao.Properties.LessonId.eq(lessonId)).orderAsc(ClassexercisesDao.Properties.Orderid).list();
        studentExpandableAdapter=new StudentExpandableAdapter(getContext());
        expandableListView.setAdapter(studentExpandableAdapter);
    }



    public static ClassExplainFragment newInstance(String str) {
        ClassExplainFragment frag = new ClassExplainFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_PARAM, str);
        frag.setArguments(bundle);//设置参数
        return frag;
    }
    class StudentExpandableAdapter extends BaseExpandableListAdapter {

        private Context context;

        public StudentExpandableAdapter(Context context) {
            this.context = context;
        }

        /**
         * 获取组的数目
         *
         * @return 返回一级列表组的数量
         */
        @Override
        public int getGroupCount() {
            return classexercisesList == null ? 0 : classexercisesList.size();
        }

        /**
         * 获取指定组中的子节点数量
         *
         * @param groupPosition 子元素组所在的位置
         * @return 返回指定组中的子数量
         */
        @Override
        public int getChildrenCount(int groupPosition) {
            return 1;
        }

        /**
         * 获取与给定组相关联的对象
         *
         * @param groupPosition 子元素组所在的位置
         * @return 返回指定组的子数据
         */
        @Override
        public Object getGroup(int groupPosition) {
            return classexercisesList.get(groupPosition).getEname();
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
            return classexercisesList.get(groupPosition).getEanswer();
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
            StudentExpandableAdapter.ParentHolder parentHolder;
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.tree_list_item, null);
                parentHolder = new StudentExpandableAdapter.ParentHolder();
                parentHolder.tvParent = convertView.findViewById(R.id.tv_body);
                parentHolder.img_right = convertView.findViewById(R.id.iv_title);
                convertView.setTag(parentHolder);
            } else {
                parentHolder = (StudentExpandableAdapter.ParentHolder) convertView.getTag();
            }
            parentHolder.tvParent.setText(classexercisesList.get(groupPosition).getEname());


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
            final StudentExpandableAdapter.ChildrenHolder childrenHolder;
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.tree_list_item, null);
                childrenHolder = new StudentExpandableAdapter.ChildrenHolder();
                childrenHolder.tvChild = convertView.findViewById(R.id.tv_body);
                childrenHolder.ivChild = convertView.findViewById(R.id.iv_title);
                convertView.setTag(childrenHolder);
            } else {
                childrenHolder = (StudentExpandableAdapter.ChildrenHolder) convertView.getTag();
            }

            childrenHolder.tvChild.setText(classexercisesList.get(groupPosition).getEanswer());

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
        public void reFreshData() {
            notifyDataSetChanged();
        }
    }
}
