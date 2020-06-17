package com.example.anote;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.anote.databinding.ActivityMainBinding;
import com.example.anote.vm.MainVm;
import com.example.commlib.base.mvvm.BaseActivity;

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
    public void initViewObservable() {
//        mBinding.getViewModel().addNote("测试地址","http://doc.bmob.cn/data/android/develop_doc/#4");
        mViewModel.getNotes();

    }

    @Override
    protected void initView() {
//        mBinding.mRefreshLayout.setOnRefreshListener(refreshLayout ->{
//            getHomeList(true);
//            getWanBanner();
//        });
//        mBinding.mRefreshLayout.setOnLoadMoreListener(refreshLayout ->getHomeList(false));
    }
}