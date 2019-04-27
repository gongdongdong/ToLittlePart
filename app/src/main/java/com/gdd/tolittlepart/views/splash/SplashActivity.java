package com.gdd.tolittlepart.views.splash;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.gdd.tolittlepart.R;
import com.gdd.useraccountapi.loginService.LoginService;

@Route(path = "/tolittle/splash")
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash2);
        LoginService loginService = (LoginService) ARouter.getInstance().build("/tolittle/hello").navigation();
        Log.e("test", loginService.sayHello("gdd"));
    }
}
