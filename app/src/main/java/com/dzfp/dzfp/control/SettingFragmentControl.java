package com.dzfp.dzfp.control;

import com.dzfp.dzfp.BasePresenter;
import com.dzfp.dzfp.BaseView;

public class SettingFragmentControl {
    public interface View extends BaseView {
        void setUserDescribe(String userName, String userDescribe);
        void OnHttpListenerFailed(String error);

    }

    public interface Presenter extends BasePresenter {
        void getUserDescribe(String userId);
    }
}
