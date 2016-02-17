package com.maneater.dm.app.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.maneater.base.util.InjectUtil;
import com.maneater.base.util.ToastUtil;
import com.maneater.base.util.XImageLoader;
import com.maneater.base.widget.adapter.BaseListRecyclerAdapter;
import com.maneater.dm.app.R;
import com.maneater.dm.app.model.Hospital;

public class HospitalViewHolder extends BaseListRecyclerAdapter.BaseViewHolder implements InjectUtil.InjectViewAble {
    public HospitalViewHolder(ViewGroup parent, int viewType) {
        super(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_hosptial, parent, false));
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.showToast(itemView.getContext(), "click");
            }
        });
    }

    private ImageView ivHospital;
    private TextView txHospitalName;

    public void render(Hospital hospital) {
        txHospitalName.setText(hospital.getName());
        XImageLoader.getDefault().displayImage(hospital.getPicUrl(), ivHospital);
    }
}
