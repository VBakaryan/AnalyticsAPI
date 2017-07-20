package com.plexonic.analyticsapi.util;

import com.plexonic.analyticsapi.model.RetentionType;


public class RetentionTypeValidationHelper {

    /**
     * Checks retention type validation
     *
     * @param retentionType
     * @return
     */
    public static boolean isValidRetType(String retentionType) {
        boolean result = false;

        for (RetentionType element : RetentionType.values()) {
            if (element.getType().equals(retentionType)) {
                result = true;
                break;
            }
        }
        return result;
    }
}
