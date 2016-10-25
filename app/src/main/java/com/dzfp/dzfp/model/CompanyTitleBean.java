package com.dzfp.dzfp.model;

import java.util.List;

public class CompanyTitleBean {
    private String resultType;
    private String msg;
    /**
     * NAME : 陕西森弗高科实业有限公司1 详细
     * ADMINID : F1B948B8D3C34331BCFF0AB6B96982EA
     * STATUS : 1
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

    public class DataBean {
        private String NAME;
        private String ADMINID;
        private String STATUS;
        private String USERID;

        public String getNAME() {
            return NAME;
        }

        public void setNAME(String NAME) {
            this.NAME = NAME;
        }

        public String getADMINID() {
            return ADMINID;
        }

        public void setADMINID(String ADMINID) {
            this.ADMINID = ADMINID;
        }

        public String getSTATUS() {
            return STATUS;
        }

        public void setSTATUS(String STATUS) {
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
