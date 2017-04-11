package com.rambostudio.zojoz.realmdatabase.model;

import java.util.Date;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by rambo on 1/4/2560.
 */

public class Home extends RealmObject {

    @PrimaryKey
    private String id;

    private String title;

    private Date createAt;

    private RealmList<Person> personList;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public RealmList<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(RealmList<Person> personList) {
        this.personList = personList;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }
}
