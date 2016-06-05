package io.sf.weather.callback;

import io.sf.weather.model.Weather;

/**
 * Created by adly on 2016/6/5.
 */
public interface OnWeather {
    void onWeatherComplete(Weather weather);

    void onWeatherError();
}
