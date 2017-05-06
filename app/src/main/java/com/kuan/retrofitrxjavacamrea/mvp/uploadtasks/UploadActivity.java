package com.kuan.retrofitrxjavacamrea.mvp.uploadtasks;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


import com.kuan.retrofitrxjavacamrea.BaseActivity;
import com.kuan.retrofitrxjavacamrea.Camera2BasicFragment;
import com.kuan.retrofitrxjavacamrea.R;
import com.kuan.retrofitrxjavacamrea.utils.SharedPreferencesUtil;

import java.util.ArrayList;
import java.util.List;
public class UploadActivity extends BaseActivity implements UploadTasksContract.View,View.OnClickListener{
    private String strPath="";
    private UploadTasksContract.Presenter mPresenter;
    private RecyclerView recyclerView;
    private Adapter adapter;
    private List<String> data=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uploadtasks);
        strPath=getIntent().getStringExtra(Camera2BasicFragment.KEY_IMGPATH);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 2;
        Bitmap bitmap= BitmapFactory.decodeFile(strPath,options);
        Log.i("zuuangwu",strPath);
        String ip= SharedPreferencesUtil.getData(this,SharedPreferencesUtil.NETWORK_IP,"");
        String port=SharedPreferencesUtil.getData(this,SharedPreferencesUtil.NETWORK_PORT,"");
        findViewById(R.id.btn_ret).setOnClickListener(this);
        ((ImageView)findViewById(R.id.imageview)).setImageBitmap(bitmap);
//        recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
//        adapter=new Adapter();
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));
//        recyclerView.setAdapter(adapter);
        new UploadTasksPresenter(this);
        if(!strPath.equals("")){
            if(!ip.equals("")&&!port.equals("")) {
                String url = String.format("http://%s:%s", ip, port);
                List<String> pathlist=new ArrayList<>();
                pathlist.add(strPath);
                //showProgressDialog("正在识别请稍后...");
                mPresenter.uploadImage(url,pathlist);
            }else{
                Toast.makeText(this, "IP和PORT不能为空", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "图片路径无效请返回重试", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void setPresenter(UploadTasksContract.Presenter presenter) {
        if(presenter!=null){
            mPresenter=presenter;
        }
    }

    @Override
    public void Success(String data) {
        Log.i("zhangwu",data);
        hideProgressDialog();
    }

    @Override
    public void Error() {
        hideProgressDialog();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_ret:{
                finish();
            }break;
            default:break;
        }
    }
}
