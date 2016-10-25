package com.dzfp.dzfp.control;


import com.dzfp.dzfp.BasePresenter;
import com.dzfp.dzfp.BaseView;

/**
 * Created by Administrator on 2016/8/26.
 */

public class AddProcessControl {
    public interface View extends BaseView {
        void addProcessSuccess(String msg);

    }

    public interface Presenter extends BasePresenter {
        /**
         * 创建报销流程
         *
         * @param processname 报销流程名称
         * @param companyId 公司id
         * @param ids       用户id集合
         */
        void addProcess(String processname, String companyId, String ids);
    }
}
