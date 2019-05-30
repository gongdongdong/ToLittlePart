package com.gdd.tolittlepart.views.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.gdd.base.component.BaseActivity;
import com.gdd.beans.LoginRegistBean;
import com.gdd.network.RetrofitManager;
import com.gdd.tolittlepart.R;
import com.gdd.tolittlepart.services.MyIntentService;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by GongDongdong on 2019/5/3.
 */

@Route(path = "/tolittle/login")
public class LoginActivity extends BaseActivity {

    private static final String TAG = "test";
    @BindView(R.id.btn_login)
    Button btn_login;
    @BindView(R.id.btn_regist)
    Button btn_regist;
    @BindView(R.id.et_input_name)
    EditText et_input_name;
    @BindView(R.id.et_input_pwd)
    EditText et_input_pwd;


    @Override
    protected int getContentViewID() {
        return R.layout.login_layout_activity;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {

    }

    public void login_action(View view){
        boolean isparamsok = login_params_check();
        if(!isparamsok) {
            Toast.makeText(this, "parame not ok!", Toast.LENGTH_SHORT).show();
            return;
        }
        doLoginfunction();
    }

    boolean mydebug = true;
    private void doLoginfunction() {
        MyIntentService.startActionBaz(this, "111", "111");
        MyIntentService.startActionFoo(this, "222", "222");
//        ARouter.getInstance().build("/tolittle/main").navigation();
//        LoginActivity.this.finish();
//        if(mydebug){
//            return;
//        }
        RetrofitManager.getInstance().doLogin(et_input_name.getText().toString(),
                et_input_pwd.getText().toString())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(/*loginResult -> {
                    Log.e(TAG, "DO LOGIN FUNCTION RETURN");

                    if(loginResult.getData() != null &&
                            loginResult.getData().getUsername() != null){
                        ARouter.getInstance().build("/tolittle/main").navigation();
                        LoginActivity.this.finish();
                        return;
                    }
                    Toast.makeText(LoginActivity.this, loginResult.toString(),
                            Toast.LENGTH_SHORT).show();
                }*/
                        new Observer<LoginRegistBean>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(LoginRegistBean loginResult) {
                                Log.e(TAG, "DO LOGIN FUNCTION RETURN");

                                if(loginResult.getData() != null &&
                                        loginResult.getData().getUsername() != null){
//                                    ARouter.getInstance().build("/tolittle/main").navigation();
//                                    LoginActivity.this.finish();
                                    return;
                                }
                                Toast.makeText(LoginActivity.this, loginResult.toString(),
                                        Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.e(TAG, "DO LOGIN FUNCTION RETURN onError");
                            }

                            @Override
                            public void onComplete() {

                            }
                        });
    }

    private boolean login_params_check() {
        if(et_input_pwd.getText().toString() == null ||
                "".equals(et_input_pwd.getText().toString()))
            return true;
        if(et_input_name.getText().toString() == null ||
                "".equals(et_input_name.getText().toString()))
            return true;
        return true;
    }

    public void regist_action(View view){
        ARouter.getInstance().build("/tolittle/regist").navigation();
    }

    public void goto_mapActivity(View view){
        ARouter.getInstance().build("/tolittle/map").navigation();
    }

}
