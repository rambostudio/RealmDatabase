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
import com.rambostudio.zojoz.realmdatabase.adapter.PersonManagementRecyclerViewAdapter;
import com.rambostudio.zojoz.realmdatabase.manager.PersonManager;
import com.rambostudio.zojoz.realmdatabase.model.Person;
import com.rambostudio.zojoz.realmdatabase.util.CollectionIndexUtil;

import java.util.Date;
import java.util.List;
import java.util.UUID;


/**
 * Created by nuuneoi on 11/16/2014.
 */
@SuppressWarnings("unused")
public class PersonManagementFragment extends Fragment {

    List<Person> list;
    RecyclerView recyclerView;
    PersonManagementRecyclerViewAdapter recyclerViewAdapter;
    AppCompatButton btnAddPerson;
    public PersonManagementFragment() {
        super();
    }

    @SuppressWarnings("unused")
    public static PersonManagementFragment newInstance() {
        PersonManagementFragment fragment = new PersonManagementFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_person_management, container, false);
        initInstances(rootView, savedInstanceState);
        initPerson();
        list =  getDataFromDatabase();
        initRecyclerView();
        return rootView;
    }

    private void initPerson() {
        PersonManager.getInstance().clearPerson();
        if (PersonManager.getInstance().clearPerson()) {
            for (int i = 0; i < 5; i++) {
                Person person = new Person();
                person.setId(UUID.randomUUID().toString());
                person.setName("Person " + i);
                person.setCreateAt(new Date());
                PersonManager.getInstance().addPerson(person);
            }
        } else {
            Toast.makeText(Contextor.getInstance().getContext(), "clearPerson Error", Toast.LENGTH_LONG).show();
        }



    }

    private List<Person> getDataFromDatabase() {
        return PersonManager.getInstance().getAllPerson();
    }


    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });


        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        recyclerViewAdapter = new PersonManagementRecyclerViewAdapter(list);
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    private void init(Bundle savedInstanceState) {
        // Init Fragment level's variable(s) here
    }

    @SuppressWarnings("UnusedParameters")
    private void initInstances(View rootView, Bundle savedInstanceState) {
        // Init 'View' instance(s) with rootView.findViewById here
        recyclerView = (RecyclerView) rootView.findViewById(R.id.rvPersonManagement);
        btnAddPerson = (AppCompatButton) rootView.findViewById(R.id.btnAddPerson);
        btnAddPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                generatePerson();


            }
        });


    }

    private void generatePerson() {
        int currentSize = CollectionIndexUtil.getSize(PersonManager.getInstance().getAllPerson());
        Person person = new Person();
        person.setName("person "+currentSize);
        person.setCreateAt(new Date());
        person.setId(UUID.randomUUID().toString());
        PersonManager.getInstance().addPerson(person);
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

}
