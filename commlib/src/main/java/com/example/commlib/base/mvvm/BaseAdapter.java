package com.example.commlib.base.mvvm;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.commlib.R;
import com.example.commlib.api.ConfigApi;

import java.util.List;

/**
 * 基于BaseQuickAdapter 优化  BaseMvvmRecyclerAdapter
 * @anthor zjx
 * @time 2020/06/15 18:54
 */
public abstract class BaseAdapter<T,VDB extends ViewDataBinding> extends BaseQuickAdapter<T,BaseAdapter.VH> {
    private ObservableList<T> mTObservableList;//让list数据变更后自动notifyItemRangeChanged刷新
    protected BaseAdapter(@LayoutRes int layoutResId, @Nullable List<T> data) {
        super(layoutResId, data);
//        this.mTObservableList = data == null ? new ObservableArrayList<>() : (ObservableList<T>) data;
//        if (layoutResId != 0) {
//            this.mLayoutResId = layoutResId;
//        }
//        mTObservableList.addOnListChangedCallback(new ObservableList.OnListChangedCallback<ObservableList<T>>() {
//            @Override
//            public void onChanged(ObservableList<T> ts) {
//                 notifyDataSetChanged();
//            }
//            @Override
//            public void onItemRangeChanged(ObservableList<T> ts, int positionStart, int itemCount) {
//                notifyItemRangeChanged(positionStart, itemCount);
//            }
//
//            @Override
//            public void onItemRangeInserted(ObservableList<T> ts, int positionStart, int itemCount) {
//                //踩坑提示：使用 quickadapter.setEmptyView 设置空布局后， 刷新又有了数据 必须调用mAdapter.setNewData(mList); 而不是调用notifyDataSetChanged()系列; 否则会报错
//                if(ConfigApi.EMPTY_VIEW){
//                    setNewData(ts);
//                }else{
//                    notifyItemRangeInserted(positionStart, itemCount);
//                }
//            }
//
//            @Override
//            public void onItemRangeMoved(ObservableList<T> ts, int fromPosition, int toPosition, int itemCount) {
//                for (int i = 0; i < itemCount; i++) {
//                    notifyItemMoved(fromPosition + i, toPosition + i);
//                }
//            }
//            @Override
//            public void onItemRangeRemoved(ObservableList<T> ts, int positionStart, int itemCount) {
//                notifyItemRangeRemoved(positionStart, itemCount);
//            }
//        });
    }

    @Override
    protected void convert(@NonNull VH helper, T item) {
        vdbConvert(DataBindingUtil.bind(helper.itemView),item);
    }

    static class VH extends BaseViewHolder {
        public VH(View view) {
            super(view);
        }
    }

    public abstract void vdbConvert(VDB vdb,T item);

}
