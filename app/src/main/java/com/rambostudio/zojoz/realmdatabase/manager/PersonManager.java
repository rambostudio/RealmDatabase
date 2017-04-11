package com.rambostudio.zojoz.realmdatabase.manager;

import android.content.Context;

import com.inthecheesefactory.thecheeselibrary.manager.Contextor;
import com.rambostudio.zojoz.realmdatabase.model.Person;
import com.rambostudio.zojoz.realmdatabase.viewmodel.AddPersonInHomeViewModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by nuuneoi on 11/16/2014.
 */
public class PersonManager {

    Realm realm;
    RealmResults<Person> mList;
    private static PersonManager instance;

    public static PersonManager getInstance() {
        if (instance == null)
            instance = new PersonManager();
        return instance;
    }

    private Context mContext;

    private PersonManager() {
        mContext = Contextor.getInstance().getContext();
        realm = Realm.getDefaultInstance();
    }

    public RealmResults<Person> getAllPerson() {
        mList = realm.where(Person.class)
                .findAll();
        return mList;
    }

    public boolean clearPerson() {
        try {
            realm.beginTransaction();
            realm.delete(Person.class);
            realm.commitTransaction();
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    public boolean addPerson(Person person) {
        realm.beginTransaction();
        try {
            realm.copyToRealm(person);
            realm.commitTransaction();
        } catch (Exception ex) {
            return false;
        }
        return true;
    }
    public boolean update(Person person,String name) {
        realm.beginTransaction();
        try {
            person.setName(name);
            realm.copyToRealm(person);
            realm.commitTransaction();
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    public List<AddPersonInHomeViewModel> getAddPersonInHome()
    {
        mList = realm.where(Person.class).findAll();
        List<AddPersonInHomeViewModel> resultList = new ArrayList<>();
        for (int i = 0; i < mList.size(); i++) {
            AddPersonInHomeViewModel obj = new AddPersonInHomeViewModel();
            obj.setName(mList.get(i).getName());
            resultList.add(obj);
        }
        return resultList;
    }

}
