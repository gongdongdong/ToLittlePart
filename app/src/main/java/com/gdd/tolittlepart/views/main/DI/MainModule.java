package com.gdd.tolittlepart.views.main.DI;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {

    public MainModule(){
    }


    @Provides
    List<String> provideKeys(){
        List<String> myKeys = new LinkedList<>();
        myKeys.add("one");
        myKeys.add("gddcust");
        myKeys.add("gddcard");
        return myKeys;
    }

    @Provides
    Map<String, String> provideIds(){
        Map<String, String> myIds = new HashMap<>();
        myIds.put("one", "T1348647909107");
        myIds.put("eleven", "T1350383429665");
        myIds.put("twolve", "T1350383429665");
        return myIds;
    }



}
