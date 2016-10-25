package com.dzfp.dzfp.ui.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.dzfp.dzfp.CustomApplication;
import com.dzfp.dzfp.R;
import com.dzfp.dzfp.control.MainAtyControl;
import com.dzfp.dzfp.databinding.MainLayoutBinding;
import com.dzfp.dzfp.databinding.NavigationBottomBinding;
import com.dzfp.dzfp.model.Card.CompanyRoot;
import com.dzfp.dzfp.model.Card.Data;
import com.dzfp.dzfp.presenter.MainPresenter;
import com.dzfp.dzfp.ui.BaseActivity;
import com.dzfp.dzfp.ui.adapter.CompanyListAdapter;
import com.dzfp.dzfp.ui.fragment.CompanyFragment;
import com.dzfp.dzfp.ui.fragment.HomeFragment;
import com.dzfp.dzfp.ui.fragment.InvoiceFragment;
import com.dzfp.dzfp.ui.fragment.SettingFragment;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by zby on 2016/8/5. 首页
 */
public class MainAty extends BaseActivity implements MainAtyControl.View, RadioGroup.OnCheckedChangeListener {
    private MainLayoutBinding mBinding;
    private MainPresenter mPresenter;
    private FragmentManager mFragmentManager;
    private HomeFragment mHomeFragment = null;
    private InvoiceFragment mInvoiceFragment = null;
    private CompanyFragment mCompanyFragment = null;
    private SettingFragment mSettingFragment = null;
    private int currentFragment = 0;//记录当前Fragmentment
    private static final int HOME = 0;
    private static final int INVOICE = 1;
    private static final int COMPANY = 2;
    private static final int INFO = 3;
    private List<Fragment> mFragmentList;
    private long mExitTime;


    private List<Data> mCardBeanList;
    private PopupWindow popupWindow;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.main_layout);
        initParams();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("position", currentFragment);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        currentFragment = savedInstanceState.getInt("position");
        setTabSelection(currentFragment);
        super.onRestoreInstanceState(savedInstanceState);
    }

    private void initParams() {
        NavigationBottomBinding mNavigationBottomBinding =
                DataBindingUtil.bind(mBinding.mainNavigationBottom.getRoot());
        mNavigationBottomBinding.navContain.setOnCheckedChangeListener(this);
        mFragmentManager = getFragmentManager();
        mFragmentList = new ArrayList<>();
        mPresenter = new MainPresenter(this);
        mPresenter.showFragment(HOME, "首页");
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.nav_home:
                mPresenter.showFragment(HOME, "首页");
                updateCompanyCallBack.updateCompany();
                break;
            case R.id.nav_invoice:
                mPresenter.showFragment(INVOICE, "发票管理");
                break;
            case R.id.nav_company:
                // TODO: 2016/9/29 我的企业
                String titleName =
                        TextUtils.isEmpty(CustomApplication.currentCompanyName) ?
                                "我的企业" : CustomApplication.currentCompanyName;
                mPresenter.showFragment(COMPANY, titleName);
                break;
            case R.id.nav_info:
                mPresenter.showFragment(INFO, "我的信息");
                break;
        }
    }

    @Override
    public void initFragmentAndTitle(int index, String title) {
        setTabSelection(index);
        // TODO: 2016/9/29 增加xml Title 可以点击选择公司
        /*如果当前显示的是CompanyFragment则设置title可点击*/
        if (index == 2) {
            mBinding.mainTvTitle.setVisibility(View.GONE);
            mBinding.mainTvCompanyTitle.setVisibility(View.VISIBLE);
        } else {
            /*隐藏不可点击title*/
            mBinding.mainTvTitle.setVisibility(View.VISIBLE);
            /*显示可点击的title 并绑定监听*/
            mBinding.mainTvCompanyTitle.setVisibility(View.GONE);

            mPresenter.queryCompanyTitle();
            mBinding.mainTvCompanyTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mCardBeanList != null && mCardBeanList.size() > 0) {
                        getPopupWindow();
                    } else {
                        mPresenter.queryCompanyTitle();
                    }
                }
            });
        }
        mBinding.setTitle(title);
    }

    @Override
    public void queryCompanySuccess(CompanyRoot mCompanyRoot) {
        mCardBeanList = mCompanyRoot.getData();
        // 创建PopupWindow实例,200,LayoutParams.MATCH_PARENT分别是宽度和高度
        if (mCardBeanList.size() > 0) {
            getPopupWindow();
        }
    }

    List<Data> dataList;

    @Override
    public void queryCompanyTitleSuccess(CompanyRoot mCompanyRoot) {
        dataList = mCompanyRoot.getData();
        if (mCompanyFragment != null) {
            if (!mCompanyFragment.isHidden() && dataList.size() > 0) {
                getPopupWindow();
            }
        }
    }

    @Override
    public void OnHttpListenerFailed(String error) {

    }

    private void setTabSelection(int position) {
        currentFragment = position;
        FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
        hideFragment();
        switch (position) {
            case HOME:
                if (mHomeFragment == null) {
                    mHomeFragment = new HomeFragment();
                    mHomeFragment.setViewPagerChangedCallBack(new HomeFragment.viewPagerChangedCallBack() {
                        @Override
                        public void changed(String companyName, boolean isCompanyManager) {
                            if (showCompanyCallBack != null) {
                                showCompanyCallBack.showCompany(companyName, isCompanyManager);
                            }
                        }
                    });
                    mFragmentTransaction.add(R.id.main_fragment_contain, mHomeFragment);
                    mFragmentList.add(mHomeFragment);
                } else {
                    mFragmentTransaction.show(mHomeFragment);
                }
                break;
            case INVOICE:
                if (mInvoiceFragment == null) {
                    mInvoiceFragment = new InvoiceFragment();
                    mFragmentTransaction.add(R.id.main_fragment_contain, mInvoiceFragment);
                    mFragmentList.add(mInvoiceFragment);
                } else {
                    mFragmentTransaction.show(mInvoiceFragment);
                }
                break;
            case COMPANY:
                if (mCompanyFragment == null) {
                    mCompanyFragment = new CompanyFragment();
                    mFragmentTransaction.add(R.id.main_fragment_contain, mCompanyFragment);
                    mFragmentList.add(mCompanyFragment);
                } else {
                    mFragmentTransaction.show(mCompanyFragment);
                }
                break;
            case INFO:
                if (mSettingFragment == null) {
                    mSettingFragment = new SettingFragment();
                    mFragmentTransaction.add(R.id.main_fragment_contain, mSettingFragment);
                    mFragmentList.add(mSettingFragment);
                } else {
                    mFragmentTransaction.show(mSettingFragment);
                }
                break;
        }
        mFragmentTransaction.commitAllowingStateLoss();
    }

    private void hideFragment() {
        if (mFragmentList.size() > 0) {
            for (Fragment mFragment : mFragmentList) {
                FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
                mFragmentTransaction.hide(mFragment);
                mFragmentTransaction.commitAllowingStateLoss();
            }
        }
    }

    @Override
    public void hintMessage(String msg) {

    }

    @Override
    public Context getViewContext() {
        return this;
    }

    protected void initPopuptWindow() {
        View popupWindow_view = getLayoutInflater().inflate(R.layout.list_main_company, null, false);
        ListView listView = (ListView) popupWindow_view.findViewById(R.id.main_company_list);
        // 创建PopupWindow实例,200,LayoutParams.MATCH_PARENT分别是宽度和高度
        CompanyListAdapter comListAdapter = new CompanyListAdapter(dataList, this);
        listView.setAdapter(comListAdapter);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        System.out.println("heigth : " + dm.heightPixels);

        popupWindow = new PopupWindow(popupWindow_view, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, true);

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.alpha = 0.7f;
        getWindow().setAttributes(params);

        popupWindow.showAsDropDown(mBinding.mainRlTitle);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Data data = dataList.get(i);
                CustomApplication.currentCompanyName = data.getNAME();

                if (data.getUSERID().equals(CustomApplication.userId)) {
                    CustomApplication.isCompanyManager = true;
                } else {
                    CustomApplication.isCompanyManager = false;
                }
                mBinding.mainTvCompanyTitle.setText(data.getNAME());
                // TODO: 2016/9/29 修改title 传值给Fragment 跟新页面
                if (showCompanyCallBack != null) {
                    showCompanyCallBack.showCompany(data.getNAME(), CustomApplication.isCompanyManager);
                    CustomApplication.updateCompany = true;
                }
                popupWindow.dismiss();
                WindowManager.LayoutParams params = getWindow().getAttributes();
                params.alpha = 1f;
                getWindow().setAttributes(params);
                popupWindow = null;
            }
        });

        // 点击其他地方消失
        popupWindow_view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (popupWindow != null && popupWindow.isShowing()) {
                    popupWindow.dismiss();
                    WindowManager.LayoutParams params = getWindow().getAttributes();
                    params.alpha = 1f;
                    getWindow().setAttributes(params);
                    popupWindow = null;
                }
                return false;
            }
        });
    }

    private void getPopupWindow() {
        if (null != popupWindow) {
            popupWindow.dismiss();
            return;
        } else {
            initPopuptWindow();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {


        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                mExitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public showCompanyCallBack showCompanyCallBack;
    public updateCompanyCallBack updateCompanyCallBack;

    public interface showCompanyCallBack {
        void showCompany(String companyName, boolean isCompanyManager);
    }

    public interface updateCompanyCallBack {
        void updateCompany();
    }

    public void setShowCompanyCallBack(showCompanyCallBack showCompanyCallBack) {
        this.showCompanyCallBack = showCompanyCallBack;
    }

    public void setUpdateCompanyCallBack(updateCompanyCallBack updateCompanyCallBack) {
        this.updateCompanyCallBack = updateCompanyCallBack;
    }


}
