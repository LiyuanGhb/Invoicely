package com.dzfp.dzfp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.dzfp.dzfp.R;
import com.dzfp.dzfp.control.AddProcessControl;
import com.dzfp.dzfp.databinding.AddprocessLayoutBinding;
import com.dzfp.dzfp.model.CompanyUserListBean;
import com.dzfp.dzfp.presenter.AddProcessPresenter;
import com.dzfp.dzfp.ui.BaseActivity;
import com.dzfp.dzfp.ui.adapter.AddProcessAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/26.
 */

public class AddProcessActivity extends BaseActivity
        implements AdapterView.OnItemClickListener, View.OnClickListener, AddProcessControl.View, TextWatcher {
    private AddprocessLayoutBinding mBinding;
    private String mCompanyId;
    private List<CompanyUserListBean.DataBean> mDataBeanList;
    private AddProcessAdapter mAddProcessAdapter;
    private int addIndex = 0;
    public static final int ADD_STAFF = 1;
    private AddProcessPresenter mAddProcessPresenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.addprocess_layout);
        initParameter();
    }

    private void initParameter() {
        mCompanyId = getIntent().getStringExtra("comid");
        mBinding.apGridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
        CompanyUserListBean.DataBean mDataBean = new CompanyUserListBean.DataBean();
        mDataBean.setZSXM("ADMIN");
        mDataBeanList = new ArrayList<>();
        initList(mDataBean);
        mAddProcessAdapter = new AddProcessAdapter(mDataBeanList, this);
        mBinding.apGridView.setAdapter(mAddProcessAdapter);
        mBinding.apGridView.setOnItemClickListener(this);
        mBinding.apAddProcessName.addTextChangedListener(this);
        mBinding.setOnClickListener(this);
        mAddProcessPresenter = new AddProcessPresenter(this);
        mBinding.apSure.setBackground(getResources().getDrawable(R.drawable.buttonblue_0));
        mBinding.apSure.setEnabled(false);
    }

    private void initList(CompanyUserListBean.DataBean mDataBean) {
        mDataBeanList.add(mDataBean);
        if (mDataBeanList.size() >= 2) {
            mDataBean = mDataBeanList.get(addIndex);
            mDataBeanList.remove(addIndex);
            mDataBeanList.add(mDataBean);
            addIndex++;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (mDataBeanList.get(position).getZSXM().equals("ADMIN")) {
            Intent mIntent = new Intent(AddProcessActivity.this, SelectPeopleActivity.class);
            mIntent.putExtra("comid", mCompanyId);
            startActivityForResult(mIntent, ADD_STAFF);
        } else {
            mDataBeanList.remove(position);
            mAddProcessAdapter.notifyDataSetInvalidated();
            addIndex--;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == resultCode) {
            CompanyUserListBean.DataBean mDataBean = (CompanyUserListBean.DataBean) data.getSerializableExtra("staff");
            if (isHave(mDataBean)) {
                Toast.makeText(this, "该审批人已在列表中", Toast.LENGTH_SHORT).show();
            } else {
                initList(mDataBean);
                mAddProcessAdapter.notifyDataSetInvalidated();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    //*判断该用户是不是添加过了*//*
    private boolean isHave(CompanyUserListBean.DataBean mDataBean) {
        boolean b = false;
        for (CompanyUserListBean.DataBean dataBean : mDataBeanList) {
            if (dataBean.getZSXM().equals(mDataBean.getZSXM())) {
                b = true;
            }
        }
        return b;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
                case R.id.ap_back:
                setResult(0x123);
                finish();
                break;
            case R.id.ap_sure:
                /*确认添加*/
                String processName = mBinding.apAddProcessName.getText().toString();
                if (TextUtils.isEmpty(processName)) {
                    Toast.makeText(this, "流程名称不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    mAddProcessPresenter.addProcess(processName, mCompanyId, getids());
                }
                break;
        }
    }

    private String getids() {
        String ids = "";
        for (CompanyUserListBean.DataBean dataBean : mDataBeanList) {
            if (!dataBean.getZSXM().equals("ADMIN")) {
                ids += dataBean.getID() + ",";
            }
        }
        return ids;
    }

    @Override
    public void addProcessSuccess(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void hintMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public Context getViewContext() {
        return null;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Override
    public void afterTextChanged(Editable s) {
        String processName = mBinding.apAddProcessName.getText().toString();
        Log.i("System", "afterTextChanged: " + processName);
        if (TextUtils.isEmpty(processName)) {
            mBinding.apSure.setBackground(getResources().getDrawable(R.drawable.buttonblue_0));
            mBinding.apSure.setEnabled(false);
        } else {
            mBinding.apSure.setBackground(getResources().getDrawable(R.drawable.buttonblue));
            mBinding.apSure.setEnabled(true);
        }
    }
}
