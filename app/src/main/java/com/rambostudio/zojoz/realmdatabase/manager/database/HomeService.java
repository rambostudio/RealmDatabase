package com.rambostudio.zojoz.realmdatabase.manager.database;

import com.rambostudio.zojoz.realmdatabase.model.Home;

import java.util.List;

/**
 * Created by rambo on 4/4/2560.
 */

public interface HomeService {

    List<Home> getAll();

    Home getById(String id);

    boolean insert(Home home);

    boolean delete();

    boolean update(Home home,String title);
}
