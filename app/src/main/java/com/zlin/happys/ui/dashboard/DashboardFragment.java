package com.zlin.happys.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.zlin.happys.R;
import com.zlin.happys.base.BaseFragment;
import com.zlin.happys.databinding.FragmentDashboardBinding;
import com.zlin.happys.model.ClassName;

import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends BaseFragment {

    private DashboardViewModel dashboardViewModel;
    private FragmentDashboardBinding binding;

    ViewPagerFragmentStateAdapter mAdapter;

    private ViewPager2 mViewPager2;
    private TabLayout mTabLayout;
    private List<ClassName> classes = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        initView(root);
        return root;
    }

    public void initView(View view){
        mTabLayout = view.findViewById(R.id.tablayout);
        mViewPager2 = view.findViewById(R.id.viewpager2);
        mAdapter = new ViewPagerFragmentStateAdapter(DashboardFragment.this);
        mViewPager2.setAdapter(mAdapter);
        mViewPager2.setUserInputEnabled(true);//true:滑动，false：禁止滑动
        mTabLayout.addTab(mTabLayout.newTab().setText("生活"));
        mTabLayout.addTab(mTabLayout.newTab().setText("体育"));
        mTabLayout.addTab(mTabLayout.newTab().setText("美食"));
        mTabLayout.addTab(mTabLayout.newTab().setText("头条"));
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
                mTabLayout.setScrollPosition(position,0,false);
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