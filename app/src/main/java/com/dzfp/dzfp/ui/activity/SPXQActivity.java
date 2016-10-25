package com.dzfp.dzfp.ui.activity;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


import com.dzfp.dzfp.CustomApplication;
import com.dzfp.dzfp.R;
import com.dzfp.dzfp.control.SQXPControl;
import com.dzfp.dzfp.databinding.SqxqItemBinding;
import com.dzfp.dzfp.databinding.SqxqLayoutBinding;
import com.dzfp.dzfp.model.BxlcxqBean;
import com.dzfp.dzfp.presenter.SQXPPresenter;
import com.dzfp.dzfp.ui.BaseActivity;
import com.dzfp.dzfp.ui.adapter.FpAdapter;
import com.dzfp.dzfp.ui.adapter.SPXQAdapter;

import java.util.List;


public class SPXQActivity extends BaseActivity implements SQXPControl.View, View.OnClickListener {
    private SqxqLayoutBinding mBinding;
    private SqxqItemBinding mSqxqItemBinding;
    private List<BxlcxqBean.DataBean.OrderDetailBean> mOrderDetailBeen;
    private List<BxlcxqBean.DataBean.FpDetailBean> mFpDetailBeen;
    private SQXPControl.Presenter mPresenter;
    private String orderId;
    private SPXQAdapter mSPXQAdapter;
    private FpAdapter mFpAdapter;
    private int userType;
    private String shState;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.sqxq_layout);
        initParameter();
    }

    private void initParameter() {
        orderId = getIntent().getStringExtra("orderId");
        userType = getIntent().getIntExtra("currentAddress", 0);
        mPresenter = new SQXPPresenter(this);
        mSqxqItemBinding = DataBindingUtil.bind(mBinding.slIncludeContain.getRoot());
        mBinding.setOnClickListener(this);


        mPresenter.checkOrderDetail(orderId);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sl_back:
                finish();
                break;
            case R.id.sl_lc:
                /*报销流程详情*/
                mBinding.slLc.setBackground(getResources().getDrawable(R.drawable.loginsleft_click));
                mBinding.slFp.setBackground(getResources().getDrawable(R.drawable.logins2));
                if (mSPXQAdapter == null) {
                    mSPXQAdapter = new SPXQAdapter(SPXQActivity.this, mOrderDetailBeen);
                }
                mBinding.slList.setAdapter(mSPXQAdapter);
                break;
            case R.id.sl_fp:
                /*报销的发票详情*/
                mBinding.slLc.setBackground(getResources().getDrawable(R.drawable.loginsleft));
                mBinding.slFp.setBackground(getResources().getDrawable(R.drawable.logins));
                if (mFpAdapter == null) {
                    mFpAdapter = new FpAdapter(mFpDetailBeen, SPXQActivity.this);
                }
                mBinding.slList.setAdapter(mFpAdapter);
                break;
            case R.id.sl_agree:
                /*同意申请*/
                mPresenter.orderPass(orderId, "");
                break;
            case R.id.sl_disAgree:
                /*不同意申请*/
                mPresenter.orderNotPass(orderId, "");
                break;
            case R.id.sl_invoke:
                /*取消申请*/
                mPresenter.invokeProcess(orderId);
                break;
        }
    }


    @Override
    public void checkOrderDetailSuccess(BxlcxqBean mBxlcxqBean) {

        mOrderDetailBeen = mBxlcxqBean.getData().getOrder_detail();
        mFpDetailBeen = mBxlcxqBean.getData().getFp_detail();

        mSqxqItemBinding.siFace.setText(CustomApplication.ZSXM);
        mSqxqItemBinding.siName.setText(CustomApplication.ZSXM);
        int lcStatus = mBxlcxqBean.getData().getOrder_msg().getSTATUS();
        switch (lcStatus){
            case 0:
                shState = "正在审批";
                break;
            case 1:
                shState = "审批完成(不通过)";
                break;
            case 2:
                shState = "已撤销";
                break;
            case 999:
                shState = "审批完成(已通过)";
                break;
        }
        mSqxqItemBinding.siState.setText(shState);
        /*根据userType 控制显示的东西*/
        // FIXME: 2016/8/31 还要判断整个流程是否结束 列如审核通过就不该显示撤销按钮
        layoutShowFromUserType();
        mSPXQAdapter = new SPXQAdapter(SPXQActivity.this, mOrderDetailBeen);
        mBinding.slList.setAdapter(mSPXQAdapter);
    }

    @Override
    public void orderPassSuccess() {
        setResult(ReimburseActivity.REQUEST_DWSP);
        finish();
    }

    @Override
    public void orderNotPassSuccess() {
        setResult(ReimburseActivity.REQUEST_DWSP);
        finish();
    }

    @Override
    public void invokeProcessSuccess() {
        setResult(ReimburseActivity.REQUEST_WFQD);
        finish();
    }

    @Override
    public void hintMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public Context getViewContext() {
        return this;
    }


    private void layoutShowFromUserType() {
        switch (userType) {
            case 0:
                mBinding.slAdmin.setVisibility(View.VISIBLE);
                mBinding.slInvoke.setVisibility(View.GONE);
                break;
            case 1:
                if (shState.equals("正在审批")) {
                    mBinding.slAdmin.setVisibility(View.GONE);
                    mBinding.slInvoke.setVisibility(View.VISIBLE);
                } else {
                    mBinding.slAdmin.setVisibility(View.GONE);
                    mBinding.slInvoke.setVisibility(View.GONE);
                }
                break;
        }
    }
}
