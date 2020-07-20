package com.example.anote.vm;

import android.annotation.SuppressLint;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.databinding.ObservableArrayList;
import androidx.lifecycle.MutableLiveData;

import com.blankj.ALog;
import com.example.anote.R;
import com.example.anote.base.BaseVM;
import com.example.anote.bean.UserBean;
import com.example.anote.databinding.ItemTimeBinding;
import com.example.anote.module.TimeManager;
import com.example.anote.view.CenterDialog;
import com.example.commlib.base.mvvm.BaseAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.UnaryOperator;

import static com.example.commlib.utils.CommUtils.getUUID;

/**
 * @Description:
 * @Author: zjx
 * @CreateDate: 2020/7/17 0017
 */
public class TrackVM extends BaseVM {
    private MutableLiveData<UserBean> mutableLiveData = new MutableLiveData<>();
    private ObservableArrayList<UserBean> mUserList = new ObservableArrayList<>();
    private List<Integer> selects = new ArrayList<>();

    public TrackVM(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<UserBean> getMutableLiveData() {
        return mutableLiveData;
    }

    /**
     * 初始化
     */
    public void initTime() {
        TimeManager.getInstance().init();
        TimeManager.getInstance().setTimeListeren(() -> {
            add();
        });
    }


    private synchronized void add() {
        if (selects.size()>0){
            UserBean userBean=mUserList.get(selects.get(0));
            userBean.setCurrentTime(userBean.getCurrentTime()+1);
            mUserList.set(selects.get(0),userBean);
            mutableLiveData.postValue(userBean);
        }
    }


    public BaseAdapter mAdapter = new BaseAdapter<UserBean, ItemTimeBinding>(R.layout.item_time, mUserList) {
        @SuppressLint("NewApi")
        @Override
        public void vdbConvert(ItemTimeBinding itemTimeBinding, UserBean item, int position) {
            itemTimeBinding.mTimeTextView.setText(secondsToFormat(item.getCurrentTime()));
            itemTimeBinding.mName.setText(item.getName());
            itemTimeBinding.getRoot().getRootView().setOnClickListener(v -> {
                if (selects.contains(position)){
                    selects.clear();
                }else {
                    selects.add(position);
                }
            });
        }
    };

    /**
     * 返回时间
     * @param seconds
     * @return
     */
    public String secondsToFormat(long seconds){
        long hour =0;
        long min =0;
        long second;
        if (seconds<60){
            second=seconds;
        }else if (seconds<3600){
            min=seconds/60;
            second=seconds%60;
        }else {
            hour=seconds/3600;
            min=(seconds%3600)/60;
            second=seconds%60;
        }
        String hs=hour<10?"0"+hour:hour+"";
        String ms=min<10?"0"+min:min+"";
        String ss=second<10?"0"+second:second+"";
        return hs+":"+ms+":"+ss;
    }

    /**
     * 添加  用户
     */
    public void addUser() {
        CenterDialog centerDialog=new CenterDialog(mactivity,R.style.dialog);
        centerDialog.setAddListeren(name -> {
            UserBean userBean=new UserBean(getUUID(),name);
            mUserList.add(userBean);
        });
        centerDialog.show();
    }


}
