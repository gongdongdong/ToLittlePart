package com.gdd.tolittlepart.presenters;

import android.content.Context;

import com.gdd.tolittlepart.rxpackage.executer.JobExecutor;
import com.gdd.tolittlepart.rxpackage.executer.RxJavaExecuter;
import com.gdd.tolittlepart.rxpackage.executer.UIThread;

/**
 * Created by Administrator on 2017/2/17.
 */

public abstract class Presenter {

    protected Context context;
    protected RxJavaExecuter rxJavaExecuter;

    public Presenter(Context context){
        this.context = context;
        this.rxJavaExecuter = new RxJavaExecuter(JobExecutor.instance(), UIThread.instance());
    }

    public abstract void destroy();
}
