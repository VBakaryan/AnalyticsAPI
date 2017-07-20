package com.plexonic.analyticsapi.service;

import com.plexonic.analyticsapi.model.User;


public interface UserService {

    /**
     * Gets User from database
     * @param id
     * @return
     */
    User getUser(String id);
}
