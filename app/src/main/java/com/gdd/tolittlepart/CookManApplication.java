package com.gdd.tolittlepart;

import android.content.Context;

import org.litepal.LitePalApplication;

/**
 * Created by Administrator on 2017/2/17.
 */

public class CookManApplication extends LitePalApplication {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();

        mContext = getApplicationContext();

    }

    public static Context getContext() {
        return mContext;
    }

}
