package com.dzfp.dzfp.ui.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.dzfp.dzfp.BuildConfig;
import com.dzfp.dzfp.CustomApplication;
import com.dzfp.dzfp.QRUtil;
import com.dzfp.dzfp.R;
import com.dzfp.dzfp.control.HomeFragmentControl;
import com.dzfp.dzfp.databinding.HomeFragmentBinding;
import com.dzfp.dzfp.model.Card.CompanyRoot;
import com.dzfp.dzfp.model.Card.Data;
import com.dzfp.dzfp.presenter.HomeFragmentPresenter;
import com.dzfp.dzfp.ui.activity.CurrentActivity;
import com.dzfp.dzfp.ui.activity.CurrentInfoActivity;
import com.dzfp.dzfp.ui.activity.MainAty;
import com.dzfp.dzfp.ui.activity.UpdateCompanyAty;
import com.dzfp.dzfp.ui.adapter.ViewPagerAdapter;
import com.dzfp.dzfp.ui.util.DialogHb;
import com.dzfp.dzfp.ui.util.NetWorkUtil;

import java.util.List;

import static android.content.ContentValues.TAG;
import static com.dzfp.dzfp.R.mipmap.company;


public class HomeFragment extends Fragment implements ViewPager.OnPageChangeListener, View.OnClickListener,
        HomeFragmentControl.HomeFragmentView,
        ViewPagerAdapter.showimg, MainAty.updateCompanyCallBack {
    private HomeFragmentBinding mBinding;
    private HomeFragmentPresenter mPresenter;
    private List<Data> mCardBeanList;
    public static final int ADD_COMPANY = 1; //添加公司标识符
    public static final int UPDATE_COMPANY = 2;//更新公司标识符
    public static final int NOT_FUNCTION = 3;//无任何操作
    public static PopupWindow popupWindow;
    private int currentIndex;
    private int index;


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof MainAty) {
            ((MainAty) activity).setUpdateCompanyCallBack(this);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.bind(inflater.inflate(R.layout.home_fragment, container, false));
        initParameters();
        return mBinding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (CustomApplication.index) {
            mPresenter.queryCompany();
            CustomApplication.index = false;
        } else if (CustomApplication.isIntent == 1 && NetWorkUtil.isNetworkConnected(getActivity())) {
            mPresenter.queryCompany();
            CustomApplication.isIntent = 0;
        }
    }

    private void initParameters() {
        mPresenter = new HomeFragmentPresenter(this);
        mBinding.setOnClickListener(this);
        mBinding.viewpagerContain.addOnPageChangeListener(this);
        mPresenter.queryCompany();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        mBinding.companyDescribe.setText(mCardBeanList.get(position).getNAME());
        // TODO: 2016/9/29 添加修改 获取当前viewpager公司信息
        int currentPosition = mBinding.viewpagerContain.getCurrentItem();
        Data data = mCardBeanList.get(currentPosition);
        CustomApplication.currentCompanyName = data.getNAME();
        boolean isCompanyManager;
        if (data.getUSERID().equals(CustomApplication.userId)) {
            isCompanyManager = true;
        } else {
            isCompanyManager = false;
        }
        if (mCallBack != null) {
            mCallBack.changed(data.getNAME(), isCompanyManager);
        }
    }

    private viewPagerChangedCallBack mCallBack;

    public interface viewPagerChangedCallBack {
        public void changed(String companyName, boolean isCompanyManager);
    }

    public void setViewPagerChangedCallBack(viewPagerChangedCallBack callBack) {
        mCallBack = callBack;
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.home_btn_qure:
                Intent mIntents = new Intent(getActivity(), CurrentInfoActivity.class);
                mIntents.putExtra("companyBean",
                        mCardBeanList.get(mBinding.viewpagerContain.getCurrentItem()));
                startActivityForResult(mIntents, UPDATE_COMPANY);
                break;
            case R.id.home_btn_add:
                startActivityForResult(new Intent(getActivity(), CurrentActivity.class), ADD_COMPANY);
                break;
            case R.id.home_btn_update:
                if (mCardBeanList.get(mBinding.viewpagerContain.getCurrentItem()).getUPLOAD().equals("2")) {
                    DialogHb.showdialog(getActivity(), "不能修改已认证企业");
                } else if (mCardBeanList.get(mBinding.viewpagerContain.getCurrentItem()).getUPLOAD().equals("1")) {
                    DialogHb.showdialog(getActivity(), "不能修改正在认证的企业");
                } else {
                    Intent mIntent = new Intent(getActivity(), UpdateCompanyAty.class);
                    mIntent.putExtra("companyBean",
                            mCardBeanList.get(mBinding.viewpagerContain.getCurrentItem()));
                    startActivityForResult(mIntent, UPDATE_COMPANY);
                }
                break;
            case R.id.home_btn_delete:
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(getActivity());
                mBuilder.setTitle("请确认删除信息");
                mBuilder.setMessage(mCardBeanList.get(mBinding.viewpagerContain.getCurrentItem()).getNAME());
                mBuilder.setNegativeButton("取消", null);
                mBuilder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (mCardBeanList.get(mBinding.viewpagerContain.getCurrentItem()).getUPLOAD().equals("2")) {
                            DialogHb.showdialog(getActivity(), "不能删除已认证企业");
                        } else if (mCardBeanList.get(mBinding.viewpagerContain.getCurrentItem()).getUPLOAD().equals("1")) {
                            DialogHb.showdialog(getActivity(), "不能删除正在认证的企业");
                        } else {
                            mPresenter.deleteCompany(mCardBeanList.get(mBinding.viewpagerContain.getCurrentItem()).getID());
                        }
                    }
                });
                mBuilder.create();
                mBuilder.show();
                break;
            case R.id.Home_createCompanyFirst:
                startActivityForResult(new Intent(getActivity(), CurrentActivity.class), ADD_COMPANY);
                break;
            case R.id.home_img_left:
                currentIndex = mBinding.viewpagerContain.getCurrentItem();
                index = currentIndex > 0 ? currentIndex - 1 : 0;
                mBinding.viewpagerContain.setCurrentItem(index);
                break;
            case R.id.home_img_right:
                currentIndex = mBinding.viewpagerContain.getCurrentItem();
                index = currentIndex < mCardBeanList.size()
                        ? currentIndex + 1 : mCardBeanList.size();
                mBinding.viewpagerContain.setCurrentItem(index);
                break;
        }
    }

    @Override
    public void hintMessage(String msg) {
        Log.i(TAG, "hintMessage: " + msg);
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public Context getViewContext() {
        return getActivity();
    }


    @Override
    public void queryCompanySuccess(CompanyRoot mCompanyRoot) {
        mCardBeanList = mCompanyRoot.getData();
        Log.e(TAG, "mCardBeanList: " + mCardBeanList.size());
        if (mCardBeanList.size() > 0) {
            userHaveCompany();
            // TODO: 2016/9/29 company
            CustomApplication.currentCompanyName = mCardBeanList.get(0).getNAME();
            if (mCardBeanList.get(0).getUSERID().equals(CustomApplication.userId)) {
                CustomApplication.isCompanyManager = true;
            } else {
                CustomApplication.isCompanyManager = false;
            }
            ViewPagerAdapter mViewPagerAdapter = new ViewPagerAdapter(getActivity(), mCardBeanList, this);
            mBinding.viewpagerContain.setAdapter(mViewPagerAdapter);
            mBinding.companyDescribe.setText(mCardBeanList.get(mBinding.viewpagerContain.getCurrentItem()).getNAME());
        } else {
            userNotHaveCompany();
        }
    }


    @Override
    public void OnHttpListenerFailed(String error) {

    }

    @Override
    public void deleteCompanySuccess() {
        mPresenter.queryCompany();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == resultCode) {
            switch (resultCode) {
                case ADD_COMPANY:
                    mPresenter.queryCompany();
                    break;
                case UPDATE_COMPANY:
                    mPresenter.queryCompany();
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void userHaveCompany() {
        mBinding.viewpagerContain.setVisibility(View.VISIBLE);
        mBinding.companyDescribe.setVisibility(View.VISIBLE);
        mBinding.homeBtnContain.setVisibility(View.VISIBLE);
        mBinding.homeImgLeft.setVisibility(View.VISIBLE);
        mBinding.homeImgRight.setVisibility(View.VISIBLE);
        mBinding.HomeCreateCompanyFirst.setVisibility(View.GONE);
        mBinding.HomeCreteCompanyHint.setVisibility(View.GONE);
    }

    private void userNotHaveCompany() {
        mBinding.HomeCreateCompanyFirst.setVisibility(View.VISIBLE);
        mBinding.HomeCreteCompanyHint.setVisibility(View.VISIBLE);
        mBinding.homeImgLeft.setVisibility(View.GONE);
        mBinding.homeImgRight.setVisibility(View.GONE);
        mBinding.viewpagerContain.setVisibility(View.GONE);
        mBinding.companyDescribe.setVisibility(View.GONE);
        mBinding.homeBtnContain.setVisibility(View.GONE);
    }


    /**
     * 创建PopupWindow
     */
    protected void initPopuptWindow(String rever) {
        // 获取自定义布局文件activity_popupwindow_left.xml的视图
        View popupWindow_view = getActivity().getLayoutInflater().inflate(R.layout.poplayout, null,
                false);
        ImageView imageView = (ImageView) popupWindow_view.findViewById(R.id.poo_img);
        imageView.setImageBitmap(QRUtil.createQRImage(rever));
        // 创建PopupWindow实例,200,LayoutParams.MATCH_PARENT分别是宽度和高度
        popupWindow = new PopupWindow(popupWindow_view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        // 点击其他地方消失
        popupWindow_view.setFocusable(true);//comment by danielinbiti,设置view能够接听事件，标注1
        popupWindow_view.setFocusableInTouchMode(true);
        popupWindow_view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (popupWindow != null && popupWindow.isShowing()) {
                    popupWindow.dismiss();
                    popupWindow = null;
                }
                return false;
            }
        })
        ;
        popupWindow_view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View arg0, int arg1, KeyEvent arg2) {
                if (arg1 == KeyEvent.KEYCODE_BACK) {
                    if (popupWindow != null && popupWindow.isShowing()) {
                        popupWindow.dismiss();
                        popupWindow = null;
                    }
                }
                return false;
            }
        });
    }

    ;

    /***
     * 获取PopupWindow实例
     */

    private void getPopupWindow(String r) {
        if (null != popupWindow) {
            popupWindow.dismiss();
            return;
        } else {
            initPopuptWindow(r);
        }
    }

    @Override
    public void show(String s) {
        getPopupWindow(s);
        popupWindow.showAtLocation(mBinding.viewpagerContain, Gravity.CENTER, 0, 0);
    }

    @Override
    public void updateCompany() {
        // TODO: 2016/9/29 company
        if (BuildConfig.DEBUG) Log.d(TAG, "company:" + company);
        if (CustomApplication.updateCompany) {
            for (int i = 0; i < mCardBeanList.size(); i++) {
                if (CustomApplication.currentCompanyName.equals(mCardBeanList.get(i).getNAME())) {
                    mBinding.viewpagerContain.setCurrentItem(i);
                }
            }
        }
    }
}
