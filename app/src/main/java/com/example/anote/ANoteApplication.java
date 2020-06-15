package com.example.anote;

import com.example.commlib.base.BaseApplication;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobConfig;

public class ANoteApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        modulesApplicationInit("com.example.commlib.api.App");
        initBmob();
    }

    private void initBmob(){
        //第一：默认初始化
        Bmob.initialize(this, "d292f3ed0fa90a02407d9735b59cf3d4");
    }
}
