package com.plexonic.analyticsapi.service.impl;

import com.plexonic.analyticsapi.dao.RequestDao;
import com.plexonic.analyticsapi.exception.dao.DAOException;
import com.plexonic.analyticsapi.exception.service.InvalidEntryException;
import com.plexonic.analyticsapi.exception.service.ServiceException;
import com.plexonic.analyticsapi.model.RetentionType;
import com.plexonic.analyticsapi.service.RequestService;
import com.plexonic.analyticsapi.util.DateValidationHelper;
import com.plexonic.analyticsapi.util.RetentionTypeValidationHelper;
import com.plexonic.analyticsapi.util.StringDateFormatConverter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RequestServiceImpl implements RequestService {

    private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class);

    private RequestDao requestDao;

    @Autowired
    public RequestServiceImpl(RequestDao requestDao) {
        this.requestDao = requestDao;
    }

    /**
     * @param dateStr
     * @return
     * @see RequestService#getDailyActiveUsers(String)
     */
    @Override
    public List<String> getDailyActiveUsers(String dateStr) {

        // Check date format validation.
        if (!DateValidationHelper.isValidDateFormat(dateStr)) {
            LOGGER.error(String.format("Input date format is not valid: %s", dateStr));
            throw new InvalidEntryException("Invalid date format");
        }

        // Get daily active user lists.
        try {
            return requestDao.getDailyActiveUsers(dateStr);
        } catch (DAOException e) {
            String error = "Failed to get Daily active users: %s";
            LOGGER.error(String.format(error, e.getMessage()));
            throw new ServiceException(String.format(error, e.getMessage()));
        }
    }

    /**
     * @param retentionType
     * @param dateStr
     * @return
     * @see RequestService#getRetentionForGivenPeriod(String, String)
     */
    public List<Map<String, Object>> getRetentionForGivenPeriod(String retentionType, String dateStr) {

        // Check type and date format validation.
        if (!DateValidationHelper.isValidDateFormat(dateStr) ||
                !RetentionTypeValidationHelper.isValidRetType(retentionType)) {
            LOGGER.error(String.format("Input type or date format is not valid: %s", dateStr));
            throw new InvalidEntryException("Invalid type or date format");
        }

        // Get retention for the given period
        try {
            int retention = RetentionType.valueOf(retentionType.toUpperCase()).getValue();
            String convertedDate = StringDateFormatConverter.changeDateFormat(dateStr);
            return requestDao.getRetentionForGivenPeriod(retention, convertedDate);
        } catch (DAOException e) {
            String error = "Failed to get Retention: %s";
            LOGGER.error(String.format(error, e.getMessage()));
            throw new ServiceException(String.format(error, e.getMessage()));
        }
    }
}
