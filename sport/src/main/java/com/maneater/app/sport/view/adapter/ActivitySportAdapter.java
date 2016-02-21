package com.maneater.app.sport.view.adapter;

import android.view.ViewGroup;
import com.maneater.app.sport.model.ActivitySport;
import com.maneater.base.util.InjectUtil;
import com.maneater.base.widget.adapter.BaseListRecyclerAdapter;

public class ActivitySportAdapter extends BaseListRecyclerAdapter<ActivitySport, ActivitySportViewHolder> {


    @Override
    public ActivitySportViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return InjectUtil.injectViews(new ActivitySportViewHolder(parent, viewType), parent.getContext(), null);
    }

    @Override
    public void onBindViewHolder(ActivitySportViewHolder holder, int position) {
        holder.render(getItem(position));
    }
}
