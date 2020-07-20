package com.example.anote.base;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public class BaseVM extends AndroidViewModel {
    public BaseVM(@NonNull Application application) {
        super(application);
    }
    public BaseActivity mactivity;
    public void setBaseActivity(BaseActivity activity){
        mactivity=activity;
    }
}
