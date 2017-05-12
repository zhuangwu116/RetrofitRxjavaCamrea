package com.kuan.retrofitrxjavacamrea;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDialogFragment;

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
    public static class ErrorDialog extends AppCompatDialogFragment {

        private static final String ARG_MESSAGE = "message";

        public static ErrorDialog newInstance(String message) {
            ErrorDialog dialog = new ErrorDialog();
            Bundle args = new Bundle();
            args.putString(ARG_MESSAGE, message);
            dialog.setArguments(args);
            return dialog;
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Activity activity = getActivity();
            return new AlertDialog.Builder(activity)
                    .setMessage(getArguments().getString(ARG_MESSAGE))
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            activity.finish();
                        }
                    })
                    .create();
        }

    }
}
