package com.example.anote.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModelProvider;
import com.example.commlib.base.RootActivity;
import com.example.commlib.base.mvvm.BaseViewModel;

public abstract class BaseActivity<V extends ViewDataBinding, VM extends BaseVM> extends RootActivity {
    public V mBinding;
    public VM mViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, getLayoutId());
        mViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(getVM());
        initViewObservable();
    }

    protected abstract int getLayoutId();

    protected abstract Class<VM> getVM();

    protected abstract void initViewObservable();


}
