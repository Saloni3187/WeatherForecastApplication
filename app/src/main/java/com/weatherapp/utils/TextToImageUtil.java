package com.weatherapp.utils;

import com.weatherapp.R;

import static com.weatherapp.constants.AppConstant.CLEARSKY;
import static com.weatherapp.constants.AppConstant.CLOUDY;
import static com.weatherapp.constants.AppConstant.DRIZZLE;
import static com.weatherapp.constants.AppConstant.LIGHT_RAIN;
import static com.weatherapp.constants.AppConstant.RAIN;

/**
 * Created by Saloni on 23/11/16.
 */

public class TextToImageUtil {
    /**
     * Method change image resource Id based on summary text
     * @param summary
     * @return
     */
    public static int getImageDrawable(String summary) {
        int type_of_image = 0;
        if (summary.contains(CLEARSKY.toLowerCase()))
            type_of_image = R.drawable.clear;
        else if (summary.contains(RAIN.toLowerCase()))
            type_of_image = R.drawable.rain;
        else if (summary.contains(DRIZZLE.toLowerCase()))
            type_of_image = R.drawable.drizzle;
        else if (summary.contains(CLOUDY.toLowerCase()))
            type_of_image = R.drawable.cloudy;
        else if (summary.contains(LIGHT_RAIN.toLowerCase()))
            type_of_image = R.drawable.drizzle;
        else
            type_of_image = R.drawable.clear;
        return type_of_image;
    }

}
