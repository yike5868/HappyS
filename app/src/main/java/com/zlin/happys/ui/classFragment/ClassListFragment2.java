package com.zlin.happys.ui.classFragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.print.PageRange;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.zlin.happys.MainActivity;
import com.zlin.happys.R;
import com.zlin.happys.base.BaseFragment;
import com.zlin.happys.model.ClassgradeDao;
import com.zlin.happys.model.Classlesson;
import com.zlin.happys.model.ClasslessonDao;
import com.zlin.happys.model.ClassnameDao;
import com.zlin.happys.model.Classunit;
import com.zlin.happys.model.ClassunitDao;
import com.zlin.happys.model.ResultJson;
import com.zlin.happys.ui.SplishActivity;
import com.zlin.happys.ui.classes.ChineseActivity;
import com.zlin.happys.utils.OkGoUtils;
import com.zlin.happys.utils.UrlUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public  class ClassListFragment2 extends BaseFragment {

    private static String ARG_PARAM = "param_key";
//    private List<DataEntity>  dataEntityList=new ArrayList<>();
    private List<Classunit> classunitList = new ArrayList<>();
    private StudentExpandableAdapter  studentExpandableAdapter;
    String classnameId;

    ExpandableListView expandableListView;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_classlist2, container, false);
        expandableListView = root.findViewById(R.id.listview);
        classnameId = getArguments().getString( ARG_PARAM);

        setOnClickEvent();
        getData();
        return root;
    }

    private void initData() {
        ClassunitDao classunitDao = getDaoSession().getClassunitDao();
        ClasslessonDao classlessonDao = getDaoSession().getClasslessonDao();
        classunitList = classunitDao.queryBuilder().where(ClassunitDao.Properties.ClassId.eq(classnameId)).orderAsc(ClassunitDao.Properties.OrderId).list();
        for(Classunit classunit:classunitList){
            List<Classlesson> classlessons = classlessonDao.queryBuilder().where(ClasslessonDao.Properties.UnitId.eq(classunit.getUnitId())).orderAsc(ClasslessonDao.Properties.LessonLev).list();
            classunit.setClasslessonList(classlessons);
        }
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    private void setOnClickEvent() {
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                if(classunitList.get(i).getClasslessonList().get(i1).getIsbody()==1){
                    Intent intent = new Intent(getContext(), ChineseActivity.class);
                    intent.putExtra("lessonId",classunitList.get(i).getClasslessonList().get(i1).getLessonId());
                    startActivity(intent);
                }
                return false;
            }
        });
    }

    private void initAdapter() {
        initData();
        studentExpandableAdapter=new StudentExpandableAdapter(getContext());
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

    public void getData(){
        Map<String,String> params = new HashMap<>();
        params.put("classnameId",classnameId);
        OkGoUtils.getByOkGo(UrlUtil.UNIT_LESSON_LIST, params, new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                if(response.code() == 200){
                    String str = response.body();
                    ResultJson resultModel = JSON.parseObject(str, ResultJson.class);
                    if(resultModel.getData()!=null){
                        if(resultModel.getData().getClassunitList()!=null&&resultModel.getData().getClassunitList().size()>0) {
                            ClassunitDao classunitDao = getDaoSession().getClassunitDao();
                            classunitDao.insertOrReplaceInTx(resultModel.getData().getClassunitList());
                        }
                        if(resultModel.getData().getClasslessonList()!=null && resultModel.getData().getClasslessonList().size()>0){
                            ClasslessonDao classlessonDao = getDaoSession().getClasslessonDao();
                            classlessonDao.insertOrReplaceInTx(resultModel.getData().getClasslessonList());
                        }
                    }
                    initData();
                    initAdapter();
                }
            }

            @Override
            public void onError(Response response) {
                super.onError(response);

            }

            @Override
            public void onFinish() {
                super.onFinish();
            }
        });
    }


    //fragment切换后 listview 点击失效 暂时解决办法，不可取
    public void datachange(){
        if(studentExpandableAdapter!=null)
            initAdapter();
    }

    class StudentExpandableAdapter extends BaseExpandableListAdapter{

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
            return classunitList == null ? 0 : classunitList.size();
        }

        /**
         * 获取指定组中的子节点数量
         *
         * @param groupPosition 子元素组所在的位置
         * @return 返回指定组中的子数量
         */
        @Override
        public int getChildrenCount(int groupPosition) {
            return classunitList.get(groupPosition).getClasslessonList().size();
        }

        /**
         * 获取与给定组相关联的对象
         *
         * @param groupPosition 子元素组所在的位置
         * @return 返回指定组的子数据
         */
        @Override
        public Object getGroup(int groupPosition) {
            return classunitList.get(groupPosition).getUnitName();
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
            return classunitList.get(groupPosition).getClasslessonList().get(childPosition);
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
            parentHolder.tvParent.setText(classunitList.get(groupPosition).getUnitName());


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
                convertView = LayoutInflater.from(context).inflate(R.layout.tree_list_child_item, null);
                childrenHolder = new ChildrenHolder();
                childrenHolder.tvChild = convertView.findViewById(R.id.tv_body);
                childrenHolder.ivChild = convertView.findViewById(R.id.iv_title);
                convertView.setTag(childrenHolder);
            } else {
                childrenHolder = (ChildrenHolder) convertView.getTag();
            }

                childrenHolder.tvChild.setText(classunitList.get(groupPosition).getClasslessonList().get(childPosition).getLessonName());

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
