package com.gdd.base.component;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.gdd.base.BuildConfig;

public abstract class BaseApplication extends Application {

    public Application getAppInstance() {
        return appInstance;
    }

    private Application appInstance;
    @Override
    public void onCreate() {
        super.onCreate();
        appInstance = this;
        if (isDebug()) {           // These two lines must be written before init, otherwise these configurations will be invalid in the init process
            ARouter.openLog();     // Print log
            ARouter.openDebug();   // Turn on debugging mode (If you are running in InstantRun mode, you must turn on debug mode! Online version needs to be closed, otherwise there is a security risk)
        }
        ARouter.init(appInstance); // As early as possible, it is recommended to initialize in the Application
    }

    private boolean isDebug() {
        return BuildConfig.DEBUG;
    }
}
