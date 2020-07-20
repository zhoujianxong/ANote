package com.example.anote.vm;

import android.annotation.SuppressLint;
import android.app.Application;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.blankj.ALog;
import com.example.anote.ANoteApplication;
import com.example.anote.R;
import com.example.anote.base.BaseVM;
import com.example.anote.bean.NoteBean;
import com.example.anote.databinding.ItemMianBinding;
import com.example.commlib.base.mvvm.BaseAdapter;
import com.example.commlib.webview.WebViewActivity;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;

public class MainVM extends BaseVM {
    public MainVM(@NonNull Application application) {
        super(application);
    }


    private ObservableArrayList<NoteBean> mList = new ObservableArrayList<>();

    public BaseAdapter mAdapter = new BaseAdapter<NoteBean, ItemMianBinding>(R.layout.item_mian, mList) {
        @SuppressLint("NewApi")
        @Override
        public void vdbConvert(ItemMianBinding itemMianBinding, NoteBean item,int position) {
            //标题
            String title = item.getTitle();
            //介绍描述
            String describe = item.getDescribe();
            if (title != null) {
                String content = item.getTitle();
                if (describe != null) {
                    //合并内容
                    content =content + item.getDescribe();
                }
                SpannableString spannableString = new SpannableString(content);
                //标题加粗
                spannableString.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 0, item.getTitle().length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                if (describe != null) {
                    //介绍字体减小  颜色淡化设置   字体变细
                    spannableString.setSpan(new StyleSpan(android.graphics.Typeface.NORMAL), item.getTitle().length(), content.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                    spannableString.setSpan(new AbsoluteSizeSpan(30), item.getTitle().length(), content.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    spannableString.setSpan(new ForegroundColorSpan(getApplication().getColor(R.color.colorText2)), item.getTitle().length(), content.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
                itemMianBinding.mTextView.setText(spannableString);
            }
            itemMianBinding.mId.setText(""+position);


            itemMianBinding.getRoot().setOnClickListener(v -> {
                //调到相应的博客
                WebViewActivity.loadUrl(item.getWebUrl(), null);
            });
        }
    };

    /**
     * 添加笔记
     *
     * @param title
     * @param url
     */
    public void addNote(String title, String url, String describe) {
        NoteBean noteBean = new NoteBean();
        noteBean.setTitle(title);
        noteBean.setWebUrl(url);
        noteBean.setDescribe(describe);
        noteBean.save(new SaveListener<String>() {
            @Override
            public void done(String objectId, BmobException e) {
                if (e == null) {
                    ALog.v("添加数据成功，返回objectId为：" + objectId);
                } else {
                    ALog.v("创建数据失败：" + e.getMessage());
                }
            }
        });
    }

    /**
     * 获取一行数据
     */
    public void getNote(String objectId) {
        //查找Person表里面id为6b6c11c537的数据
        BmobQuery<NoteBean> bmobQuery = new BmobQuery<>();
        bmobQuery.getObject(objectId, new QueryListener<NoteBean>() {
            @Override
            public void done(NoteBean noteBean, BmobException e) {
                if (e == null) {
                    ALog.v("查询成功 " + noteBean.toString());
                } else {
                    ALog.v("查询失败：" + e.getMessage());
                }
            }
        });
    }

    public void getNotes(int skip) {
        //查找Person表里面id为6b6c11c537的数据
        BmobQuery<NoteBean> bmobQuery = new BmobQuery<>();
        //一次取10条  skip跳过多少条 做分页查询
        bmobQuery.setLimit(15).setSkip(skip).order("-createdAt")
                .findObjects(new FindListener<NoteBean>() {
                    @Override
                    public void done(List<NoteBean> noteBeans, BmobException e) {
                        if (e == null) {
                            ALog.v("查询成功 " + noteBeans);
                            if (skip == 0) {
                                mList.clear();
                                mList.addAll(noteBeans);
                            } else {
                                mList.addAll(noteBeans);
                            }
                        } else {
                            ALog.v("查询失败：" + e.getMessage());
                        }
                    }
                });
    }

    /**
     * 刷新
     */
    public void reFresh() {
        getNotes(0);
    }

    /**
     * 加载
     */
    public void loadMore() {
        getNotes(mList.size());
    }

}
