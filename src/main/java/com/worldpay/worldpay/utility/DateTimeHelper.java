package com.worldpay.worldpay.utility;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeHelper {
    private static final String dateFormat = "yyyy-MM-dd HH:mm:ss";
    private static final SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);

    public static String covertToDateString(Date date) {
        return sdf.format(date);
    }
}
