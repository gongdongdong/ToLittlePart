package com.gdd.tolittlepart.di.component;

import android.content.Context;


import com.gdd.tolittlepart.di.module.ApplicationModule;
import com.gdd.tolittlepart.di.scope.ContextLife;
import com.gdd.tolittlepart.di.scope.PerApp;

import dagger.Component;

/**
 * Created by Eric on 2017/1/19.
 */
@PerApp
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    @ContextLife("Application")
    Context getApplication();
}