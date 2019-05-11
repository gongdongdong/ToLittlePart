package com.gdd.tolittlepart.views.main.projectfragment;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gdd.base.component.BaseFragment;
import com.gdd.beans.ProjectListData;
import com.gdd.network.RetrofitManager;
import com.gdd.tolittlepart.Adapters.ProjectQuickAdapter;
import com.gdd.tolittlepart.R;

import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.ResourceObserver;
import io.reactivex.schedulers.Schedulers;

public class ProjectFragment extends BaseFragment {

    @BindView(R.id.rv_show_project)
    RecyclerView rv_show_project;
    ProjectQuickAdapter projectQuickAdapter;
    List<ProjectListData.ProjectData> datalist;

    public static ProjectFragment getInstance() {
        return new ProjectFragment();
    }


    @Override
    protected void initData(Bundle savedInstanceState) {
        RetrofitManager.getInstance().getProjectInfo(mCurrentPage, 294)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ResourceObserver<ProjectListData>() {
                    @Override
                    public void onNext(ProjectListData projectListData) {
                        datalist.addAll(projectListData.data.getDatas());
                        projectQuickAdapter.notifyDataSetChanged();
//                        projectQuickAdapter.loadMoreComplete();
                    }

                    @Override
                    public void onError(Throwable e) {
//                        projectQuickAdapter.loadMoreComplete();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    private int mCurrentPage = 1;

    @Override
    protected void initView() {
        datalist = new LinkedList<>();
        projectQuickAdapter = new ProjectQuickAdapter(getActivity(), datalist);
        rv_show_project.addItemDecoration(new DividerItemDecoration(getActivity(),
                DividerItemDecoration.VERTICAL));
        rv_show_project.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_show_project.setAdapter(projectQuickAdapter);
        projectQuickAdapter.setEnableLoadMore(true);
        projectQuickAdapter.setOnLoadMoreListener(
                () -> {
                    mCurrentPage++;
                    RetrofitManager.getInstance().getProjectInfo(mCurrentPage, 294)
                            .subscribeOn(Schedulers.newThread())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new ResourceObserver<ProjectListData>() {
                                        @Override
                                        public void onNext(ProjectListData projectListData) {
                                            datalist.addAll(projectListData.data.getDatas());
                                            projectQuickAdapter.notifyDataSetChanged();
                                            projectQuickAdapter.loadMoreComplete();
                                        }

                                        @Override
                                        public void onError(Throwable e) {
                                            projectQuickAdapter.loadMoreComplete();
                                        }

                                        @Override
                                        public void onComplete() {

                                        }
                                    });
                }
                ,rv_show_project);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.project_frag_layout;
    }
}
