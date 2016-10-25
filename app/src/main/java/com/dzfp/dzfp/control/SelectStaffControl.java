package com.dzfp.dzfp.control;


import com.dzfp.dzfp.BasePresenter;
import com.dzfp.dzfp.BaseView;
import com.dzfp.dzfp.model.CompanyUserListBean;

import java.util.List;

/**
 * Created by Administrator on 2016/8/26.
 */

public class SelectStaffControl {

    public interface View extends BaseView {
        void listComUserSuccess(List<CompanyUserListBean.DataBean> mDataBeanList);
    }

    public interface Presenter extends BasePresenter {
        /**
         * 获取员工列表
         *
         * @param companyId 公司id
         */
        void listComUser(String companyId);
    }
}
