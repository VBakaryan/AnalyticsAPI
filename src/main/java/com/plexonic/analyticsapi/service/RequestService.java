package com.plexonic.analyticsapi.service;

import java.util.List;
import java.util.Map;


public interface RequestService {

    /**
     * Gets Daily Active User List
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
    List<Map<String, Object>> getRetentionForGivenPeriod(String retentionType, String dateStr);
}
