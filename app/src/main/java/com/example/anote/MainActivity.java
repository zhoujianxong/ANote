package com.example.anote;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.example.anote.base.BaseActivity;
import com.example.anote.databinding.ActivityMainBinding;
import com.example.anote.interf.MyRefreshListener;
import com.example.anote.vm.MainVm;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainVm> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected Class<MainVm> getVM() {
        return MainVm.class;
    }


    @Override
    protected void initViewObservable() {
//        mViewModel.addNote("测试地址","http://doc.bmob.cn/data/android/develop_doc/#4");
        mBinding.setMainVm(mViewModel);
        mViewModel.reFresh();


        mBinding.mRefreshLayout.setOnRefreshListener(new MyRefreshListener() {


            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mViewModel.reFresh();
                refreshLayout.finishRefresh(true);
            }
        });

        mBinding.mRefreshLayout.setOnLoadMoreListener(new MyRefreshListener(){
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                mViewModel.loadMore();
                refreshLayout.finishLoadMore(true);
            }
        });
    }
}