package com.gdd.tolittlepart.views.main.cardviewfragment;

import android.os.Bundle;

import com.gdd.base.component.BaseFragment;
import com.gdd.tolittlepart.R;

public class CardViewFragment extends BaseFragment {

    public static CardViewFragment getInstance() {
        return new CardViewFragment();
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutID() {
        return R.layout.cardview_layout_fragment;
    }
}
