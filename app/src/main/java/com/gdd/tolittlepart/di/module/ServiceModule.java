package com.gdd.tolittlepart.di.module;

import android.app.Service;
import android.content.Context;

import com.gdd.tolittlepart.di.scope.ContextLife;
import com.gdd.tolittlepart.di.scope.PerService;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Eric on 2017/1/19.
 */
@Module
public class ServiceModule {
    private Service mService;

    public ServiceModule(Service service) {
        mService = service;
    }

    @Provides
    @PerService
    @ContextLife("Service")
    public Context ProvideServiceContext() {
        return mService;
    }
}
