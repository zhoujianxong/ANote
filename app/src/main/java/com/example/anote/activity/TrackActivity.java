package com.example.anote.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.os.Bundle;

import com.example.anote.R;
import com.example.anote.base.BaseActivity;
import com.example.anote.bean.UserBean;
import com.example.anote.databinding.ActivityTrackBinding;
import com.example.anote.module.TimeManager;
import com.example.anote.view.CenterDialog;
import com.example.anote.vm.TrackVM;

import java.util.List;
import java.util.Random;

public class TrackActivity extends BaseActivity<ActivityTrackBinding, TrackVM> {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_track;
    }

    @Override
    protected Class<TrackVM> getVM() {
        return TrackVM.class;
    }

    @Override
    protected void initViewObservable() {
        mBinding.setTrackVM(mViewModel);
        mViewModel.initTime();

        mBinding.mButton.setOnClickListener(v -> {
            mViewModel.addUser();
        });

        mViewModel.getMutableLiveData().observe(this, new Observer<UserBean>() {
            @Override
            public void onChanged(UserBean userBean) {
                mBinding.mName.setText(userBean.getName());
                mBinding.mTime.setText(mViewModel.secondsToFormat(userBean.getCurrentTime()));
            }
        });


    }
}