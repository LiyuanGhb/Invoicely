package com.dzfp.dzfp.ui.requestParames;
import android.util.Log;

import com.dzfp.dzfp.CustomApplication;

import org.json.JSONException;
import org.json.JSONObject;

public class StaffApplyParameter {
    /**
     * 报销申请
     *
     * @param comid 公司id
     * @param processid  报销流程id
     * @param fpids 发票
     * @return
     */
    public String addOrder(String comid,String processid,String fpids) {
        String url;
        JSONObject json = new JSONObject();
        try {
            json.put("invoke", "addOrder");
            JSONObject js = new JSONObject();
            js.put("type", "Android");
            js.put("athID", CustomApplication.athID);
            js.put("comid", comid);
            js.put("processid", processid);
            js.put("fpids", fpids);
            json.put("p", js);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        url = json.toString();
        Log.e("url", url);
        return url;
    }

    /**
     * 获取公司流程列表
     *
     * @param comid 公司id
     * @return
     */
    public String listComProcess(String comid) {
        String url;
        JSONObject json = new JSONObject();
        try {
            json.put("invoke", "listComProcess");
            JSONObject js = new JSONObject();
            js.put("comid", comid);
            js.put("type", "Android");
            js.put("athID", CustomApplication.athID);
            json.put("p", js);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        url = json.toString();
        Log.e("url", url);
        return url;
    }

    /**
     * 查看审批流程详情
     *
     * @param orderid
     * @return
     */
    public String checkOrderDetail(String orderid) {
        String url;
        JSONObject json = new JSONObject();
        try {
            json.put("invoke", "checkOrderDetail");
            JSONObject js = new JSONObject();
            js.put("type", "Android");
            js.put("athID", CustomApplication.athID);
            js.put("orderid", orderid);
            json.put("p", js);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        url = json.toString();
        Log.e("url", url);
        return url;
    }

    /**
     * 撤销报销
     *
     * @param orderid
     * @return
     */
    public String invokeProcess(String orderid) {
        String url;
        JSONObject json = new JSONObject();
        try {
            json.put("invoke", "invokeProcess");
            JSONObject js = new JSONObject();
            js.put("type", "Android");
            js.put("athID", CustomApplication.athID);
            js.put("orderid", orderid);
            json.put("p", js);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        url = json.toString();
        Log.e("url", url);
        return url;
    }

    /**
     * 我发起的审评
     *
     * @param comid    公司id
     * @return
     */
    public String orderOfMe(String comid) {
        String url;
        JSONObject json = new JSONObject();
        try {
            json.put("invoke", "orderOfMe");
            JSONObject js = new JSONObject();
            js.put("type", "Android");
            js.put("athID", CustomApplication.athID);
            js.put("comid", comid);
            json.put("p", js);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        url = json.toString();
        Log.e("url", url);
        return url;
    }
}
