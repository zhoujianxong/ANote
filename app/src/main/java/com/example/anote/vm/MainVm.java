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

public class MainVm extends BaseVM {
    public MainVm(@NonNull Application application) {
        super(application);
    }


    private ObservableArrayList<NoteBean> mList = new ObservableArrayList<>();

    public BaseAdapter mAdapter = new BaseAdapter<NoteBean, ItemMianBinding>(R.layout.item_mian, mList) {
        @SuppressLint("NewApi")
        @Override
        public void vdbConvert(ItemMianBinding itemMianBinding, NoteBean item) {
            String title = item.getTitle();
            String describe = item.getDescribe();
            if (title != null) {
                String content = item.getTitle();
                if (describe != null) {
                    content = content + item.getDescribe();
                }
                SpannableString spannableString = new SpannableString(content);
                spannableString.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 0, item.getTitle().length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                if (describe!=null){
                    spannableString.setSpan(new StyleSpan(android.graphics.Typeface.NORMAL), item.getTitle().length(), content.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                    spannableString.setSpan(new AbsoluteSizeSpan(35), item.getTitle().length(), content.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    spannableString.setSpan(new ForegroundColorSpan(getApplication().getColor(R.color.black_aplsh40)), item.getTitle().length(), content.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
                itemMianBinding.mTextView.setText(spannableString);
            }


            itemMianBinding.getRoot().setOnClickListener(v -> {
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
    public void addNote(String title, String url) {
        NoteBean noteBean = new NoteBean();
        noteBean.setTitle(title);
        noteBean.setWebUrl(url);
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

    public void getNotes() {
        //查找Person表里面id为6b6c11c537的数据
        BmobQuery<NoteBean> bmobQuery = new BmobQuery<>();
//        bmobQuery.setLimit(8).setSkip(1).order("-createdAt")
        bmobQuery.setLimit(17).order("-createdAt")
                .findObjects(new FindListener<NoteBean>() {
                    @Override
                    public void done(List<NoteBean> noteBeans, BmobException e) {
                        if (e == null) {
                            ALog.v("查询成功 " + noteBeans);
                            mList.addAll(noteBeans);
                        } else {
                            ALog.v("查询失败：" + e.getMessage());
                        }
                    }
                });

//        Disposable disposable=;
//        Observable
    }

    private LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplication());
}
