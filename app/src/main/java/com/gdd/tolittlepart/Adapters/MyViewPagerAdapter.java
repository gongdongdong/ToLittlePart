package com.gdd.tolittlepart.Adapters;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.gdd.tolittlepart.views.main.newsfragment.NewsFragment;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MyViewPagerAdapter extends FragmentPagerAdapter {


    Map<String, Fragment> mFragments = new HashMap<>();
    List<String> keywords;
    Map<String, String> myIds;
    public MyViewPagerAdapter(FragmentManager fm, List<String> keywords, Map<String, String> myIds) {
        super(fm);
        this.keywords = keywords;
        this.myIds = myIds;
    }

    @Override
    public Fragment getItem(int i) {
        if(mFragments.get(keywords.get(i)) != null){
            return mFragments.get(keywords.get(i));
        }
        String id = myIds.get(keywords.get(i));
        Fragment oneFragment = NewsFragment.getInstance();
        Bundle bundle = new Bundle();
        bundle.clear();
        bundle.putString("keyword", keywords.get(i));
        bundle.putString("id", id);
        oneFragment.setArguments(bundle);
        mFragments.put(keywords.get(i), oneFragment);
        return oneFragment;
    }

    @Override
    public int getCount() {
        return keywords.size();
    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return keywords.get(position);
    }
}
