package com.kuan.retrofitrxjavacamrea.mvp.details;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.kuan.retrofitrxjavacamrea.BaseActivity;
import com.kuan.retrofitrxjavacamrea.R;
import com.kuan.retrofitrxjavacamrea.utils.SharedPreferencesUtil;

public class DetailTasksActivity extends BaseActivity implements DetailTasksContract.View{
    private AppCompatImageView imageView;
    private TextView mcTv;
    private TextView msTv;
    private TextView clTv;
    private TextView ret_btn;
    private DetailTasksContract.Presenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tasks);
        imageView=(AppCompatImageView)findViewById(R.id.iv_tp1);
        msTv=(TextView)findViewById(R.id.tv_ms);
        clTv=(TextView)findViewById(R.id.tv_czcl);
        mcTv=(TextView)findViewById(R.id.tv_mc);
        ret_btn=(TextView)findViewById(R.id.return_btn);
        ret_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        String id=getIntent().getStringExtra("id");
        if(id.equals("")){
            Toast.makeText(this, "id 为空", Toast.LENGTH_SHORT).show();
            return;
        }
        String ip= SharedPreferencesUtil.getData(this,SharedPreferencesUtil.NETWORK_IP,"");
        String port=SharedPreferencesUtil.getData(this,SharedPreferencesUtil.NETWORK_PORT,"");
        if(ip.equals("")||port.equals("")){
            ErrorDialog.newInstance("您没有设置网络,请设置网络IP和端口号").show(getSupportFragmentManager(),"dialog");
            return;
        }
        String url = String.format("http://%s:%s", ip, port);
        showProgressDialog("正在请求数据，请稍后...");
        presenter.getDetailInfos(url,ip);
    }

    @Override
    public void Success(String data) {
        hideProgressDialog();
        Log.i("zhuangwu",data);
    }

    @Override
    public void Error() {
        hideProgressDialog();
    }

    @Override
    public void setPresenter(DetailTasksContract.Presenter presenter) {
        if(presenter!=null){
            this.presenter=presenter;
        }
    }
}
