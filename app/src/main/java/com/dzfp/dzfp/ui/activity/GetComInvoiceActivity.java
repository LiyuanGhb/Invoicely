package com.dzfp.dzfp.ui.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.dzfp.dzfp.CustomApplication;
import com.dzfp.dzfp.R;
import com.dzfp.dzfp.databinding.GetcominvoiceBinding;
import com.dzfp.dzfp.model.InvoiceInfoBean;
import com.dzfp.dzfp.nohttp.CallServer;
import com.dzfp.dzfp.nohttp.HttpListener;
import com.dzfp.dzfp.ui.BaseActivity;
import com.dzfp.dzfp.ui.util.DialogHb;
import com.dzfp.dzfp.ui.util.UrlUtils;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2016/8/29.
 */

public class GetComInvoiceActivity extends BaseActivity implements View.OnClickListener {
    GetcominvoiceBinding binding;
    String comid;
    Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        intent = getIntent();
        comid = intent.getStringExtra("comid");
    }

    private void init() {
        binding = DataBindingUtil.setContentView(this, R.layout.getcominvoice);
        binding.setOnCliclistener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_back_cominvoice:
                finish();
                break;
            case R.id.baoxiao_button_invoice:
                if ("".equals(binding.fapiaodaimaEditInvoice.getText().toString()) || "".equals(binding.fapiaohaomaEditInvoice.getText().toString())) {
                    Toast.makeText(GetComInvoiceActivity.this, "请填写完善", Toast.LENGTH_SHORT).show();
                } else {
                    binding.baoxiaoButtonInvoice.setBackgroundResource(R.drawable.buttonblue_0);
                    binding.baoxiaoButtonInvoice.setEnabled(false);
                    insertInfo(binding.fapiaodaimaEditInvoice.getText().toString(), binding.fapiaohaomaEditInvoice.getText().toString());
                }
                break;
        }
    }

    private void insertInfo(String dm, String hm) {
        Request<JSONObject> jsonObjectRequest = NoHttp.createJsonObjectRequest(UrlUtils.mainUrl, RequestMethod.POST);
        jsonObjectRequest.setDefineRequestBodyForJson(getComInvoice(dm, hm));
        CallServer.getRequestInstance().add(0, GetComInvoiceActivity.this, jsonObjectRequest, new HttpListener<JSONObject>() {
            @Override
            public void onSucceed(int what, Response<JSONObject> response) {
                String s = response.get().toString();
                Log.i("tah", "onSucceed: " + response.get().toString());
                if (!"成功".equals(rtnData(s))) {
                    DialogHb.showdialog(GetComInvoiceActivity.this, rtnData(s));
                    binding.baoxiaoButtonInvoice.setBackgroundResource(R.drawable.buttonblue);
                    binding.baoxiaoButtonInvoice.setEnabled(true);
                } else {
                    initData(s);
                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("invoice", initData(s));
                    intent.putExtras(bundle);
                    setResult(2, intent);
                    finish();//结束当前的activity的生命周期
                }
            }

            @Override
            public void onFailed(int what, String url, Object tag, String error, int resCode, long ms) {
                binding.baoxiaoButtonInvoice.setBackgroundResource(R.drawable.buttonblue);
                binding.baoxiaoButtonInvoice.setEnabled(true);
            }
        });
    }


    public String getComInvoice(String dm, String hm) {
        String url = null;
        JSONObject json = new JSONObject();
        try {
            json.put("invoke", "getComInvoice");
            JSONObject js = new JSONObject();
            js.put("comid", comid);
            js.put("fphm", hm);
            js.put("fpdm", dm);
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

    private InvoiceInfoBean initData(String resutl) {
        InvoiceInfoBean invoiceInfoBean = new InvoiceInfoBean();
        if (resutl != null) {
            try {
                JSONObject jsonObject = new JSONObject(resutl);
                if (jsonObject.getString("resultType").equals("SUCCESS")) {
                    String arrays = jsonObject.getString("data");
                    Log.e("s", arrays);
                    JSONObject array = new JSONObject(arrays);
                    invoiceInfoBean.setFpdm(array.getString("FPDM"));
                    invoiceInfoBean.setFphm(array.getString("FPHM"));

                    String time = array.getString("KPRQ")+array.getString("KPSJ");
                    Date date = new SimpleDateFormat("yyyyMMDDHHmmss").parse(time);
                    time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
                    invoiceInfoBean.setTime(time);
                    invoiceInfoBean.setShf(array.getString("GHDWMC"));
                    invoiceInfoBean.setKpf(array.getString("XHDWMC"));
                    invoiceInfoBean.setMoney(array.getString("JSHJ"));
                    invoiceInfoBean.setId(array.getString("ID"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return invoiceInfoBean;
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
