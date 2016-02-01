package com.maneater.base.widget.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.widget.BaseAdapter;

import com.maneater.base.util.InjectUtil;

/**
 * 由ArrayList 构成的ListAdapter抽象类
 *
 * @author
 */
public abstract class BaseListAdapter<T> extends BaseAdapter {
    protected Context mContext;

    public BaseListAdapter(Context context) {
        this.mContext = context;
    }

    protected List<T> dataList = null;

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
        notifyDataSetInvalidated();
    }

    public void appendDataList(List<T> dataList) {
        if (dataList == null) {
            return;
        }
        if (this.dataList == null) {
            setDataList(dataList);
        } else {
            this.dataList.addAll(dataList);
            notifyDataSetChanged();
        }
    }

    @Override
    public int getCount() {
        return dataList != null ? dataList.size() : 0;
    }

    @Override
    public T getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    protected OnItemClickListener<T> onItemClickListener = null;

    public OnItemClickListener<T> getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener<T> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener<T> {
        void onItemClick(T t);
    }

    public static class BaseViewHolder implements InjectUtil.InjectViewAble {
        private View rootView = null;

        public BaseViewHolder(View rootView) {
            this.rootView = rootView;
        }

        @Override
        public View findViewById(int viewId) {
            return rootView.findViewById(viewId);
        }

    }

}
