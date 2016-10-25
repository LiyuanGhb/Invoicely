package com.dzfp.dzfp.ui.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.dzfp.dzfp.R;
import com.dzfp.dzfp.databinding.SqxqListItemBinding;
import com.dzfp.dzfp.model.BxlcxqBean;

import java.util.List;

/**
 * Created by Administrator on 2016/8/30.
 */

public class SPXQAdapter extends BaseAdapter {
    private Context mContext;
    private List<BxlcxqBean.DataBean.OrderDetailBean> mOrderDetailBeanList;
    private SqxqListItemBinding mBinding;

    public SPXQAdapter(Context mContext, List<BxlcxqBean.DataBean.OrderDetailBean> mOrderDetailBeanList) {
        this.mContext = mContext;
        this.mOrderDetailBeanList = mOrderDetailBeanList;
    }

    @Override
    public int getCount() {
        return mOrderDetailBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return mOrderDetailBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.sqxq_list_item, parent, false);
            mBinding = DataBindingUtil.bind(convertView);
            convertView.setTag(mBinding);
        }
        mBinding = (SqxqListItemBinding) convertView.getTag();

        BxlcxqBean.DataBean.OrderDetailBean mOrderDetailBean = mOrderDetailBeanList.get(position);
        mBinding.sliDescribe.setText(mOrderDetailBean.getZSXM());
        mBinding.sliImage.setText(mOrderDetailBean.getZSXM());
        String status = mOrderDetailBean.getStatus();
        switch (status) {
            case "等待报销":
                mBinding.sliStatus.setBackground(mContext.getResources().getDrawable(R.drawable.sq_state1));
                break;
            case "已通过":
                mBinding.sliStatus.setBackground(mContext.getResources().getDrawable(R.drawable.sq_state2));
                break;
            case "不通过":
                mBinding.sliStatus.setBackground(mContext.getResources().getDrawable(R.drawable.sq_state3));
                break;
        }
        mBinding.sliTime.setText(mOrderDetailBean.getExc_time());
        mBinding.sliState.setText(status);
        return convertView;
    }
}
