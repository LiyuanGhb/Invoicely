package com.dzfp.dzfp.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/8/26.
 */

public class CompanyUserListBean {

    /**
     * data : [{"ZSXM":"zby","ID":"F1B948B8D3C34331BCFF0AB6B96982EA","EMAIL":"","USERNAME":"18981443691"},{"ZSXM":"lansirs_tab2","ID":"5E4411A6846443169DAABF57A87AE093","EMAIL":"","USERNAME":"18349280204"},{"ZSXM":"deng","ID":"6E4411A6846443169DAABF57A87AE094","EMAIL":"","USERNAME":"13880836842"},{"ZSXM":"xian","ID":"7R4411A6846443169DAABF57A87AE095","EMAIL":"","USERNAME":"13880836843"},{"ZSXM":"guo","ID":"8S4411A6846443169DAABF57A87AE096","EMAIL":"","USERNAME":"13880836844"}]
     * resultType : SUCCESS
     * msg : 成功
     */

    private String resultType;
    private String msg;
    /**
     * ZSXM : zby
     * ID : F1B948B8D3C34331BCFF0AB6B96982EA
     * EMAIL :
     * USERNAME : 18981443691
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

    public static class DataBean implements Serializable{
        private String ZSXM;
        private String ID;
        private String EMAIL;
        private String USERNAME;

        public String getZSXM() {
            return ZSXM;
        }

        public void setZSXM(String ZSXM) {
            this.ZSXM = ZSXM;
        }

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getEMAIL() {
            return EMAIL;
        }

        public void setEMAIL(String EMAIL) {
            this.EMAIL = EMAIL;
        }

        public String getUSERNAME() {
            return USERNAME;
        }

        public void setUSERNAME(String USERNAME) {
            this.USERNAME = USERNAME;
        }
    }
}
