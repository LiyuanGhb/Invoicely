package com.dzfp.dzfp.model;

import java.util.List;

/**
 * Created by Administrator on 2016/8/30.
 */

public class OrderAuditOfMeBean {

    /**
     * data : [{"PROCESS_ITEMID":"F1B948B8D3C34331BCFF0AB6B96982EA","ID":"889FC4426F4F49F0828EE7929DF69F0D","COMID":"C2B62E98048D498980A6568C14EAF083","PROCESSID":"E8BEF29C607341BF8EC345EDDCBCDFF0","CREATETIME":"2016-08-30 15:11:37","STATUS":0,"USERID":"F1B948B8D3C34331BCFF0AB6B96982EA"},{"PROCESS_ITEMID":"F1B948B8D3C34331BCFF0AB6B96982EA","ID":"20F0C560EFCF4790BF79421B639F1ADF","COMID":"C2B62E98048D498980A6568C14EAF083","PROCESSID":"E8BEF29C607341BF8EC345EDDCBCDFF0","CREATETIME":"2016-08-30 13:35:41","STATUS":0,"USERID":"F1B948B8D3C34331BCFF0AB6B96982EA"}]
     * resultType : SUCCESS
     * msg : 成功
     */

    private String resultType;
    private String msg;
    /**
     * PROCESS_ITEMID : F1B948B8D3C34331BCFF0AB6B96982EA
     * ID : 889FC4426F4F49F0828EE7929DF69F0D
     * COMID : C2B62E98048D498980A6568C14EAF083
     * PROCESSID : E8BEF29C607341BF8EC345EDDCBCDFF0
     * CREATETIME : 2016-08-30 15:11:37
     * STATUS : 0
     * USERID : F1B948B8D3C34331BCFF0AB6B96982EA
     */

    private List<DataBean> data;

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String PROCESS_ITEMID;
        private String ID;
        private String COMID;
        private String PROCESSID;
        private String CREATETIME;
        private int STATUS;
        private String USERID;

        public String getPROCESS_ITEMID() {
            return PROCESS_ITEMID;
        }

        public void setPROCESS_ITEMID(String PROCESS_ITEMID) {
            this.PROCESS_ITEMID = PROCESS_ITEMID;
        }

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getCOMID() {
            return COMID;
        }

        public void setCOMID(String COMID) {
            this.COMID = COMID;
        }

        public String getPROCESSID() {
            return PROCESSID;
        }

        public void setPROCESSID(String PROCESSID) {
            this.PROCESSID = PROCESSID;
        }

        public String getCREATETIME() {
            return CREATETIME;
        }

        public void setCREATETIME(String CREATETIME) {
            this.CREATETIME = CREATETIME;
        }

        public int getSTATUS() {
            return STATUS;
        }

        public void setSTATUS(int STATUS) {
            this.STATUS = STATUS;
        }

        public String getUSERID() {
            return USERID;
        }

        public void setUSERID(String USERID) {
            this.USERID = USERID;
        }
    }
}
