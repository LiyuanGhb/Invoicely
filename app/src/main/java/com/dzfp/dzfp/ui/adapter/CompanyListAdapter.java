package com.dzfp.dzfp.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dzfp.dzfp.R;
import com.dzfp.dzfp.model.Card.Data;

import java.util.List;

/**
 * Created by Administrator on 2016/9/29.
 */

public class CompanyListAdapter extends BaseAdapter {
    private List<Data> mDataList;
    private Context context;

    public CompanyListAdapter(List<Data> dataList, Context context) {
        mDataList = dataList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return mDataList.size();
    }

    @Override
    public Object getItem(int position) {
        return mDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.main_company_listitem,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.mTextView = (TextView) convertView.findViewById(R.id.main_company_item);
            convertView.setTag(viewHolder);
        }
        viewHolder = (ViewHolder) convertView.getTag();
        viewHolder.mTextView.setText(mDataList.get(position).getNAME());
        return convertView;
    }

    private class ViewHolder{
        TextView mTextView;
    }


}
