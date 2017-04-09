package com.rambostudio.zojoz.realmdatabase;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import com.inthecheesefactory.thecheeselibrary.manager.Contextor;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by rambo on 1/4/2560.
 */

public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Contextor.getInstance().init(getApplicationContext());

        initRealm();
    }

    private void initRealm() {
        Realm.init(getApplicationContext());
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name("android.realm")
                .schemaVersion(0)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm realm = Realm.getInstance(realmConfiguration);
        Toast.makeText(getApplicationContext(), "initRealm", Toast.LENGTH_LONG).show();

    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
