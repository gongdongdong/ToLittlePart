package com.gdd.tolittlepart.views.main.newsfragment;

import android.os.Bundle;
import android.widget.TextView;

import com.gdd.base.component.BaseFragment;
import com.gdd.tolittlepart.R;

import butterknife.BindView;

public class NewsFragment extends BaseFragment {

    @BindView(R.id.tv_show_content)
    TextView tv_show_content;

    public static NewsFragment getInstance() {
        return new NewsFragment();
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initView() {
        tv_show_content.setText("showing content set by the butterknife");
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_news_layout;
    }
}
