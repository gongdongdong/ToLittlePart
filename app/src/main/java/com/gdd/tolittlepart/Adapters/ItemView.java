package com.gdd.tolittlepart.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gdd.tolittlepart.R;

public class ItemView extends RecyclerView.ViewHolder {

    public ImageView iv_show_summary;
    public TextView tv_first;
    public TextView tv_second;
    public TextView tv_third;
    public ItemView(View itemView) {
        super(itemView);
        iv_show_summary = itemView.findViewById(R.id.iv_show_summary);
        tv_first = itemView.findViewById(R.id.tv_first);
        tv_second = itemView.findViewById(R.id.tv_second);
        tv_third = itemView.findViewById(R.id.tv_third);
    }
}
