package com.rambostudio.zojoz.realmdatabase.adapter;

import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.rambostudio.zojoz.realmdatabase.R;
import com.rambostudio.zojoz.realmdatabase.model.Person;
import com.rambostudio.zojoz.realmdatabase.util.CollectionIndexUtil;
import com.rambostudio.zojoz.realmdatabase.viewmodel.AddPersonInHomeViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rambo on 11/4/2560.
 */

public class AddPersonInHomeDialogRecyclerViewAdapter extends RecyclerView.Adapter<AddPersonInHomeDialogRecyclerViewAdapter.AddPersonInHomeDialogViewHolder> {
    List<AddPersonInHomeViewModel> mList = new ArrayList<>();

    public AddPersonInHomeDialogRecyclerViewAdapter(List<AddPersonInHomeViewModel> list) {
        this.mList = list;
    }

    @Override
    public AddPersonInHomeDialogViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AddPersonInHomeDialogViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_add_person_in_home_item, parent, false));
    }

    @Override
    public void onBindViewHolder(AddPersonInHomeDialogViewHolder holder, int position) {

        if (CollectionIndexUtil.isAvailableData(mList, position)) {
            holder.bindData(mList.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return CollectionIndexUtil.getSize(mList);
    }

    public class AddPersonInHomeDialogViewHolder extends RecyclerView.ViewHolder {
        AppCompatCheckBox cbAddPersonInHome;
        TextView tvName;
        public AddPersonInHomeDialogViewHolder(View itemView) {
            super(itemView);
            initInstances();
        }

        private void initInstances() {
            tvName = (TextView) itemView.findViewById(R.id.tvPersonName);
            cbAddPersonInHome = (AppCompatCheckBox) itemView.findViewById(R.id.cbAddPersonInHome);
            cbAddPersonInHome.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    mList.get(getAdapterPosition()).setChecked(b);
                }
            });
        }

        public void bindData(AddPersonInHomeViewModel obj) {
            if (obj != null) {
                tvName.setText(obj.getName());
                cbAddPersonInHome.setChecked(obj.isChecked());

            }
        }
    }

}
