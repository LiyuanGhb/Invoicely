package com.dzfp.dzfp.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.dzfp.dzfp.R;
import com.dzfp.dzfp.model.OrderAuditOfMeBean;

import java.util.List;


public class OrderAuditOfMeAdapter extends BaseAdapter{
    private List<OrderAuditOfMeBean.DataBean> mDataBeanList;
    private Context mContext;

    public OrderAuditOfMeAdapter(List<OrderAuditOfMeBean.DataBean> mDataBeanList, Context mContext) {
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
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.checking, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.name = (TextView) convertView.findViewById(R.id.checking_name);
            viewHolder.no = (Button) convertView.findViewById(R.id.checking_no_btn);
            viewHolder.ok = (Button) convertView.findViewById(R.id.checking_ok_btn);
            viewHolder.status = (TextView) convertView.findViewById(R.id.checking_stute);
            viewHolder.phone = (TextView) convertView.findViewById(R.id.checking_phone);
            viewHolder.ly = (TextView) convertView.findViewById(R.id.checking_ly);
            convertView.setTag(viewHolder);
        }
        viewHolder = (ViewHolder) convertView.getTag();
        final OrderAuditOfMeBean.DataBean mDataBean = mDataBeanList.get(position);
        viewHolder.ok.setVisibility(View.GONE);
        viewHolder.no.setVisibility(View.GONE);


        viewHolder.name.setText("申请类型:报销申请");
        String state = "";
        switch (mDataBean.getSTATUS()) {
            case 0:
                state = "正在等待审批";
                break;
            case 1:
                state = "申请失败";
                break;
            case 999:
                state = "申请成功";
                break;
        }
        viewHolder.phone.setText("申请时间:" + mDataBean.getCREATETIME());
        viewHolder.ly.setVisibility(View.VISIBLE);
        viewHolder.ly.setText("申请进度:" + state);
        return convertView;
    }

    public class ViewHolder {
        TextView name, status, phone, ly;
        Button ok, no;
    }
}
