package com.dzfp.dzfp.ui.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;

import com.dzfp.dzfp.CustomApplication;
import com.dzfp.dzfp.R;
import com.dzfp.dzfp.databinding.SetipBinding;
import com.dzfp.dzfp.ui.BaseActivity;
import com.dzfp.dzfp.ui.util.DialogHb;
import com.dzfp.dzfp.ui.util.SharedPreferencesHelper;
import com.google.common.base.Strings;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import scanner.CaptureActivity;

public class SetIpActivity extends BaseActivity {
    private SetipBinding mbinding;
    String ip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mbinding = DataBindingUtil.setContentView(this, R.layout.setip);
        init();
    }

    private void init() {
        if (CustomApplication.saveIp) {
            String[] strings = CustomApplication.ip.split("\\.");
            mbinding.ipeditSetip.setText(strings[0], strings[1], strings[2], strings[3]);
        }
        listern();

    }

    private void listern() {
        mbinding.rlBackSetip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mbinding.btBingSetip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ip = mbinding.ipeditSetip.getText(SetIpActivity.this);
                Log.e("ip",ip);
                if (isIP(ip)) {
                    CustomApplication.ip = ip;
                    CustomApplication.saveIp = true;
                    SharedPreferencesHelper mSharedPreferencesHelper = new SharedPreferencesHelper(SetIpActivity.this);
                    mSharedPreferencesHelper.setParameterToShared(SharedPreferencesHelper.IP, ip);
                    mSharedPreferencesHelper.setBooleanToShared(SharedPreferencesHelper.SAVE_IP, true);
                    DialogHb.showdialog(SetIpActivity.this, "保存成功");
                } else {
                    DialogHb.showdialog(SetIpActivity.this, "请输入正确的ip");
                }
            }
        });
        mbinding.btSaomaSetip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SetIpActivity.this, CaptureActivity.class);
                intent.putExtra("what", CaptureActivity.ScanIp);
                startActivityForResult(intent, 33);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 33 && resultCode == RESULT_OK) {
            String datas = data.getStringExtra("content");
            String s = datas.substring(0, 4);
            if (s.equals("CDHY")) {
                ip = datas.substring(4, datas.length());
                if (CustomApplication.saveIp) {
                    String[] strings = ip.split("\\.");
                    mbinding.ipeditSetip.setText(strings[0], strings[1], strings[2], strings[3]);
                }
            } else {
                DialogHb.showdialog(SetIpActivity.this, "请扫描正确的二维码");
            }
        }
    }

    public boolean isIP(String addr) {
        if (addr.length() < 7 || addr.length() > 15 || "".equals(addr)) {
            return false;
        }
        /**
         * 判断IP格式和范围
         */
        String rexp = "([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}";

        Pattern pat = Pattern.compile(rexp);

        Matcher mat = pat.matcher(addr);

        boolean ipAddress = mat.find();

        return ipAddress;
    }
}
