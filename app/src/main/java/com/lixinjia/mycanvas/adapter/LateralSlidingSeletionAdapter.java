package com.lixinjia.mycanvas.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lixinjia.mycanvas.R;

import java.util.List;

public class LateralSlidingSeletionAdapter extends BaseAdapter {

    private Context mContext = null;
    private String[] data;
    private int selectIndex = 0;

    public LateralSlidingSeletionAdapter(Context ctx, String[] data) {
        mContext = ctx;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_adaptive_horizontal_layout, parent, false);
            holder = new ViewHolder();
            holder.text = (TextView)convertView.findViewById(R.id.item_adaptive_horizontal_layout_text);
            convertView.setTag(holder);
        } else {// 有直接获得ViewHolder
            holder = (ViewHolder)convertView.getTag();
        }
        holder.text.setText(data[position].toString());
        if(selectIndex == position){
            holder.text.setTextColor(Color.RED);
            holder.text.setTextSize(20);
        }else{
            holder.text.setTextColor(Color.BLACK);
            holder.text.setTextSize(17);
        }
        return convertView;
    }
    static class ViewHolder {
        TextView text;
    }

    public void setSelectIndex(int index){
        this.selectIndex = index;
    }
}
