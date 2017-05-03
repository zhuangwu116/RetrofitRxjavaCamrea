package com.kuan.retrofitrxjavacamrea.mvp.uploadtasks;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.kuan.retrofitrxjavacamrea.R;

public class UploadActivity extends AppCompatActivity implements UploadTasksContract.View{
    private UploadTasksContract.Presenter mPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uploadtasks);
        new UploadTasksPresenter(this);
    }

    @Override
    public void setPresenter(UploadTasksContract.Presenter presenter) {
        if(presenter!=null){
            mPresenter=presenter;
        }
    }
}
