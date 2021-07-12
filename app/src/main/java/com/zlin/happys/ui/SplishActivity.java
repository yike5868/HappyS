package com.zlin.happys.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.zlin.happys.MainActivity;
import com.zlin.happys.R;
import com.zlin.happys.base.BaseActivity;
import com.zlin.happys.model.ClassGrade;
import com.zlin.happys.model.ClassGradeDao;
import com.zlin.happys.model.ClassNameDao;
import com.zlin.happys.model.ResultJson;
import com.zlin.happys.model.ResultModel;
import com.zlin.happys.utils.OkGoUtils;
import com.zlin.happys.utils.UrlUtil;

public class SplishActivity extends BaseActivity {
    TextView tv_time;
    private static final String TAG = SplishActivity.class.getName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splish);
        tv_time = findViewById(R.id.tv_time);
    }

    @Override
    protected void onStart() {
        super.onStart();
        getData();
    }
    public void getData(){
        OkGoUtils.getByOkGo(UrlUtil.CLASS_LIST, null, new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                if(response.code() == 200){
                    Log.w(TAG,response.body());
                    String str = response.body();
                    ResultJson resultModel = JSON.parseObject(str, ResultJson.class);
                    if(resultModel.getData()!=null){
                        if(resultModel.getData().getClassgradeList()!=null&&resultModel.getData().getClassgradeList().size()>0) {
                            ClassGradeDao classGradeDao = getDaoSession().getClassGradeDao();
                            classGradeDao.insertOrReplaceInTx(resultModel.getData().getClassgradeList());
                        }
                        if(resultModel.getData().getClassnameList()!=null && resultModel.getData().getClassnameList().size()>0){
                            ClassNameDao classNameDao = getDaoSession().getClassNameDao();
                            classNameDao.insertOrReplaceInTx(resultModel.getData().getClassnameList());
                        }
                    }

                }
            }

            @Override
            public void onError(Response response) {
                super.onError(response);
                Intent intent = new Intent(SplishActivity.this, MainActivity.class);
                startActivity(intent);
                SplishActivity.this.finish();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                Intent intent = new Intent(SplishActivity.this, MainActivity.class);
                startActivity(intent);
                SplishActivity.this.finish();
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        /** 倒计时60秒，一次1秒 */
//        CountDownTimer timer = new CountDownTimer(5*1000, 1000) {
//            @Override
//            public void onTick(long millisUntilFinished) {
//                // TODO Auto-generated method stub
//                tv_time.setText("跳过（"+millisUntilFinished/1000+"s)");
//            }
//
//            @Override
//            public void onFinish() {
//                Intent intent = new Intent(SplishActivity.this, MainActivity.class);
//                startActivity(intent);
//                SplishActivity.this.finish();
//            }
//        }.start();
    }
}
