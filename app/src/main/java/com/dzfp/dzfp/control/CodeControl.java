package com.dzfp.dzfp.control;

import com.dzfp.dzfp.BasePresenter;
import com.dzfp.dzfp.BaseView;

public class CodeControl {
    public interface View extends BaseView {
        String getPhone();

        void getCodeSuccess();

        void OnHttpListenerFailed(String error);
    }

    public interface Presenter extends BasePresenter {
        void getCode();
    }
}
