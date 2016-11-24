package com.weatherapp.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.weatherapp.R;
import com.weatherapp.model.DataMinuely;
import com.weatherapp.utils.DateUtils;

import java.util.ArrayList;
/**This class used to set data in recycler view which holds all the data while inflating the layout base on
 * different output value
 * Created by Saloni on 21/10/16.
 */
public class WeatherForecastMinutelyDataApdapter extends RecyclerView
        .Adapter<WeatherForecastMinutelyDataApdapter
        .DataObjectHolder> {
    private ArrayList<DataMinuely> mDataset;

    //data holder to hold values for all the views
    public static class DataObjectHolder extends RecyclerView.ViewHolder
            implements View
            .OnClickListener {
        TextView tv_prepIntensity;
        TextView tv_time;
        TextView tv_preciProb;
        public DataObjectHolder(View itemView) {
            super(itemView);
            tv_time = (TextView) itemView.findViewById(R.id.tv_time);
            tv_preciProb = (TextView) itemView.findViewById(R.id.tv_precipProb);
            tv_prepIntensity = (TextView) itemView.findViewById(R.id.tv_prepIntensity);
        }

        @Override
        public void onClick(View v) {
        }
    }

    public WeatherForecastMinutelyDataApdapter(ArrayList<DataMinuely> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.weather_forecast_minutely_data_display, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {
        //convert the time in sec to date time stamp
        DateUtils timeConversions = new DateUtils();
        holder.tv_time.setText(timeConversions.getTimeStamp(mDataset.get(position).getTime()+""));

        holder.tv_preciProb.setText(mDataset.get(position).getPrecipProbability()+"");
        holder.tv_prepIntensity.setText(mDataset.get(position).getPrecipIntensity()+"");
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}

