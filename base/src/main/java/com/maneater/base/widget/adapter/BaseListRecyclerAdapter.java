package com.maneater.base.widget.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.maneater.base.util.InjectUtil;

import java.util.List;

public abstract class BaseListRecyclerAdapter<T, VH extends BaseListRecyclerAdapter.BaseViewHolder> extends RecyclerView.Adapter<VH> {

    private List<T> dataList = null;

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
        notifyDataSetChanged();
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
    public int getItemCount() {
        return dataList != null ? dataList.size() : 0;
    }

    public T getItem(int location) {
        return dataList != null ? dataList.get(location) : null;
    }

    public abstract static class BaseViewHolder extends RecyclerView.ViewHolder implements InjectUtil.InjectViewAble {

        public BaseViewHolder(View itemView) {
            super(itemView);
        }


        @Override
        public View findViewById(int viewId) {
            return itemView.findViewById(viewId);
        }
    }
}
