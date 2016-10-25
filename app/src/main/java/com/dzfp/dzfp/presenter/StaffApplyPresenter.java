package com.dzfp.dzfp.presenter;

import android.content.Context;

import com.dzfp.dzfp.control.StaffApplyControl;
import com.dzfp.dzfp.nohttp.CallServer;
import com.dzfp.dzfp.nohttp.HttpListener;
import com.dzfp.dzfp.ui.requestParames.StaffApplyParameter;
import com.dzfp.dzfp.ui.util.UrlUtils;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.Response;

import org.json.JSONObject;

public class StaffApplyPresenter implements StaffApplyControl.Presenter {
    private StaffApplyControl.View mView;
    private StaffApplyParameter mApplyParameter;
    private Request<JSONObject> mRequest;
    private CallServer mCallServer;
    private Context mContext;

    public StaffApplyPresenter(StaffApplyControl.View mView) {
        this.mView = mView;
        mApplyParameter = new StaffApplyParameter();
        mRequest = NoHttp.createJsonObjectRequest(UrlUtils.mainUrl, RequestMethod.POST);
        mCallServer = CallServer.getRequestInstance();
        this.mContext = mView.getViewContext();
    }

    @Override
    public void addOrder(String companyId, String processid, String fpids) {
        mRequest.setDefineRequestBodyForJson(mApplyParameter.addOrder(companyId, processid, fpids));
        mCallServer.add(0, mContext, mRequest, new HttpListener<JSONObject>() {
            @Override
            public void onSucceed(int what, Response<JSONObject> response) {
                mView.addOrderSuccess(what, response);
            }

            @Override
            public void onFailed(int what, String url, Object tag, String error, int resCode, long ms) {
                mView.RequestFailed(what, url, tag, error, resCode, ms);
            }
        });
    }

    @Override
    public void invokeProcess(String orderid) {
        mRequest.setDefineRequestBodyForJson(mApplyParameter.invokeProcess(orderid));
        mCallServer.add(0, mContext, mRequest, new HttpListener<JSONObject>() {
            @Override
            public void onSucceed(int what, Response<JSONObject> response) {
                mView.invokeProcessSuccess(what, response);
            }

            @Override
            public void onFailed(int what, String url, Object tag, String error, int resCode, long ms) {
                mView.RequestFailed(what, url, tag, error, resCode, ms);
            }
        });
    }

    @Override
    public void checkOrderDetail(String orderid) {
        mRequest.setDefineRequestBodyForJson(mApplyParameter.checkOrderDetail(orderid));
        mCallServer.add(0, mContext, mRequest, new HttpListener<JSONObject>() {
            @Override
            public void onSucceed(int what, Response<JSONObject> response) {
                mView.checkOrderDetailSuccess(what, response);
            }

            @Override
            public void onFailed(int what, String url, Object tag, String error, int resCode, long ms) {
                mView.RequestFailed(what, url, tag, error, resCode, ms);
            }
        });
    }

    @Override
    public void listComProcess(String companyId) {
        mRequest.setDefineRequestBodyForJson(mApplyParameter.listComProcess(companyId));
        mCallServer.add(0, mContext, mRequest, new HttpListener<JSONObject>() {
            @Override
            public void onSucceed(int what, Response<JSONObject> response) {
                mView.listComProcessSuccess(what, response);
            }

            @Override
            public void onFailed(int what, String url, Object tag, String error, int resCode, long ms) {
                mView.RequestFailed(what, url, tag, error, resCode, ms);
            }
        });
    }

    @Override
    public void orderOfMe(String companyId) {
        mRequest.setDefineRequestBodyForJson(mApplyParameter.orderOfMe(companyId));
        mCallServer.add(0, mContext, mRequest, new HttpListener<JSONObject>() {
            @Override
            public void onSucceed(int what, Response<JSONObject> response) {
                mView.orderOfMeSuccess(what, response);
            }

            @Override
            public void onFailed(int what, String url, Object tag, String error, int resCode, long ms) {
                mView.RequestFailed(what, url, tag, error, resCode, ms);
            }
        });
    }

    @Override
    public void start() {

    }
}
