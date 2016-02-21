package com.maneater.app.sport.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.maneater.app.sport.R;
import com.maneater.app.sport.model.ActivitySport;
import com.maneater.base.util.InjectUtil;
import com.maneater.base.util.ToastUtil;
import com.maneater.base.widget.adapter.BaseListRecyclerAdapter;

public class ActivitySportViewHolder extends BaseListRecyclerAdapter.BaseViewHolder implements InjectUtil.InjectViewAble {
    public ActivitySportViewHolder(ViewGroup parent, int viewType) {
        super(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_activity_sport, parent, false));
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.showToast(itemView.getContext(), "click");
            }
        });
    }

    private ImageView ivHospital;
    private TextView txHospitalName;

    public void render(ActivitySport activitySport) {
//        txHospitalName.setText(hospital.getName());
//        XImageLoader.getDefault().displayImage(hospital.getPicUrl(), ivHospital);
    }
}
