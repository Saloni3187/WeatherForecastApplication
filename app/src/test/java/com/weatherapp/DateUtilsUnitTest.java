package com.weatherapp;

import android.content.Context;

import com.weatherapp.utils.DateUtils;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

/**
 * Created by Saloni
 */
public class DateUtilsUnitTest
{
    private DateUtils dateUtils;

    @Mock
    private Context context;

    @Before
    public void setUp() throws Exception {
        dateUtils = new DateUtils();
    }

    @Test
    public void testTimeStamp() throws Exception {
        dateUtils.getTimeStamp("1479425242");
    }
}