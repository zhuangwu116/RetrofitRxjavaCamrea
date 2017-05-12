package com.kuan.retrofitrxjavacamrea.mvp.uploadtasks;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kuan.retrofitrxjavacamrea.R;

/**
 * Created by zhuangwu on 17-5-6.
 */

public class ViewHolder extends RecyclerView.ViewHolder{
    public TextView mName;
    public LinearLayout layout_items;
    public ViewHolder(View itemView) {
        super(itemView);
        mName=(TextView) itemView.findViewById(R.id.name);
        layout_items=(LinearLayout)itemView.findViewById(R.id.layout_items);
    }
}
