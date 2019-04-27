package com.gdd.tolittlepart.views.main;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.gdd.base.component.BaseActivity;
import com.gdd.tolittlepart.R;

@Route(path = "/tolittle/views/main")
public class MainActivity extends BaseActivity {
    @Override
    protected int getContentViewID() {
        return R.layout.activity_main2;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {

    }
}
