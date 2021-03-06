package com.gdd.tolittlepart.views.myMap;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.gdd.base.component.BaseActivity;
import com.gdd.maplibrary.MapviewExt;
import com.gdd.tolittlepart.R;
import com.gdd.tolittlepart.views.main.DI.DaggerMainComponent;
import com.gdd.tolittlepart.views.main.DI.Student;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

@Route(path = "/tolittle/map")
public class MapActivity extends BaseActivity {

    @BindView(R.id.myMapview)
    MapviewExt myMapview;
    @BindView(R.id.cardview_formap)
    CardView cardview_formap;
    @BindView(R.id.iv_show_something)
    ImageView iv_show_something;

    @Inject
    Student student;

    @Override
    protected int getContentViewID() {
        return R.layout.map_activity_layout;
    }

    @Override
    protected void initData() {
        DaggerMainComponent.builder().build().inject(this);
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        myMapview.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        myMapview.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        myMapview.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        myMapview.onDestroy();
    }

    @OnClick({R.id.cardview_formap, R.id.btn_test})
    public void myOnclick(View view){
        switch (view.getId()){
            case R.id.cardview_formap:
                break;
            case R.id.btn_test:
                starttest();
                break;
        }
    }

    private void starttest(){
        Toast.makeText(this, student.toString(), Toast.LENGTH_SHORT).show();
        View view = LayoutInflater.from(this).inflate(R.layout.map_activity_layout, null, false);
        BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromView(view);
        cardview_formap.setDrawingCacheEnabled(true);
        Bitmap bitmap = cardview_formap.getDrawingCache();
        if(bitmap == null){

        }
        else{
            iv_show_something.setImageBitmap(bitmap);
        }
        if(bitmapDescriptor == null){
        }
        else{
//            iv_show_something.setImageBitmap(bitmapDescriptor.getBitmap());
        }
    }

}
