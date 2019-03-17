package com.gdd.tolittlepart.fragment;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gdd.tolittlepart.R;
import com.gdd.tolittlepart.adapter.CookListAdapter;
import com.gdd.tolittlepart.component.fab_transformation.FabTransformation;
import com.gdd.tolittlepart.component.twinklingrefreshlayout.Footer.BottomProgressView;
import com.gdd.tolittlepart.component.twinklingrefreshlayout.PeRefreshLayout.PeRefreshLayout;
import com.gdd.tolittlepart.component.twinklingrefreshlayout.PeRefreshLayout.PeRefreshLayoutListener;
import com.gdd.tolittlepart.component.twinklingrefreshlayout.RefreshListenerAdapter;
import com.gdd.tolittlepart.component.twinklingrefreshlayout.TwinklingRefreshLayout;
import com.gdd.tolittlepart.component.twinklingrefreshlayout.header.bezierlayout.BezierLayout;
import com.gdd.tolittlepart.models.entity.CookEntity.CookDetail;
import com.gdd.tolittlepart.models.entity.tb_cook.TB_CustomCategory;
import com.gdd.tolittlepart.presenters.CookListPresenter;
import com.gdd.tolittlepart.presenters.Presenter;
import com.gdd.tolittlepart.utils.ToastUtil;
import com.gdd.tolittlepart.views.ICookListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/2/17.
 */

public class CookListFragment extends BaseFragment implements
        ICookListView
    , PeRefreshLayoutListener
{

    @Bind(R.id.refreshLayout_data)
    public PeRefreshLayout peRefreshLayout;

    @Bind(R.id.view_overlay)
    public View viewOverlay;
    @Bind(R.id.fab_app)
    public FloatingActionButton floatingActionButton;
    @Bind(R.id.view_sheet)
    public View viewSheet;

    public TwinklingRefreshLayout twinklingRefreshLayout;
    public RecyclerView recyclerList;
    private CookListAdapter cookListAdapter;

    private TB_CustomCategory customCategoryData;
    private CookListPresenter cookListPresenter;

    @Override
    protected Presenter getPresenter(){
        return cookListPresenter;
    }

    @Override
    protected int getLayoutId(){
        return R.layout.fragment_cook_list;
    }

    @Override
    protected void initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        peRefreshLayout.setPeRefreshLayoutListener(this);
        twinklingRefreshLayout = peRefreshLayout.getTwinklingRefreshLayout();
        recyclerList = peRefreshLayout.getRecyclerView();

        recyclerList.setLayoutManager(new LinearLayoutManager(recyclerList.getContext()));
        cookListAdapter = new CookListAdapter(getActivity());
        recyclerList.setAdapter(cookListAdapter);

        BezierLayout headerView = new BezierLayout(getActivity());
        headerView.setRoundProgressColor(getResources().getColor(R.color.colorPrimary));
        headerView.setWaveColor(getResources().getColor(R.color.main_indicator_bg));
        headerView.setRippleColor(getResources().getColor(R.color.white));
        twinklingRefreshLayout.setHeaderView(headerView);
        twinklingRefreshLayout.setWaveHeight(140);

        BottomProgressView bottomProgressView = new BottomProgressView(twinklingRefreshLayout.getContext());
        bottomProgressView.setAnimatingColor(getResources().getColor(R.color.colorPrimary));
        twinklingRefreshLayout.setBottomView(bottomProgressView);
        twinklingRefreshLayout.setOverScrollBottomShow(true);

        final ArrayList<CookDetail> datas = new ArrayList<>();
        cookListAdapter.setDataList(datas);

        cookListPresenter = new CookListPresenter(getActivity(), this);
        cookListPresenter.updateRefreshCookMenuByID(customCategoryData.getCtgId());

        twinklingRefreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(final TwinklingRefreshLayout refreshLayout) {
                cookListPresenter.updateRefreshCookMenuByID(customCategoryData.getCtgId());
            }

            @Override
            public void onLoadMore(final TwinklingRefreshLayout refreshLayout) {
                cookListPresenter.loadMoreCookMenuByID(customCategoryData.getCtgId());
            }
        });
    }

    @OnClick(R.id.fab_app)
    public void onClickFabApp(){
        if (floatingActionButton.getVisibility() == View.VISIBLE) {
            FabTransformation.with(floatingActionButton).setOverlay(viewOverlay).transformTo(viewSheet);
        }
    }

    @OnClick(R.id.view_overlay)
    public void onClickOverlay() {
        closeFabMenu();
    }

    @OnClick(R.id.relative_category)
    public void onClickCategory(){
        onClickOverlay();
    }

    @OnClick(R.id.relative_collection)
    public void onClickCollection(){
        onClickOverlay();
    }

    @OnClick(R.id.relative_about)
    public void onClickAbout(){
        onClickOverlay();
    }

    /********************************************************************************************/
    @Override
    public void onCookListUpdateRefreshSuccess(ArrayList<CookDetail> list){

        if(peRefreshLayout.isShowDataView()){
            peRefreshLayout.setModeList();
        }

        twinklingRefreshLayout.finishRefreshing();
        cookListAdapter.setDataList(conversion(list));
        cookListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onCookListUpdateRefreshFaile(String msg){

        if(peRefreshLayout.isShowDataView()){
            peRefreshLayout.setModeException(msg);
            return ;
        }

        twinklingRefreshLayout.finishRefreshing();
        ToastUtil.showToast(getActivity(), msg);
    }

    @Override
    public void onCookListLoadMoreSuccess(ArrayList<CookDetail> list){
        twinklingRefreshLayout.finishLoadmore();
        cookListAdapter.addItems(conversion(list));
    }

    @Override
    public void onCookListLoadMoreFaile(String msg){
        twinklingRefreshLayout.finishLoadmore();
        ToastUtil.showToast(getActivity(), msg);
    }
    /********************************************************************************************/

    @Override
    public void onPeRefreshLayoutClick(){
        cookListPresenter.updateRefreshCookMenuByID(customCategoryData.getCtgId());
    }
    /********************************************************************************************/

    private List<CookDetail> conversion(ArrayList<CookDetail> list){
        List<CookDetail> datas = new ArrayList<>();
        for(CookDetail item : list){
            datas.add(item);
        }

        return datas;
    }

    public boolean closeFabMenu(){
        if (floatingActionButton.getVisibility() != View.VISIBLE) {
            FabTransformation.with(floatingActionButton).setOverlay(viewOverlay).transformFrom(viewSheet);
            return true;
        }

        return false;
    }

    public void setCustomCategoryData(TB_CustomCategory customCategoryData){
        this.customCategoryData = customCategoryData;
    }
}
