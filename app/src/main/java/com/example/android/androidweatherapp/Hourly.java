package com.example.android.androidweatherapp;

/**
 * Created by bcauc on 2018-05-02.
 */

public class Hourly {

    String hour;
    String temp;
    String weather;
    String icon; //GImage icon;

    public Hourly(String hour, String temp, String weather, String icon){
        this.hour = hour;
        this.temp = temp;
        this.weather = weather;
        this.icon = icon;
    }
}

