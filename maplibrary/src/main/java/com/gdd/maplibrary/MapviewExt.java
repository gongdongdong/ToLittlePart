package com.gdd.maplibrary;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;

import com.amap.api.maps.AMap;
import com.amap.api.maps.AMapOptions;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;

public class MapviewExt extends MapView {
    public MapviewExt(Context context) {
        super(context);
    }

    public MapviewExt(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public MapviewExt(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public MapviewExt(Context context, AMapOptions aMapOptions) {
        super(context, aMapOptions);
    }

    public void setMarkerView(){
        AMap aMap = this.getMap();
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(new LatLng(31, 121));
        aMap.addMarker(markerOptions);
        aMap.moveCamera(CameraUpdateFactory.changeLatLng(new LatLng(31, 121)));
    }

    public void takeShoot(){
        AMap aMap = this.getMap();
        aMap.getMapScreenShot(new AMap.OnMapScreenShotListener() {
            @Override
            public void onMapScreenShot(Bitmap bitmap) {

            }

            @Override
            public void onMapScreenShot(Bitmap bitmap, int i) {

            }
        });
    }
}
