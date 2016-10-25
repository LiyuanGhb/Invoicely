package com.dzfp.dzfp.ui.adapter;

import android.content.Context;
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

public class AddProcessAdapter extends BaseAdapter {
    private List<CompanyUserListBean.DataBean> mDataBeen;
    private Context mContext;

    public AddProcessAdapter(List<CompanyUserListBean.DataBean> mDataBeen, Context mContext) {
        this.mDataBeen = mDataBeen;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        if (mDataBeen.size() > 0) {
            return mDataBeen.size();
        } else {
            return 0;
        }
    }

    @Override
    public Object getItem(int position) {
        return mDataBeen.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder mViewHolder;
        if (convertView == null) {
            mViewHolder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.addprocess_item, null);
            mViewHolder.mTopTv = (TextView) convertView.findViewById(R.id.ap_item_centerTv);
            mViewHolder.mBottomTv = (TextView) convertView.findViewById(R.id.ap_item_bottomTv);
            convertView.setTag(mViewHolder);
        }
        mViewHolder = (ViewHolder) convertView.getTag();
        if (mDataBeen.get(position).getZSXM().equals("ADMIN")) {
            mViewHolder.mTopTv.setText("添加");
            mViewHolder.mBottomTv.setVisibility(View.GONE);
        } else {
            if (mDataBeen.get(position).getZSXM().length() > 2) {
                mViewHolder.mTopTv.setText(mDataBeen.get(position).getZSXM().substring(0, 2));
            } else {
                mViewHolder.mTopTv.setText(mDataBeen.get(position).getZSXM());
            }
            mViewHolder.mBottomTv.setText(mDataBeen.get(position).getZSXM());
            mViewHolder.mBottomTv.setVisibility(View.VISIBLE);
        }
        return convertView;
    }

    public class ViewHolder {
        TextView mTopTv;
        TextView mBottomTv;
    }

}
