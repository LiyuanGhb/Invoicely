package com.dzfp.dzfp.model;

import java.util.List;

/**
 * Created by Administrator on 2016/8/29.
 */

public class WfqdBean {


    /**
     * data : [{"ID":"03033670F3AB4BFCA80F809C868A1BAF","PROCESS_ITEMID":"F1B948B8D3C34331BCFF0AB6B96982EA","COMID":"C2B62E98048D498980A6568C14EAF083","PROCESSID":"E8BEF29C607341BF8EC345EDDCBCDFF0","CREATETIME":"2016-08-06 10:44:44","STATUS":0,"USERID":"F1B948B8D3C34331BCFF0AB6B96982EA"}]
     * msg : 成功
     * resultType : SUCCESS
     */

    private String msg;
    private String resultType;


    /**
     * ID : 03033670F3AB4BFCA80F809C868A1BAF              orderid
     * PROCESS_ITEMID : F1B948B8D3C34331BCFF0AB6B96982EA 流程当前结点
     * COMID : C2B62E98048D498980A6568C14EAF083         公司id
     * PROCESSID : E8BEF29C607341BF8EC345EDDCBCDFF0     流程id
     * CREATETIME : 2016-08-06 10:44:44                 创建时间
     * STATUS : 0 审批状态                              0 正在审批 1 审批没有通过 999成功
     * USERID : F1B948B8D3C34331BCFF0AB6B96982EA       用户id
     */

    private List<DataBean> data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String ID;
        private String PROCESS_ITEMID;
        private String COMID;
        private String PROCESSID;
        private String CREATETIME;
        private int STATUS;
        private String USERID;

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getPROCESS_ITEMID() {
            return PROCESS_ITEMID;
        }

        public void setPROCESS_ITEMID(String PROCESS_ITEMID) {
            this.PROCESS_ITEMID = PROCESS_ITEMID;
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
