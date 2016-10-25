package com.dzfp.dzfp.ui.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.dzfp.dzfp.CustomApplication;
import com.dzfp.dzfp.R;
import com.dzfp.dzfp.databinding.ReimbursementApplicationBinding;
import com.dzfp.dzfp.model.InvoiceInfoBean;
import com.dzfp.dzfp.model.ProcessBean;
import com.dzfp.dzfp.nohttp.CallServer;
import com.dzfp.dzfp.nohttp.HttpListener;
import com.dzfp.dzfp.ui.BaseActivity;
import com.dzfp.dzfp.ui.MineUrl;
import com.dzfp.dzfp.ui.adapter.ListRebmentAdapter;
import com.dzfp.dzfp.ui.adapter.ProcessListAdapter;
import com.dzfp.dzfp.ui.util.DialogHb;
import com.dzfp.dzfp.ui.util.UrlUtils;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import scanner.CaptureActivity;

/**
 * Created by Administrator on 2016/8/29.
 */

public class ResmentApplicationActivity extends BaseActivity implements View.OnClickListener {
    ReimbursementApplicationBinding binding;
    String comid;
    AlertDialog alertDialog = null;
    Intent intent;
    List<InvoiceInfoBean> list;
    ListRebmentAdapter listRebmentAdapter;
    String processid;
    String pid = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        binding = DataBindingUtil.setContentView(this, R.layout.reimbursement_application);
        binding.setOnclick(this);
        intent = getIntent();
        comid = intent.getStringExtra("comid");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_back_reimbursement:
                finish();
                break;
            case R.id.rl_rebment_type:
                //选择报销类型
                actionAlertDialog();
                break;
            case R.id.rl_rebment_chose:
                //选择发票
                showdialog();
                break;
            case R.id.reimbment_button:
                //发票报销
                getpids();
                pid = "";
                break;
        }
    }

    private void getpids() {
        if (list == null) {
            DialogHb.showdialog(ResmentApplicationActivity.this, "请选择发票");
        } else {
            for (int i = 0; i < list.size(); i++) {
                if (i + 1 == list.size()) {
                    pid += list.get(i).getId();
                } else {
                    pid += list.get(i).getId() + ",";
                }
            }
            binding.reimbmentButton.setBackgroundResource(R.drawable.buttonblue_0);
            binding.reimbmentButton.setEnabled(false);
            reimbent();
        }

    }

    protected void actionAlertDialog() {
        Request<JSONObject> jsonObjectRequest = NoHttp.createJsonObjectRequest(UrlUtils.mainUrl, RequestMethod.POST);
        jsonObjectRequest.setDefineRequestBodyForJson(listComProcess());
        CallServer.getRequestInstance().add(0, ResmentApplicationActivity.this, jsonObjectRequest, new HttpListener<JSONObject>() {
            @Override
            public void onSucceed(int what, Response<JSONObject> response) {

                String s = response.get().toString();
                Log.i("tah", "onSucceed: " + response.get().toString());
                final List<ProcessBean> list = initData(s);
                AlertDialog.Builder builder;
                LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                View layout = inflater.inflate(R.layout.process_list, null);
                ListView myListView = (ListView) layout.findViewById(R.id.process_listview);
                myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        binding.tvType.setText(list.get(i).getProcessName());
                        processid = list.get(i).getProcessId();
                        alertDialog.dismiss();
                    }
                });
                ProcessListAdapter adapter = new ProcessListAdapter(ResmentApplicationActivity.this, list);
                myListView.setAdapter(adapter);
                builder = new AlertDialog.Builder(ResmentApplicationActivity.this);
                builder.setView(layout);
                alertDialog = builder.create();
                alertDialog.show();
                Window dialogWindow = alertDialog.getWindow();
                WindowManager m = getWindowManager();
                Display d = m.getDefaultDisplay(); // 获取屏幕宽、高度
                WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 获取对话框当前的参数值
                p.height = (int) (d.getHeight() * 0.8); // 高度设置为屏幕的0.6，根据实际情况调整
                p.width = (int) (d.getWidth() * 0.8); // 宽度设置为屏幕的0.65，根据实际情况调整
                dialogWindow.setAttributes(p);
            }

            @Override
            public void onFailed(int what, String url, Object tag, String error, int resCode, long ms) {
            }
        });
    }

    private void reimbent() {
        Request<JSONObject> jsonObjectRequest = NoHttp.createJsonObjectRequest(UrlUtils.mainUrl, RequestMethod.POST);
        jsonObjectRequest.setDefineRequestBodyForJson(addOrder(pid));
        CallServer.getRequestInstance().add(0, ResmentApplicationActivity.this, jsonObjectRequest, new HttpListener<JSONObject>() {
            @Override
            public void onSucceed(int what, Response<JSONObject> response) {
                String s = response.get().toString();
                Log.i("tah", "onSucceed: " + response.get().toString());
                binding.reimbmentButton.setBackgroundResource(R.drawable.buttonblue);
                binding.reimbmentButton.setEnabled(true);
                DialogHb.showdialog(ResmentApplicationActivity.this, rtnData(s));
            }

            @Override
            public void onFailed(int what, String url, Object tag, String error, int resCode, long ms) {
                binding.reimbmentButton.setBackgroundResource(R.drawable.buttonblue);
                binding.reimbmentButton.setEnabled(true);
            }
        });
    }


    private List<ProcessBean> initData(String resutl) {
        List<ProcessBean> ecuInfoBeans = new ArrayList<>();
        if (resutl != null) {
            try {
                JSONObject jsonObject = new JSONObject(resutl);
                if (jsonObject.getString("resultType").equals("SUCCESS")) {
                    JSONObject object = new JSONObject(resutl);
                    JSONArray array = object.optJSONArray("data");
                    if (null != array) {
                        for (int i = 0; i < array.length(); i++) {
                            ProcessBean ecuInfoBean = new ProcessBean();
                            JSONObject obj = array.optJSONObject(i);
                            ecuInfoBean.setProcessName(obj.optString("NAME"));
                            ecuInfoBean.setProcessId(obj.optString("ID"));
                            ecuInfoBeans.add(ecuInfoBean);
                        }
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return ecuInfoBeans;
    }

    public String listComProcess() {
        String url = null;
        JSONObject json = new JSONObject();
        try {
            json.put("invoke", "listComProcess");
            JSONObject js = new JSONObject();
            js.put("comid", comid);
            js.put("type", "Android");
            js.put("athID", CustomApplication.athID);
            json.put("p", js);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        url = json.toString();
        Log.e("url", url);
        return url;
    }

    public String addOrder(String fpids) {
        String url = null;
        JSONObject json = new JSONObject();
        try {
            json.put("invoke", "addOrder");
            JSONObject js = new JSONObject();
            js.put("comid", comid);
            js.put("processid", processid);
            js.put("fpids", fpids);
            js.put("type", "Android");
            js.put("athID", CustomApplication.athID);
            json.put("p", js);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        url = json.toString();
        Log.e("url", url);
        return url;
    }


    private void showdialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(ResmentApplicationActivity.this);
        builder.setTitle("提示");
        builder.setMessage("请选择查询方式");
        builder.setPositiveButton("手动查询", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(ResmentApplicationActivity.this, GetComInvoiceActivity.class);
                intent.putExtra("comid", comid);
                startActivityForResult(intent, 77);
            }
        });
        builder.setNegativeButton("扫码查询", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(ResmentApplicationActivity.this, CaptureActivity.class);
                intent.putExtra("what",CaptureActivity.inquiry);
                startActivity(intent);
            }
        });
        builder.setNeutralButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 77 && resultCode == 2) {
            InvoiceInfoBean bean = new InvoiceInfoBean();
            bean = (InvoiceInfoBean) data.getSerializableExtra("invoice");
            if (list == null) {
                list = new ArrayList<>();
                list.add(bean);
                listRebmentAdapter = new ListRebmentAdapter(ResmentApplicationActivity.this, list);
                binding.listRebment.setAdapter(listRebmentAdapter);
            } else {
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getId().equals(bean.getId())) {
                        Toast.makeText(ResmentApplicationActivity.this, "该发票已经添加", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    if (i + 1 == list.size()) {
                        list.add(bean);
                        listRebmentAdapter.notifyDataSetChanged();
                        break;
                    }
                }
            }
        }
    }

    private String rtnData(String resutl) {
        String rtndata = null;
        if (resutl != null) {
            try {
                JSONObject jsonObject = new JSONObject(resutl);
                rtndata = jsonObject.getString("msg");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return rtndata;
    }
}
