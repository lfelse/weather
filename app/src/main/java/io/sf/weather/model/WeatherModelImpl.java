package io.sf.weather.model;

import android.content.Context;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

import io.sf.weather.callback.OnWeather;

/**
 * Created by adly on 2016/6/4.
 */
public class WeatherModelImpl implements WeatherModel {

    private final String URL_WEATHER = "http://apis.baidu.com/apistore/weatherservice/weather?citypinyin=beijing";
    private Context context;
    private RequestQueue requestQueue;


    public WeatherModelImpl(Context context){
        this.context = context;
    }

    @Override
    public Weather getWeather(final OnWeather listener){
        final Weather weather = new Weather();

        VolleyLog.DEBUG = true;
        requestQueue = Volley.newRequestQueue(context);

        requestQueue.add(new JsonObjectRequest(Request.Method.GET, URL_WEATHER, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        if(response.optInt("errNum") == 0 && response.optString("errMsg").equals("success")){
                            JSONObject result = response.optJSONObject("retData");

                            weather.setCity(result.optString("city"));
                            weather.setDate(result.optString("date"));
                            weather.setTime(result.optString("time"));
                            weather.setLongitude(result.optDouble("longitude"));
                            weather.setLatitude(result.optDouble("latitude"));
                            weather.setWeather(result.optString("weather"));
                            weather.setTemp(result.optString("temp"));
                            weather.setL_tmp(result.optString("l_tmp"));
                            weather.setH_tmp(result.optString("h_tmp"));
                            weather.setWd(result.optString("WD"));
                            weather.setWs(result.optString("WS"));
                            weather.setSunrise(result.optString("sunrise"));
                            weather.setSunset(result.optString("sunset"));

                            listener.onWeatherComplete(weather);
                        }else{
                            listener.onWeatherError();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onWeatherError();
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                headers.put("apikey", "8083205faedcb24040b1871f12a7b3a0");
                return headers;
            }
        });

/*
        requestQueue.add(new StringRequest(Request.Method.GET, URL_WEATHER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if(response == null){
                            Toast.makeText(context.getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        try {
                            JSONObject result = new JSONObject(response).optJSONObject("weatherinfo");


                            weather.setCity(result.optString("city"));
                            weather.setTemp(result.optString("temp"));
                            weather.setWd(result.optString("wd"));
                            weather.setWs(result.optString("ws"));
                            weather.setSd(result.optString("sd"));
                            weather.setTime(result.optString("time"));
                            weather.setNjd(result.optString("njd"));
                            weather.setRain(result.optString("rain"));

                            listener.onWeatherComplete(weather);
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                NetworkResponse response = error.networkResponse;
                if (error instanceof ServerError && response != null) {
                    try {
                        String res = new String(response.data,
                                HttpHeaderParser.parseCharset(response.headers, "utf-8"));
                        // Now you can use any deserializer to make sense of data
                        JSONObject obj = new JSONObject(res);
                    } catch (UnsupportedEncodingException e1) {
                        // Couldn't properly decode data to string
                        e1.printStackTrace();
                    } catch (JSONException e2) {
                        // returned data is not JSONObject?
                        e2.printStackTrace();
                    }
                }
                Log.v(WeatherModelImpl.class.getSimpleName(), response + "");

                listener.onWeatherError();
            }
        }){
            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded; charset=" + getParamsEncoding();
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }
        });
*/
        /*
        requestQueue.add(new GsonRequest<Weather>(Request.Method.GET, URL_WEATHER,
                Weather.class, new Response.Listener<Weather>() {
            @Override
            public void onResponse(Weather response) {
                listener.onWeatherComplete(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onWeatherError();
            }
        }));
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://www.baidu.com")
                .get()
                .addHeader("cache-control", "no-cache")
                .addHeader("postman-token", "553483f4-59e2-9e9c-59e5-9eacb318e56d")
                .build();

        try {
            Response response = client.newCall(request).execute();
            if(response.body().string().equals("")){
                Log.v("test", "fuck");
            }
            Log.v("test", response.body().string() + "");
        }catch (IOException e){
            e.printStackTrace();
        }*/


        return weather;
    }
/*
    public class GsonRequest<t> extends Request<t> {

        private final Response.Listener<t> mlisten;

        private Gson gson;
        private Class<t> mClass;

        public GsonRequest(int method, String url,Class<t> clazz, Response.Listener<t> listener, Response.ErrorListener errorListener) {
            super(method, url, errorListener);
            mClass = clazz;
            gson = new Gson();
            mlisten = listener;
        }

        public GsonRequest(String url, Class<t> clazz,Response.Listener<t> listener, Response.ErrorListener errorListener) {
            this(Method.GET, url,clazz,listener, errorListener);
        }



        @Override
        protected Response<t> parseNetworkResponse(NetworkResponse networkResponse) {
            Log.v(this.getClass().getSimpleName(), networkResponse.toString());
            try {
                String jsonString = new String(networkResponse.data,
                        HttpHeaderParser.parseCharset(networkResponse.headers));
                return  Response.success(gson.fromJson(jsonString,mClass),
                        HttpHeaderParser.parseCacheHeaders(networkResponse));
            } catch (UnsupportedEncodingException e) {
                return Response.error(new ParseError(e));
            }
        }

        @Override
        protected void deliverResponse(t response) {
            mlisten.onResponse(response);
        }

    }
    */
}
