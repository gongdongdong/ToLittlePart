package com.gdd.useraccount.loginServiceImpl;

import android.content.Context;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.gdd.useraccountapi.loginService.LoginService;

@Route(path = "/tolittle/hello", name = "test service")
public class LoginServiceImpl implements LoginService {
    @Override
    public String sayHello(String name) {
        return "hello, " + name;
    }

    @Override
    public void init(Context context) {

    }
}
