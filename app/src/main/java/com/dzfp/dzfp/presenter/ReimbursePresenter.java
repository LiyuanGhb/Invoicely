package com.dzfp.dzfp.presenter;

import android.content.Context;
import android.util.Log;

import com.dzfp.dzfp.approval.ApprovalTask;
import com.dzfp.dzfp.control.ReimburseControl;
import com.dzfp.dzfp.model.OrderAuditOfMeBean;
import com.dzfp.dzfp.model.WfqdBean;
import com.google.gson.Gson;
import com.yolanda.nohttp.rest.Response;

import org.json.JSONObject;


public class ReimbursePresenter implements ReimburseControl.Presenter {
    private static final String TAG = "ReimbursePresenter";
    private ReimburseControl.View mView;

    private ApprovalTask mApprovalTask;

    public ReimbursePresenter(ReimburseControl.View mView) {
        this.mView = mView;
        mApprovalTask = new ApprovalTask(mView.getViewContext());
    }




    @Override
    public void orderAuditOfMe(String companyId) {
        mApprovalTask.orderAuditOfMe(companyId);
        mApprovalTask.setApprovalTaskCallBack(new ApprovalTask.ApprovalTaskCallBack() {
            @Override
            public void onSucceed(int what, Response<JSONObject> response) {
                Log.i(TAG, "onSucceed: orderAuditOfMe" + response.get());
                OrderAuditOfMeBean mOrderAuditOfMeBean = new Gson().fromJson(response.get().toString(), OrderAuditOfMeBean.class);
                if (mOrderAuditOfMeBean.getResultType().equals("SUCCESS")) {
                    mView.orderAuditOfMeSuccess(mOrderAuditOfMeBean.getData());
                } else {
                    mView.hintMessage(mOrderAuditOfMeBean.getMsg());
                }
            }

            @Override
            public void onFailed(int what, String url, Object tag, String error, int resCode, long ms) {
                mView.hintMessage(error);
            }
        });
    }


    @Override
    public void orderOfMe(String companyId) {
        mApprovalTask.orderOfMe(companyId);
        mApprovalTask.setApprovalTaskCallBack(new ApprovalTask.ApprovalTaskCallBack() {
            @Override
            public void onSucceed(int what, Response<JSONObject> response) {
                Log.i(TAG, "onSucceed: orderOfMe" + response.get());
                WfqdBean mWfqdBean = new Gson().fromJson(response.get().toString(), WfqdBean.class);
                if (mWfqdBean.getResultType().equals("SUCCESS")) {
                    mView.orderOfMeSuccess(mWfqdBean);
                }
            }

            @Override
            public void onFailed(int what, String url, Object tag, String error, int resCode, long ms) {
                mView.hintMessage(error);
            }
        });
    }

    @Override
    public void start() {

    }
}
