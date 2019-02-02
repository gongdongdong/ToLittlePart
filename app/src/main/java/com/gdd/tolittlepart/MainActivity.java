package com.gdd.tolittlepart;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.gdd.tolittlepart.di.component.ActivityComponent;
import com.gdd.tolittlepart.di.component.DaggerActivityComponent;
import com.gdd.tolittlepart.di.module.ActivityModule;
import com.gdd.tolittlepart.entity.StudentBean;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    StudentBean studentBean;
    private ActivityComponent mActivityComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initActivityComponent();
        initInjector();
        Log.e("tag", studentBean.get_id() + studentBean.getName());
    }

    private void initActivityComponent() {
        mActivityComponent = DaggerActivityComponent.builder()
                .applicationComponent(((App) getApplication()).getApplicationComponent())
                .activityModule(new ActivityModule(this))
                .build();
    }

    public void initInjector() {
        mActivityComponent.inject(this);
    }

}
