package com.weatherapp.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Saloni on 22/11/16.
 */

public class DateUtils {
    /**
     * Method will take string input and convert the input to timestamp
     * @param dateTime
     * @return
     */
    public String getTimeStamp(String dateTime) {
        DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        Date date = new Date(Long.parseLong(dateTime)*1000L);

        return sdf.format(date);
    }
}
