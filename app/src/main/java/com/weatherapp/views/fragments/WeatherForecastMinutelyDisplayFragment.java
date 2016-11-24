package com.weatherapp.views.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.weatherapp.R;
import com.weatherapp.adapters.WeatherForecastMinutelyDataApdapter;
import com.weatherapp.globalApplicationData.GlobalAppData;
import com.weatherapp.model.DataMinuely;
import com.weatherapp.model.WeatherData;
import com.weatherapp.utils.TextToImageUtil;
import com.weatherapp.views.activities.WeatherForecastInitialView;

import java.util.ArrayList;

/**
 * Created by Saloni on 23/11/16.
 */

public class WeatherForecastMinutelyDisplayFragment extends android.support.v4.app.Fragment {
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private WeatherForecastMinutelyDataApdapter mAdapter;
    private WeatherData weatherData;
    private View view;
    private TextView tv_summary;
    private TextView tv_no_data;
    private RelativeLayout common_outer_layout;
    private ImageView img_weather_type;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.weather_forecast_minutely_display, container, false);
        getDataFromGlobalClass();
        getViews();
        setDataInViews();
        return view;
    }

    private void getViews(){
        mRecyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        tv_summary= (TextView)view.findViewById(R.id.tv_summary);
        tv_no_data=(TextView)view.findViewById(R.id.tv_no_data_available);
        common_outer_layout = (RelativeLayout)view.findViewById(R.id.heading_layout);
        img_weather_type= (ImageView)view.findViewById(R.id.img_weather_type_full);
    }

    private void getDataFromGlobalClass() {
        GlobalAppData data = (GlobalAppData) ((WeatherForecastInitialView) getActivity()).getApplicationContext();
        weatherData = data.getWeatherData();
    }

    private void setDataInViews(){
        if(weatherData.getMinutely()!=null) {

            int resourceId = TextToImageUtil.getImageDrawable(weatherData.getMinutely().getSummary().toLowerCase());
            img_weather_type.setBackgroundResource(resourceId);

            ArrayList<DataMinuely> data = weatherData.getMinutely().getData();
            tv_summary.setText(weatherData.getMinutely().getSummary());
            mAdapter = new WeatherForecastMinutelyDataApdapter(data);
            mLayoutManager = new LinearLayoutManager(getActivity());
            mRecyclerView.setVisibility(View.VISIBLE);
            common_outer_layout.setVisibility(View.VISIBLE);
            mRecyclerView.setLayoutManager(mLayoutManager);
            mRecyclerView.setAdapter(mAdapter);
        }else{
            mRecyclerView.setVisibility(View.GONE);
            common_outer_layout.setVisibility(View.GONE);
            tv_no_data.setVisibility(View.VISIBLE);
        }
    }
}
