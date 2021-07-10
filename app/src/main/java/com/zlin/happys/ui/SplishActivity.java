package com.zlin.happys.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

import com.zlin.happys.MainActivity;
import com.zlin.happys.R;
import com.zlin.happys.base.BaseActivity;

public class SplishActivity extends BaseActivity {
    TextView tv_time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splish);
        tv_time = findViewById(R.id.tv_time);
    }

    @Override
    protected void onResume() {
        super.onResume();
        /** 倒计时60秒，一次1秒 */
        CountDownTimer timer = new CountDownTimer(5*1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                // TODO Auto-generated method stub
                tv_time.setText("跳过（"+millisUntilFinished/1000+"s)");
            }

            @Override
            public void onFinish() {
                Intent intent = new Intent(SplishActivity.this, MainActivity.class);
                startActivity(intent);
                SplishActivity.this.finish();
            }
        }.start();
    }
}
