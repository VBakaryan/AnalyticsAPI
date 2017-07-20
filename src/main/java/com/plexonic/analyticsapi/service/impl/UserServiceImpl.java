package com.plexonic.analyticsapi.service.impl;

import com.plexonic.analyticsapi.dao.UserDao;
import com.plexonic.analyticsapi.exception.dao.DAOException;
import com.plexonic.analyticsapi.exception.service.InvalidEntryException;
import com.plexonic.analyticsapi.exception.service.ServiceException;
import com.plexonic.analyticsapi.model.User;
import com.plexonic.analyticsapi.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class);

    private UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }


    /**
     * Gets User from database
     *
     * @param id
     * @return
     */
    @Override
    public User getUser(String id) {

        // Check input id validation
        if (id == null || id.isEmpty()) {
            LOGGER.error(String.format("Input id is not valid: %s", id));
            throw new InvalidEntryException("Invalid id");
        }

        // Get User from database
        try {
            return userDao.getUser(id);
        }catch (DAOException e) {
            String error = "Failed to get user: %s";
            LOGGER.error(String.format(error, e.getMessage()));
            throw new ServiceException(String.format(error, e.getMessage()));
        }
    }
}
