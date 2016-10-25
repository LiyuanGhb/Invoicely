package com.dzfp.dzfp.presenter;

import android.content.Context;

import com.dzfp.dzfp.approval.ApprovalTask;
import com.dzfp.dzfp.control.SelectStaffControl;
import com.dzfp.dzfp.model.CompanyUserListBean;
import com.google.gson.Gson;
import com.yolanda.nohttp.rest.Response;

import org.json.JSONObject;

/**
 * Created by Administrator on 2016/8/26.
 */

public class SelectStaffPresenter implements SelectStaffControl.Presenter {
    private Context mContext;
    private SelectStaffControl.View mView;
    private ApprovalTask mApprovalTask;

    public SelectStaffPresenter(SelectStaffControl.View mView) {
        this.mView = mView;
        mContext = mView.getViewContext();
        mApprovalTask = new ApprovalTask(mContext);
    }

    @Override
    public void listComUser(String companyId) {
        mApprovalTask.listComUser(companyId);
        mApprovalTask.setApprovalTaskCallBack(new ApprovalTask.ApprovalTaskCallBack() {
            @Override
            public void onSucceed(int what, Response<JSONObject> response) {
                CompanyUserListBean mCompanyUserListBean = new Gson().fromJson(response.get().toString(), CompanyUserListBean.class);
                if (mCompanyUserListBean.getResultType().equals("SUCCESS")) {
                    mView.listComUserSuccess(mCompanyUserListBean.getData());
                }else{
                    mView.hintMessage(mCompanyUserListBean.getMsg());
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
