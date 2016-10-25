package com.dzfp.dzfp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.dzfp.dzfp.R;
import com.dzfp.dzfp.control.ReimburseControl;
import com.dzfp.dzfp.databinding.ReimburseLayoutBinding;
import com.dzfp.dzfp.model.OrderAuditOfMeBean;
import com.dzfp.dzfp.model.WfqdBean;
import com.dzfp.dzfp.presenter.ReimbursePresenter;
import com.dzfp.dzfp.ui.BaseActivity;
import com.dzfp.dzfp.ui.adapter.OrderAuditOfMeAdapter;
import com.dzfp.dzfp.ui.adapter.WfqdAdapter;

import java.util.List;

public class ReimburseActivity extends BaseActivity implements ReimburseControl.View, View.OnClickListener, AdapterView.OnItemClickListener {
    private static final String TAG = "System";
    private String mCompanyId;
    private int userType;
    private ReimburseLayoutBinding mBinding;
    private ReimbursePresenter mReimbursePresenter;
    private PopupMenu mPopupMenu;


    private List<WfqdBean.DataBean> mWfqdBeanList;
    private WfqdAdapter mWfqdAdapter;

    private List<OrderAuditOfMeBean.DataBean> mOrderAuditOfMeBean;
    private OrderAuditOfMeAdapter mOrderAuditOfMeAdapter;

    private int currentAddress = 0; // 0 待我审批 1我发起的审批

    public static final int REQUEST_DWSP = 1;
    public static final int REQUEST_WFQD = 2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.reimburse_layout);
        initParameter();
    }

    private void initParameter() {
        mCompanyId = getIntent().getStringExtra("comid");
        userType = getIntent().getIntExtra("admin", 0);
        mReimbursePresenter = new ReimbursePresenter(this);
        mBinding.setOnClickListener(this);
        mBinding.reimburseList.setOnItemClickListener(this);
        mReimbursePresenter.orderAuditOfMe(mCompanyId);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.remburse_menu, menu);
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.reimburse_back:
                finish();
                break;
            case R.id.reimburse_dwsh:
                mBinding.reimburseDwsh.setBackground(getResources().getDrawable(R.drawable.loginsleft_click));
                mBinding.reimburseWfqd.setBackground(getResources().getDrawable(R.drawable.logins2));
                mReimbursePresenter.orderAuditOfMe(mCompanyId);
                currentAddress = 0;
                break;
            case R.id.reimburse_wfqd:
                mBinding.reimburseWfqd.setBackground(getResources().getDrawable(R.drawable.logins));
                mBinding.reimburseDwsh.setBackground(getResources().getDrawable(R.drawable.loginsleft));
                mReimbursePresenter.orderOfMe(mCompanyId);
                currentAddress = 1;
                break;
            case R.id.reimburse_menu_rl:
                showPopupMenu(v);
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent mIntent = new Intent(ReimburseActivity.this, SPXQActivity.class);
        mIntent.putExtra("currentAddress", currentAddress);
        String orderId = null;
        int intentType = 0;
        switch (currentAddress) {
            case 0:
                orderId = mOrderAuditOfMeBean.get(position).getID();
                intentType = REQUEST_DWSP;
                break;
            case 1:
                orderId = mWfqdBeanList.get(position).getID();
                intentType = REQUEST_WFQD;
                break;
        }

        mIntent.putExtra("orderId", orderId);
        startActivityForResult(mIntent, intentType);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) {
            case REQUEST_DWSP:
                mReimbursePresenter.orderAuditOfMe(mCompanyId);
                break;
            case REQUEST_WFQD:
                mReimbursePresenter.orderOfMe(mCompanyId);
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void orderAuditOfMeSuccess(List<OrderAuditOfMeBean.DataBean> mDataBeen) {
        mOrderAuditOfMeBean = mDataBeen;
        mOrderAuditOfMeAdapter = new OrderAuditOfMeAdapter(mDataBeen, ReimburseActivity.this);
        mBinding.reimburseList.setAdapter(mOrderAuditOfMeAdapter);


    }

    @Override
    public void orderOfMeSuccess(WfqdBean mWfqdBean) {
        mWfqdBeanList = mWfqdBean.getData();
        mWfqdAdapter = new WfqdAdapter(mWfqdBeanList, ReimburseActivity.this);
        mBinding.reimburseList.setAdapter(mWfqdAdapter);
    }


    @Override
    public void hintMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public Context getViewContext() {
        return this;
    }


    private void showPopupMenu(View mView) {
        mPopupMenu = new PopupMenu(this, mView);
        getMenuInflater().inflate(R.menu.remburse_menu, mPopupMenu.getMenu());
        mPopupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.reimburse_create:
                        switch (userType) {
                            case 0:
                                Intent mIntent = new Intent(ReimburseActivity.this, AddProcessActivity.class);
                                mIntent.putExtra("comid", mCompanyId);
                                startActivity(mIntent);
                                break;
                            case 1:
                                Toast.makeText(ReimburseActivity.this, "权限不足，不能使用该功能", Toast.LENGTH_SHORT).show();
                                break;
                        }
                        break;
                }
                return true;
            }
        });
        mPopupMenu.show();
    }


}
