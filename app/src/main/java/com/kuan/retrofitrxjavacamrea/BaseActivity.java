package com.kuan.retrofitrxjavacamrea;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by zhuangwu on 17-5-6.
 */

public class BaseActivity extends AppCompatActivity {
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
