package com.dzfp.dzfp.control;


import com.dzfp.dzfp.BasePresenter;
import com.dzfp.dzfp.BaseView;
import com.dzfp.dzfp.model.Card.CompanyRoot;

public class MainAtyControl {
    public interface View extends BaseView<Presenter> {
        void initFragmentAndTitle(int index,String title);

        void queryCompanySuccess(CompanyRoot mCompanyRoot);

        void queryCompanyTitleSuccess(CompanyRoot mCompanyRoot);

        void OnHttpListenerFailed(String error);
    }

    public interface Presenter extends BasePresenter {
        void showFragment(int index,String title);

        void queryCompany();

        void queryCompanyTitle();
    }
}
