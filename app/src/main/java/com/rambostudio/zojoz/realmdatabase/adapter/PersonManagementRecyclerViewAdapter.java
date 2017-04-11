package com.rambostudio.zojoz.realmdatabase.adapter;

import android.app.Dialog;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.inthecheesefactory.thecheeselibrary.manager.Contextor;
import com.rambostudio.zojoz.realmdatabase.R;
import com.rambostudio.zojoz.realmdatabase.manager.HomeManager;
import com.rambostudio.zojoz.realmdatabase.manager.PersonManager;
import com.rambostudio.zojoz.realmdatabase.model.Home;
import com.rambostudio.zojoz.realmdatabase.model.Person;
import com.rambostudio.zojoz.realmdatabase.util.CollectionIndexUtil;
import com.rambostudio.zojoz.realmdatabase.util.ToastUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import io.realm.RealmResults;

/**
 * Created by rambo on 9/4/2560.
 */

public class PersonManagementRecyclerViewAdapter extends RecyclerView.Adapter<PersonManagementRecyclerViewAdapter.PersonManagementViewHolder> {

    private List<Person> mList = new ArrayList<>();
    private final static int HEADER_VIEW = 0;
    private final static int CONTENT_VIEW = 1;
    int lastViewType = -1;

    public PersonManagementRecyclerViewAdapter(List<Person> list) {
//        add(new Person());
        this.mList.addAll(list);
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? HEADER_VIEW : CONTENT_VIEW;
    }

    public void add(Person Person) {
        mList.add(Person);
//        notifyItemInserted(mList.size() - 1);
    }


    @Override
    public PersonManagementViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        lastViewType = viewType;
        switch (viewType) {
            case 0:

//                layoutRes = R.layout.view_person_management_header_item;
                return new PersonManagementViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_person_management_header_item, parent, false));
            case 1:
//                layoutRes = R.layout.view_person_management_item;
                return new PersonManagementViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_person_management_item, parent, false));
            default:
                return null;
        }

    }

    @Override
    public void onBindViewHolder(PersonManagementViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case HEADER_VIEW:
                break;
            case CONTENT_VIEW:
                if (CollectionIndexUtil.isAvailableData(mList, position)) {
                    holder.bindData(mList.get(position));
                }
                break;
        }

    }

    @Override
    public int getItemCount() {
        return CollectionIndexUtil.getSize(mList);
    }

    public class PersonManagementViewHolder extends RecyclerView.ViewHolder {
        TextView tvPersonName;
        AppCompatButton btnAddPerson;
        CardView cvPersonManagementItem;

        public PersonManagementViewHolder(View itemView) {
            super(itemView);
            initInstances();
        }

        private void initInstances() {
            if (lastViewType == HEADER_VIEW) {
                btnAddPerson = (AppCompatButton) itemView.findViewById(R.id.btnAddPerson);
                btnAddPerson.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        insertPerson();
                    }
                });
            } else {
                tvPersonName = (TextView) itemView.findViewById(R.id.tvPersonName);
                cvPersonManagementItem = (CardView) itemView.findViewById(R.id.cvPersonManagementItem);
                cvPersonManagementItem.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        final Dialog dialog = new Dialog(itemView.getContext());
                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialog.setContentView(R.layout.custom_dialog_update_person_management);
                        dialog.setCancelable(true);
                        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                        lp.copyFrom(dialog.getWindow().getAttributes());
                        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                        dialog.show();
                        dialog.getWindow().setAttributes(lp);
                        final EditText edtTitle = (EditText) dialog.findViewById(R.id.edtPersonTitle);
                        edtTitle.setText(mList.get(getAdapterPosition()).getName());
                        AppCompatButton btnSave = (AppCompatButton) dialog.findViewById(R.id.btnSaveUpdatePersonManagement);
                        btnSave.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                String name = edtTitle.getText().toString();
                                if (name != "") {
                                    updatePerson(mList.get(getAdapterPosition()),name);
                                    dialog.dismiss();
                                }
                            }
                        });
                        AppCompatButton btnCancel = (AppCompatButton) dialog.findViewById(R.id.btnCloseUpdatePersonManagement);
                        btnCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                dialog.dismiss();
                            }
                        });
                        return true;
                    }
                });
            }
        }

        private void insertPerson() {
            int currentSize = CollectionIndexUtil.getSize(PersonManager.getInstance().getAllPerson());
            Person person = new Person();
            person.setName("person " + currentSize);
            person.setCreateAt(new Date());
            person.setId(UUID.randomUUID().toString());

            if (PersonManager.getInstance().addPerson(person)) {
                mList.add(person);
                notifyItemInserted(mList.size());
            } else {
                Toast.makeText(Contextor.getInstance().getContext(), "Insert Error", Toast.LENGTH_SHORT).show();
            }
        }

        private void updatePerson(Person person, String title) {

            if (PersonManager.getInstance().update(person,title)) {
                notifyDataSetChanged();
            } else {
                ToastUtil.Toast("Update Home Error", 0);
            }
        }

        private void bindData(Person person) {
            tvPersonName.setText(person.getName());
        }
    }


}
