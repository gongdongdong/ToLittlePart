package com.gdd.tolittlepart.Adapters;

import android.support.annotation.AnimRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.gdd.base.GlideFacede.ImageLoader;
import com.gdd.beans.DatasBean;
import com.gdd.beans.NewsSummary;
import com.gdd.tolittlepart.R;

import java.util.Collections;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter implements ItemDragHelperCallback.OnItemMoveListener{

    private final int FOOT_VIEW = 1;
    private final int NORMAL_VIEW = 2;
    private List<NewsSummary> showingDatas;
    private ItemDragHelperCallback mItemDragHelperCallback;
    private int mLastPosition = -1;

    public void setShowFootView(boolean showFootView) {
        isShowFootView = showFootView;
    }

    private boolean isShowFootView;

    public RecyclerViewAdapter(List<NewsSummary> showingDatas){
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
            if(!isShowFootView){
                footholder.itemView.setVisibility(View.GONE);
            }
            else{
                footholder.itemView.setVisibility(View.VISIBLE);
            }
        }
        else{
            if(showingDatas != null && showingDatas.size() > 0) {
                ItemView itemholder = (ItemView) holder;
                NewsSummary onedata = showingDatas.get(position);
                ImageLoader.load(itemholder.iv_show_summary.getContext(),
                        onedata.getImgsrc(), itemholder.iv_show_summary);
                itemholder.tv_first.setText(onedata.getTname());
                itemholder.tv_second.setText(onedata.getTitle());
                if("".equals(onedata.getAlias()) || null == onedata.getAlias()){
                    itemholder.tv_third.setText(" no alias ");
                }else{
                    itemholder.tv_third.setText(onedata.getAlias());
                }
                setItemAppearAnimation(holder, position, R.anim.anim_bottom_in);
                handleLongPress(itemholder);
            }
        }
    }

    protected void setItemAppearAnimation(RecyclerView.ViewHolder holder, int position, @AnimRes int type) {
        if (position > mLastPosition/* && !isFooterPosition(position)*/) {
            Animation animation = AnimationUtils.loadAnimation(holder.itemView.getContext(), type);
            holder.itemView.startAnimation(animation);
            mLastPosition = position;
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

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(showingDatas, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    @Override
    public void onViewDetachedFromWindow(RecyclerView.ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        if (isShowingAnimation(holder)) {
            holder.itemView.clearAnimation();
        }
    }

    private boolean isShowingAnimation(RecyclerView.ViewHolder holder) {
        return holder.itemView.getAnimation() != null && holder.itemView
                .getAnimation().hasStarted();
    }

    public void setItemDragHelperCallback(ItemDragHelperCallback itemDragHelperCallback) {
        mItemDragHelperCallback = itemDragHelperCallback;
    }

    private void handleLongPress(final ItemView newsChannelViewHolder) {
        if (mItemDragHelperCallback != null) {
            newsChannelViewHolder.itemView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    mItemDragHelperCallback.setLongPressEnabled(true);
                    return false;
                }
            });
        }
    }
}
