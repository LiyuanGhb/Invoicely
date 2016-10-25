package com.dzfp.dzfp.approval;

import android.content.Context;

import com.dzfp.dzfp.nohttp.CallServer;
import com.dzfp.dzfp.nohttp.HttpListener;
import com.dzfp.dzfp.ui.requestParames.ReimburseParameter;
import com.dzfp.dzfp.ui.requestParames.StaffApplyParameter;
import com.dzfp.dzfp.ui.util.UrlUtils;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.Response;

import org.json.JSONObject;

public class ApprovalTask implements ApprovalInterface {
    private ApprovalTaskCallBack approvalTaskCallBack;
    private ReimburseParameter mReimburseParameter;
    private StaffApplyParameter mStaffApplyParameter;
    private Request<JSONObject> mRequest;
    private CallServer mCallServer;
    private Context mContext;

    public ApprovalTask(Context mContext) {
        this.mContext = mContext;
        initParameter();
    }

    private void initParameter() {
        mReimburseParameter = new ReimburseParameter();
        mStaffApplyParameter = new StaffApplyParameter();
        mRequest = NoHttp.createJsonObjectRequest(UrlUtils.mainUrl, RequestMethod.POST);
        mCallServer = CallServer.getRequestInstance();
    }

    @Override
    public void addProcess(String processname,String companyId, String ids) {
        mRequest.setDefineRequestBodyForJson(mReimburseParameter.addProcess(processname,companyId, ids));
        startRequest();
    }

    @Override
    public void listComProcess(String companyId) {
        mRequest.setDefineRequestBodyForJson(mReimburseParameter.listComProcess(companyId));
        startRequest();
    }

    @Override
    public void orderPass(String orderid, String bz) {
        mRequest.setDefineRequestBodyForJson(mReimburseParameter.orderPass(orderid, bz));
        startRequest();
    }

    @Override
    public void orderNotPass(String orderid, String bz) {
        mRequest.setDefineRequestBodyForJson(mReimburseParameter.orderPass(orderid, bz));
        startRequest();
    }

    @Override
    public void orderAuditOfMe(String companyId) {
        mRequest.setDefineRequestBodyForJson(mReimburseParameter.orderAuditOfMe(companyId));
        startRequest();
    }

    @Override
    public void listComUser(String companyId) {
        mRequest.setDefineRequestBodyForJson(mReimburseParameter.listComUser(companyId));
        startRequest();
    }

    @Override
    public void addOrder(String companyId, String processid, String fpids) {
        mRequest.setDefineRequestBodyForJson(mStaffApplyParameter.addOrder(companyId, processid, fpids));
        startRequest();
    }

    @Override
    public void invokeProcess(String orderid) {
        mRequest.setDefineRequestBodyForJson(mStaffApplyParameter.invokeProcess(orderid));
        startRequest();
    }

    @Override
    public void checkOrderDetail(String orderid) {
        mRequest.setDefineRequestBodyForJson(mStaffApplyParameter.checkOrderDetail(orderid));
        startRequest();
    }

    @Override
    public void orderOfMe(String companyId) {
        mRequest.setDefineRequestBodyForJson(mStaffApplyParameter.orderOfMe(companyId));
        startRequest();
    }

    private void startRequest() {
        mCallServer.add(0, mContext, mRequest, new HttpListener<JSONObject>() {
            @Override
            public void onSucceed(int what, Response<JSONObject> response) {
                approvalTaskCallBack.onSucceed(what, response);
            }

            @Override
            public void onFailed(int what, String url, Object tag, String error, int resCode, long ms) {
                approvalTaskCallBack.onFailed(what, url, tag, error, resCode, ms);
            }
        });
    }

    public interface ApprovalTaskCallBack {
        void onSucceed(int what, Response<JSONObject> response);

        void onFailed(int what, String url, Object tag, String error, int resCode, long ms);
    }

    public void setApprovalTaskCallBack(ApprovalTaskCallBack approvalTaskCallBack) {
        this.approvalTaskCallBack = approvalTaskCallBack;
    }
}
