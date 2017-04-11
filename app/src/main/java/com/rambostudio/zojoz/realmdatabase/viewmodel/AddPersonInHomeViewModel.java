package com.rambostudio.zojoz.realmdatabase.viewmodel;

import com.rambostudio.zojoz.realmdatabase.model.Person;

/**
 * Created by rambo on 11/4/2560.
 */

public class AddPersonInHomeViewModel {
    public String name;

    public boolean isChecked;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
