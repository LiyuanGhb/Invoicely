package com.dzfp.dzfp.presenter;

import android.util.Log;

import com.dzfp.dzfp.approval.ApprovalTask;
import com.dzfp.dzfp.control.SQXPControl;
import com.dzfp.dzfp.model.BxlcxqBean;
import com.dzfp.dzfp.model.ResultRoot;
import com.google.gson.Gson;
import com.yolanda.nohttp.rest.Response;

import org.json.JSONObject;


public class SQXPPresenter implements SQXPControl.Presenter {
    private SQXPControl.View mView;
    private ApprovalTask mApprovalTask;

    public SQXPPresenter(SQXPControl.View mView) {
        this.mView = mView;
        mApprovalTask = new ApprovalTask(mView.getViewContext());
    }

    @Override
    public void checkOrderDetail(String orderId) {
        mApprovalTask.checkOrderDetail(orderId);
        mApprovalTask.setApprovalTaskCallBack(new ApprovalTask.ApprovalTaskCallBack() {
            @Override
            public void onSucceed(int what, Response<JSONObject> response) {
                Log.i("System", "onSucceed: " + response.get().toString());
                BxlcxqBean mBean = new Gson().fromJson(response.get().toString(), BxlcxqBean.class);
                if (mBean.getResultType().equals("SUCCESS")) {
                    if (mBean.getData().getOrder_detail().size() > 0)
                        mView.checkOrderDetailSuccess(mBean);
                } else {
                    mView.hintMessage(mBean.getMsg());
                }
            }

            @Override
            public void onFailed(int what, String url, Object tag, String error, int resCode, long ms) {
                mView.hintMessage(error);
            }
        });
    }

    @Override
    public void orderPass(String orderid, String bz) {
        mApprovalTask.orderPass(orderid, bz);
        mApprovalTask.setApprovalTaskCallBack(new ApprovalTask.ApprovalTaskCallBack() {
            @Override
            public void onSucceed(int what, Response<JSONObject> response) {
                ResultRoot mRoot = new Gson().fromJson(response.get().toString(), ResultRoot.class);
                if (mRoot.getResultType().equals("SUCCESS")) {
                    mView.orderPassSuccess();
                }
                mView.hintMessage(mRoot.getMsg());
                Log.i("System", "orderPass: " + response.get().toString());
            }

            @Override
            public void onFailed(int what, String url, Object tag, String error, int resCode, long ms) {
                mView.hintMessage(error);
            }
        });
    }

    @Override
    public void orderNotPass(String orderid, String bz) {
        mApprovalTask.orderNotPass(orderid, bz);
        mApprovalTask.setApprovalTaskCallBack(new ApprovalTask.ApprovalTaskCallBack() {
            @Override
            public void onSucceed(int what, Response<JSONObject> response) {
                ResultRoot mRoot = new Gson().fromJson(response.get().toString(), ResultRoot.class);
                if (mRoot.getResultType().equals("SUCCESS")) {
                    mView.orderNotPassSuccess();
                }
                mView.hintMessage(mRoot.getMsg());
                Log.i("System", "orderNotPass: " + response.get().toString());
            }

            @Override
            public void onFailed(int what, String url, Object tag, String error, int resCode, long ms) {
                mView.hintMessage(error);
            }
        });
    }

    @Override
    public void invokeProcess(String orderid) {
        mApprovalTask.invokeProcess(orderid);
        mApprovalTask.setApprovalTaskCallBack(new ApprovalTask.ApprovalTaskCallBack() {
            @Override
            public void onSucceed(int what, Response<JSONObject> response) {
                ResultRoot mRoot = new Gson().fromJson(response.get().toString(), ResultRoot.class);
                if (mRoot.getResultType().equals("SUCCESS")) {
                    mView.invokeProcessSuccess();
                }
                mView.hintMessage(mRoot.getMsg());
                Log.i("System", "orderNotPass: " + response.get().toString());
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
