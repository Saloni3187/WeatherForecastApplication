package com.weatherapp.views.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.weatherapp.R;
import com.weatherapp.globalApplicationData.GlobalAppData;
import com.weatherapp.model.WeatherData;
import com.weatherapp.utils.DateUtils;
import com.weatherapp.views.activities.WeatherForecastInitialView;

/**
 * Created by Saloni on 22/11/16.
 */

public class WeatherForecastCurrentDisplayFragment extends android.support.v4.app.Fragment {
    private WeatherData weatherData;
    private View view;

    private TextView tv_time;
    private TextView tv_strom_distance;
    private TextView tv_strom_bearing;
    private TextView tv_prep_intensity;
    private TextView tv_prep_prob;
    private TextView tv_temp;
    private TextView tv_app_temp;
    private TextView tv_dew_point;
    private TextView tv_humidity;
    private TextView tv_wind_speed;
    private TextView tv_wind_bearing;
    private TextView tv_visibility;
    private TextView tv_cloud_cover;
    private TextView tv_pressure;
    private TextView tv_ozone;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.weather_forecast_current_data_display, container, false);
        getDataFromGLobalClass();
        getViews();
        setDataInViews();
        return view;
    }

    private void getViews() {
        tv_time =(TextView) view.findViewById(R.id.tv_time);
        tv_strom_distance = (TextView) view.findViewById(R.id.tv_nearestStormDistance);
        tv_strom_bearing = (TextView) view.findViewById(R.id.tv_nearestStormBearing);
        tv_prep_intensity = (TextView) view.findViewById(R.id.tv_prepIntensity);
        tv_prep_prob = (TextView) view.findViewById(R.id.tv_precipProb);
        tv_temp = (TextView) view.findViewById(R.id.tv_temprature);
        tv_app_temp = (TextView) view.findViewById(R.id.tv_appreantTemp);
        tv_dew_point = (TextView) view.findViewById(R.id.tv_dewPoint);
        tv_humidity = (TextView) view.findViewById(R.id.tv_humidity);
        tv_wind_speed = (TextView) view.findViewById(R.id.tv_windSpeed);
        tv_wind_bearing = (TextView) view.findViewById(R.id.tv_windBearing);
        tv_visibility = (TextView) view.findViewById(R.id.tv_visibilty);
        tv_cloud_cover = (TextView) view.findViewById(R.id.tv_cloudCover);
        tv_pressure = (TextView) view.findViewById(R.id.tv_pressure);
        tv_ozone = (TextView) view.findViewById(R.id.tv_ozone);
    }

    private void getDataFromGLobalClass() {
        GlobalAppData data = (GlobalAppData) ((WeatherForecastInitialView) getActivity()).getApplicationContext();
        weatherData = data.getWeatherData();
    }

    private void setDataInViews() {
        DateUtils timeConversions = new DateUtils();
        if(weatherData.getCurrently()!=null) {
            tv_time.setText(timeConversions.getTimeStamp(weatherData.getCurrently().getTime() + ""));
            tv_strom_distance.setText(weatherData.getCurrently().getNearestStormDistance() + "");
            tv_strom_bearing.setText(weatherData.getCurrently().getNearestStormBearing() + "");
            tv_prep_intensity.setText(weatherData.getCurrently().getPrecipIntensity() + "");
            tv_prep_prob.setText(weatherData.getCurrently().getPrecipProbability() + "");
            tv_temp.setText(weatherData.getCurrently().getTemperature() + "");
            tv_app_temp.setText(weatherData.getCurrently().getApparentTemperature() + "");
            tv_dew_point.setText(weatherData.getCurrently().getDewPoint() + "");
            tv_humidity.setText(weatherData.getCurrently().getHumidity() + "");
            tv_wind_speed.setText(weatherData.getCurrently().getWindSpeed() + "");
            tv_wind_bearing.setText(weatherData.getCurrently().getWindBearing() + "");
            tv_visibility.setText(weatherData.getCurrently().getVisibility() + "");
            tv_cloud_cover.setText(weatherData.getCurrently().getCloudCover() + "");
            tv_pressure.setText(weatherData.getCurrently().getPressure() + "");
            tv_ozone.setText(weatherData.getCurrently().getOzone() + "");
        }
    }
}
