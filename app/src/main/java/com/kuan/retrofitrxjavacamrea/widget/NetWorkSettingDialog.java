package com.kuan.retrofitrxjavacamrea.widget;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.kuan.retrofitrxjavacamrea.R;
import com.kuan.retrofitrxjavacamrea.utils.SharedPreferencesUtil;

/**
 * Created by zhuangwu on 17-5-6.
 */

public class NetWorkSettingDialog extends AppCompatDialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=getActivity().getLayoutInflater();
        View view=inflater.inflate(R.layout.network_setting,null);
        final EditText ipEdit=(EditText)view.findViewById(R.id.edt_ip);
        final EditText portEdit=(EditText)view.findViewById(R.id.edt_port);
        ipEdit.setText(SharedPreferencesUtil.getData(getActivity(),
                SharedPreferencesUtil.NETWORK_IP,""));
        portEdit.setText(SharedPreferencesUtil.getData(getActivity(),
                SharedPreferencesUtil.NETWORK_PORT,""));
        builder.setView(view)
                .setPositiveButton("保存", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                      SharedPreferencesUtil.saveData(getActivity(),
                              SharedPreferencesUtil.NETWORK_IP,
                              ipEdit.getText().toString().trim());
                      SharedPreferencesUtil.saveData(getActivity(),
                              SharedPreferencesUtil.NETWORK_PORT,
                              portEdit.getText().toString().trim());
                      dismiss();
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dismiss();
            }
        });
        return builder.create();
    }
}
