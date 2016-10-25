package com.dzfp.dzfp.ui.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.dzfp.dzfp.R;
import com.dzfp.dzfp.model.CompanyUserListBean;

import java.util.List;

/**
 * Created by Administrator on 2016/8/26.
 */

public class SelectStaffAdapter extends BaseAdapter {
    private Context mContext;
    private List<CompanyUserListBean.DataBean> mDataBeanList;

    public SelectStaffAdapter(Context mContext, List<CompanyUserListBean.DataBean> mDataBeanList) {
        this.mContext = mContext;
        this.mDataBeanList = mDataBeanList;
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
        ViewHolder mViewHolder;
        if (convertView == null) {
            mViewHolder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.selectstaff_item, null);
            mViewHolder.centerTv = (TextView) convertView.findViewById(R.id.selectstaff_tv);
            mViewHolder.nameTv = (TextView) convertView.findViewById(R.id.selectstaff_userName);
            mViewHolder.zsxmTv = (TextView) convertView.findViewById(R.id.selectstaff_zsxm);
            convertView.setTag(mViewHolder);
        }
        mViewHolder = (ViewHolder) convertView.getTag();
        CompanyUserListBean.DataBean mDataBean = mDataBeanList.get(position);
        if (mDataBean.getZSXM().length() > 2) {
            mViewHolder.centerTv.setText(mDataBean.getZSXM().substring(0, 2));
        } else {
            mViewHolder.centerTv.setText(mDataBean.getZSXM());
        }
        mViewHolder.nameTv.setText(mDataBean.getZSXM());
        String str = TextUtils.isEmpty(mDataBean.getUSERNAME()) ? "暂无电话号码" : mDataBean.getUSERNAME();
        mViewHolder.zsxmTv.setText(str);
        return convertView;
    }

    public class ViewHolder {
        TextView centerTv;
        TextView nameTv;
        TextView zsxmTv;
    }
}
