package com.plexonic.analyticsapi.dao;

import com.plexonic.analyticsapi.model.User;


public interface UserDao {

    /**
     * Gets user from database
     * @param id
     * @return
     */
    User getUser(String id);
}
