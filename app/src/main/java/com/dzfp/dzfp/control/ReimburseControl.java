package com.dzfp.dzfp.control;


import com.dzfp.dzfp.BasePresenter;
import com.dzfp.dzfp.BaseView;
import com.dzfp.dzfp.model.OrderAuditOfMeBean;
import com.dzfp.dzfp.model.WfqdBean;

import java.util.List;

public class ReimburseControl {
    public interface View extends BaseView {

        void orderAuditOfMeSuccess(List<OrderAuditOfMeBean.DataBean> mDataBeen);

        void orderOfMeSuccess(WfqdBean mWfqdBean);
    }

    public interface Presenter extends BasePresenter {




        /**
         * 待我审批
         *
         * @param companyId 公司id
         */
        void orderAuditOfMe(String companyId);


        /**
         * 我发的申请
         *
         * @param companyId 公司id
         */
        void orderOfMe(String companyId);
    }
}
