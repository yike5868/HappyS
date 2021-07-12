package com.zlin.happys.base;

import android.app.Activity;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.zlin.happys.HSApp;
import com.zlin.happys.model.DaoSession;

public class BaseFragment extends Fragment {
    public DaoSession getDaoSession(){
        HSApp hsApp = (HSApp) ((Activity)getContext()).getApplication();
        DaoSession daoSession =  hsApp.getDaoSession();
        return daoSession;
    }

    public void showLongToast(String str){
        Toast.makeText(getContext(),str,Toast.LENGTH_LONG).show();
    }
    public void showShortToast(String str){
        Toast.makeText(getContext(),str,Toast.LENGTH_SHORT).show();
    }

}
