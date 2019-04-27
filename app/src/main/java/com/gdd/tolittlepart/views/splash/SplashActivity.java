package com.gdd.tolittlepart.views.splash;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.alibaba.android.arouter.launcher.ARouter;
import com.gdd.tolittlepart.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash2);
        findViewById(R.id.btn_check_arouter).setOnClickListener(view->{
            ARouter.getInstance().build("/tolittle/main").navigation();
        });
    }
}
