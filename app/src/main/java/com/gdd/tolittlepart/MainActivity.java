package com.gdd.tolittlepart;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.gdd.tolittlepart.entity.StudentBean;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    StudentBean studentBean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("tag", studentBean.get_id() + studentBean.getName());
    }
}
