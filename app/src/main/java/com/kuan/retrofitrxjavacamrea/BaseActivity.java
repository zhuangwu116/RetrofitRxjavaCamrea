package com.kuan.retrofitrxjavacamrea;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by zhuangwu on 17-5-6.
 */

public class BaseActivity extends AppCompatActivity {
    protected ProgressDialog mProgressDialog;
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
    /**
     * 显示dialog
     *
     * @param msg 显示信息
     */
    protected void showProgressDialog(String msg) {
        if (!isFinishing()) {
            mProgressDialog = ProgressDialog.show(this, "", msg);
            mProgressDialog.setIndeterminate(true);
            mProgressDialog.setCancelable(false);
            mProgressDialog.setCanceledOnTouchOutside(false);
        }
    }
    /**
     * 隐藏dialog
     */
    protected void hideProgressDialog() {
        if (!isFinishing()) {
            if (mProgressDialog != null && mProgressDialog.isShowing()) {
                mProgressDialog.dismiss();
            }
        }
    }

}
