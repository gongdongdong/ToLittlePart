package com.gdd.tolittlepart.Adapters;

import android.content.Context;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.gdd.base.GlideFacede.ImageLoader;
import com.gdd.beans.ProjectListData;
import com.gdd.tolittlepart.R;

import java.util.List;

public class ProjectQuickAdapter extends BaseQuickAdapter<ProjectListData.ProjectData, BaseViewHolder> {
    Context context;
    public ProjectQuickAdapter(Context context, List<ProjectListData.ProjectData> list) {
        super(R.layout.item_project_list, list);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, ProjectListData.ProjectData projectInfo) {
        helper.setText(R.id.item_project_list_title_tv, projectInfo.getTitle())
                .setText(R.id.item_project_list_content_tv, projectInfo.getDesc())
                .setText(R.id.item_project_list_time_tv, projectInfo.getNiceDate())
                .setText(R.id.item_project_list_author_tv, projectInfo.getAuthor());

        ImageLoader.load(context,projectInfo.getEnvelopePic(),(ImageView) helper.getView(R.id.item_project_list_iv));
    }
}
