package com.rambostudio.zojoz.realmdatabase.manager.database;

import com.rambostudio.zojoz.realmdatabase.model.Home;

import java.util.List;

/**
 * Created by rambo on 4/4/2560.
 */

public interface HomeService {

    List<Home> GetAll();

    Home getById(String id);

    void insert(Home home);

    void delete();

    void update(Home home);
}
