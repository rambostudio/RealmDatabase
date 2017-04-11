package com.rambostudio.zojoz.realmdatabase.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.inthecheesefactory.thecheeselibrary.manager.Contextor;
import com.rambostudio.zojoz.realmdatabase.R;
import com.rambostudio.zojoz.realmdatabase.adapter.HomeManagementRecyclerViewAdapter;
import com.rambostudio.zojoz.realmdatabase.adapter.PersonInHomeManagementRecyclerViewAdapter;
import com.rambostudio.zojoz.realmdatabase.manager.HomeManager;
import com.rambostudio.zojoz.realmdatabase.model.Home;
import com.rambostudio.zojoz.realmdatabase.util.CollectionIndexUtil;
import com.rambostudio.zojoz.realmdatabase.util.ToastUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


/**
 * Created by nuuneoi on 11/16/2014.
 */
@SuppressWarnings("unused")
public class PersonInHomeManagementFragment extends Fragment {

    List<Home> list;
    RecyclerView recyclerView;
    PersonInHomeManagementRecyclerViewAdapter recyclerViewAdapter;
    AppCompatButton btnAddHome;

    public PersonInHomeManagementFragment() {
        super();
    }

    @SuppressWarnings("unused")
    public static PersonInHomeManagementFragment newInstance() {
        PersonInHomeManagementFragment fragment = new PersonInHomeManagementFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(savedInstanceState);

        if (savedInstanceState != null)
            onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_person_in_home_management, container, false);
        initInstances(rootView, savedInstanceState);
        list = new ArrayList<>();
        list.addAll(getDataFromDatabase());
        if (list.isEmpty()) {
            initHome();
        }
        initRecyclerView();
        return rootView;
    }

    private void initHome() {
        Toast.makeText(Contextor.getInstance().getContext(), "in initHome ", Toast.LENGTH_SHORT).show();
        for (int i = 0; i < 5; i++) {
            Home obj = new Home();
            obj.setId(UUID.randomUUID().toString());
            obj.setTitle("Home " + i);
            obj.setCreateAt(new Date());
            HomeManager.getInstance().insert(obj);
        }
    }

    private List<Home> getDataFromDatabase() {
        return HomeManager.getInstance().getAll();
    }


    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()) {
//            @Override
//            public boolean canScrollVertically() {
//                return false;
//            }
        });

//
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        recyclerViewAdapter = new PersonInHomeManagementRecyclerViewAdapter(list,getContext());
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    private void init(Bundle savedInstanceState) {
        // Init Fragment level's variable(s) here
    }

    @SuppressWarnings("UnusedParameters")
    private void initInstances(View rootView, Bundle savedInstanceState) {
        // Init 'View' instance(s) with rootView.findViewById here
        recyclerView = (RecyclerView) rootView.findViewById(R.id.rvPersonInHomeManagement);

    }

    private void generateData() {
        int currentSize = CollectionIndexUtil.getSize(HomeManager.getInstance().getAll());
        Home obj = new Home();
        obj.setTitle("Home " + currentSize);
        obj.setCreateAt(new Date());
        obj.setId(UUID.randomUUID().toString());
        HomeManager.getInstance().insert(obj);
        recyclerViewAdapter.notifyItemInserted(currentSize);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    /*
     * Save Instance State Here
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save Instance State here
    }

    /*
     * Restore Instance State Here
     */
    @SuppressWarnings("UnusedParameters")
    private void onRestoreInstanceState(Bundle savedInstanceState) {
        // Restore Instance State here
    }

    @Override
    public void onResume() {
        super.onResume();
        ToastUtil.Toast("onResume", 1);
    }
    public void refreshView() {
        recyclerViewAdapter.notifyDataSetChanged();
    }


}
