package com.example.anote.vm;

import android.app.Application;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;

import com.blankj.ALog;
import com.example.anote.R;
import com.example.anote.base.BaseVM;
import com.example.anote.bean.NoteBean;
import com.example.anote.databinding.ItemMianBinding;
import com.example.commlib.base.mvvm.BaseAdapter;
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



    private ObservableArrayList<NoteBean> mList=new ObservableArrayList<>();

    public BaseAdapter mAdapter=new BaseAdapter<NoteBean,ItemMianBinding>(R.layout.item_mian, mList){
        @Override
        public void vdbConvert(ItemMianBinding itemMianBinding, NoteBean item) {
            itemMianBinding.mTextView.setText(item.getTitle());
        }
    };

    /**
     * 添加笔记
     * @param title
     * @param url
     */
    public void addNote(String title,String url){
        NoteBean noteBean=new NoteBean();
        noteBean.setTitle(title);
        noteBean.setWebUrl(url);
        noteBean.save(new SaveListener<String>() {
            @Override
            public void done(String objectId, BmobException e) {
                if(e==null){
                    ALog.v("添加数据成功，返回objectId为："+objectId);
                }else{
                    ALog.v("创建数据失败：" + e.getMessage());
                }
            }
        });
    }

    /**
     * 获取一行数据
     */
    public void getNote(String objectId){
        //查找Person表里面id为6b6c11c537的数据
        BmobQuery<NoteBean> bmobQuery = new BmobQuery<>();
        bmobQuery.getObject(objectId, new QueryListener<NoteBean>() {
            @Override
            public void done(NoteBean noteBean,BmobException e) {
                if(e==null){
                    ALog.v("查询成功 "+noteBean.toString());
                }else{
                    ALog.v("查询失败：" + e.getMessage());
                }
            }
        });
    }

    public void getNotes(){
        //查找Person表里面id为6b6c11c537的数据
        BmobQuery<NoteBean> bmobQuery = new BmobQuery<>();
//        bmobQuery.setLimit(8).setSkip(1).order("-createdAt")
        bmobQuery.setLimit(8).order("-createdAt")
                .findObjects(new FindListener<NoteBean>() {
                    @Override
                    public void done(List<NoteBean> noteBeans, BmobException e) {
                        if (e == null) {
                            ALog.v("查询成功 "+noteBeans);
                            mList.addAll(noteBeans);
                        } else {
                            ALog.v("查询失败：" + e.getMessage());
                        }
                    }
                });

//        Disposable disposable=;
//        Observable
    }
}
