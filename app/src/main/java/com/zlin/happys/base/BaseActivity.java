package com.zlin.happys.base;

import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.zlin.happys.HSApp;
import com.zlin.happys.model.CityDao;
import com.zlin.happys.model.DaoSession;

import org.greenrobot.greendao.AbstractDao;

public class BaseActivity extends AppCompatActivity {

    public DaoSession getDaoSession(){
        HSApp hsApp = (HSApp) getApplication();
        DaoSession daoSession =  hsApp.getDaoSession();
        return daoSession;
    }

    public void showLongToast(String str){
        Toast.makeText(this,str,Toast.LENGTH_LONG).show();
    }
    public void showShortToast(String str){
        Toast.makeText(this,str,Toast.LENGTH_SHORT).show();
    }

}
