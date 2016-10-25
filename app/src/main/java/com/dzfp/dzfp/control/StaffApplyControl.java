package com.dzfp.dzfp.control;

import com.dzfp.dzfp.BasePresenter;
import com.dzfp.dzfp.BaseView;
import com.yolanda.nohttp.rest.Response;

import org.json.JSONObject;

public class StaffApplyControl {

    public interface View extends BaseView {
        void addOrderSuccess(int what, Response<JSONObject> response);

        void invokeProcessSuccess(int what, Response<JSONObject> response);

        void checkOrderDetailSuccess(int what, Response<JSONObject> response);

        void listComProcessSuccess(int what, Response<JSONObject> response);

        void orderOfMeSuccess(int what, Response<JSONObject> response);

        void RequestFailed(int what, String url, Object tag, String error, int resCode, long ms);
    }

    public interface Presenter extends BasePresenter {
        /**
         * 报销申请
         *
         * @param companyId 公司id
         * @param processid 报销流程id
         * @param fpids     发票集合
         */
        void addOrder(String companyId, String processid, String fpids);

        /**
         * 取消申请
         *
         * @param orderid 流程id
         */
        void invokeProcess(String orderid);

        /**
         * 查看报销申请流程详情
         *
         * @param orderid 流程id
         */
        void checkOrderDetail(String orderid);

        /**
         * 获取公司报销流程列表
         *
         * @param companyId 公司id
         */
        void listComProcess(String companyId);

        /**
         * 我发的申请
         *
         * @param companyId 公司id
         */
        void orderOfMe(String companyId);
    }
}
