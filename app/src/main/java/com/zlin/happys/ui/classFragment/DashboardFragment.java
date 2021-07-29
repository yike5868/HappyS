package com.zlin.happys.ui.classFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.zlin.happys.R;
import com.zlin.happys.base.BaseFragment;
import com.zlin.happys.databinding.FragmentDashboardBinding;
import com.zlin.happys.model.ClassgradeDao;
import com.zlin.happys.model.Classname;
import com.zlin.happys.model.ClassnameDao;

import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends BaseFragment {

    private DashboardViewModel dashboardViewModel;
    private FragmentDashboardBinding binding;
    ViewPagerFragmentStateAdapter mAdapter;
    List<ClassListFragment> listFragments;
    private ViewPager2 mViewPager2;
    private TabLayout mTabLayout;
    private List<Classname> classes = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        initData();
        initView(root);
        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
    }



    public void initData(){
        ClassgradeDao classGradeDao = getDaoSession().getClassgradeDao();
        ClassnameDao classNameDao = getDaoSession().getClassnameDao();
        classes = classNameDao.queryBuilder().list();

//        classes.add( new ClassName("aa","道德","11111"));
//        classes.add( new ClassName("aa","科学","11111"));
//        classes.add( new ClassName("aa","美术","11111"));
//        classes.add( new ClassName("aa","音乐","11111"));
    }


    public void initView(View view){
        mTabLayout = view.findViewById(R.id.tablayout);
        mViewPager2 = view.findViewById(R.id.viewpager2);
        mAdapter = new ViewPagerFragmentStateAdapter(DashboardFragment.this);
        mViewPager2.setAdapter(mAdapter);
        mViewPager2.setUserInputEnabled(true);//true:滑动，false：禁止滑动
        for (int i = 0; i < classes.size(); i++) {
            mTabLayout.addTab(mTabLayout.newTab().setText(classes.get(i).getName()));
        }

        //tab点击选中
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        mViewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                mTabLayout.setScrollPosition(position,0,true);
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
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