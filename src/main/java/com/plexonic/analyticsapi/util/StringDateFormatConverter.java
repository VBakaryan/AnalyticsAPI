package com.plexonic.analyticsapi.util;

public class StringDateFormatConverter {

    /**
     * Changes String format from "dd/mm/yyyy" to "yyyy-MM-dd"
     *
     * @param str
     * @return
     */
    public static String changeDateFormat(String str) {
        String[] strArgs = str.split("/");
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = strArgs.length - 1; i >= 0; i--) {
            stringBuilder.append(strArgs[i]);
            if (i != 0) {
                stringBuilder.append("-");
            }
        }
        return stringBuilder.toString();
    }
}
