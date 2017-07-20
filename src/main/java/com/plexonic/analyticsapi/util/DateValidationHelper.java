package com.plexonic.analyticsapi.util;


public class DateValidationHelper {

    /**
     * Checkes input date format matches "dd/mm/yyyy"
     *
     * @param dateStr
     * @return
     */
    public static boolean isValidDateFormat(String dateStr) {
        return dateStr.matches("([0-9]{2})/([0-9]{2})/([0-9]{4})");
    }
}
