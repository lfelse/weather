package io.sf.weather.model;

import io.sf.weather.callback.OnWeather;

/**
 * Created by adly on 2016/6/4.
 */
public interface WeatherModel {

    Weather getWeather(OnWeather listener);
}
