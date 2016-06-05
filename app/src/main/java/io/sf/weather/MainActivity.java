package io.sf.weather;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import io.sf.weather.callback.OnWeather;
import io.sf.weather.model.Weather;
import io.sf.weather.model.WeatherModelImpl;

public class MainActivity extends AppCompatActivity {

    Weather weatherInfo;

    private TextView city;
    private TextView date;
    private TextView time;
    private TextView longitude;
    private TextView latitude;
    private TextView weather;
    private TextView temp;
    private TextView l_tmp;
    private TextView h_tmp;
    private TextView wd;
    private TextView ws;
    private TextView sunrise;
    private TextView sunset;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);

        city = (TextView)findViewById(R.id.city);
        date = (TextView)findViewById(R.id.date);
        time = (TextView)findViewById(R.id.time);
        longitude = (TextView)findViewById(R.id.longitude);
        latitude = (TextView)findViewById(R.id.latitude);
        weather = (TextView)findViewById(R.id.weather);
        temp = (TextView)findViewById(R.id.temp);
        l_tmp = (TextView)findViewById(R.id.l_tmp);
        h_tmp = (TextView)findViewById(R.id.h_tmp);
        wd = (TextView)findViewById(R.id.wd);
        ws = (TextView)findViewById(R.id.ws);
        sunrise = (TextView)findViewById(R.id.sunrise);
        sunset = (TextView)findViewById(R.id.sunset);

        final WeatherModelImpl weatherModel = new WeatherModelImpl(this);
        weatherModel.getWeather(new OnWeather() {
            @Override
            public void onWeatherComplete(Weather weatherInfo) {
                city.setText(weatherInfo.getCity());
                date.setText(weatherInfo.getDate());
                time.setText(weatherInfo.getTime());
                longitude.setText(Double.toString(weatherInfo.getLongitude()));
                latitude.setText(String.valueOf(weatherInfo.getLatitude()));
                weather.setText(weatherInfo.getWeather());
                temp.setText(weatherInfo.getTemp());
                l_tmp.setText(weatherInfo.getLtmp());
                h_tmp.setText(weatherInfo.getH_tmp());
                wd.setText(weatherInfo.getWd());
                ws.setText(weatherInfo.getWs());
                sunrise.setText(weatherInfo.getSunrise());
                sunset.setText(weatherInfo.getSunset());

            }

            @Override
            public void onWeatherError() {
                Toast.makeText(getApplicationContext(), "请求失败!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
