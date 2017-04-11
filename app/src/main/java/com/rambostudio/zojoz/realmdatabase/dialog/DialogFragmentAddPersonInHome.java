package com.rambostudio.zojoz.realmdatabase.dialog;

import android.app.DialogFragment;
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

import com.inthecheesefactory.thecheeselibrary.manager.Contextor;
import com.rambostudio.zojoz.realmdatabase.R;
import com.rambostudio.zojoz.realmdatabase.adapter.AddPersonInHomeDialogRecyclerViewAdapter;
import com.rambostudio.zojoz.realmdatabase.manager.PersonManager;
import com.rambostudio.zojoz.realmdatabase.model.Person;
import com.rambostudio.zojoz.realmdatabase.viewmodel.AddPersonInHomeViewModel;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by nuuneoi on 11/16/2014.
 */
@SuppressWarnings("unused")
public class DialogFragmentAddPersonInHome extends DialogFragment{

    List<AddPersonInHomeViewModel> mList;
    RecyclerView recyclerView;
    AddPersonInHomeDialogRecyclerViewAdapter adapter;
    public DialogFragmentAddPersonInHome() {
        super();
    }

    @SuppressWarnings("unused")
    public static DialogFragmentAddPersonInHome newInstance() {
        DialogFragmentAddPersonInHome fragment = new DialogFragmentAddPersonInHome();
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
        View rootView = inflater.inflate(R.layout.custom_dialog_add_person_in_home_management, container, false);
        initInstances(rootView, savedInstanceState);
        mList = new ArrayList<>();
            mList = PersonManager.getInstance().getAddPersonInHome();
        initRecyclerView();
        return rootView;
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        adapter = new AddPersonInHomeDialogRecyclerViewAdapter(mList);
        recyclerView.setAdapter(adapter);
    }

    private void init(Bundle savedInstanceState) {
        // Init Fragment level's variable(s) here
    }

    @SuppressWarnings("UnusedParameters")
    private void initInstances(View rootView, Bundle savedInstanceState) {
        // Init 'View' instance(s) with rootView.findViewById here
        recyclerView = (RecyclerView) rootView.findViewById(R.id.rvDialogAddPersonInHome);
        AppCompatButton btnSave = (AppCompatButton) rootView.findViewById(R.id.btnSaveAddPersonInHomeManagement);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
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

}
