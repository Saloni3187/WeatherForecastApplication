package com.weatherapp.interfaces;

import com.weatherapp.constants.URLConstants;
import com.weatherapp.model.WeatherData;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**This class calls retrofit library to get data from given URL
 * Created by Saloni on 21/10/16.
 */
public interface WeatherDataServiceInterface {
    @GET(URLConstants.API_KEY+"{lat},{lng}")
    Call<WeatherData> getWeatherData(@Path(value = "lat" ,encoded = true) double lat , @Path(value = "lng" ,encoded = true) double lng);

    //inner class used to create singleton instance
    class Factory{
        private static WeatherDataServiceInterface service;
        public static WeatherDataServiceInterface getInstance(){
            if (service == null) {
                Retrofit retrofit = new Retrofit.Builder().baseUrl(URLConstants.abc).
                                        addConverterFactory(GsonConverterFactory.create()).build();

                service = retrofit.create(WeatherDataServiceInterface.class);

                return service;
            }else
                return service;
            }
        }
    }
