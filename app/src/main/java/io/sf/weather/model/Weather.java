package io.sf.weather.model;

/**
 * Created by adly on 2016/6/4.
 */
public class Weather {
    private String city;
    private String pinyin;
    private String cityCode;
    private String date;
    private String time;
    private String postCode;
    private Double longitude;
    private Double latitude;
    private String altitude;
    private String weather;
    private String temp;
    private String l_tmp;
    private String h_tmp;
    private String wd;
    private String ws;
    private String sunrise;
    private String sunset;


    /*private String sd;
    private String wse;
    private String isRadar;
    private String Radar;
    private String njd;
    private String qy;
    private String rain;*/

    public String getCity(){
        return city;
    }

    public void setCity(String city){
        this.city = city;
    }

    public String getDate(){
        return date;
    }

    public void setDate(String date){
        this.date = date;
    }

    public String getTime(){
        return time;
    }

    public void setTime(String time){
        this.time = time;
    }

    public Double getLongitude(){
        return longitude;
    }

    public void setLongitude(Double longitude){
        this.longitude = longitude;
    }

    public Double getLatitude(){
        return latitude;
    }

    public void setLatitude(Double latitude){
        this.latitude = latitude;
    }

    public String getWeather(){
        return weather;
    }

    public void setWeather(String weather){
        this.weather = weather;
    }


    public String getTemp(){
        return temp;
    }

    public void setTemp(String temp){
        this.temp = temp;
    }

    public String getLtmp(){
        return l_tmp;
    }

    public void setL_tmp(String l_tmp){
        this.l_tmp = l_tmp;
    }

    public String getH_tmp(){
        return h_tmp;
    }

    public void setH_tmp(String h_tmp){
        this.h_tmp = h_tmp;
    }

    public String getSunrise(){
        return sunrise;
    }

    public void setSunrise(String sunrise){
        this.sunrise = sunrise;
    }

    public String getSunset(){
        return sunset;
    }

    public void setSunset(String sunset){
        this.sunset = sunset;
    }


    public String getWd(){
        return wd;
    }

    public void setWd(String wd){
        this.wd = wd;
    }

    public String getWs(){
        return ws;
    }

    public void setWs(String ws){
        this.ws = ws;
    }



    /*public String getSd(){
        return sd;
    }

    public void setSd(String sd){
        this.sd = sd;
    }*/



    /*public String getNjd(){
        return njd;
    }

    public void setNjd(String njd){
        this.njd = njd;
    }

    public String getRain(){
        return rain;
    }

    public void setRain(String rain){
        this.rain = rain;
    }*/

}
