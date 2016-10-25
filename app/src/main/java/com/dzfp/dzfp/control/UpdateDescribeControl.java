package com.dzfp.dzfp.control;

import com.dzfp.dzfp.BaseView;
import com.dzfp.dzfp.BasePresenter;

/**
 * Created by Administrator on 2016/8/15.
 */

public class UpdateDescribeControl {
    public interface View extends BaseView {
        void updateUserDescribeSuccess();
        void OnHttpListenerFailed(String error);
    }

    public interface Presenter extends BasePresenter{
        void updateUserDescribe(String zsxm);
    }

}
