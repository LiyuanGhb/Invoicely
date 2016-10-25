package com.dzfp.dzfp.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dzfp.dzfp.CustomApplication;
import com.dzfp.dzfp.R;
import com.dzfp.dzfp.model.EcuInfoBean;
import com.dzfp.dzfp.ui.BaseActivity;
import com.dzfp.dzfp.ui.adapter.MyecuItemAdapter;
import com.dzfp.dzfp.ui.fragment.CompanyFragment;
import com.dzfp.dzfp.ui.util.DialogHb;
import com.dzfp.dzfp.ui.util.MineUtil;
import com.dzfp.dzfp.ui.util.NetWorkUtil;
import com.dzfp.dzfp.ui.util.UrlUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/17.
 */

public class SetCompanyActivity extends BaseActivity {
    ListView listView;
    public static Activity activity;
    RelativeLayout back_rl;
    TextView textView;
    public static int what=0;

    List<EcuInfoBean> ecuInfoBeans;
    MyecuItemAdapter myecuItemAdapter;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            ecuInfoBeans = (List<EcuInfoBean>) msg.obj;
            Log.i("SetComActivityHandler", ecuInfoBeans.size() + "Count");
            if(ecuInfoBeans.size()==0){
                DialogHb.showdialogclick(SetCompanyActivity.this,"您还没有加入过企业");
            }else{
                myecuItemAdapter = new MyecuItemAdapter(ecuInfoBeans, SetCompanyActivity.this,what);
                listView.setAdapter(myecuItemAdapter);
            }
        }
    };

    int intentType;
    String adminType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myecucom);
        init();
    }

    private void init() {
        listView = (ListView) findViewById(R.id.myecu_list);
        back_rl = (RelativeLayout) findViewById(R.id.rl_back_myecu);
        textView = (TextView) findViewById(R.id.tx_gscm_myecu);
        activity = this;
        /*初始化请求类型默认为操作的人员管理*/
        intentType = getIntent().getIntExtra(CompanyFragment.INTENT_TYPE, CompanyFragment.RYGL_REQUEST);
        if (intentType == CompanyFragment.RYGL_REQUEST) {
            textView.setText("人员管理");
            adminType = "1";
        } else if(intentType == CompanyFragment.BXSP_REQUEST){
            textView.setText("报销审批");
            adminType = "0";
        }else if(intentType == CompanyFragment.BXSQ_REQUEST){
            textView.setText("报销申请");
            adminType = "0";
        }


        if(NetWorkUtil.isNetworkConnected(SetCompanyActivity.this)){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String resutl = MineUtil.postHttp(UrlUtils.mainUrl, fincomList(adminType));
                    ecuInfoBeans = rtnEcuInfoBean(resutl);
                    Message message = new Message();
                    message.obj = ecuInfoBeans;
                    handler.sendMessage(message);
                }
            }).start();
        }else{
            NetWorkUtil.showNoNetWorkDlg(SetCompanyActivity.this);
        }

        back_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                switch (intentType) {
                    case CompanyFragment.BXSQ_REQUEST:
                        /*报销申请*/
                        Intent mIntents = new Intent(SetCompanyActivity.this, ResmentApplicationActivity.class);
                        mIntents.putExtra("comid", ecuInfoBeans.get(position).getCustinfo());
                        startActivity(mIntents);
                        break;
                    case CompanyFragment.BXSP_REQUEST:
                        /*报销审批*/
                        Log.d("CompanyFragment", ecuInfoBeans.get(position).getCustinfo());
                        Intent mIntent = new Intent(SetCompanyActivity.this, ReimburseActivity.class);
                        mIntent.putExtra("comid", ecuInfoBeans.get(position).getCustinfo());
                        if(ecuInfoBeans.get(position).getQyyhid().equals(CustomApplication.userId)){
                            mIntent.putExtra("admin",0);
                        }else{
                            mIntent.putExtra("admin",1);
                        }
                        startActivity(mIntent);
                        break;
                    case CompanyFragment.RYGL_REQUEST:
                        /*人员管理*/
                        Intent intent = new Intent(SetCompanyActivity.this, ChecktoComActyity.class);
                        intent.putExtra("comid", ecuInfoBeans.get(position).getCustinfo());
                        startActivity(intent);
                        break;
                }
            }
        });
    }


    /*获取自己的公司名片*/
    public String fincomList(String admin) {
        String url = null;
        JSONObject json = new JSONObject();
        try {
            json.put("invoke", "getMyCom");
            JSONObject js = new JSONObject();
            js.put("type", "Android");
            js.put("athID", CustomApplication.athID);
            js.put("admin", admin);
            json.put("p", js);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        url = json.toString();
        Log.e("phone", url);
        return url;
    }

    private List<EcuInfoBean> rtnEcuInfoBean(String resutl) {
        ecuInfoBeans = new ArrayList<>();
        if (resutl != null) {
            try {
                JSONObject jsonObject = new JSONObject(resutl);
                if (jsonObject.getString("resultType").equals("SUCCESS")) {
                    JSONObject object = new JSONObject(resutl);
                    JSONArray array = object.optJSONArray("data");
                    Log.i("ListArray", array.length() + "Count");
                    if (null != array) {
                        for (int i = 0; i < array.length(); i++) {
                            EcuInfoBean ecuInfoBean = new EcuInfoBean();
                            JSONObject obj = array.optJSONObject(i);
                            ecuInfoBean.setEcuName(obj.optString("COMNAME"));
                            ecuInfoBean.setQyyhid(obj.optString("ADMINID"));
                            ecuInfoBean.setCustinfo(obj.optString("COMID"));
                            ecuInfoBean.setUPLOAD(obj.optString("UPLOAD"));
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

}
