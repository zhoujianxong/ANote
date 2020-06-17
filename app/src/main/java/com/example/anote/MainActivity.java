package com.example.anote;
import android.os.Bundle;
import com.example.anote.base.BaseActivity;
import com.example.anote.databinding.ActivityMainBinding;
import com.example.anote.vm.MainVm;

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
        mViewModel.getNotes();
    }
}