package com.kuan.retrofitrxjavacamrea.mvp.uploadtasks;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.kuan.retrofitrxjavacamrea.BaseActivity;
import com.kuan.retrofitrxjavacamrea.Camera2BasicFragment;
import com.kuan.retrofitrxjavacamrea.R;
import com.kuan.retrofitrxjavacamrea.bean.VisionDetRet;
import com.kuan.retrofitrxjavacamrea.utils.SharedPreferencesUtil;
public class UploadActivity extends BaseActivity implements UploadTasksContract.View,OnClickItemsListener{
  private AppCompatImageView imageView;
    private UploadTasksContract.Presenter mPresenter;
    private RecyclerView mListView;
    private Adapter adapter;
    private TextView timeText;
    private TextView return_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uploadtasks);
        imageView=(AppCompatImageView)findViewById(R.id.imageView);
        mListView=(RecyclerView)findViewById(R.id.materialListView);
        timeText=(TextView)findViewById(R.id.time);
        return_btn=(TextView)findViewById(R.id.return_btn);
        return_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        String path=getIntent().getStringExtra(Camera2BasicFragment.KEY_IMGPATH);
        Log.i("path",path);
        Bitmap bitmap= BitmapFactory.decodeFile(path);
        imageView.setImageBitmap(bitmap);
        LinearLayout.LayoutParams mParams=(LinearLayout.LayoutParams)imageView.getLayoutParams();
        int mWidth=imageView.getWidth();

        int mHeight=calculate(mWidth,bitmap);
        mParams.height=mHeight;
        imageView.setLayoutParams(mParams);
        mListView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false));
        mListView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        mListView.setItemAnimator(new DefaultItemAnimator());
        adapter=new Adapter(this);
        mListView.setAdapter(adapter);

        String ip= SharedPreferencesUtil.getData(this,SharedPreferencesUtil.NETWORK_IP,"");
        String port=SharedPreferencesUtil.getData(this,SharedPreferencesUtil.NETWORK_PORT,"");
       if(ip.equals("")||port.equals("")){
           ErrorDialog.newInstance("您没有设置网络,请设置网络IP和端口号").show(getSupportFragmentManager(),"dialog");
           return;
       }
        new UploadTasksPresenter(this);
        String url = String.format("http://%s:%s", ip, port);
        Log.i("zhuangwu",url);
        showProgressDialog("正在识别请稍后...");
        mPresenter.uploadImage(url,path);
    }

    @Override
    public void setPresenter(UploadTasksContract.Presenter presenter) {
        if(presenter!=null){
            mPresenter=presenter;
        }
    }

    @Override
    public void Success(VisionDetRet visionDetRet) {
        hideProgressDialog();
        if(visionDetRet.getErrorMsg().equals("success")){
            timeText.setText("识别时间:"+visionDetRet.getTime()+"s");
            adapter.swap(visionDetRet);
        }else{
            timeText.setText("0s");
            adapter.swap(null);
            Toast.makeText(this, "无数据", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void Error() {
        hideProgressDialog();
        ErrorDialog.newInstance("网络出错，关闭重试").show(getSupportFragmentManager(),"dialog");
        adapter.swap(null);
        timeText.setText("0s");
    }

    private  int calculate(int w,Bitmap bitmap){
        int bitmap_max=  Math.max(bitmap.getWidth(),bitmap.getHeight());
        int bitmap_min=Math.min(bitmap.getWidth(),bitmap.getHeight());
        return w*bitmap_min/bitmap_max;
    }

    @Override
    public void onClickItemsId(String id) {

    }
}
