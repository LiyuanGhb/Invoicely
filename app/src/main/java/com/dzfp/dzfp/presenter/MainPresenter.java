package com.dzfp.dzfp.presenter;

import android.content.Context;
import android.util.Log;

import com.dzfp.dzfp.control.MainAtyControl;
import com.dzfp.dzfp.model.Card.CompanyRoot;
import com.dzfp.dzfp.nohttp.CallServer;
import com.dzfp.dzfp.nohttp.HttpListener;
import com.dzfp.dzfp.ui.requestParames.CompanyParameter;
import com.dzfp.dzfp.ui.util.UrlUtils;
import com.google.gson.Gson;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.Response;

import org.json.JSONObject;

import static android.content.ContentValues.TAG;


public class MainPresenter implements MainAtyControl.Presenter {
    private MainAtyControl.View mView;
    private CompanyParameter mParameter;
    private Context mContext;

    public MainPresenter(MainAtyControl.View mView) {
        this.mView = mView;
        mParameter = new CompanyParameter();
        mContext = mView.getViewContext();
    }

    @Override
    public void start() {

    }

    @Override
    public void showFragment(int index, String title) {
        mView.initFragmentAndTitle(index, title);
    }

    @Override
    public void queryCompany() {
        Request<JSONObject> mRequest = NoHttp.createJsonObjectRequest(UrlUtils.mainUrl, RequestMethod.POST);
        mRequest.setDefineRequestBodyForJson(mParameter.fincomList());
        CallServer.getRequestInstance().add(0, mContext, mRequest, new HttpListener<JSONObject>() {
            @Override
            public void onSucceed(int what, Response<JSONObject> response) {
                Log.e(TAG, "queryCompanySucceed: " + response.get());
                CompanyRoot mCompanyRoot = new Gson().fromJson(response.get().toString(), CompanyRoot.class);
                if (mCompanyRoot.getResultType().equals("SUCCESS")) {
                    mView.queryCompanySuccess(mCompanyRoot);
                }
            }

            @Override
            public void onFailed(int what, String url, Object tag, String error, int resCode, long ms) {
                Log.e(TAG, "HomeFragmentPresenter_queryCompany_onFailed_url: " + url);
                Log.e(TAG, "HomeFragmentPresenter_queryCompany_onFailed_tag: " + tag);
                Log.e(TAG, "HomeFragmentPresenter_queryCompany_onFailed_error: " + error);
                Log.e(TAG, "HomeFragmentPresenter_queryCompany_onFailed_resCode: " + resCode);
                Log.e(TAG, "HomeFragmentPresenter_queryCompany_onFailed_ms: " + ms);
            }
        });
    }

    @Override
    public void queryCompanyTitle() {
        Request<JSONObject> mRequest = NoHttp.createJsonObjectRequest(UrlUtils.mainUrl, RequestMethod.POST);
        mRequest.setDefineRequestBodyForJson(mParameter.fincomList());
        CallServer.getRequestInstance().add(0, mContext, mRequest, new HttpListener<JSONObject>() {
            @Override
            public void onSucceed(int what, Response<JSONObject> response) {
                Log.e(TAG, "queryCompanySucceed: " + response.get());
                CompanyRoot mCompanyRoot = new Gson().fromJson(response.get().toString(), CompanyRoot.class);
                if (mCompanyRoot.getResultType().equals("SUCCESS")) {
                    mView.queryCompanyTitleSuccess(mCompanyRoot);
                }
            }

            @Override
            public void onFailed(int what, String url, Object tag, String error, int resCode, long ms) {
                Log.e(TAG, "HomeFragmentPresenter_queryCompany_onFailed_url: " + url);
                Log.e(TAG, "HomeFragmentPresenter_queryCompany_onFailed_tag: " + tag);
                Log.e(TAG, "HomeFragmentPresenter_queryCompany_onFailed_error: " + error);
                Log.e(TAG, "HomeFragmentPresenter_queryCompany_onFailed_resCode: " + resCode);
                Log.e(TAG, "HomeFragmentPresenter_queryCompany_onFailed_ms: " + ms);
            }
        });
    }
}
