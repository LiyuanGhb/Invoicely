package com.dzfp.dzfp.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dzfp.dzfp.R;
import com.dzfp.dzfp.model.InvoiceInfoBean;

import java.util.List;

/**
 * Created by Administrator on 2016/8/29.
 */

public class ListRebmentAdapter extends BaseAdapter {
    Context context;
    List<InvoiceInfoBean> list;

    public ListRebmentAdapter(Context context, List<InvoiceInfoBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        if (list.size() == 0) {
            return 0;
        }
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.list_rebment_item, null);
            viewHolder = new ViewHolder();
            viewHolder.fpdm = (TextView) view.findViewById(R.id.tv_fpdm);
            viewHolder.fphm = (TextView) view.findViewById(R.id.tv_fphm);
            viewHolder.ghf = (TextView) view.findViewById(R.id.tv_ghf);
            viewHolder.kpf = (TextView) view.findViewById(R.id.tv_kpf);
            viewHolder.kpje = (TextView) view.findViewById(R.id.tv_bxje);
            viewHolder.kprq = (TextView) view.findViewById(R.id.tv_kpsj);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        InvoiceInfoBean invoiceInfoBean = list.get(i);
        viewHolder.kpje.setText(invoiceInfoBean.getMoney());
        viewHolder.kprq.setText(invoiceInfoBean.getTime());
        viewHolder.kpf.setText(invoiceInfoBean.getKpf());
        viewHolder.ghf.setText(invoiceInfoBean.getShf());
        viewHolder.fpdm.setText(invoiceInfoBean.getFpdm());
        viewHolder.fphm.setText(invoiceInfoBean.getFphm());

        return view;
    }

    public class ViewHolder {
        TextView fphm, fpdm, kprq, kpje, ghf, kpf;
    }

}
