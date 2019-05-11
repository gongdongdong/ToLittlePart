package com.gdd.tolittlepart.views.main.DI;

import com.gdd.tolittlepart.views.main.MainActivity;
import com.gdd.tolittlepart.views.myMap.MapActivity;

import dagger.Component;

@Component(modules = MainModule.class)
public interface MainComponent {
    void inject(MainActivity ctivity);
    void inject(MapActivity ctivity);
}
