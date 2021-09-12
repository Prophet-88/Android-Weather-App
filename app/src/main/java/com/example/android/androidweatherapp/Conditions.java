package com.example.android.androidweatherapp;

/**
 * Created by bcauc on 2018-05-02.
 */

public class Conditions {
    String day; // Weekday (Mon, Wed...) + Number [ like: Wed 18 ]
    String temp;
    String weather;
    String icon; //GImage icon;
    String high;
    String low;

    // for current observations:
    String city;
    String wind_mph;
    String humidity;

    public Conditions(){

    }

    public Conditions(String day, String temp, String weather, String/*GImage*/ icon , String high, String low){
        this.day = day;
        this.temp = temp;
        this.weather = weather;
        this.icon = icon;
        this.high = high;
        this.low = low;
    }

}

