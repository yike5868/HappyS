package com.zlin.happys;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.zlin.happys.model.Student;
import com.zlin.happys.utils.StringUtils;

public class HSApp extends Application {
    Student student;
    static HSApp hsApp;
    public HSApp(){
        hsApp = HSApp.this;
       // student = getSP("student",Student.class);
    }
    public   <T> T getSP(String name,Class<T> clazz){

        String ss= getSP(name);
        if(StringUtils.isNotEmpty(ss)) {
            JSONObject jsonObject = JSON.parseObject(ss);
            T t = JSON.parseObject(ss, new TypeReference<T>() {});
            return t;
        }
        return null;
    }
    public  <T> T getSP(Class<T> clazz){
       return getSP(clazz.getName(),clazz);
    }
    public  String getSP(String name){
        SharedPreferences sharedPreferences= getApplicationContext().getSharedPreferences("data", Context .MODE_PRIVATE);
        String ss=sharedPreferences.getString(name,"");
        return ss;
    }
    public  void putSP(String name,String value){
        //步骤1：创建一个SharedPreferences对象
        SharedPreferences sharedPreferences= getApplicationContext().getSharedPreferences("data", Context.MODE_PRIVATE);
        //步骤2： 实例化SharedPreferences.Editor对象
        SharedPreferences.Editor editor = sharedPreferences.edit();
        //步骤3：将获取过来的值放入文件
        editor.putString(name, value);
        //步骤4：提交
        editor.commit();
    }
    public  void putSP(String name,Object value){
        putSP(name, JSON.toJSONString(value));
    }
}
