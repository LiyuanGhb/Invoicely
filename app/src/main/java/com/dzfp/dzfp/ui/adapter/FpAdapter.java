package com.dzfp.dzfp.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.dzfp.dzfp.R;
import com.dzfp.dzfp.model.BxlcxqBean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class FpAdapter extends BaseAdapter {
    private List<BxlcxqBean.DataBean.FpDetailBean> mFpDetailBeanList;
    private Context mContext;

    public FpAdapter(List<BxlcxqBean.DataBean.FpDetailBean> mFpDetailBeanList, Context mContext) {
        this.mFpDetailBeanList = mFpDetailBeanList;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mFpDetailBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return mFpDetailBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder mViewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_rebment_item, parent, false);
            mViewHolder = new ViewHolder();
            mViewHolder.tv_fpdm = (TextView) convertView.findViewById(R.id.tv_fpdm);
            mViewHolder.tv_fphm = (TextView) convertView.findViewById(R.id.tv_fphm);
            mViewHolder.tv_bxje = (TextView) convertView.findViewById(R.id.tv_bxje);
            mViewHolder.tv_kpsj = (TextView) convertView.findViewById(R.id.tv_kpsj);
            mViewHolder.tv_kpf = (TextView) convertView.findViewById(R.id.tv_kpf);
            mViewHolder.tv_ghf = (TextView) convertView.findViewById(R.id.tv_ghf);
            convertView.setTag(mViewHolder);
        }
        mViewHolder = (ViewHolder) convertView.getTag();
        BxlcxqBean.DataBean.FpDetailBean mFpDetailBean = mFpDetailBeanList.get(position);

        mViewHolder.tv_fpdm.setText(mFpDetailBean.getFPDM());
        mViewHolder.tv_fphm.setText(mFpDetailBean.getFPHM());
        mViewHolder.tv_bxje.setText(mFpDetailBean.getJSHJ());
        // FIXME: 2016/9/1 等会来
        String time = mFpDetailBean.getKPRQ() + mFpDetailBean.getKPSJ();
        try {
            Date mDate = new SimpleDateFormat("yyyyMMddHHmmss").parse(time);
            time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(mDate);
        } catch (ParseException mE) {
            mE.printStackTrace();
        }


        mViewHolder.tv_kpsj.setText(time);
        mViewHolder.tv_kpf.setText(mFpDetailBean.getXHDWMC());
        mViewHolder.tv_ghf.setText(mFpDetailBean.getGHDWMC());
        return convertView;
    }

    public class ViewHolder {
        TextView tv_fpdm, tv_fphm, tv_bxje, tv_kpsj, tv_kpf, tv_ghf;
    }
}
