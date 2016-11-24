package com.weatherapp.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.weatherapp.R;
import com.weatherapp.model.DatumDaily;
import com.weatherapp.utils.DateUtils;
import com.weatherapp.utils.TextToImageUtil;

import java.util.ArrayList;

/**This class used to set data in recycler view which holds all the data while inflating the layout base on
 * different output value
 * Created by Saloni on 21/10/16.
 */
public class WeatherForecastDailyDataAdapter extends RecyclerView
        .Adapter<WeatherForecastDailyDataAdapter
        .DataObjectHolder> {
    private ArrayList<DatumDaily> mDataset;

    //data holder to hold values for all the views
    public static class DataObjectHolder extends RecyclerView.ViewHolder
            implements View
            .OnClickListener {
        TextView tv_time;
        TextView tv_summary;
        TextView tv_strom_distance;
        TextView tv_strom_bearing;
        TextView tv_prep_intensity;
        TextView tv_prep_prob;
        TextView tv_dew_point;
        TextView tv_humidity;
        TextView tv_wind_speed;
        TextView tv_wind_bearing;
        TextView tv_visibility;
        TextView tv_cloud_cover;
        TextView tv_pressure;
        TextView tv_ozone;
        ImageView img_weather_type;
        public DataObjectHolder(View itemView) {
            super(itemView);
            img_weather_type=(ImageView) itemView.findViewById(R.id.img_weather_type);
            tv_time=(TextView) itemView.findViewById(R.id.tv_time);
            tv_summary=(TextView) itemView.findViewById(R.id.tv_summary);
            tv_strom_distance=(TextView) itemView.findViewById(R.id.tv_nearestStormDistance);
            tv_strom_bearing=(TextView) itemView.findViewById(R.id.tv_nearestStormBearing);
            tv_prep_intensity=(TextView) itemView.findViewById(R.id.tv_prepIntensity);
            tv_prep_prob=(TextView) itemView.findViewById(R.id.tv_precipProb);
            tv_dew_point=(TextView) itemView.findViewById(R.id.tv_dewPoint);
            tv_humidity=(TextView) itemView.findViewById(R.id.tv_humidity);
            tv_wind_speed=(TextView) itemView.findViewById(R.id.tv_windSpeed);
            tv_wind_bearing=(TextView) itemView.findViewById(R.id.tv_windBearing);
            tv_visibility=(TextView) itemView.findViewById(R.id.tv_visibilty);
            tv_cloud_cover=(TextView) itemView.findViewById(R.id.tv_cloudCover);
            tv_pressure=(TextView) itemView.findViewById(R.id.tv_pressure);
            tv_ozone=(TextView) itemView.findViewById(R.id.tv_ozone);
        }

        @Override
        public void onClick(View v) {
        }
    }

    public WeatherForecastDailyDataAdapter(ArrayList<DatumDaily> myDataset) {
        
        mDataset = myDataset;
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.weather_forecast_daily_data_display, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }
    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {
        //convert the time in sec to date time stamp
        DateUtils timeConversions = new DateUtils();
        holder.tv_time.setText(timeConversions.getTimeStamp(mDataset.get(position).getTime()+""));
        //get the image resource based on summary of weather type
        int resourceId = TextToImageUtil.getImageDrawable(mDataset.get(position).getSummary().toLowerCase());
        holder.img_weather_type.setBackgroundResource(resourceId);

        holder.tv_summary.setText(mDataset.get(position).getSummary()+"");
        holder.tv_prep_intensity.setText(mDataset.get(position).getPrecipIntensity()+"");
        holder.tv_prep_prob.setText(mDataset.get(position).getPrecipProbability()+"");
        holder.tv_dew_point.setText(mDataset.get(position).getDewPoint()+"");
        holder.tv_humidity.setText(mDataset.get(position).getHumidity()+"");
        holder.tv_wind_speed.setText(mDataset.get(position).getWindSpeed()+"");
        holder.tv_wind_bearing.setText(mDataset.get(position).getWindBearing()+"");
        holder.tv_visibility.setText(mDataset.get(position).getVisibility()+"");
        holder.tv_cloud_cover.setText(mDataset.get(position).getCloudCover()+"");
        holder.tv_pressure.setText(mDataset.get(position).getPressure()+"");
        holder.tv_ozone.setText(mDataset.get(position).getOzone()+"");
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}

