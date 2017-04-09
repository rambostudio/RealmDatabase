package com.rambostudio.zojoz.realmdatabase.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rambostudio.zojoz.realmdatabase.R;
import com.rambostudio.zojoz.realmdatabase.model.Person;
import com.rambostudio.zojoz.realmdatabase.util.CollectionIndexUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rambo on 9/4/2560.
 */

public class PersonManagementRecyclerViewAdapter extends RecyclerView.Adapter<PersonManagementRecyclerViewAdapter.PersonManagementViewHolder> {

    private List<Person> mList = new ArrayList<>();

    public PersonManagementRecyclerViewAdapter(List<Person> mList) {
        this.mList = mList;
    }

    @Override
    public PersonManagementViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PersonManagementViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_person_management_item, parent, false));

    }

    @Override
    public void onBindViewHolder(PersonManagementViewHolder holder, int position) {
        if (CollectionIndexUtil.isAvailableData(mList, position)) {
            holder.bindData(mList.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return CollectionIndexUtil.getSize(mList);
    }

    public class PersonManagementViewHolder extends RecyclerView.ViewHolder {
        TextView tvPersonName;

        public PersonManagementViewHolder(View itemView) {
            super(itemView);
            initInstances();
        }

        private void initInstances() {
            tvPersonName = (TextView) itemView.findViewById(R.id.tvPersonName);

        }

        private void bindData(Person person) {
            tvPersonName.setText(person.getName());
        }
    }


}
