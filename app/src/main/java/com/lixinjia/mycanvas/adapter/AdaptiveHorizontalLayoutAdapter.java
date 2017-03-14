package com.lixinjia.mycanvas.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.lixinjia.mycanvas.R;

import java.util.List;

public class AdaptiveHorizontalLayoutAdapter extends BaseAdapter {

    private Context mContext = null;
    private List<String> data;

    public AdaptiveHorizontalLayoutAdapter(Context ctx, List<String> data) {
        mContext = ctx;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
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

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(mContext,data.get(position),Toast.LENGTH_SHORT);
                }
            });
        } else {// 有直接获得ViewHolder
            holder = (ViewHolder)convertView.getTag();
        }
        holder.text.setText(data.get(position).toString());
        return convertView;
    }
    static class ViewHolder {
        TextView text;
    }
}
