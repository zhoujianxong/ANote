package com.example.anote.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

import com.example.commlib.utils.AnimatorUtils;


/**
 * @Description: 自带点击效果的ImageView
 * @Author: zjx
 * @CreateDate: 2019/11/8 15:52
 */
public class ScaleImageView extends AppCompatImageView {
    public ScaleImageView(Context context) {
        super(context);
    }

    public ScaleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScaleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public boolean isCanClick() {
        return canClick;
    }

    /**
     * 是否屏蔽点击事件
     * @param canClick
     */
    public void setCanClick(boolean canClick) {
        this.canClick = canClick;
    }

    private boolean canClick=true;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(!canClick){
            return false;
        }
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                AnimatorUtils.loadscaleDown(this);
               // Log.v("ScaleImageView","ACTION_DOWN");
                break;
            case MotionEvent.ACTION_CANCEL://取消不响应事件
                AnimatorUtils.loadscaleUp(this);
                Log.v("ScaleImageView","ACTION_CANCEL");
                break;
            case MotionEvent.ACTION_UP://抬起时响应事件
                AnimatorUtils.loadscaleUp(this);
                //Log.v("ScaleImageView","ACTION_UP");
                break;
        }
        return super.onTouchEvent(event);

    }


    //延迟一下点击
    @Override
    public void setOnClickListener(@Nullable OnClickListener l) {
        postDelayed(() -> super.setOnClickListener(l),200);
    }
}
