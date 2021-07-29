package com.zlin.happys.ui.classes;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.zlin.happys.R;

import java.util.ArrayList;
import java.util.List;

import github.xuqk.kdtablayout.KDTabAdapter;
import github.xuqk.kdtablayout.KDTabLayout;
import github.xuqk.kdtablayout.widget.KDTab;
import github.xuqk.kdtablayout.widget.KDTabIndicator;
import github.xuqk.kdtablayout.widget.indicator.KDRecIndicator;
import github.xuqk.kdtablayout.widget.tab.KDColorMorphingTextTab;

public class ChineseActivity extends FragmentActivity {

    ViewPager2 vp2;
    KDTabLayout kdTabLayout;

    String [] strs = new String[]{"课文","生字","讲解","习题"};
    List<Fragment> fragmentList;
    String lessonId;


    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chinese);
        Intent intent = getIntent();
        lessonId = intent.getStringExtra("lessonId");

        initTabView();
    }

    public void initTabView(){
        fragmentList = new ArrayList<>();
        fragmentList.add(ClassBodyFragment.newInstance(lessonId));
        fragmentList.add(NewWordFragment.newInstance(lessonId));
        fragmentList.add(ClasspointsFragment.newInstance(lessonId));
        fragmentList.add(ClassExplainFragment.newInstance(lessonId));
        vp2 = findViewById(R.id.vp2);

        ViewPagerFragmentStateAdapter mAdapter = new ViewPagerFragmentStateAdapter(ChineseActivity.this);
        vp2.setAdapter(mAdapter);
        vp2.setUserInputEnabled(true);//true:滑动，false：禁止滑动


        kdTabLayout = findViewById(R.id.tab2);
        KDTabAdapter kdTabAdapter = new KDTabAdapter() {
            @org.jetbrains.annotations.Nullable
            @Override
            public KDTab createTab(int position) {
                KDColorMorphingTextTab kdColorMorphingTextTab = new KDColorMorphingTextTab(ChineseActivity.this,strs[position]);
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
                return strs.length;
            }
        };
        kdTabLayout.setContentAdapter(kdTabAdapter);
        kdTabLayout.setViewPager2(vp2);
    }


    class ViewPagerFragmentStateAdapter extends FragmentStateAdapter {

        ViewPagerFragmentStateAdapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @Override
        public int getItemCount() {
            return strs.length;
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return fragmentList.get(position);
        }
    }
}
