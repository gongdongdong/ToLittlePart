package com.gdd.tolittlepart.presenters;

import android.content.Context;

import com.gdd.tolittlepart.models.entity.CookEntity.CategoryChildInfo1;
import com.gdd.tolittlepart.models.entity.CookEntity.subscriberEntity.CategorySubscriberResultInfo;
import com.gdd.tolittlepart.models.interfaces.ICookRespository;
import com.gdd.tolittlepart.models.manager.CookCategoryManager;
import com.gdd.tolittlepart.models.manager.CustomCategoryManager;
import com.gdd.tolittlepart.models.respository.CookRespository;
import com.gdd.tolittlepart.views.ISplashView;

import java.util.ArrayList;

import rx.Subscriber;

/**
 * Created by Administrator on 2017/2/19.
 */

public class SplashPresenter extends Presenter{

    private ICookRespository iCookRespository;
    private ISplashView iSplashView;

    public SplashPresenter(Context context, ISplashView iSplashView){
        super(context);

        this.iCookRespository = CookRespository.getInstance();
        this.iSplashView = iSplashView;
    }

    public void destroy(){
        if(getCategoryQuerySubscriber != null){
            getCategoryQuerySubscriber.unsubscribe();
        }
    }

    public void initData(){

        rxJavaExecuter.execute(
                iCookRespository.getCategoryQuery()
                , getCategoryQuerySubscriber = new GetCategoryQuerySubscriber()
        );

    }

    private GetCategoryQuerySubscriber getCategoryQuerySubscriber;
    private class GetCategoryQuerySubscriber extends Subscriber<CategorySubscriberResultInfo> {
        @Override
        public void onCompleted(){

        }

        @Override
        public void onError(Throwable e){
            if(getCategoryQuerySubscriber != null){
                getCategoryQuerySubscriber.unsubscribe();
            }

            CustomCategoryManager.getInstance().initData(null);

            if(iSplashView != null)
                iSplashView.onSplashInitData();

        }

        @Override
        public void onNext(CategorySubscriberResultInfo data){

            ArrayList<CategoryChildInfo1> datas = CookCategoryManager.removeBangZi(data.getResult().getChilds());

            CookCategoryManager.getInstance().initDatas(datas);
            CustomCategoryManager.getInstance().initData(datas);

            if(iSplashView != null)
                iSplashView.onSplashInitData();

            this.onCompleted();
        }
    }
}
