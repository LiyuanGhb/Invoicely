package com.dzfp.dzfp.model;

import java.util.List;

/**
 * Created by Administrator on 2016/8/29.
 */

public class BxlcxqBean {


    /**
     * order_detail : [{"BYORDER":"1","bz":"","status":"已撤销","ITEM":"F1B948B8D3C34331BCFF0AB6B96982EA","ZSXM":"zby","ID":"59CF5099A2EC46BFA5C64D6BADE86125","PROCESSID":"7B72BA52A651491989C96EFFC59EA9DC","exc_time":""},{"BYORDER":"2","bz":"","status":"等待审批","ITEM":"5E4411A6846443169DAABF57A87AE093","ZSXM":"lansirs_tab2","ID":"8D077672172842AB9353B51D9B0A5213","PROCESSID":"7B72BA52A651491989C96EFFC59EA9DC","exc_time":""}]
     * order_msg : {"PROCESS_ITEMID":"F1B948B8D3C34331BCFF0AB6B96982EA","ID":"8C95247932E347BAA527D4DB942A6CB4","COMID":"C2B62E98048D498980A6568C14EAF083","PROCESSID":"7B72BA52A651491989C96EFFC59EA9DC","CREATETIME":"2016-08-31 15:44:20","STATUS":2,"USERID":"F1B948B8D3C34331BCFF0AB6B96982EA"}
     * fp_detail : [{"JYM":"2","FPHM":"2222","YYLXDM":"2","FPDM":"2222","JSHJ":2,"STATUS":"0","BZ":"2","SPSM":"2","KPRQ":"2","FPLX":"2","GHDWMC":"2","KPSJ":"2","JMBBH":"2","ID":"2222","KPR":"2","SKR":"2","FHR":"2","GHDWSBH":"64948","GHDWDZDH":"2","SKM":"2","ZHSL":2,"HJJE":2,"TSPZ":"2","GHDWYHZH":"2","SCBZ":"2","XHDWMC":"2","HJSE":2,"SKPBH":"2","FPZT":"2","ZYSPMC":"2","XHDWSBH":"2","XHDWDZDH":"2","XHDWYHZH":"2"}]
     */

    private DataBean data;
    /**
     * data : {"order_detail":[{"BYORDER":"1","bz":"","status":"已撤销","ITEM":"F1B948B8D3C34331BCFF0AB6B96982EA","ZSXM":"zby","ID":"59CF5099A2EC46BFA5C64D6BADE86125","PROCESSID":"7B72BA52A651491989C96EFFC59EA9DC","exc_time":""},{"BYORDER":"2","bz":"","status":"等待审批","ITEM":"5E4411A6846443169DAABF57A87AE093","ZSXM":"lansirs_tab2","ID":"8D077672172842AB9353B51D9B0A5213","PROCESSID":"7B72BA52A651491989C96EFFC59EA9DC","exc_time":""}],"order_msg":{"PROCESS_ITEMID":"F1B948B8D3C34331BCFF0AB6B96982EA","ID":"8C95247932E347BAA527D4DB942A6CB4","COMID":"C2B62E98048D498980A6568C14EAF083","PROCESSID":"7B72BA52A651491989C96EFFC59EA9DC","CREATETIME":"2016-08-31 15:44:20","STATUS":2,"USERID":"F1B948B8D3C34331BCFF0AB6B96982EA"},"fp_detail":[{"JYM":"2","FPHM":"2222","YYLXDM":"2","FPDM":"2222","JSHJ":2,"STATUS":"0","BZ":"2","SPSM":"2","KPRQ":"2","FPLX":"2","GHDWMC":"2","KPSJ":"2","JMBBH":"2","ID":"2222","KPR":"2","SKR":"2","FHR":"2","GHDWSBH":"64948","GHDWDZDH":"2","SKM":"2","ZHSL":2,"HJJE":2,"TSPZ":"2","GHDWYHZH":"2","SCBZ":"2","XHDWMC":"2","HJSE":2,"SKPBH":"2","FPZT":"2","ZYSPMC":"2","XHDWSBH":"2","XHDWDZDH":"2","XHDWYHZH":"2"}]}
     * resultType : SUCCESS
     * msg : 成功
     */

    private String resultType;
    private String msg;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

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

    public static class DataBean {
        /**
         * PROCESS_ITEMID : F1B948B8D3C34331BCFF0AB6B96982EA
         * ID : 8C95247932E347BAA527D4DB942A6CB4
         * COMID : C2B62E98048D498980A6568C14EAF083
         * PROCESSID : 7B72BA52A651491989C96EFFC59EA9DC
         * CREATETIME : 2016-08-31 15:44:20
         * STATUS : 2
         * USERID : F1B948B8D3C34331BCFF0AB6B96982EA
         */

        private OrderMsgBean order_msg;
        /**
         * BYORDER : 1
         * bz :
         * status : 已撤销
         * ITEM : F1B948B8D3C34331BCFF0AB6B96982EA
         * ZSXM : zby
         * ID : 59CF5099A2EC46BFA5C64D6BADE86125
         * PROCESSID : 7B72BA52A651491989C96EFFC59EA9DC
         * exc_time :
         */

        private List<OrderDetailBean> order_detail;
        /**
         * JYM : 2
         * FPHM : 2222
         * YYLXDM : 2
         * FPDM : 2222
         * JSHJ : 2
         * STATUS : 0
         * BZ : 2
         * SPSM : 2
         * KPRQ : 2
         * FPLX : 2
         * GHDWMC : 2
         * KPSJ : 2
         * JMBBH : 2
         * ID : 2222
         * KPR : 2
         * SKR : 2
         * FHR : 2
         * GHDWSBH : 64948
         * GHDWDZDH : 2
         * SKM : 2
         * ZHSL : 2
         * HJJE : 2
         * TSPZ : 2
         * GHDWYHZH : 2
         * SCBZ : 2
         * XHDWMC : 2
         * HJSE : 2
         * SKPBH : 2
         * FPZT : 2
         * ZYSPMC : 2
         * XHDWSBH : 2
         * XHDWDZDH : 2
         * XHDWYHZH : 2
         */

        private List<FpDetailBean> fp_detail;

        public OrderMsgBean getOrder_msg() {
            return order_msg;
        }

        public void setOrder_msg(OrderMsgBean order_msg) {
            this.order_msg = order_msg;
        }

        public List<OrderDetailBean> getOrder_detail() {
            return order_detail;
        }

        public void setOrder_detail(List<OrderDetailBean> order_detail) {
            this.order_detail = order_detail;
        }

        public List<FpDetailBean> getFp_detail() {
            return fp_detail;
        }

        public void setFp_detail(List<FpDetailBean> fp_detail) {
            this.fp_detail = fp_detail;
        }

        public static class OrderMsgBean {
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

        public static class OrderDetailBean {
            private String BYORDER;
            private String bz;
            private String status;
            private String ITEM;
            private String ZSXM;
            private String ID;
            private String PROCESSID;
            private String exc_time;

            public String getBYORDER() {
                return BYORDER;
            }

            public void setBYORDER(String BYORDER) {
                this.BYORDER = BYORDER;
            }

            public String getBz() {
                return bz;
            }

            public void setBz(String bz) {
                this.bz = bz;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getITEM() {
                return ITEM;
            }

            public void setITEM(String ITEM) {
                this.ITEM = ITEM;
            }

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

            public String getPROCESSID() {
                return PROCESSID;
            }

            public void setPROCESSID(String PROCESSID) {
                this.PROCESSID = PROCESSID;
            }

            public String getExc_time() {
                return exc_time;
            }

            public void setExc_time(String exc_time) {
                this.exc_time = exc_time;
            }
        }

        public static class FpDetailBean {
            private String JYM;
            private String FPHM;
            private String YYLXDM;
            private String FPDM;
            private String JSHJ;
            private String STATUS;
            private String BZ;
            private String SPSM;
            private String KPRQ;
            private String FPLX;
            private String GHDWMC;
            private String KPSJ;
            private String JMBBH;
            private String ID;
            private String KPR;
            private String SKR;
            private String FHR;
            private String GHDWSBH;
            private String GHDWDZDH;
            private String SKM;
            private String ZHSL;
            private String HJJE;
            private String TSPZ;
            private String GHDWYHZH;
            private String SCBZ;
            private String XHDWMC;
            private String HJSE;
            private String SKPBH;
            private String FPZT;
            private String ZYSPMC;
            private String XHDWSBH;
            private String XHDWDZDH;
            private String XHDWYHZH;

            public String getJYM() {
                return JYM;
            }

            public void setJYM(String JYM) {
                this.JYM = JYM;
            }

            public String getFPHM() {
                return FPHM;
            }

            public void setFPHM(String FPHM) {
                this.FPHM = FPHM;
            }

            public String getYYLXDM() {
                return YYLXDM;
            }

            public void setYYLXDM(String YYLXDM) {
                this.YYLXDM = YYLXDM;
            }

            public String getFPDM() {
                return FPDM;
            }

            public void setFPDM(String FPDM) {
                this.FPDM = FPDM;
            }

            public String  getJSHJ() {
                return JSHJ;
            }

            public void setJSHJ(String JSHJ) {
                this.JSHJ = JSHJ;
            }

            public String getSTATUS() {
                return STATUS;
            }

            public void setSTATUS(String STATUS) {
                this.STATUS = STATUS;
            }

            public String getBZ() {
                return BZ;
            }

            public void setBZ(String BZ) {
                this.BZ = BZ;
            }

            public String getSPSM() {
                return SPSM;
            }

            public void setSPSM(String SPSM) {
                this.SPSM = SPSM;
            }

            public String getKPRQ() {
                return KPRQ;
            }

            public void setKPRQ(String KPRQ) {
                this.KPRQ = KPRQ;
            }

            public String getFPLX() {
                return FPLX;
            }

            public void setFPLX(String FPLX) {
                this.FPLX = FPLX;
            }

            public String getGHDWMC() {
                return GHDWMC;
            }

            public void setGHDWMC(String GHDWMC) {
                this.GHDWMC = GHDWMC;
            }

            public String getKPSJ() {
                return KPSJ;
            }

            public void setKPSJ(String KPSJ) {
                this.KPSJ = KPSJ;
            }

            public String getJMBBH() {
                return JMBBH;
            }

            public void setJMBBH(String JMBBH) {
                this.JMBBH = JMBBH;
            }

            public String getID() {
                return ID;
            }

            public void setID(String ID) {
                this.ID = ID;
            }

            public String getKPR() {
                return KPR;
            }

            public void setKPR(String KPR) {
                this.KPR = KPR;
            }

            public String getSKR() {
                return SKR;
            }

            public void setSKR(String SKR) {
                this.SKR = SKR;
            }

            public String getFHR() {
                return FHR;
            }

            public void setFHR(String FHR) {
                this.FHR = FHR;
            }

            public String getGHDWSBH() {
                return GHDWSBH;
            }

            public void setGHDWSBH(String GHDWSBH) {
                this.GHDWSBH = GHDWSBH;
            }

            public String getGHDWDZDH() {
                return GHDWDZDH;
            }

            public void setGHDWDZDH(String GHDWDZDH) {
                this.GHDWDZDH = GHDWDZDH;
            }

            public String getSKM() {
                return SKM;
            }

            public void setSKM(String SKM) {
                this.SKM = SKM;
            }

            public String getZHSL() {
                return ZHSL;
            }

            public void setZHSL(String ZHSL) {
                this.ZHSL = ZHSL;
            }

            public String getHJJE() {
                return HJJE;
            }

            public void setHJJE(String HJJE) {
                this.HJJE = HJJE;
            }

            public String getTSPZ() {
                return TSPZ;
            }

            public void setTSPZ(String TSPZ) {
                this.TSPZ = TSPZ;
            }

            public String getGHDWYHZH() {
                return GHDWYHZH;
            }

            public void setGHDWYHZH(String GHDWYHZH) {
                this.GHDWYHZH = GHDWYHZH;
            }

            public String getSCBZ() {
                return SCBZ;
            }

            public void setSCBZ(String SCBZ) {
                this.SCBZ = SCBZ;
            }

            public String getXHDWMC() {
                return XHDWMC;
            }

            public void setXHDWMC(String XHDWMC) {
                this.XHDWMC = XHDWMC;
            }

            public String getHJSE() {
                return HJSE;
            }

            public void setHJSE(String HJSE) {
                this.HJSE = HJSE;
            }

            public String getSKPBH() {
                return SKPBH;
            }

            public void setSKPBH(String SKPBH) {
                this.SKPBH = SKPBH;
            }

            public String getFPZT() {
                return FPZT;
            }

            public void setFPZT(String FPZT) {
                this.FPZT = FPZT;
            }

            public String getZYSPMC() {
                return ZYSPMC;
            }

            public void setZYSPMC(String ZYSPMC) {
                this.ZYSPMC = ZYSPMC;
            }

            public String getXHDWSBH() {
                return XHDWSBH;
            }

            public void setXHDWSBH(String XHDWSBH) {
                this.XHDWSBH = XHDWSBH;
            }

            public String getXHDWDZDH() {
                return XHDWDZDH;
            }

            public void setXHDWDZDH(String XHDWDZDH) {
                this.XHDWDZDH = XHDWDZDH;
            }

            public String getXHDWYHZH() {
                return XHDWYHZH;
            }

            public void setXHDWYHZH(String XHDWYHZH) {
                this.XHDWYHZH = XHDWYHZH;
            }
        }
    }
}
