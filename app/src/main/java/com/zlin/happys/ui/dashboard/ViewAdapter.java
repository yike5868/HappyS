package com.zlin.happys.ui.dashboard;


import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;

class ViewAdapter extends PagerAdapter {

    public static ArrayList<ArrayList> datasxx =new ArrayList<ArrayList>();//总数据源

    public static ArrayList<Object> additem(String Title, View view){
        //添加键值对，标题：view
        ArrayList<Object> item=new ArrayList<Object>();
        item.add(Title);
        item.add(view);
        datasxx.add(item);
        return item;
    }

    private ArrayList<ArrayList> datas;

    //        private String [] t=new String[]{"高亮", "结果"};
    public ViewAdapter(ArrayList<ArrayList> list) {
        datas=list;//手动控制
    }
    public ViewAdapter() {
        datas=datasxx;//自动
    }


    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return (String)datas.get(position).get(0);//设置标题
//            return super.getPageTitle(position);

    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view=(View)datas.get(position).get(1);//设置view
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)datas.get(position).get(1));//删除view
    }
}