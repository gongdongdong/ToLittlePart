package com.gdd.tolittlepart;

import android.app.Application;

import com.gdd.tolittlepart.di.component.ApplicationComponent;
import com.gdd.tolittlepart.di.component.DaggerApplicationComponent;
import com.gdd.tolittlepart.di.module.ApplicationModule;

public class App extends Application {
    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initApplicationComponent();
    }

    // Fixme
    private void initApplicationComponent() {
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }
}
