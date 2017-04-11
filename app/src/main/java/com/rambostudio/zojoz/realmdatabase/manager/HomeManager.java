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
        realm = Realm.getDefaultInstance();
    }

    public void close() {
        realm.close();
    }

    @Override
    public List<Home> getAll() {
        return realm.where(Home.class).findAll();
    }

    @Override
    public Home getById(String id) {
        return null;
    }

    @Override
    public boolean insert(Home home) {
        realm.beginTransaction();
        try {
            Home object = realm.copyToRealm(home);
            realm.commitTransaction();
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    @Override
    public boolean delete() {
        return false;
    }

    @Override
    public boolean update(Home home,String title) {
        realm.beginTransaction();
        try {
            home.setTitle(title);
            realm.copyToRealmOrUpdate(home);
            realm.commitTransaction();
        } catch (Exception ex) {
            return false;
        }
        return true;
    }
}
