package com.zlin.happys.ui.dashboard;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.zlin.happys.R;
import com.zlin.happys.base.BaseFragment;
import com.zlin.happys.model.ClassGradeDao;
import com.zlin.happys.model.ClassName;
import com.zlin.happys.model.ClassNameDao;

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
    KDTabLayout kdTabLayout;
    private List<ClassName> classes = new ArrayList<>();
    ViewPager2 vp2;
    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {

         view =inflater.inflate(R.layout.activity_scrollable_tab,container,false);
        initData();
        initView();
        return view;
    }
    public void initData(){
        ClassGradeDao classGradeDao = getDaoSession().getClassGradeDao();
        ClassNameDao classNameDao = getDaoSession().getClassNameDao();
        classes = classNameDao.queryBuilder().list();

        classes.add( new ClassName("aa","道德","11111"));
        classes.add( new ClassName("aa","科学","11111"));
        classes.add( new ClassName("aa","美术","11111"));
        classes.add( new ClassName("aa","音乐","11111"));
    }
    public void initView(){
        vp2 = view.findViewById(R.id.vp2);

        ViewPagerFragmentStateAdapter mAdapter = new ViewPagerFragmentStateAdapter(DashboardFragment2.this);
        vp2.setAdapter(mAdapter);
        vp2.setUserInputEnabled(true);//true:滑动，false：禁止滑动


        kdTabLayout = view.findViewById(R.id.tab2);
        KDTabAdapter kdTabAdapter = new KDTabAdapter() {
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
            return ClassListFragment.newInstance(classes.get(position).getName());
        }
    }
}