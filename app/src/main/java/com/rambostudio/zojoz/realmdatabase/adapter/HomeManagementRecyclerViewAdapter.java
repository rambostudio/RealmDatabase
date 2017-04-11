package com.rambostudio.zojoz.realmdatabase.adapter;

import android.app.Dialog;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.inthecheesefactory.thecheeselibrary.manager.Contextor;
import com.rambostudio.zojoz.realmdatabase.R;
import com.rambostudio.zojoz.realmdatabase.fragment.HomeManagementFragment;
import com.rambostudio.zojoz.realmdatabase.fragment.PersonInHomeManagementFragment;
import com.rambostudio.zojoz.realmdatabase.manager.HomeManager;
import com.rambostudio.zojoz.realmdatabase.manager.PersonManager;
import com.rambostudio.zojoz.realmdatabase.model.Home;
import com.rambostudio.zojoz.realmdatabase.model.Person;
import com.rambostudio.zojoz.realmdatabase.util.CollectionIndexUtil;
import com.rambostudio.zojoz.realmdatabase.util.ToastUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static com.rambostudio.zojoz.realmdatabase.R.id.btnAddPerson;
import static com.rambostudio.zojoz.realmdatabase.R.id.item_touch_helper_previous_elevation;

/**
 * Created by rambo on 9/4/2560.
 */

public class HomeManagementRecyclerViewAdapter extends RecyclerView.Adapter<HomeManagementRecyclerViewAdapter.HomeManagementViewHolder> {

    private List<Home> mList = new ArrayList<>();
    private final static int HEADER_VIEW = 0;
    private final static int CONTENT_VIEW = 1;
    int lastViewType = -1;

    public HomeManagementRecyclerViewAdapter(List<Home> list) {
        this.mList.addAll(list);
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? HEADER_VIEW : CONTENT_VIEW;
    }

    @Override
    public HomeManagementViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        lastViewType = viewType;
        switch (viewType) {
            case HEADER_VIEW:
                return new HomeManagementViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_home_management_header_item, parent, false));
            case CONTENT_VIEW:
                return new HomeManagementViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_home_management_item, parent, false));
            default:
                return null;
        }

    }

    @Override
    public void onBindViewHolder(HomeManagementViewHolder holder, int position) {
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

    public class HomeManagementViewHolder extends RecyclerView.ViewHolder {
        TextView tvHomeTitle;
        AppCompatButton btnAddHome;
        CardView cvHomeManagement;

        public HomeManagementViewHolder(View itemView) {
            super(itemView);
            initInstances();
        }

        private void initInstances() {
            if (lastViewType == HEADER_VIEW) {
                btnAddHome = (AppCompatButton) itemView.findViewById(R.id.btnAddHome);
                btnAddHome.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(Contextor.getInstance().getContext(), "Add Home Clicked", Toast.LENGTH_SHORT).show();
                        insertHome();
                    }
                });
            } else {
                tvHomeTitle = (TextView) itemView.findViewById(R.id.tvHomeTitle);
                cvHomeManagement = (CardView) itemView.findViewById(R.id.cvHomeManagementItem);
                cvHomeManagement.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {

                        final Dialog dialog = new Dialog(itemView.getContext());
                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialog.setContentView(R.layout.custom_dialog_update_home_management);
                        dialog.setCancelable(true);
                        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                        lp.copyFrom(dialog.getWindow().getAttributes());
                        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
                        dialog.show();
                        dialog.getWindow().setAttributes(lp);
                        final EditText edtHomeTitle = (EditText) dialog.findViewById(R.id.edtHomeTitle);
                        edtHomeTitle.setText(mList.get(getAdapterPosition()).getTitle());
                        AppCompatButton btnSave = (AppCompatButton) dialog.findViewById(R.id.btnSaveUpdateHomeManagement);
                        btnSave.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                String homeTitle = edtHomeTitle.getText().toString();
                                if (homeTitle != "" && getAdapterPosition()> -1) {
                                    updateHome(mList.get(getAdapterPosition()),homeTitle);
                                }

                            }
                        });
                        AppCompatButton btnCancel = (AppCompatButton) dialog.findViewById(R.id.btnCloseUpdateHomeManagement);
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

        private void updateHome(Home home,String title) {

            if (HomeManager.getInstance().update(home,title)) {
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
