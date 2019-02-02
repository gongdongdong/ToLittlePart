package com.gdd.tolittlepart.di.component;

import android.app.Activity;
import android.content.Context;

import com.gdd.tolittlepart.MainActivity;
import com.gdd.tolittlepart.di.module.ActivityModule;
import com.gdd.tolittlepart.di.scope.ContextLife;
import com.gdd.tolittlepart.di.scope.PerActivity;

import dagger.Component;

/**
 * Created by Eric on 2017/1/19.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    @ContextLife("Activity")
    Context getActivityContext();

    @ContextLife("Application")
    Context getApplicationContext();

    Activity getActivity();

    void inject(MainActivity mainActivity);

//    void inject(NewsActivity newsActivity);
//
//    void inject(NewsDetailActivity newsDetailActivity);
//
//    void inject(NewsChannelActivity newsChannelActivity);
//
//    void inject(NewsPhotoDetailActivity newsPhotoDetailActivity);

    //void inject(PhotoActivity photoActivity);

    //void inject(PhotoDetailActivity photoDetailActivity);
}
