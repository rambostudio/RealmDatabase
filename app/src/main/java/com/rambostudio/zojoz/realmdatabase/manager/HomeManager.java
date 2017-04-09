package com.rambostudio.zojoz.realmdatabase.manager;

import android.content.Context;

import com.inthecheesefactory.thecheeselibrary.manager.Contextor;
import com.rambostudio.zojoz.realmdatabase.manager.database.HomeService;
import com.rambostudio.zojoz.realmdatabase.model.Home;
import com.rambostudio.zojoz.realmdatabase.model.Person;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by nuuneoi on 11/16/2014.
 */
public class HomeManager implements HomeService {
    private Realm realm;

    private static HomeManager instance;

    public static HomeManager getInstance() {
        if (instance == null)
            instance = new HomeManager();
        return instance;
    }

    private Context mContext;

    public HomeManager() {
        mContext = Contextor.getInstance().getContext();
    }

    public void init() {
//        realm = Realm.getDefaultInstance();
    }

    public void close() {
        realm.close();
    }

    @Override
    public List<Home> GetAll() {
        return realm.where(Home.class).findAll();
    }

    @Override
    public Home getById(String id) {
        return null;
    }

    @Override
    public void insert(Home home) {
        realm.beginTransaction();

        Home homeObj = realm.createObject(Home.class);
        homeObj.setTitle(home.getTitle());

        Person personObj = realm.createObject(Person.class);
        personObj.setId(UUID.randomUUID().toString());
        personObj.setName(personObj.getName());

        homeObj.setCreateAt(new Date());
        realm.commitTransaction();
    }

    @Override
    public void delete() {

    }

    @Override
    public void update(Home home) {

    }
}
