package com.maneater.dm.app.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.maneater.base.util.InjectUtil;
import com.maneater.base.widget.adapter.BaseListRecyclerAdapter;
import com.maneater.dm.app.R;
import com.maneater.dm.app.model.Hospital;

public class HospitalAdapter extends BaseListRecyclerAdapter<Hospital, HospitalViewHolder> {


    @Override
    public HospitalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return InjectUtil.injectViews(new HospitalViewHolder(parent, viewType), parent.getContext(), null);
    }

    @Override
    public void onBindViewHolder(HospitalViewHolder holder, int position) {
        holder.render(getItem(position));
    }
}
