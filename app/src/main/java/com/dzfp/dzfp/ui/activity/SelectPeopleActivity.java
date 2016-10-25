package com.dzfp.dzfp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.dzfp.dzfp.R;
import com.dzfp.dzfp.control.SelectStaffControl;
import com.dzfp.dzfp.databinding.SelectPeopleBinding;
import com.dzfp.dzfp.model.CompanyUserListBean;
import com.dzfp.dzfp.presenter.SelectStaffPresenter;
import com.dzfp.dzfp.ui.BaseActivity;
import com.dzfp.dzfp.ui.adapter.SelectStaffAdapter;

import java.util.List;


/**
 * Created by Administrator on 2016/8/26.
 */

public class SelectPeopleActivity extends BaseActivity implements SelectStaffControl.View, AdapterView.OnItemClickListener {
    private SelectPeopleBinding mBinding;
    private String mCompanyId;
    private List<CompanyUserListBean.DataBean> mDataBeen;
    private SelectStaffAdapter mSelectStaffAdapter;
    private SelectStaffPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.select_people);
        initParameter();
        mBinding.selectBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initParameter() {
        mCompanyId = getIntent().getStringExtra("comid");
        mPresenter = new SelectStaffPresenter(this);
        mPresenter.listComUser(mCompanyId);

        mBinding.selectList.setOnItemClickListener(this);
    }

    @Override
    public void listComUserSuccess(List<CompanyUserListBean.DataBean> mDataBean) {
        this.mDataBeen = mDataBean;
        mSelectStaffAdapter = new SelectStaffAdapter(this, mDataBean);
        mBinding.selectList.setAdapter(mSelectStaffAdapter);
    }

    @Override
    public void hintMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public Context getViewContext() {
        return this;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent();
        intent.putExtra("staff", mDataBeen.get(position));
        setResult(AddProcessActivity.ADD_STAFF, intent);
        finish();
    }
}
