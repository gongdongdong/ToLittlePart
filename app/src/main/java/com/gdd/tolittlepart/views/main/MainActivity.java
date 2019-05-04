package com.gdd.tolittlepart.views.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.gdd.base.adapter.listview.CommonAdapter;
import com.gdd.tolittlepart.Adapters.MyViewPagerAdapter;
import com.gdd.tolittlepart.aop.TestAnnoTrace;
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

    private static final String TAG = "test";
//    @BindView(R.id.fl_show_fragment)
//    FrameLayout fl_show_fragment;
    @BindView(R.id.dl_dlcontent)
    DrawerLayout dl_dlcontent;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.vp_show_content)
    ViewPager vp_show_content;
    @BindView(R.id.tabs)
    TabLayout mtabs;
    @BindView(R.id.add_channel_iv)
    ImageView add_channel_iv;

    CommonAdapter<String> commonAdapter;

    MyViewPagerAdapter myViewPagerAdapter;

    NewsFragment newsFragment;
    FragmentManager fragmentManager;


    String[] myarray = {"2222222", "aaaaaaaaa", "3333333333", "ddddddddddd"};
    @Override
    protected int getContentViewID() {
        return R.layout.activity_main2;
    }

    @Override
    protected void initData() {

        setSupportActionBar(toolbar);
//        if(newsFragment == null){
//            newsFragment = NewsFragment.getInstance();
//        }
//        fragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction;
//        if(newsFragment.isAdded()){
//            fragmentTransaction = fragmentManager.beginTransaction();
//            fragmentTransaction.show(newsFragment);
//        }
//        else{
//            fragmentTransaction = fragmentManager.beginTransaction();
//            fragmentTransaction.add(R.id.fl_show_fragment, newsFragment);
//        }
//        fragmentTransaction.commit();

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
        fragmentManager = getSupportFragmentManager();
        List<String> myKeys = new LinkedList<>();
        myKeys.add("one");
        myKeys.add("two");
        myKeys.add("three");
        myKeys.add("four");
        myKeys.add("five");
        myKeys.add("six");
        myKeys.add("senven");
        myKeys.add("eight");
        myKeys.add("nine");
        myKeys.add("ten");
        myViewPagerAdapter = new MyViewPagerAdapter(fragmentManager, myKeys);
        vp_show_content.setAdapter(myViewPagerAdapter);
        vp_show_content.setCurrentItem(0);
        mtabs.setupWithViewPager(vp_show_content);
        dynamicSetTabLayoutMode(mtabs);
        add_channel_iv.setOnClickListener((view)->{
            Log.e(TAG, "ADD IMAGE CLICKED");
        });
    }

    public void dynamicSetTabLayoutMode(TabLayout tabLayout) {
        int tabWidth = calculateTabWidth(tabLayout);
        int screenWidth = getScreenWith();

        if (tabWidth <= screenWidth) {
            tabLayout.setTabMode(TabLayout.MODE_FIXED);
        } else {
            tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        }
    }

    public int getScreenWith() {
        return getResources().getDisplayMetrics().widthPixels;
    }

    private int calculateTabWidth(TabLayout tabLayout) {
        int tabWidth = 0;
        for (int i = 0; i < tabLayout.getChildCount(); i++) {
            final View view = tabLayout.getChildAt(i);
            view.measure(0, 0); // 通知父view测量，以便于能够保证获取到宽高
            tabWidth += view.getMeasuredWidth();
        }
        return tabWidth;
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        test();
    }

//    @TestAnnoTrace(value = "GDD_test", type = 1)
    private void test() {
        Log.e(TAG, "TEST");
    }

    @TestAnnoTrace(value = "GDD_test", type = 1)
    @Subscribe(threadMode = ThreadMode.MAIN)
    void getNetworkEvent(NetworkEvent events){
        Log.e(TAG, "net work return " + events.msg);
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
