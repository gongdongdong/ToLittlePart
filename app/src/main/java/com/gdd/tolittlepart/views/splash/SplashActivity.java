package com.gdd.tolittlepart.views.splash;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.gdd.base.component.BaseActivity;
import com.gdd.tolittlepart.R;
//import com.gdd.useraccountapi.loginService.LoginService;

import butterknife.BindView;

@Route(path = "/tolittle/splash")
public class SplashActivity extends BaseActivity {

    private static final String TAG = "gdd";
    @BindView(R.id.iv_show_background)
    ImageView iv_show_background;
    @BindView(R.id.iv_show_loading)
    ImageView iv_show_loading;

    @Override
    protected int getContentViewID() {
//        LoginService loginService = (LoginService) ARouter.getInstance().build("/tolittle/hello").navigation();
//        Log.e("test", loginService.sayHello("gdd"));
        return R.layout.activity_splash2;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        iv_show_background.setBackgroundColor(Color.GRAY);
        iv_show_loading.setBackgroundResource(R.drawable.ic_launcher_background);
        Log.e(TAG, "initview");

        new Thread(()-> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            runOnUiThread(()->{
                ARouter.getInstance().build("/tolittle/views/main").navigation();
            });
        }).start();
    }
}
