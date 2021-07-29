package com.zlin.happys.ui.classFragment;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.zlin.happys.R;
import com.zlin.happys.base.BaseFragment;
import com.zlin.happys.model.Classgrade;
import com.zlin.happys.model.ClassgradeDao;
import com.zlin.happys.model.Classname;
import com.zlin.happys.model.ClassnameDao;

import org.greenrobot.greendao.query.QueryBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import github.xuqk.kdtablayout.KDTabAdapter;
import github.xuqk.kdtablayout.KDTabLayout;
import github.xuqk.kdtablayout.widget.KDTab;
import github.xuqk.kdtablayout.widget.KDTabIndicator;
import github.xuqk.kdtablayout.widget.indicator.KDRecIndicator;
import github.xuqk.kdtablayout.widget.tab.KDColorMorphingTextTab;


public class DashboardFragment2 extends BaseFragment {

    View view;
    ViewPager2 vp2;
    KDTabLayout kdTabLayout;
    TextView tv_grade;
    private PopupWindow popupwindow;
    private List<Classgrade> gradeList = new ArrayList<>();
    private List<Classname> classes = new ArrayList<>();
    KDTabAdapter kdTabAdapter;

    List<ClassListFragment2> fragmentList = new ArrayList<>();

    Classgrade selectClassgrade ;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {

        view =inflater.inflate(R.layout.activity_scrollable_tab,container,false);
        initGradeData();
        initTabView();
        initTabData();
        initPop();
        initClassnameData();
        return view;
    }



    public void initGradeData(){
        ClassgradeDao classGradeDao = getDaoSession().getClassgradeDao();

        QueryBuilder<Classgrade> qb = classGradeDao.queryBuilder();
        gradeList = qb.orderDesc(ClassgradeDao.Properties.OrderId).list();

        if(gradeList!=null&&gradeList.size()>0){
            selectClassgrade = gradeList.get(0);
        }
    }

    public void initClassnameData(){
        ClassnameDao classNameDao = getDaoSession().getClassnameDao();
        QueryBuilder<Classname> classnameQueryBuilder = classNameDao.queryBuilder();
        classes = classnameQueryBuilder.where(ClassnameDao.Properties.ClassGradeId.eq(selectClassgrade.getClassGradeId())).orderAsc(ClassnameDao.Properties.OrderId).list();
        fragmentList.clear();
        if(classes!=null) {
            for (int i = 0; i < classes.size(); i++) {
                fragmentList.add(ClassListFragment2.newInstance(classes.get(i).getId()));
            }
        }
        initTabData();
    }

    public void initPop(){
        // // 获取自定义布局文件pop.xml的视图
        View customView = getLayoutInflater().inflate(R.layout.popview_item,
                null, false);
        // 创建PopupWindow实例,200,150分别是宽度和高度
        popupwindow = new PopupWindow(customView, 380, 580);
        // 设置动画效果 [R.style.AnimationFade 是自己事先定义好的]
        popupwindow.setAnimationStyle(R.style.popwin_anim_style);

        popupwindow.setFocusable(true);
        popupwindow.setBackgroundDrawable(new BitmapDrawable());
        // 自定义view添加触摸事件
        customView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (popupwindow != null && popupwindow.isShowing()) {
                    popupwindow.dismiss();
                    popupwindow = null;
                }
                return false;
            }
        });
        ListView popListView = customView.findViewById(R.id.popListView);
        popListView.setAdapter(new popAdapter(getContext()));
        popListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectClassgrade = gradeList.get(i);
                tv_grade.setText(selectClassgrade.getClassGradeName());
                initClassnameData();
                initTabData();
                popupwindow.dismiss();

            }
        });
        tv_grade = view.findViewById(R.id.tv_grade);
        tv_grade.setText(selectClassgrade.getClassGradeName());
        tv_grade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (popupwindow != null&&popupwindow.isShowing()) {
                    popupwindow.dismiss();
                    return;
                } else {
                    popupwindow.showAsDropDown(tv_grade, 10, 40);
                }

            }
        });
    }

    class popAdapter extends BaseAdapter{
        private  LayoutInflater mLayoutInflater;

        public popAdapter(Context contex) {
            mLayoutInflater =  LayoutInflater.from(contex);;
        }
        @Override
        public int getCount() {
            return gradeList.size();
        }

        @Override
        public Object getItem(int i) {
            return gradeList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View convertView, ViewGroup viewGroup) {
            View conView;
            final ViewHolder holder;
            if (null == convertView) {
                conView = mLayoutInflater.inflate(R.layout.pop_class_item, null);
                holder = new ViewHolder();
                holder.title = conView.findViewById(R.id.tv_show_grade);
                view.setTag(holder);
            } else {
                conView = convertView;
                holder = (ViewHolder) view.getTag();
            }
            holder.title.setText(gradeList.get(i).getClassGradeName());
            return conView;
        }
        private  class ViewHolder {
            public TextView title;
        }
    }


    public void initTabView(){
        vp2 = view.findViewById(R.id.vp2);
        kdTabLayout = view.findViewById(R.id.tab2);
    }

    public void initTabData(){

        ViewPagerFragmentStateAdapter mAdapter = new ViewPagerFragmentStateAdapter(DashboardFragment2.this);
        vp2.setAdapter(mAdapter);
        vp2.setUserInputEnabled(true);//true:滑动，false：禁止滑动
        vp2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                fragmentList.get(position).datachange();
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });

        kdTabAdapter = new KDTabAdapter() {
            @org.jetbrains.annotations.Nullable
            @Override
            public KDTab createTab(int position) {
                KDColorMorphingTextTab kdColorMorphingTextTab = new KDColorMorphingTextTab(getContext(),classes.get(position).getName());
                kdColorMorphingTextTab.setHorizontalPadding(16f);
                kdColorMorphingTextTab.setSelectedTextColor(Color.parseColor("#ff5722"));
                kdColorMorphingTextTab.setNormalTextColor( Color.parseColor("#9e9e9e"));
                kdColorMorphingTextTab.setSelectedTextSize(32f);
                kdColorMorphingTextTab.setNormalTextSize(16f);
                kdColorMorphingTextTab.setResizeWithFontSize(true);

                kdColorMorphingTextTab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        vp2.setCurrentItem(position);
                        fragmentList.get(position).datachange();
                    }
                });
                return kdColorMorphingTextTab;
            }

            @org.jetbrains.annotations.Nullable
            @Override
            public KDTabIndicator createIndicator() {

                KDRecIndicator kdRecIndicator = new KDRecIndicator(kdTabLayout);
                kdRecIndicator.setIndicatorHeight(6f);
                kdRecIndicator.setColor(Color.parseColor("#ff5722"));
                kdRecIndicator.setCornerRadius(3f);
                kdRecIndicator.setMode(KDRecIndicator.MODE_EXACT);
                kdRecIndicator.setIndicatorWidth(16f);
                kdRecIndicator.setStartInterpolator(new AccelerateInterpolator());
                kdRecIndicator.setEndInterpolator(new DecelerateInterpolator(2f));
                return kdRecIndicator;
            }

            @Override
            public int getTabCount() {
                return classes.size();
            }
        };
        kdTabLayout.setContentAdapter(kdTabAdapter);
        kdTabLayout.setViewPager2(vp2);
    }


    class ViewPagerFragmentStateAdapter extends FragmentStateAdapter {

        ViewPagerFragmentStateAdapter(@NonNull Fragment fragment) {
            super(fragment);
        }


        @Override
        public int getItemCount() {
            return classes.size();
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return fragmentList.get(position);
        }
    }
}