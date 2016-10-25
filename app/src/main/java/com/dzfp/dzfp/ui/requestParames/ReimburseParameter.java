package com.dzfp.dzfp.ui.requestParames;

import android.util.Log;


import com.dzfp.dzfp.CustomApplication;

import org.json.JSONException;
import org.json.JSONObject;

public class ReimburseParameter {
    /**
     * 创建报销流程
     *
     * @param processname  报销流程名称
     * @param comid 公司id
     * @param ids   审核员的id，多个审核员用userid拼接
     * @return
     */
    public String addProcess(String processname, String comid, String ids) {
        String url;
        JSONObject json = new JSONObject();
        try {
            json.put("invoke", "addProcess");
            JSONObject js = new JSONObject();
            js.put("type", "Android");
            js.put("athID", CustomApplication.athID);
            js.put("processname", processname);
            js.put("comid", comid);
            js.put("ids", ids);
            json.put("p", js);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        url = json.toString();
        Log.e("url", url);
        return url;
    }

    /**
     * 获取公司人员列表
     *
     * @param comid 公司id
     * @return
     */
    public String listComUser(String comid) {
        String url;
        JSONObject json = new JSONObject();
        try {
            json.put("invoke", "listComUser");
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


    /**
     * 同意报销
     *
     * @param orderid 报销orderid
     * @param bz      备注
     * @return
     */
    public String orderPass(String orderid, String bz) {
        String url;
        JSONObject json = new JSONObject();
        try {
            json.put("invoke", "orderPass");
            JSONObject js = new JSONObject();
            js.put("athID", CustomApplication.athID);
            js.put("type", "Android");
            js.put("orderid", orderid);
            js.put("bz", bz);
            json.put("p", js);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        url = json.toString();
        Log.e("url", url);
        return url;
    }

    /**
     * 不同意报销
     *
     * @param orderid 报销orderid
     * @param bz      备注
     * @return
     */
    public String orderNotPass(String orderid, String bz) {
        String url;
        JSONObject json = new JSONObject();
        try {
            json.put("invoke", "orderNotPass");
            JSONObject js = new JSONObject();
            js.put("athID", CustomApplication.athID);
            js.put("type", "Android");
            js.put("orderid", orderid);
            js.put("bz", bz);
            json.put("p", js);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        url = json.toString();
        Log.e("url", url);
        return url;
    }

    /**
     * 待我审评
     *
     * @param comid 公司id
     * @return
     */
    public String orderAuditOfMe(String comid) {
        String url;
        JSONObject json = new JSONObject();
        try {
            json.put("invoke", "orderAuditOfMe");
            JSONObject js = new JSONObject();
            js.put("athID", CustomApplication.athID);
            js.put("type", "Android");
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
