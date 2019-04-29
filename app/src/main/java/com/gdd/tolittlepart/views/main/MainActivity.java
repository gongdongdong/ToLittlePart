package com.gdd.tolittlepart.views.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.gdd.base.adapter.listview.CommonAdapter;
import com.gdd.base.adapter.listview.ViewHolder;
import com.gdd.base.component.BaseActivity;
import com.gdd.events.NetworkEvent;
import com.gdd.tolittlepart.R;
import com.gdd.tolittlepart.views.main.newsfragment.NewsFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;

@Route(path = "/tolittle/main")
public class MainActivity extends BaseActivity {

    @BindView(R.id.fl_show_fragment)
    FrameLayout fl_show_fragment;
    @BindView(R.id.lv_test_common)
    ListView lv_test_common;

    CommonAdapter<String> commonAdapter;

    NewsFragment newsFragment;
    FragmentManager fragmentManager;


    String[] myarray = {"2222222", "aaaaaaaaa", "3333333333", "ddddddddddd"};
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

//        List<String> mylist = new LinkedList<>();
//        for(String one : myarray){
//            mylist.add(one);
//        }
//        commonAdapter = new CommonAdapter<String>(this, R.layout.test_common, mylist) {
//            @Override
//            protected void convert(ViewHolder viewHolder, String item, int position) {
//                viewHolder.setText(R.id.tv_show_text, mylist.get(position));
//            }
//        };
//
//        lv_test_common.setAdapter(commonAdapter);
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
