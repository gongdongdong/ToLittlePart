package com.gdd.tolittlepart.di.component;

import android.app.Activity;
import android.content.Context;

import com.gdd.tolittlepart.di.module.FragmentModule;
import com.gdd.tolittlepart.di.scope.ContextLife;
import com.gdd.tolittlepart.di.scope.PerFragment;

import dagger.Component;

/**
 * Created by Eric on 2017/1/19.
 */
@PerFragment
@Component(dependencies = ApplicationComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {
    @ContextLife("Activity")
    Context getActivityContext();

    @ContextLife("Application")
    Context getApplicationContext();

    Activity getActivity();

}
