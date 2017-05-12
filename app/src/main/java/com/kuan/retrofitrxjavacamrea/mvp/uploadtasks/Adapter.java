package com.kuan.retrofitrxjavacamrea.mvp.uploadtasks;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kuan.retrofitrxjavacamrea.R;
import com.kuan.retrofitrxjavacamrea.bean.VisionDetRet;

import java.util.List;


/**
 * Created by zhuangwu on 17-5-6.
 */

public class Adapter extends RecyclerView.Adapter<ViewHolder>{
    private VisionDetRet visionDetRet;
    private OnClickItemsListener listener;
    public Adapter(OnClickItemsListener listener){
        this.listener=listener;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final List<VisionDetRet.ArrayListBean> arrayListBeen=visionDetRet.getArrayList();
        holder.mName.setText(arrayListBeen.get(position).getLabel()+", "+arrayListBeen.get(position).getConfidence());
        holder.layout_items.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener!=null){
                    listener.onClickItemsId( arrayListBeen.get(position).getId());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if(visionDetRet==null){
            return 0;
        }else{
            List<VisionDetRet.ArrayListBean> arrayListBeen=visionDetRet.getArrayList();
            if(arrayListBeen==null){
                return 0;
            }else{
                return arrayListBeen.size();
            }
        }
    }
    public void swap(VisionDetRet visionDetRet){
        this.visionDetRet=visionDetRet;
        notifyDataSetChanged();
    }
}
