package com.gdd.tolittlepart.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gdd.tolittlepart.R;
import com.gdd.tolittlepart.beans.DatasBean;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter {

    private final int FOOT_VIEW = 1;
    private final int NORMAL_VIEW = 2;
    private List<DatasBean> showingDatas;
    public RecyclerViewAdapter(List<DatasBean> showingDatas){
        this.showingDatas = showingDatas;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == FOOT_VIEW){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.foot_layout, parent, false);
            return new FootView(view);
        }
        else{
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
            return new ItemView(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof FootView){
            FootView footholder = (FootView)holder;
        }
        else{
            if(showingDatas != null && showingDatas.size() > 0) {
                ItemView footholder = (ItemView) holder;
                DatasBean onedata = showingDatas.get(position);
                footholder.tv_first.setText(onedata.getAuthor());
                footholder.tv_second.setText(onedata.getTitle());
                if("".equals(onedata.getProjectLink()) || null == onedata.getProjectLink()){
                    footholder.tv_third.setText(" no project link here");
                }else{
                    footholder.tv_third.setText(onedata.getProjectLink());
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return showingDatas.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if(position == showingDatas.size()){
            return FOOT_VIEW;
        }
        else{
            return NORMAL_VIEW;
        }
    }
}
