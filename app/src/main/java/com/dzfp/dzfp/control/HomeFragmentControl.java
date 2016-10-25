package com.dzfp.dzfp.control;

import com.dzfp.dzfp.BasePresenter;
import com.dzfp.dzfp.BaseView;
import com.dzfp.dzfp.model.Card.CompanyRoot;

/**
 * Created by Administrator on 2016/8/11.
 */

public class HomeFragmentControl {
    public interface HomeFragmentView extends BaseView {
        void deleteCompanySuccess();

        void queryCompanySuccess(CompanyRoot mCompanyRoot);

        void OnHttpListenerFailed(String error);
    }

    public interface Presenter extends BasePresenter {
        void deleteCompany(String params);

        void queryCompany();

    }
}
