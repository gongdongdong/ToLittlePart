package com.gdd.tolittlepart.views.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.gdd.base.component.BaseActivity;
import com.gdd.events.NetworkEvent;
import com.gdd.tolittlepart.R;
import com.gdd.tolittlepart.views.main.newsfragment.NewsFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;

@Route(path = "/tolittle/main")
public class MainActivity extends BaseActivity {

    @BindView(R.id.fl_show_fragment)
    FrameLayout fl_show_fragment;

    NewsFragment newsFragment;
    FragmentManager fragmentManager;

    @Override
    protected int getContentViewID() {
        return R.layout.activity_main2;
    }

    @Override
    protected void initData() {
        if(newsFragment == null){
            newsFragment = NewsFragment.getInstance();
        }
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction;
        if(newsFragment.isAdded()){
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.show(newsFragment);
        }
        else{
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.fl_show_fragment, newsFragment);
        }
        fragmentTransaction.commit();
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    void getNetworkEvent(NetworkEvent events){
        Toast.makeText(this, "net work return " + events.msg,
                Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }
}
