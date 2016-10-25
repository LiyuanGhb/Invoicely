package com.dzfp.dzfp.ui.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.dzfp.dzfp.CustomApplication;
import com.dzfp.dzfp.R;
import com.dzfp.dzfp.databinding.WfqdLayoutBinding;
import com.dzfp.dzfp.model.WfqdBean;

import java.util.List;


public class WfqdAdapter extends BaseAdapter {
    private List<WfqdBean.DataBean> mDataBeanList;
    private Context mContext;
    private WfqdLayoutBinding mBinding;

    public WfqdAdapter(List<WfqdBean.DataBean> mDataBeanList, Context mContext) {
        this.mDataBeanList = mDataBeanList;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mDataBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return mDataBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.wfqd_layout, parent, false);
            mBinding = DataBindingUtil.bind(convertView);
            convertView.setTag(mBinding);
        }
        mBinding = (WfqdLayoutBinding) convertView.getTag();

        WfqdBean.DataBean mDataBean = mDataBeanList.get(position);
        String userName = CustomApplication.ZSXM;
        String spState = null;
        switch (mDataBean.getSTATUS()) {
            case 0:
                spState = "正在等待审批";
                break;
            case 1:
                spState = "审批完成(不通过)";
                break;
            case 2:
                spState = "已撤销";
                break;
            case 999:
                spState = "审批完成(通过)";
                break;
        }
        String[] time = mDataBean.getCREATETIME().split(" ")[0].split("-");
        Log.i("System", "getView: " + mDataBean.getCREATETIME());
        mBinding.wfqdDescribe.setText(userName + "的报销申请");
        mBinding.wfqdState.setText(spState);
        mBinding.wfqdTime.setText(time[0] + "." + time[1] + "." + time[2]);
        return convertView;
    }
}
