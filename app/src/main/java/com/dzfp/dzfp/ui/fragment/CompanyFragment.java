package com.dzfp.dzfp.ui.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dzfp.dzfp.CustomApplication;
import com.dzfp.dzfp.R;
import com.dzfp.dzfp.databinding.CompanyFragmentBinding;
import com.dzfp.dzfp.model.EcuInfoBean;
import com.dzfp.dzfp.ui.activity.AuthComActivity;
import com.dzfp.dzfp.ui.activity.ChecktoComActyity;
import com.dzfp.dzfp.ui.activity.MainAty;
import com.dzfp.dzfp.ui.activity.ReimburseActivity;
import com.dzfp.dzfp.ui.activity.ResmentApplicationActivity;
import com.dzfp.dzfp.ui.activity.SetCompanyActivity;
import com.dzfp.dzfp.ui.adapter.MyecuItemAdapter;
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
 * Created by Administrator on 2016/8/6.
 */
public class CompanyFragment extends Fragment implements View.OnClickListener, MainAty.showCompanyCallBack {
    private CompanyFragmentBinding mBinding;
    public static final String INTENT_TYPE = "intent_type"; //跳转类型
    public static final int BXSQ_REQUEST = 0; //报销申请
    public static final int BXSP_REQUEST = 1;//报销审批
    public static final int RYGL_REQUEST = 2; //人员管理
    private Intent mIntent;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof MainAty) {
            ((MainAty) activity).setShowCompanyCallBack(this);
        }
    }

    List<EcuInfoBean> ecuInfoBeans;
    MyecuItemAdapter myecuItemAdapter;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            ecuInfoBeans = (List<EcuInfoBean>) msg.obj;
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.bind(inflater.inflate(R.layout.company_fragment, container, false));
        init();
        return mBinding.getRoot();
    }

    private void init() {
        mBinding.setClickLitsener(this);
        mIntent = new Intent(getActivity(), SetCompanyActivity.class);
        selectMyCompany();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cpy_bxsq_rl:
                Intent mIntents = new Intent(getActivity(), ResmentApplicationActivity.class);
                mIntents.putExtra("comid", ecuInfoBeans.get(0).getCustinfo());
                startActivity(mIntents);
                break;
            case R.id.cpy_bxsp_rl:
                Intent mIntent = new Intent(getActivity(), ReimburseActivity.class);
                mIntent.putExtra("comid", ecuInfoBeans.get(0).getCustinfo());
                mIntent.putExtra("admin", 0);
                startActivity(mIntent);
                break;
            case R.id.cpy_qyfp_rl:
                //企业发票
                DialogHb.showdialog(getActivity(), "功能暂未开放");
                break;
            case R.id.cpy_rysh_rl:
                //入企申请
                startActivity(new Intent(getActivity(), AuthComActivity.class));
                break;
            case R.id.cpy_gnsz_rl:
                //人员管理
                Intent intent = new Intent(getActivity(), ChecktoComActyity.class);
                intent.putExtra("comid", ecuInfoBeans.get(0).getCustinfo());
                startActivity(intent);
                break;
        }
    }

    @Override
    public void showCompany(String companyName, boolean isCompanyManager) {
        Log.d("CompanyFragment", "isCompanyManager:" + isCompanyManager);
        if(isCompanyManager){
            /*管理员*/
            mBinding.cpyBxsqRl.setVisibility(View.VISIBLE);
            mBinding.cpyBxspRl.setVisibility(View.VISIBLE);
            mBinding.cpyQyfpRl.setVisibility(View.VISIBLE);
            mBinding.cpyRyshRl.setVisibility(View.VISIBLE);
            mBinding.cpyGnszRl.setVisibility(View.VISIBLE);
        }else{
            /*普通用户*/
            mBinding.cpyBxsqRl.setVisibility(View.VISIBLE);
            mBinding.cpyBxspRl.setVisibility(View.GONE);
            mBinding.cpyQyfpRl.setVisibility(View.VISIBLE);
            mBinding.cpyRyshRl.setVisibility(View.VISIBLE);
            mBinding.cpyGnszRl.setVisibility(View.GONE);
        }
    }


    String adminType = "0";

    private void selectMyCompany() {
        if (NetWorkUtil.isNetworkConnected(getActivity())) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Log.d("CompanyFragment", fincomList(adminType));
                    String resutl = MineUtil.postHttp(UrlUtils.mainUrl, fincomList(adminType));
                    ecuInfoBeans = rtnEcuInfoBean(resutl);
                    Message message = new Message();
                    message.obj = ecuInfoBeans;
                    handler.sendMessage(message);
                }
            }).start();
        } else {
            NetWorkUtil.showNoNetWorkDlg(getActivity());
        }
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
