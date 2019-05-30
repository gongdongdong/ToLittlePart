package com.gdd.tolittlepart.views.registry;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.gdd.base.component.BaseActivity;
import com.gdd.beans.LoginRegistBean;
import com.gdd.network.RetrofitManager;
import com.gdd.tolittlepart.R;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by GongDongdong on 2019/5/3.
 */

@Route(path = "/tolittle/regist")
public class RegistActivity extends BaseActivity {

    private static final String TAG = "test";
    @BindView(R.id.et_regist_name)
    EditText et_regist_name;
    @BindView(R.id.et_regist_password)
    EditText et_regist_password;
    @BindView(R.id.btn_regist_func)
    Button btn_regist_func;

    @Override
    protected int getContentViewID() {
        return R.layout.regist_layout_activity;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {

    }

    public void do_regist(View view){
        boolean isok = checkoutParams();
        if(!isok) {
            Toast.makeText(this, "参数不正确!", Toast.LENGTH_SHORT).show();
            return;
        }

        do_regist_function();
    }

    private void do_regist_function() {
        RetrofitManager.getInstance().doRegist(et_regist_name.getText().toString(),
                et_regist_password.getText().toString(),
                et_regist_password.getText().toString())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(/*new Consumer<LoginRegistBean>() {
                    @Override
                    public void accept(LoginRegistBean registResult) throws Exception {
                        Log.e(TAG, "DO REGIST FUNCTION RETURN");
                        Toast.makeText(RegistActivity.this, registResult.toString(),
                                Toast.LENGTH_SHORT).show();

                    }
                }*/
                        new Observer<LoginRegistBean>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(LoginRegistBean registResult) {
                                Log.e(TAG, "DO REGIST FUNCTION RETURN");
                                Toast.makeText(RegistActivity.this, registResult.toString(),
                                        Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.e(TAG, "DO REGIST FUNCTION RETURN onError");
                            }

                            @Override
                            public void onComplete() {

                            }
                        }
                );
    }

    private boolean checkoutParams() {
        if(et_regist_name.getText().toString().equals("")){
            return false;
        }
        if(et_regist_password.getText().toString().equals("")){
            return false;
        }
        return true;
    }

}
