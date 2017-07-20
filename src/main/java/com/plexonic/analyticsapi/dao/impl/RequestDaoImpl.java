package com.plexonic.analyticsapi.dao.impl;

import com.plexonic.analyticsapi.dao.RequestDao;
import com.plexonic.analyticsapi.exception.dao.DAOException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;


@Component
public class RequestDaoImpl implements RequestDao {

    private static final Logger LOGGER = Logger.getLogger(RequestDaoImpl.class);
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public RequestDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    /**
     * @param dateStr
     * @return
     * @see RequestDao#getDailyActiveUsers(String)
     */
    @Override
    public List<String> getDailyActiveUsers(String dateStr) {

        List<String> userList;
        try {
            String query = "SELECT DISTINCT r.user_id FROM requests r WHERE 1=1" +
                    " AND r.request_date BETWEEN str_to_date(?,'%d/%m/%Y')" +
                    " AND str_to_date(CONCAT(?,' 23:59:59'),'%d/%m/%Y %H:%i:%s')";
            userList = this.jdbcTemplate.queryForList(query, new Object[]{dateStr, dateStr}, String.class);

        } catch (EmptyResultDataAccessException e) {
            LOGGER.warn("No result was found: %s");
            return null;
        } catch (DataAccessException e) {
            String error = "Failed to get daily active users: %s";
            LOGGER.error(String.format(error, e.getMessage()));
            throw new DAOException(error, e);
        }
        return userList;
    }


    /**
     * @param retentionType
     * @param dateStr
     * @return
     * @see RequestDao#getRetentionForGivenPeriod(int, String)
     */
    public List<Map<String, Object>> getRetentionForGivenPeriod(int retentionType, String dateStr) {

        List<Map<String, Object>> userMap;
        try {
            String query = "SELECT r.user_id,count(r.request_date) AS retention FROM requests r WHERE 1=1" +
                    " AND r.request_date BETWEEN str_to_date(?,'%Y-%m-%d')" +
                    " AND DATE_ADD(str_to_date(?,'%Y-%m-%d'),INTERVAL ? DAY)" +
                    " GROUP BY r.user_id;";
            userMap = this.jdbcTemplate.queryForList(query, dateStr, dateStr, retentionType);

        } catch (EmptyResultDataAccessException e) {
            LOGGER.warn("No result was found: %s");
            return null;
        } catch (DataAccessException e) {
            String error = "Failed to get daily active users: %s";
            LOGGER.error(String.format(error, e.getMessage()));
            throw new DAOException(error, e);
        }
        return userMap;
    }
}
