package com.weatherapp.views.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.weatherapp.R;
import com.weatherapp.globalApplicationData.GlobalAppData;
import com.weatherapp.interfaces.WeatherDataServiceInterface;
import com.weatherapp.interfaces.WeatherForcastShowMessageInterface;
import com.weatherapp.model.WeatherData;
import com.weatherapp.utils.GPSTracker;
import com.weatherapp.views.fragments.WeatherForecastCurrentDisplayFragment;
import com.weatherapp.views.fragments.WeatherForecastDailyDisplayFragment;
import com.weatherapp.views.fragments.WeatherForecastHourlyDisplayFragment;
import com.weatherapp.views.fragments.WeatherForecastMinutelyDisplayFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * This class will be called as launched activity. It will fetch data from server based on GPS location
 * ,parse data and set the data in different views
 */
public class WeatherForecastInitialView extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,WeatherForcastShowMessageInterface {
    private double longitude;
    private double latitude;
    private WeatherData weatherDetailsData;
    private TextView tv_timezone;
    private TextView tv_latitude;
    private TextView tv_longitude;
    private Toolbar toolbar;
    private Fragment fr = null;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getDeviceLocation();
        setContentView(R.layout.activity_weather_forecast_main_view);
        getViews();
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void setViewFragment(){
        if (id == R.id.current_forecast) {
            fr = new WeatherForecastCurrentDisplayFragment();
        } else if (id == R.id.minute_by_minute_forecast) {
            fr = new WeatherForecastMinutelyDisplayFragment();
        } else if (id == R.id.hour_by_hour_forecast) {
            fr = new WeatherForecastHourlyDisplayFragment();
        } else if (id == R.id.daily_forecast) {
            fr = new WeatherForecastDailyDisplayFragment();
        }
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.frag_current_weather_data, fr);
        transaction.commit();
    }
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        id = item.getItemId();
        if (id == R.id.current_forecast) {
            setViewFragment();
        } else if (id == R.id.minute_by_minute_forecast) {
            setViewFragment();
        } else if (id == R.id.hour_by_hour_forecast) {
            setViewFragment();
        } else if (id == R.id.daily_forecast) {
            setViewFragment();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void getViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tv_timezone = (TextView) findViewById(R.id.tv_timezone);
        tv_latitude = (TextView) findViewById(R.id.lbl_latitude);
        tv_longitude = (TextView) findViewById(R.id.lbl_longitude);
    }

    private void setDataInView() {
        tv_timezone.setText(weatherDetailsData.getTimezone() + "");
        tv_latitude.setText(tv_latitude.getText().toString() + weatherDetailsData.getLatitude() + "");
        tv_longitude.setText(tv_longitude.getText().toString() + weatherDetailsData.getLongitude() + "");
    }

    /**
     * Method is used to retrieve data from server and parse data by calling parseServerData(weather data)
     * weatherData list having all the data from server
     */
    public void getDataFromServer(double lat, double lng) {

        WeatherDataServiceInterface.Factory.getInstance().getWeatherData(lat, lng).enqueue(new Callback<WeatherData>() {
            @Override
            public void onResponse(Call<WeatherData> call, Response<WeatherData> response) {
                Log.i("URL", call.request().url().toString());
                weatherDetailsData = response.body();
                setDataInView();
                id = R.id.current_forecast;
                setViewFragment();
                setDataInGlobalClass();
            }

            @Override
            public void onFailure(Call<WeatherData> call, Throwable t) {
            }
        });
    }

    private void getDeviceLocation() {
        GPSTracker gps = new GPSTracker(WeatherForecastInitialView.this);
        // check if GPS enabled
        if (gps.canGetLocation()) {
            latitude = gps.getLatitude();
            longitude = gps.getLongitude();
            getDataFromServer(latitude,longitude);
        } else {
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            onShowSingleButtonAlert(getString(R.string.ENABLE_GPS));
        }
    }

    private void setDataInGlobalClass() {
        GlobalAppData data = (GlobalAppData) getApplicationContext();
        data.setWeatherData(weatherDetailsData);
    }

    @Override
    public void onShowSingleButtonAlert(String message) {
        AlertDialog dialog = new AlertDialog.Builder(this)
                .create();
        dialog.setTitle(getString(R.string.alert));
        dialog.setCancelable(false);
        dialog.setMessage(message);
        dialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                finish();
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}


