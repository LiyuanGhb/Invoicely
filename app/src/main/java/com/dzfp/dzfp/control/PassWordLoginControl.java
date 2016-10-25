package com.dzfp.dzfp.control;

import com.dzfp.dzfp.BasePresenter;
import com.dzfp.dzfp.BaseView;


public class PassWordLoginControl {
    public interface View extends BaseView {
        void PassWordForSuccessSuccess();
        void OnHttpListenerFailed(String error);
    }

    public interface Presenter extends BasePresenter {
        void PassWordLogin(String userName, String passWord);
    }
}
