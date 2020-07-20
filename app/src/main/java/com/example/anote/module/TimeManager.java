package com.example.anote.module;

import com.example.commlib.utils.RxTimerUtil;

/**
 * @Description:
 * @Author: zjx
 * @CreateDate: 2020/7/17 0017
 */
public class TimeManager {
    private static TimeManager timeManager=new TimeManager();
    public static TimeManager getInstance(){
        return timeManager;
    }

    public static final String GET_FILE_SIZE_TIMER_NAME="userTimer";//定时器名
    public static final long GET_FILE_SIZE_TIMER=1;//开启定时器的  执行间隔数  单位  秒 s


    /**
     * 计时器
     */
    public void init() {
        if (!RxTimerUtil.hasTimer(GET_FILE_SIZE_TIMER_NAME)) {
            RxTimerUtil.interval(GET_FILE_SIZE_TIMER, GET_FILE_SIZE_TIMER_NAME, number -> {
                //做定时 计时间
                if (null!=mTimeListeren){
                    //一秒回调一次
                    mTimeListeren.once();
                }
            });
        }
    }

    private TimeListeren mTimeListeren;

    public void setTimeListeren(TimeListeren timeListeren) {
        this.mTimeListeren = timeListeren;
    }

    public interface TimeListeren{
        public  void  once();
    }

}
