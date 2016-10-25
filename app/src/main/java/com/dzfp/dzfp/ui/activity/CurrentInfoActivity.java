package com.dzfp.dzfp.ui.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import com.dzfp.dzfp.R;
import com.dzfp.dzfp.databinding.CurrentBinding;
import com.dzfp.dzfp.model.Card.Data;
import com.dzfp.dzfp.ui.BaseActivity;

/**
 * Created by Administrator on 2016/8/31.
 */

public class CurrentInfoActivity extends BaseActivity implements View.OnClickListener {
    CurrentBinding currentBinding;
    private Data companyBean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        companyBean = (Data) getIntent().getSerializableExtra("companyBean");
        currentBinding = DataBindingUtil.setContentView(this, R.layout.current);
        currentBinding.currentTitle.setText(companyBean.getNAME());
        currentBinding.currentQymc.setDetails(companyBean.getNAME());
        currentBinding.currentQysh.setDetails(companyBean.getNSRSBH());
        currentBinding.currentQydz.setDetails(companyBean.getADDRESS());
        currentBinding.currentQydh.setDetails(companyBean.getPHONE());
        currentBinding.currentKhh.setDetails(companyBean.getKHH());
        currentBinding.currentYhzh.setDetails(companyBean.getYHZH());
        currentBinding.btBingCurrent.setVisibility(View.GONE);
        currentBinding.setOnCliclistener(this);
        currentBinding.currentQymc.setRightButtomVisible(View.GONE);
        currentBinding.currentQysh.setRightButtomVisible(View.GONE);
        currentBinding.currentQymc.setLisner(null);
        currentBinding.currentQysh.setLisner(null);
        currentBinding.currentQydz.setLisner(null);
        currentBinding.currentQydh.setLisner(null);
        currentBinding.currentKhh.setLisner(null);
        currentBinding.currentYhzh.setLisner(null);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_back_current:
                finish();
                break;
        }

    }
}
