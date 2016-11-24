package com.weatherapp.globalApplicationData;

import android.app.Application;

import com.weatherapp.model.WeatherData;

/**
 * Created by Saloni on 21/11/16.
 */

public class GlobalAppData extends Application {
    private WeatherData weatherData;

    public WeatherData getWeatherData() {
        return weatherData;
    }

    public void setWeatherData(WeatherData weatherData) {
        this.weatherData = weatherData;
    }
}
