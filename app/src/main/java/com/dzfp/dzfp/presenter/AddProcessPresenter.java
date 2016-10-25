package com.dzfp.dzfp.presenter;

import android.content.Context;
import android.util.Log;

import com.dzfp.dzfp.approval.ApprovalTask;
import com.dzfp.dzfp.control.AddProcessControl;
import com.dzfp.dzfp.model.ResultRoot;
import com.google.gson.Gson;
import com.yolanda.nohttp.rest.Response;

import org.json.JSONObject;


public class AddProcessPresenter implements AddProcessControl.Presenter {
    private AddProcessControl.View mView;
    private Context mContext;
    private ApprovalTask mApprovalTask;

    public AddProcessPresenter(AddProcessControl.View mView) {
        this.mView = mView;
        mContext = mView.getViewContext();
        mApprovalTask = new ApprovalTask(mContext);
    }

    @Override
    public void addProcess(String processname, String companyId, String ids) {
        Log.e("AddProcessPresenter", "companyId: " + companyId);
        Log.e("AddProcessPresenter", "ids: " + ids);
        mApprovalTask.addProcess(processname, companyId, ids);
        mApprovalTask.setApprovalTaskCallBack(new ApprovalTask.ApprovalTaskCallBack() {
            @Override
            public void onSucceed(int what, Response<JSONObject> response) {
                Log.i("System", "onSucceed: " + response.get().toString());
                ResultRoot mRoot = new Gson().fromJson(response.get().toString(), ResultRoot.class);
                mView.addProcessSuccess(mRoot.getMsg());
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
