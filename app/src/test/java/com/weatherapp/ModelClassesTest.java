package com.weatherapp;

import com.weatherapp.globalApplicationData.GlobalAppData;
import com.weatherapp.model.Currently;
import com.weatherapp.model.Daily;
import com.weatherapp.model.Hourly;
import com.weatherapp.model.Minutely;
import com.weatherapp.model.WeatherData;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertThat;


/**
 * Created by Saloni
 */
public class ModelClassesTest
{

    private WeatherData weatherData;
    private Currently currently;
    private Minutely minutely;
    private Hourly hourly;
    private Daily daily;

    private GlobalAppData globalAppData;

    @Before
    public void setUp(){
        globalAppData = new GlobalAppData();
        weatherData = new WeatherData();
        minutely = new Minutely();
        currently = new Currently();
        hourly = new Hourly();
        daily = new Daily();
    }
    @Test
    public void testGlobalAppData() throws Exception{
        globalAppData.setWeatherData(weatherData);
        assertThat(globalAppData.getWeatherData(),instanceOf(WeatherData.class));
    }

    @Test
    public void TestGetDaily() throws Exception {
        weatherData.setDaily(daily);
        assertThat(weatherData.getDaily(), instanceOf(Daily.class));
    }
    @Test
    public void TestGetMinutely() throws Exception {
        weatherData.setMinutely(minutely);
        assertThat(weatherData.getMinutely(),instanceOf(Minutely.class));
    }
    @Test
    public void TestGetHourly() throws Exception {
        weatherData.setHourly(hourly);
        assertThat(weatherData.getHourly(),instanceOf(Hourly.class));
    }
    @Test
    public void TestGetCurrently() throws Exception {
        weatherData.setCurrently(currently);
        assertThat(weatherData.getCurrently(),instanceOf(Currently.class));
    }

}
