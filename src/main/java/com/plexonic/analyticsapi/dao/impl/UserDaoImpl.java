package com.plexonic.analyticsapi.dao.impl;

import com.plexonic.analyticsapi.dao.UserDao;
import com.plexonic.analyticsapi.dao.rowmapper.UserMapper;
import com.plexonic.analyticsapi.exception.dao.DAOException;
import com.plexonic.analyticsapi.model.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class UserDaoImpl implements UserDao {

    private static final Logger LOGGER = Logger.getLogger(UserDaoImpl.class);
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * @see UserDao#getUser(String)
     * @param id
     * @return
     */
    @Override
    public User getUser(String id) {
        try {
            String query = "SELECT * FROM users WHERE TRIM(user_id) = ?";
            return this.jdbcTemplate.queryForObject(query, new Object[]{id}, new UserMapper());
        } catch (EmptyResultDataAccessException e) {
            LOGGER.warn("No result was found: %s");
            return null;
        } catch (DataAccessException e) {
            String error = "Failed to get daily active users: %s";
            LOGGER.error(String.format(error, e.getMessage()));
            throw new DAOException(error, e);
        }
    }
}
