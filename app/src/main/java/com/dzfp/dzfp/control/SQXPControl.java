package com.dzfp.dzfp.control;


import com.dzfp.dzfp.BasePresenter;
import com.dzfp.dzfp.BaseView;
import com.dzfp.dzfp.model.BxlcxqBean;

/**
 * Created by Administrator on 2016/8/30. 报销详情控制器
 */

public class SQXPControl {
    public interface View extends BaseView {
        void checkOrderDetailSuccess(BxlcxqBean mBxlcxqBean);

        void orderPassSuccess();

        void orderNotPassSuccess();

        void invokeProcessSuccess();
    }

    public interface Presenter extends BasePresenter {
        void checkOrderDetail(String orderId);

        /**
         * 同意申请
         *
         * @param orderid 流程id
         * @param bz      备注
         */
        void orderPass(String orderid, String bz);

        /**
         * 不同意申请
         *
         * @param orderid 流程id
         * @param bz      备注
         */
        void orderNotPass(String orderid, String bz);

        /**
         * 取消申请
         *
         * @param orderid 流程id
         */
        void invokeProcess(String orderid);
    }
}
