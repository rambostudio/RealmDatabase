package com.rambostudio.zojoz.realmdatabase.adapter;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.inthecheesefactory.thecheeselibrary.manager.Contextor;
import com.rambostudio.zojoz.realmdatabase.R;
import com.rambostudio.zojoz.realmdatabase.dialog.DialogFragmentAddPersonInHome;
import com.rambostudio.zojoz.realmdatabase.manager.HomeManager;
import com.rambostudio.zojoz.realmdatabase.model.Home;
import com.rambostudio.zojoz.realmdatabase.util.CollectionIndexUtil;
import com.rambostudio.zojoz.realmdatabase.util.ToastUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by rambo on 9/4/2560.
 */

public class PersonInHomeManagementRecyclerViewAdapter extends RecyclerView.Adapter<PersonInHomeManagementRecyclerViewAdapter.PersonInHomeManagementViewHolder> {

    private List<Home> mList = new ArrayList<>();
    private final static int HEADER_VIEW = 0;
    private final static int CONTENT_VIEW = 1;
    int lastViewType = -1;
    private Context mContext;

    public PersonInHomeManagementRecyclerViewAdapter(List<Home> list,Context context) {
        this.mList.addAll(list);
        this.mContext = context;

    }

    @Override
    public PersonInHomeManagementViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PersonInHomeManagementViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_person_in_home_management_item, parent, false));
    }

    @Override
    public void onBindViewHolder(PersonInHomeManagementViewHolder holder, int position) {
        if (CollectionIndexUtil.isAvailableData(mList, position)) {
             holder.bindData(mList.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return CollectionIndexUtil.getSize(mList);
    }

    public class PersonInHomeManagementViewHolder extends RecyclerView.ViewHolder {
        TextView tvHomeTitle;
        AppCompatButton btnAddPersonInInHome;

        public PersonInHomeManagementViewHolder(View itemView) {
            super(itemView);
            initInstances();
        }

        private void initInstances() {

            tvHomeTitle = (TextView) itemView.findViewById(R.id.tvHomeTitle);
            btnAddPersonInInHome = (AppCompatButton) itemView.findViewById(R.id.btnAddPersonInInHome);
            btnAddPersonInInHome.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {

//                    final Dialog dialog = new Dialog(itemView.getContext());
//                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//                    dialog.setContentView(R.layout.custom_dialog_add_person_in_home_management);
//                    dialog.setCancelable(true);
//                    WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
//                    lp.copyFrom(dialog.getWindow().getAttributes());
//                    lp.width = WindowManager.LayoutParams.MATCH_PARENT;
//                    lp.height = WindowManager.LayoutParams.MATCH_PARENT;
//
//                    dialog.getWindow().setAttributes(lp);
//                    RecyclerView recyclerView = (RecyclerView) dialog.findViewById(R.id.rvdialogAddPersonInHome);
//                    recyclerView.setHasFixedSize(true);
//                    recyclerView.setLayoutManager(new LinearLayoutManager(dialog.getContext()));
//                    recyclerView.addItemDecoration(new DividerItemDecoration(dialog.getContext(), DividerItemDecoration.VERTICAL));
//                    AddPersonInHomeDialogRecyclerViewAdapter adapter = new AddPersonInHomeDialogRecyclerViewAdapter(PersonManager.getInstance().getAllPerson());
//                    recyclerView.setAdapter(adapter);
//                    dialog.show();
//

                    showDialog();

                    return true;
                }


            });
        }

        private void showDialog() {

            FragmentManager manager = ((Activity)mContext).getFragmentManager();
            DialogFragmentAddPersonInHome dialog = new DialogFragmentAddPersonInHome();
            dialog.show(manager,"DialogFragmentAddPersonInHome");

        }
        private void updateHome(Home home, String title) {

            if (HomeManager.getInstance().update(home, title)) {
                notifyDataSetChanged();
            } else {
                ToastUtil.Toast("Update Home Error", 0);
            }
        }

        private void insertHome() {
            int currentSize = CollectionIndexUtil.getSize(HomeManager.getInstance().getAll());
            Home obj = new Home();
            obj.setTitle("Home " + currentSize);
            obj.setCreateAt(new Date());
            obj.setId(UUID.randomUUID().toString());
            if (HomeManager.getInstance().insert(obj)) {
                Toast.makeText(Contextor.getInstance().getContext(), "Insert success", Toast.LENGTH_SHORT).show();
                mList.add(obj);
                notifyItemInserted(mList.size());
            } else {
                Toast.makeText(Contextor.getInstance().getContext(), "Insert Error", Toast.LENGTH_SHORT).show();
            }
        }

        private void bindData(Home home) {
            tvHomeTitle.setText(home.getTitle());
        }
    }
}




