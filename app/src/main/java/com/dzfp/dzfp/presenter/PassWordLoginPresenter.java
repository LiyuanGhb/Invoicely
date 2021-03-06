package com.dzfp.dzfp.presenter;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.dzfp.dzfp.ui.requestParames.LoginParameter;
import com.dzfp.dzfp.control.PassWordLoginControl;
import com.dzfp.dzfp.model.User.Data;
import com.dzfp.dzfp.model.User.UserMessage;
import com.dzfp.dzfp.model.User.UserRoot;
import com.dzfp.dzfp.nohttp.CallServer;
import com.dzfp.dzfp.nohttp.HttpListener;
import com.dzfp.dzfp.ui.util.UrlUtils;
import com.google.gson.Gson;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.Response;

import org.json.JSONObject;

import static android.content.ContentValues.TAG;

public class PassWordLoginPresenter implements PassWordLoginControl.Presenter {
    private PassWordLoginControl.View mView;
    private Context mContext;

    public PassWordLoginPresenter(PassWordLoginControl.View mView) {
        this.mView = mView;
        mContext = mView.getViewContext();
    }

    @Override
    public void PassWordLogin(String userName, String passWord) {
        if (TextUtils.isEmpty(userName)) {
            mView.hintMessage("用户名不能为空");
        } else if (TextUtils.isEmpty(passWord)) {
            mView.hintMessage("密码不能为空");
        } else {
            Request<JSONObject> mRequest = NoHttp.createJsonObjectRequest(UrlUtils.mainUrl, RequestMethod.POST);
            mRequest.setDefineRequestBodyForJson(new LoginParameter().loginByPwd(userName, passWord));
            CallServer.getRequestInstance().add(0, mContext, mRequest, new HttpListener<JSONObject>() {
                @Override
                public void onSucceed(int what, Response<JSONObject> response) {
                    Log.i(TAG, "onSucceed: " + response.get().toString());
                    UserRoot mRoot = new Gson().fromJson(response.get().toString(), UserRoot.class);

                    if (mRoot.getResultType().equals("SUCCESS")) {
                        Data mData = mRoot.getData();
                        // mData.setAthID("BF11C2E3BA0C4D0CB6831F8DECF81516");
                        UserMessage.newInstance().setUserBean(mData);
                        mView.PassWordForSuccessSuccess();
                    }else{
                        mView.OnHttpListenerFailed("");
                    }
                    mView.hintMessage(mRoot.getMsg());
                }

                @Override
                public void onFailed(int what, String url, Object tag, String error, int resCode, long ms) {
                    Log.e(TAG, "PassWordLoginPresenter_PassWordLogin_onFailed_url: " + url);
                    Log.e(TAG, "PassWordLoginPresenter_PassWordLogin_onFailed_tag: " + tag);
                    Log.e(TAG, "PassWordLoginPresenter_PassWordLogin_onFailed_error: " + error);
                    Log.e(TAG, "PassWordLoginPresenter_PassWordLogin_onFailed_resCode: " + resCode);
                    Log.e(TAG, "PassWordLoginPresenter_PassWordLogin_onFailed_ms: " + ms);
                    mView.OnHttpListenerFailed(error);
                }
            });
        }
    }

    @Override
    public void start() {

    }

}
