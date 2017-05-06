package com.kuan.retrofitrxjavacamrea.mvp.uploadtasks;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class UploadActivity extends BaseActivity implements UploadTasksContract.View,View.OnClickListener{
    private String strPath="";
    private UploadTasksContract.Presenter mPresenter;
    private RecyclerView recyclerView;
    private Adapter adapter;
    private List<String> data=new ArrayList<>();
    private final String IMAGE_TYPE = "image/*";

    private final int IMAGE_CODE = 0;   //这里的IMAGE_CODE是自己任意定义的
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uploadtasks);
//        strPath=getIntent().getStringExtra(Camera2BasicFragment.KEY_IMGPATH);
//        BitmapFactory.Options options = new BitmapFactory.Options();
//        options.inSampleSize = 2;
//        Bitmap bitmap= BitmapFactory.decodeFile(strPath,options);
//        Log.i("zuuangwu",strPath);
//        String ip= SharedPreferencesUtil.getData(this,SharedPreferencesUtil.NETWORK_IP,"");
//        String port=SharedPreferencesUtil.getData(this,SharedPreferencesUtil.NETWORK_PORT,"");
//        findViewById(R.id.btn_ret).setOnClickListener(this);
//        ((ImageView)findViewById(R.id.imageview)).setImageBitmap(bitmap);
//        recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
//        adapter=new Adapter();
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));
//        recyclerView.setAdapter(adapter);
//        new UploadTasksPresenter(this);
//        if(!strPath.equals("")){
//            if(!ip.equals("")&&!port.equals("")) {
//                String url = String.format("http://%s:%s", ip, port);
//                List<String> pathlist=new ArrayList<>();
//                pathlist.add(strPath);
//                //showProgressDialog("正在识别请稍后...");
//                mPresenter.uploadImage(url,pathlist);
//            }else{
//                Toast.makeText(this, "IP和PORT不能为空", Toast.LENGTH_SHORT).show();
//            }
//        }else{
//            Toast.makeText(this, "图片路径无效请返回重试", Toast.LENGTH_SHORT).show();
//        }


//使用intent调用系统提供的相册功能，使用startActivityForResult是为了获取用户选择的图片
        new UploadTasksPresenter(this);
        Intent getAlbum = new Intent(Intent.ACTION_GET_CONTENT);

        getAlbum.setType(IMAGE_TYPE);

        startActivityForResult(getAlbum, IMAGE_CODE);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK) {        //此处的 RESULT_OK 是系统自定义得一个常量

            Log.e("aaa","ActivityResult resultCode error");

            return;

        }

        Bitmap bm = null;

        //外界的程序访问ContentProvider所提供数据 可以通过ContentResolver接口

        ContentResolver resolver = getContentResolver();

        //此处的用于判断接收的Activity是不是你想要的那个

        if (requestCode == IMAGE_CODE) {

            try {

                Uri originalUri = data.getData();        //获得图片的uri

                bm = MediaStore.Images.Media.getBitmap(resolver, originalUri);        //显得到bitmap图片


                String[] proj = {MediaStore.Images.Media.DATA};

                //好像是android多媒体数据库的封装接口，具体的看Android文档

                Cursor cursor = managedQuery(originalUri, proj, null, null, null);

                //按我个人理解 这个是获得用户选择的图片的索引值

                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

                //将光标移至开头 ，这个很重要，不小心很容易引起越界

                cursor.moveToFirst();

                //最后根据索引值获取图片路径

                String path = cursor.getString(column_index);
                Log.i("zhuangwu",path);
                mPresenter.uploadImage("http://192.168.13.128:5000",path);

            }catch (IOException e) {

                Log.e("aaa",e.toString());

            }

        }


    }
}
