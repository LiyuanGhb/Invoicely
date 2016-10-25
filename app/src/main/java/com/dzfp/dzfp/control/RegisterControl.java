package com.dzfp.dzfp.control;

import com.dzfp.dzfp.BasePresenter;
import com.dzfp.dzfp.BaseView;

public class RegisterControl {
    public interface View extends BaseView {
        void registerSuccess();
        int getRegisterType();
        void OnHttpListenerFailed(String error);
    }


    public interface Presenter extends BasePresenter {
        void register(String requestParameter);
    }
}
