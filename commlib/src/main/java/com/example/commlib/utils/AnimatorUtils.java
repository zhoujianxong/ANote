package com.example.commlib.utils;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.GridLayoutAnimationController;
import android.view.animation.Interpolator;
import android.view.animation.LayoutAnimationController;
import android.view.animation.LinearInterpolator;
import android.widget.GridView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;


/**
 *
 * @author zjx 2016-1-6 17:38:43
 */
@SuppressLint("NewApi")
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class AnimatorUtils {


    private static final String TAG="AnimatorUtils";
    /**
     * 通知动画
     *
     * @param view
     * @param f
     *            需要移动的距离
     * @param lon
     *            多少毫秒后执行第二个动画
     */

    public static void setY(final View view, final float f, long lon){
        //boolean isRunning=false;//控制动画是否运行中
        Log.e("viewHeight==", f+"");
        ObjectAnimator animator1= ObjectAnimator.ofFloat(view, "ko", -f, 0f);
        animator1.setDuration(700).start();
        animator1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator arg0) {
                float f = (Float) arg0.getAnimatedValue();
                view.setY(f); // 通过设置view的Y轴 实现移动
            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ObjectAnimator animator1 = ObjectAnimator.ofFloat(view, "ok",
                        0f, -f);
                animator1.setDuration(1000);
                animator1.start();
                animator1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator arg0) {
                        float f = (Float) arg0.getAnimatedValue();
                        view.setY(f);
                    }
                });
            }
        }, lon);

    }

    public static void translate(View view, View view2, float num, long duration) {
        Log.e("", "translateY");
        ObjectAnimator anim1 = ObjectAnimator.ofFloat(view, "translationY", -30f);
        ObjectAnimator anim2 = ObjectAnimator.ofFloat(view, "translationY",-30f);
        AnimatorSet animSet = new AnimatorSet();// 动画集合
        animSet.play(anim1);
        animSet.play(anim2).after(anim1);
        animSet.setDuration(duration);
        animSet.setInterpolator(new LinearInterpolator());// 动画匀速执行
        animSet.start();
    }

    public static void translateY(final View view, final boolean isShow,
                                  float fl) {
        Log.e("", "translateY==" + view.getHeight());
        ObjectAnimator anim1 = null;
        if (isShow) {
            anim1 = ObjectAnimator.ofFloat(view, "translationY", -fl, 0);
        } else {
            anim1 = ObjectAnimator.ofFloat(view, "translationY", 0, -fl);
        }

        anim1.setDuration(400);
        anim1.setInterpolator(new LinearInterpolator());// 动画匀速执行
        anim1.addListener(new Animator.AnimatorListener() {

            @Override
            public void onAnimationStart(Animator arg0) {
                // TODO Auto-generated method stub
                Log.v("", "onAnimationStart()");
            }

            @Override
            public void onAnimationRepeat(Animator arg0) {
                // TODO Auto-generated method stub
                Log.v("", "onAnimationRepeat()");
            }

            @Override
            public void onAnimationEnd(Animator arg0) {
                // TODO Auto-generated method stub
                Log.v("", "onAnimationEnd()");
                if (!isShow) {
                    view.setVisibility(View.GONE);
                }

            }

            @Override
            public void onAnimationCancel(Animator arg0) {
                // TODO Auto-generated method stub
                Log.v("", "onAnimationCancel()");
            }
        });
        anim1.start();
    }

    /**
     * 账单明细动画
     *
     * @param view
     * @param isShow
     * @param fl
     *            移动的距离
     * @param popupWindow
     *            不需要可传null
     * @param view2
     *            不需要可传null
     * @param duration
     *            滑动时长
     */
    public static void translatePriceLayout(final View view,
                                            final boolean isShow, float fl, final PopupWindow popupWindow,
                                            View view2, long duration) {
        Log.e("", "translateY==" + view.getHeight());
        ObjectAnimator anim1 = null;
        Animator anim2 = null;
        if (isShow) {
            anim1 = ObjectAnimator.ofFloat(view, "translationY", fl, 0)
                    .setDuration(duration);
            if (view2 != null) {
                anim2 = ObjectAnimator.ofFloat(view2, "alpha", 0.1f, 1)
                        .setDuration(duration);
            }

        } else {
            anim1 = ObjectAnimator.ofFloat(view, "translationY", 0, fl)
                    .setDuration(duration);
            if (view2 != null) {
                anim2 = ObjectAnimator.ofFloat(view2, "alpha", 1, 0.1f)
                        .setDuration(duration);
            }
        }

        // anim1.setDuration(300);
        anim1.setInterpolator(new LinearInterpolator());// 动画匀速执行
        anim1.addListener(new Animator.AnimatorListener() {

            @Override
            public void onAnimationStart(Animator arg0) {
                // TODO Auto-generated method stub
                Log.v("", "onAnimationStart()");
            }

            @Override
            public void onAnimationRepeat(Animator arg0) {
                // TODO Auto-generated method stub
                Log.v("", "onAnimationRepeat()");
            }

            @Override
            public void onAnimationEnd(Animator arg0) {
                // TODO Auto-generated method stub
                Log.v("", "onAnimationEnd()");
                if (popupWindow != null && !isShow) {
                    popupWindow.dismiss();
                }
            }

            @Override
            public void onAnimationCancel(Animator arg0) {
                // TODO Auto-generated method stub
                Log.v("", "onAnimationCancel()");
            }
        });

        if (anim2 != null) {
            AnimatorSet animSet = new AnimatorSet();
            animSet.playTogether(anim1, anim2);
            animSet.start();
        } else {
            anim1.start();
        }

    }

    /**
     * 针对 PopupWindow 顶部 Y轴 动画
     *
     * @param isShow true  向下 滑出
     * @param fl
     * @param popupWindow
     * @param duration
     */
    public static void translatePopupWindowTop(final boolean isShow, float fl,
                                               final PopupWindow popupWindow, long duration) {

        Log.e("", "translateY==" + popupWindow.getContentView().getHeight());
        ObjectAnimator anim1 = null;
        if (isShow) {
            anim1 = ObjectAnimator.ofFloat(popupWindow.getContentView(),
                    "translationY", -fl, 0).setDuration(duration);

        } else {
            anim1 = ObjectAnimator.ofFloat(popupWindow.getContentView(),
                    "translationY", 0, -fl).setDuration(duration);

        }
        anim1.setInterpolator(new LinearInterpolator());// 动画匀速执行
        anim1.addListener(new Animator.AnimatorListener() {

            @Override
            public void onAnimationStart(Animator arg0) {
                // TODO Auto-generated method stub
                Log.v("", "onAnimationStart()");
            }

            @Override
            public void onAnimationRepeat(Animator arg0) {
                // TODO Auto-generated method stub
                Log.v("", "onAnimationRepeat()");
            }

            @Override
            public void onAnimationEnd(Animator arg0) {
                // TODO Auto-generated method stub
                Log.v("", "onAnimationEnd()");
                if (popupWindow != null && !isShow) {
                    popupWindow.dismiss();
                }
            }

            @Override
            public void onAnimationCancel(Animator arg0) {
                // TODO Auto-generated method stub
                Log.v("", "onAnimationCancel()");
            }
        });

        anim1.start();

    }

    /**
     * 动画设置宽高
     *
     * @param v
     */
    public static void setWidth(final View v) {

        ObjectAnimator animator1 = ObjectAnimator.ofInt(v, "width", 0, 270);
        animator1.setDuration(1000);
        animator1.setRepeatCount(3);
        animator1.setInterpolator(new LinearInterpolator());

        ObjectAnimator animator2 = ObjectAnimator.ofInt(v, "height", 0, 200);
        animator2.setDuration(1000);
        AnimatorSet animSet = new AnimatorSet();// 动画集合
        animSet.play(animator1);
        animSet.play(animator2).after(animator1);// 2在1执行完后执行
        animSet.start();
    }

    public static void loadscaleDown(View v) {
        PropertyValuesHolder propertyValues1 = PropertyValuesHolder.ofFloat(
                "scaleX", 1.0f, 0.82f);
        PropertyValuesHolder propertyValues2 = PropertyValuesHolder.ofFloat(
                "scaleY", 1.0f, 0.82f);
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(v,
                propertyValues1, propertyValues2);
        animator.setDuration(200);
        animator.start();
    }

    public static void loadscaleUp(View v) {
        PropertyValuesHolder propertyValues3 = PropertyValuesHolder.ofFloat(
                "scaleX", 0.82f, 1.0f);
        PropertyValuesHolder propertyValues4 = PropertyValuesHolder.ofFloat(
                "scaleY", 0.82f, 1.0f);
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(v,
                propertyValues3, propertyValues4);
        animator.setDuration(200);
        animator.start();
    }

    /**
     * XY轴同时缩放
     *
     * @param v
     */
    public static void loadscaleXY(View v) {
        PropertyValuesHolder propertyValues1 = PropertyValuesHolder.ofFloat(
                "scaleX", 1.0f, 0.8f);
        PropertyValuesHolder propertyValues2 = PropertyValuesHolder.ofFloat(
                "scaleY", 1.0f, 0.8f);
        PropertyValuesHolder propertyValues3 = PropertyValuesHolder.ofFloat(
                "scaleX", 0.8f, 1.0f);
        PropertyValuesHolder propertyValues4 = PropertyValuesHolder.ofFloat(
                "scaleY", 0.8f, 1.0f);
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(v,
                propertyValues1, propertyValues2, propertyValues3,
                propertyValues4);
        animator.setDuration(900);
        animator.start();

    }




    /**
     * Y坐标缩放动画
     *
     * @param view
     */
    public static void scaleY(View view, Context context) {

        // 从XML中加载属性动画
        // Animator scaleYanim1 = AnimatorInflater.loadAnimator(context,
        // R.anim.scale_y_in);
        // final Animator scaleYanim2 = AnimatorInflater.loadAnimator(context,
        // R.anim.scale_y_out);
        // view.setPivotX(0);
        // view.setPivotY(0);
        // //view.invalidate();
        // scaleYanim1.setTarget(view);
        // scaleYanim2.setTarget(view);
        //
        // scaleYanim1.start();
        // new Handler().postDelayed(new Runnable() {
        // @Override
        // public void run() {
        // scaleYanim2.start();
        // }
        // }, 2000);
    }

    /**
     * 上侧进入
     *
     * @param v
     * @param lon
     */
    public static void sideTop(final View v, long lon) {
        Animator animator1 = ObjectAnimator.ofFloat(v, "translationY", -300, 0)
                .setDuration(lon);
        Animator animator2 = ObjectAnimator.ofFloat(v, "alpha", 0.1f, 1)
                .setDuration(lon * 3 / 2);

        AnimatorSet animSet = new AnimatorSet();
        animSet.playTogether(animator1, animator2);
        animSet.start();
    }

    /**
     * 底部翻转
     *
     * @param v
     * @param lon
     */
    public static void rotateBottom(final View v, long lon) {
        Animator animator1 = ObjectAnimator.ofFloat(v, "rotationX", 90, 0)
                .setDuration(lon);
        Animator animator2 = ObjectAnimator.ofFloat(v, "translationY", 250, 0)
                .setDuration(lon);
        Animator animator3 = ObjectAnimator.ofFloat(v, "alpha", 0, 1)
                .setDuration(lon * 3 / 2);

        AnimatorSet animSet = new AnimatorSet();
        animSet.playTogether(animator1, animator2, animator3);
        animSet.start();
    }

    /**
     * 上下翻转
     *
     * @param v
     * @param lon
     */
    public static void fromTop(final View v, long lon) {
        Animator animator1 = ObjectAnimator.ofFloat(v, "rotationX", 90, 88, 88,
                45, 0).setDuration(lon);
        Animator animator2 = ObjectAnimator.ofFloat(v, "alpha", 0, 0.4f, 0.8f,
                1).setDuration(lon * 3 / 2);
        Animator animator3 = ObjectAnimator.ofFloat(v, "scaleX", 0, 0.5f, 0.9f,
                0.9f, 1).setDuration(lon);
        Animator animator4 = ObjectAnimator.ofFloat(v, "scaleY", 0, 0.5f, 0.9f,
                0.9f, 1).setDuration(lon);

        AnimatorSet animSet = new AnimatorSet();
        animSet.playTogether(animator1, animator2, animator3, animator4);
        animSet.start();
    }

    /**
     * 抖动
     *
     * @param v
     * @param lon
     * @param RepeatCount
     *            重复次数
     */
    public static void shake(final View v, long lon, int RepeatCount) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(v,
                "translationX", 0, .10f, -25, .26f, 25, .42f, -25, .58f, 25,
                .74f, -25, .90f, 1, 0);
        objectAnimator.setRepeatCount(RepeatCount);
        objectAnimator.setDuration(lon).start();
    }

    /**
     * 指定时间内进行左右晃动
     * @param view
     * @param delta 左右偏移
     * @param duration
     * @return
     */
    public static void  shakeKeyframe(View view, int delta, int duration) {

        PropertyValuesHolder pvhTranslateX = PropertyValuesHolder.ofKeyframe(View.TRANSLATION_X,
                //Keyframe是一个时间/值对，用于定义在某个时刻动画的状态。
                //比如Keyframe.ofFloat(.10f, -10)定义了当动画进行了50%的时候，x轴的偏移值应该是-10。
                Keyframe.ofFloat(0f, 0),
                Keyframe.ofFloat(.1f, -delta),
                Keyframe.ofFloat(.2f, delta),
                Keyframe.ofFloat(.3f, -delta),
                Keyframe.ofFloat(.5f, delta),
                Keyframe.ofFloat(.7f, -delta),
                Keyframe.ofFloat(.9f, delta),
                Keyframe.ofFloat(1f, 0f)
        );

        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(view, pvhTranslateX);
       // objectAnimator.setRepeatCount(RepeatCount);
        objectAnimator.setDuration(duration).start();
    }


    /**
     * 垂直翻转
     *
     * @param v
     * @param lon
     */
    public static void rotationX(final View v, long lon, int flag) {
        if (flag == 0) {
            ObjectAnimator.ofFloat(v, "rotationX", 0, 180).setDuration(lon)
                    .start();
        } else {
            ObjectAnimator.ofFloat(v, "rotationX", 180, 0).setDuration(lon)
                    .start();
        }

    }

    /**
     * 水平翻转
     *
     * @param v
     */
    public static void rotationY(final View v) {
        v.setPivotX(30);
        v.setPivotY(30);
        // rotationY=Y轴翻转
        ObjectAnimator animator = ObjectAnimator.ofFloat(v, "rotationY", 0f,
                360f).setDuration(1000);
        animator.setRepeatCount(2);
        animator.start();
    }

    public static void rotation(final View v) {
        v.setPivotX(v.getMeasuredWidth() / 2f);
        v.setPivotY(v.getMeasuredWidth() / 2f);
        // rotationY=Y轴翻转
        final ObjectAnimator animator = ObjectAnimator.ofFloat(v, "rotation",
                0, 360).setDuration(1000);
        animator.setRepeatCount(-1);// 无限循环
        animator.setInterpolator(new LinearInterpolator());// 匀速
        animator.start();
    }

    /**
     * 旋转缩放
     *
     * @param v
     * @param lon
     */
    public static void rotationScaleXY(final View v, long lon) {
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(v, "rotation", 1080,
                720, 360, 0).setDuration(lon);
        // Animator animator1=ObjectAnimator.ofFloat(v,
        // "rotation",360,0).setDuration(lon);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(v, "alpha", 0, 1)
                .setDuration(lon * 3 / 2);
        ObjectAnimator animator3 = ObjectAnimator.ofFloat(v, "scaleX", 0.1f,
                0.5f, 1).setDuration(lon);
        ObjectAnimator animator4 = ObjectAnimator.ofFloat(v, "scaleY", 0.1f,
                0.5f, 1).setDuration(lon);

        AnimatorSet animSet = new AnimatorSet();
        animSet.playTogether(animator1, animator2, animator3, animator4);
        animSet.start();
    }

    public static void test1(final View v) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(v, "tfsls", 1f, 0.6f)
                .setDuration(2000);
        animator.setRepeatCount(3);
        animator.start();
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                float f = (Float) animator.getAnimatedValue();
                v.setPivotX(v.getMeasuredWidth() / 2f);// 设置动画从 该视图的中心开始
                v.setPivotY(v.getMeasuredWidth() / 2f);
                // v.setTranslationX(f);
                // v.setTranslationY(f);
                v.setScaleX(f);
                v.setScaleY(f);
                v.setAlpha(f);
                v.setRotation(f);
            }
        });
    }
    /**
     *
     * @param view
     * @param isDown 是否向上 true 向下
     * @param fl
     */
    public static void translateYCity(final View view, final boolean isDown,
                                      float fl) {
        Log.e("", "translateY==" + view.getHeight());
        ObjectAnimator anim1 = null;
        if (isDown) {
            anim1 = ObjectAnimator.ofFloat(view, "translationY", 0, fl);
        } else {
            anim1 = ObjectAnimator.ofFloat(view, "translationY", 0, -fl);
        }

        anim1.setDuration(400);
        anim1.setInterpolator(new LinearInterpolator());// 动画匀速执行
        anim1.start();
    }

    /**
     *
     * @param view
     * @param isDown 是否向上 true 向下
     * @param fl
     */
    public static void translateYCity2(final View view, final boolean isDown,
                                       float fl) {
        Log.e("", "translateY==" + view.getHeight());
        ObjectAnimator anim1 = null;
        if (isDown) {
            anim1 = ObjectAnimator.ofFloat(view, "translationY",-fl, 0);
        } else {
            anim1 = ObjectAnimator.ofFloat(view, "translationY", fl,0);
        }

        anim1.setDuration(400);
        anim1.setInterpolator(new LinearInterpolator());// 动画匀速执行
        anim1.start();
    }


    /**
     * <b>view.setVisibility() </b>设置 高度伸缩
     * <li> v 执行view
     * <li> b true 展开 false 收缩
     * <li> finshListener {@link OnFinshListener}监听伸缩是否完成 不需要可为 null
     */
    public static void animateForVisableOrGone(final View v, final boolean b,
                                               final OnFinshListener finshListener, final long duration){
        if(b){
            v.setVisibility(View.VISIBLE);
        }

        int mHeight=0;
        if(v.getTag()!=null){
            mHeight= Integer.parseInt(v.getTag().toString());
        }else{
            if(v.getHeight()>0){
                v.setTag(v.getHeight());//保存初始高度
                mHeight=v.getHeight();
            }
        }
        Log.i(TAG, "v.getHeight()=="+v.getHeight());
        Log.i(TAG, "v.getLayoutParams().height=="+v.getLayoutParams().height);
        Log.i(TAG, "view高度:"+mHeight);
        int form =0;
        int to=0;

        if(mHeight>0){
            if(b){
                form =0;
                to=mHeight;
                //v.setVisibility(View.VISIBLE);// 先让view显示，不然看不到动画
            }else{
                //v.setVisibility(View.VISIBLE);// 先让view显示，不然看不到动画
                mHeight=v.getHeight();
                form =mHeight;
                to=0;
            }

            ObjectAnimator oa= ObjectAnimator.ofInt(v, "height",form,to).setDuration(duration);
            oa.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(final ValueAnimator va) {
                    LayoutParams l=v.getLayoutParams();
                    l.height=(Integer) va.getAnimatedValue("height");
                    v.setLayoutParams(l);
                    if(!b&&l.height==0){
                        v.setVisibility(View.GONE);
                    }
                   //  Log.d(TAG, "l.height:"+l.height);
                }
            });
            oa.start();
            addListener(oa,finshListener);
        }else{
            v.getViewTreeObserver().addOnPreDrawListener(new OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {
                    Log.v("animateForVisableOrGone", "view高度="+v.getHeight());
                    v.getViewTreeObserver().removeOnPreDrawListener(this);
                    ObjectAnimator oa= ObjectAnimator.ofInt(v, "height", 0,v.getHeight()).setDuration(duration);
                    oa.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator va) {
                            LayoutParams l=v.getLayoutParams();
                            l.height=(Integer) va.getAnimatedValue("height");
                            v.setLayoutParams(l);
                            v.setTag(v.getHeight());
                             // Log.v("", "l.height:"+l.height);

                      /*      if(v.getId()==getById("R.id.content_layout")){
                                //给 content_layout高度特殊处理一下+高度
                                v.setTag(v.getHeight()+100);
                            }*/
                        }
                    });
                    oa.start();
                    addListener(oa,finshListener);
                    return false;
                }
            });

        }



    }



    public static void addListener(ObjectAnimator oa, final OnFinshListener finshListener){
        if(oa!=null&&finshListener!=null)
            oa.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator arg0) {
                   // finshListener.isFinish(false);
                }
                @Override
                public void onAnimationRepeat(Animator arg0) {
                }
                @Override
                public void onAnimationEnd(Animator arg0) {
                    finshListener.isFinish(true);
                }
                @Override
                public void onAnimationCancel(Animator arg0) {
                }
            });
    }

    /**
     *  根据 View 高度 伸缩动画
     * @param v
     * @param hig1 上一次高度
     * @param hig2 操作后高度
     * <li> finshListener {@link OnFinshListener}监听伸缩是否完成 不需要可为 null
     */
    public static void setViewHeightChangeAnimate(final View v, int hig1, int hig2, final OnFinshListener finshListener){
         ObjectAnimator oa=null;
        Log.d("", "上一次高度："+hig1);
        Log.v("", "操作后高度："+hig2);
        if(hig1!=hig2){
            oa= ObjectAnimator.ofInt(v, "height",hig1,hig2);
        }
        if(oa!=null){
            addListener(oa,finshListener);
            oa.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator va) {
                    LayoutParams l=v.getLayoutParams();
                    l.height=(Integer) va.getAnimatedValue("height");
                    v.setLayoutParams(l);
                   // Log.v("", "l.height:"+l.height);
                }
            });
            oa.setDuration(500).start();
        }

    }

    static float alpha=1f;
    /**
     *  根据 View 高度 伸缩动画
     * @param v
     * @param hig1 上一次高度
     * @param hig2 操作后高度
     * <li> finshListener {@link OnFinshListener}监听伸缩是否完成 不需要可为 null
     */
    public static void setViewHeightChangeAnimate(final View v, int hig1, int hig2
            , final OnFinshListener finshListener, long duration){
         ObjectAnimator oa=null;
        Log.d(TAG, "上一次高度："+hig1);
        Log.v(TAG, "操作后高度："+hig2);
        if(hig1!=hig2){
            oa= ObjectAnimator.ofInt(v, "height",hig1,hig2);
        }
        if(oa!=null){

            addListener(oa,finshListener);
            oa.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator va) {
                    LayoutParams l=v.getLayoutParams();
                    l.height=(Integer) va.getAnimatedValue("height");
                    v.setLayoutParams(l);
                    // Log.v("", "l.height:"+l.height);
                }
            });
            oa.setDuration(duration).start();
        }

    }


    /**
     *  根据 View 高度 伸缩动画
     * @param v
     * @param hig1 上一次高度
     * @param hig2 操作后高度
     * <li> finshListener {@link OnFinshListener}监听伸缩是否完成 不需要可为 null
     */
    public static void setViewWidthtChangeAnimate(final View v, int hig1, int hig2
            , final OnFinshListener finshListener, long duration){
        ObjectAnimator oa=null;
        Log.d(TAG, "上一次width："+hig1);
        Log.v(TAG, "操作后width："+hig2);
        if(hig1!=hig2){
            oa= ObjectAnimator.ofInt(v, "width",hig1,hig2);
        }
        if(oa!=null){

            addListener(oa,finshListener);
            oa.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator va) {
                    LayoutParams l=v.getLayoutParams();
                    l.width=(Integer) va.getAnimatedValue("width");
                    v.setLayoutParams(l);
                    // Log.v("", "l.height:"+l.height);
                }
            });
            oa.setDuration(duration).start();
        }

    }

    /**
     *
     * @param group
     * @param anim   R.anim.left
     * @param context
     */
    public static void setLoadAnimation(ViewGroup group, int anim, Context context, float delay){
        //通过加载XML动画设置文件来创建一个Animation对象；
        Animation animation = AnimationUtils.loadAnimation(context,anim);
        LayoutAnimationController lac = new LayoutAnimationController(animation);
        //设置控件显示的顺序；
        lac.setOrder(LayoutAnimationController.ORDER_NORMAL);
        //设置控件显示间隔时间；
        lac.setDelay(delay);
        //设置LayoutAnimationController属性；
        group.setLayoutAnimation(lac);
    }

    public static void setGridLoadAnimation(GridView grid, int anim, Context context, float delay){
        Animation animation = AnimationUtils.loadAnimation(context,anim);
        GridLayoutAnimationController controller = new GridLayoutAnimationController(animation);
        controller.setColumnDelay(delay);
        controller.setRowDelay(delay+0.2f);
        //方向从上至下|从左至右
        controller.setDirection(GridLayoutAnimationController.DIRECTION_TOP_TO_BOTTOM
                | GridLayoutAnimationController.DIRECTION_LEFT_TO_RIGHT);
        //方向优先级 默认
        controller.setDirectionPriority(GridLayoutAnimationController.PRIORITY_NONE);
        grid.setLayoutAnimation(controller);
        grid.startLayoutAnimation();
    }

    //•让背景跟Dialog出现一样，变暗，带动画。
    /** 窗口背景变暗*/
   // dimBackground(1.0f,0.5f);

    private void dimBackground(float from, float to, Context context) {
        final Window window = ((Activity)context).getWindow();
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(from, to);
        valueAnimator.setDuration(500);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                WindowManager.LayoutParams params = window.getAttributes();
                params.alpha = (Float) animation.getAnimatedValue();
                window.setAttributes(params);
            }
        });
        valueAnimator.start();
    }


    public static void topAlpha(View view, long duration) {
        Log.e("", "translateY");
        ObjectAnimator anim1 = ObjectAnimator.ofFloat(view, "alpha", 0,1);
        ObjectAnimator anim2 = ObjectAnimator.ofFloat(view, "alpha",1,0);
//        anim1.setDuration(1000);
//        anim1.setDuration(1000);
        AnimatorSet animSet = new AnimatorSet();// 动画集合
        animSet.play(anim1);

        animSet.play(anim2).after(anim1);
        animSet.setDuration(duration);
        animSet.setInterpolator(new LinearInterpolator());// 动画匀速执行
        animSet.start();
    }

    /**
     *
     * @author yeziheng<br>
     * <b>可监听动画完成后在执行方法</b>（避免快速多次点击时不执行展开动画）
     *
     */
    public interface OnFinshListener{
        void isFinish(boolean bool);
    }



    /**
     * 开启Alpha 动画
     *
     * @param view
     * @param fromAlpha
     * @param toAlpha
     */
    public static void alphaAnimation(View view, float fromAlpha, float toAlpha,OnFinshListener listener) {
        if (view == null) {
            return;
        }
        if (view.getAnimation() != null) {
            view.getAnimation().cancel();
            view.clearAnimation();
            return;
        }
        //-------Alpaha--------
        ObjectAnimator objectAnimator= ObjectAnimator.ofFloat(view, "alpha", fromAlpha, toAlpha);
        objectAnimator.setDuration(300);
        objectAnimator.setInterpolator(new DecelerateInterpolator());
        objectAnimator.start();
        addListener(objectAnimator,listener);
    }

    /**
     * 清理目标View的动画
     *
     * @param paramView
     */
    public static void clearAnimation(View paramView) {
        if (paramView == null)
            return;

        if (paramView.getAnimation() == null) {
            return;
        }

        paramView.getAnimation().cancel();
        paramView.clearAnimation();
    }


    /**
     * 旋转动画
     *
     * @param paramView
     * @param paramInt
     *            时间
     */
    public static void startRotateAnimation(View paramView, int paramInt) {
        float[] arrayOfFloat = new float[2];
        arrayOfFloat[0] = 0.0F;
        arrayOfFloat[1] = 360.0F;
        ObjectAnimator localObjectAnimator = ObjectAnimator.ofFloat(paramView,
                "rotation", arrayOfFloat);
        localObjectAnimator.setDuration(paramInt);
        localObjectAnimator.setInterpolator(null);
        localObjectAnimator.setRepeatCount(-1);
        localObjectAnimator.start();
    }

    /**
     * 从上往下移动的位移动画
     *
     * @param paramView
     * @return
     */
    public static AnimatorSet startTranlateDownAnimation(View paramView) {
        if (paramView.getAnimation() != null)
            paramView.getAnimation().cancel();
        paramView.clearAnimation();
        AnimatorSet localAnimatorSet = new AnimatorSet();
        float[] arrayOfFloat = new float[2];
        arrayOfFloat[0] = -80.0F;
        arrayOfFloat[1] = 1.0F;
        ObjectAnimator localObjectAnimator = ObjectAnimator.ofFloat(paramView,
                "translationY", arrayOfFloat);
        localObjectAnimator.setDuration(240L);
        localObjectAnimator.setInterpolator(new DecelerateInterpolator());
        localAnimatorSet.play(localObjectAnimator);
        localAnimatorSet.start();
        return localAnimatorSet;
    }

    /**
     * 从下网上移动的位移动画
     *
     * @param paramView
     * @return
     */
    public static AnimatorSet startTranlateUpAnimation(View paramView) {
        if (paramView.getAnimation() != null)
            paramView.getAnimation().cancel();
        paramView.clearAnimation();
        AnimatorSet localAnimatorSet = new AnimatorSet();
        float[] arrayOfFloat = new float[2];
        arrayOfFloat[0] = 1.0F;
        arrayOfFloat[1] = -80.0F;
        ObjectAnimator localObjectAnimator = ObjectAnimator.ofFloat(paramView,
                "translationY", arrayOfFloat);
        localObjectAnimator.setDuration(240L);
        localObjectAnimator.setInterpolator(new DecelerateInterpolator());
        localAnimatorSet.play(localObjectAnimator);
        localAnimatorSet.start();
        return localAnimatorSet;
    }


    public interface OnDoIntListener {
        void doSomething(int intValue);
    }
    /**
     * 颜色渐变动画
     *
     * @param beforeColor 变化之前的颜色
     * @param afterColor  变化之后的颜色
     * @param listener    变化事件
     */
    public static void animationColorGradient(int beforeColor, int afterColor, final OnDoIntListener listener) {
        ValueAnimator valueAnimator = ValueAnimator.ofObject(new ArgbEvaluator(), beforeColor, afterColor).setDuration(3000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
//                textView.setTextColor((Integer) animation.getAnimatedValue());
                listener.doSomething((Integer) animation.getAnimatedValue());
            }
        });
        valueAnimator.start();
    }

    /**
     * 卡片翻转动画
     *
     * @param beforeView
     * @param afterView
     */
    public static void cardFilpAnimation(final View beforeView, final View afterView) {
        Interpolator accelerator = new AccelerateInterpolator();
        Interpolator decelerator = new DecelerateInterpolator();
        ObjectAnimator invisToVis = null;
        ObjectAnimator visToInvis = null;
        if (beforeView.getVisibility() == View.GONE) {
            // 局部layout可达到字体翻转 背景不翻转
            invisToVis = ObjectAnimator.ofFloat(beforeView,
                    "rotationY", -90f, 0f);
            visToInvis = ObjectAnimator.ofFloat(afterView,
                    "rotationY", 0f, 90f);
        } else if (afterView.getVisibility() == View.GONE) {
            invisToVis = ObjectAnimator.ofFloat(afterView,
                    "rotationY", -90f, 0f);
            visToInvis = ObjectAnimator.ofFloat(beforeView,
                    "rotationY", 0f, 90f);
        }

        visToInvis.setDuration(250);// 翻转速度
        visToInvis.setInterpolator(accelerator);// 在动画开始的地方速率改变比较慢，然后开始加速
        invisToVis.setDuration(250);
        invisToVis.setInterpolator(decelerator);
        final ObjectAnimator finalInvisToVis = invisToVis;
        final ObjectAnimator finalVisToInvis = visToInvis;
        visToInvis.addListener(new Animator.AnimatorListener() {

            @Override
            public void onAnimationEnd(Animator arg0) {
                if (beforeView.getVisibility() == View.GONE) {
                    afterView.setVisibility(View.GONE);
                    finalInvisToVis.start();
                    beforeView.setVisibility(View.VISIBLE);
                } else {
                    afterView.setVisibility(View.GONE);
                    finalVisToInvis.start();
                    beforeView.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onAnimationCancel(Animator arg0) {

            }

            @Override
            public void onAnimationRepeat(Animator arg0) {

            }

            @Override
            public void onAnimationStart(Animator arg0) {

            }
        });
        visToInvis.start();
    }


    /**
     * 设置ProgressBar的进度（平滑的增长）
     * @param progressBar progressBar
     * @param progress 取值0-100
     */
    public static void setProgressSmooth(ProgressBar progressBar, int progress) {
        ObjectAnimator animation = ObjectAnimator.ofInt(progressBar, "progress", progress);
        // 1 second
        animation.setDuration(1000);
        //先加速在减速
        animation.setInterpolator(new AccelerateDecelerateInterpolator());
        animation.start();

    }


}
