package com.dzfp.dzfp.control;

import com.dzfp.dzfp.BasePresenter;
import com.dzfp.dzfp.BaseView;
import com.dzfp.dzfp.model.Card.Data;
import com.dzfp.dzfp.model.VagueCompanyBean;


public class CreateCompanyControl {
    public interface View extends BaseView {

        void createCompanySuccess();

        void updateCompanySuccess();

        void queryByCompanyNameSuccess(VagueCompanyBean mVagueCompanyBean);

        void queryByCompanySHSuccess(VagueCompanyBean mVagueCompanyBean);

        void querByCompanySHERROR(VagueCompanyBean mVagueCompanyBean);

        Data getCreateCompanyDescribe();

        String getCompanyName();

        String getCompanySH();

        void OnHttpListenerFailed(String error);

    }

    public interface Presenter extends BasePresenter {

        void queryByCompanyName();

        void queryByCompanySH();

        void createCompany();

        void updateCompany();
    }
}
