package com.plexonic.analyticsapi.dao;

import java.util.List;
import java.util.Map;


public interface RequestDao {

    /**
     * Gets Daily Active Users
     *
     * @param dateStr
     */
    List<String> getDailyActiveUsers(String dateStr);


    /**
     * Gets Retention for the given period
     *
     * @param retentionType
     * @param dateStr
     * @return
     */
    List<Map<String, Object>> getRetentionForGivenPeriod(int retentionType, String dateStr);
}
