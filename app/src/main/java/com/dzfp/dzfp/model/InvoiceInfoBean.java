package com.dzfp.dzfp.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/8/29.
 */

public class InvoiceInfoBean implements Serializable {
    String kpf;//购货方
    String shf;//收货方
    String time;
    String money;
    String fpid;
    String fpdm;
    String fphm;
    String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKpf() {
        return kpf;
    }

    public void setKpf(String kpf) {
        this.kpf = kpf;
    }

    public String getShf() {
        return shf;
    }

    public void setShf(String shf) {
        this.shf = shf;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getFpid() {
        return fpid;
    }

    public void setFpid(String fpid) {
        this.fpid = fpid;
    }

    public String getFpdm() {
        return fpdm;
    }

    public void setFpdm(String fpdm) {
        this.fpdm = fpdm;
    }

    public String getFphm() {
        return fphm;
    }

    public void setFphm(String fphm) {
        this.fphm = fphm;
    }


}
