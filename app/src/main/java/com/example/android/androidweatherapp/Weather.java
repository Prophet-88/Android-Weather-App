package com.example.android.androidweatherapp;

/**
 * Created by bcauc on 2018-05-02.
 */

import android.os.StrictMode;
import android.view.View;
import android.widget.EditText;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class Weather {
    String areaCode;
    String cityName;
    String stateSymbol;
    String query;
    String[] suggestions;
    final String APIKEY = BuildConfig.ApiKey;
    JsonElement Jse;

    //Input -> Autocomplete
    //Autocomplete result -> fetch()

    public Weather(String q)
    {
        query = q;
    }

    private String trimSpaces(String in)
    {
        if(in.indexOf("  ") != -1)
        {
            in = in.replace("  ", " ");
            in = trimSpaces(in);
        }
        return in;
    }

    //fetch()
    public void fetch() {
        String weatherRequest;
        //http://api.wunderground.com/api/a571d3aa8d465016/conditions/hourly/forecast10day/q/CA/SanFrancisco.json
        query = trimSpaces(query);
        query = query.replace(" ", "%20");
        weatherRequest = "http://api.wunderground.com/api/" + APIKEY +
                "/conditions/hourly/forecast10day/q/" + query + ".json";
        /*if ( areaCode == null) {
            weatherRequest = "http://api.wunderground.com/api/" + APIKEY +
                    "/conditions/hourly/forecast10day/q/" + stateSymbol + "/" + cityName + ".json";

        } else {
            weatherRequest = "http://api.wunderground.com/api/" + APIKEY +
                    "/conditions/hourly/forecast10day/q/" + areaCode + ".json";
        }*/
        System.out.println("weatherRequest:\n" + weatherRequest);
        try {
            URL weatherURL = new URL(weatherRequest);

            InputStream is = weatherURL.openStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);

            Jse = new JsonParser().parse(br);
        } catch (java.net.MalformedURLException mue) {
            System.out.println("URL not well formed");
            mue.printStackTrace();
        } catch (java.io.IOException ioe) {
            System.out.println("Got IO Exception");
            ioe.printStackTrace();
        }
    }

    // getData
    public String getData(String key)
    {
        if (Jse == null) fetch();
        return Jse.getAsJsonObject().get("current_observation").getAsJsonObject().get(key).getAsString();
    }

    public String getTemp()
    {
        return getData( "temp_f");
    }

    public String getFull()
    {
        if (Jse == null) fetch();
        return Jse.getAsJsonObject().get("current_observation").getAsJsonObject().get("display_location").getAsJsonObject().get("full").getAsString();
    }

    public String getIcon()
    {
        return getData("icon");
    }

    public String getSatImageURL()
    {
        String radarRequest = "http://api.wunderground.com/api/" + APIKEY + "/radar/satellite/q/" + query + ".png?radius=100&width=300&height=300";
        System.out.println(radarRequest);
        return radarRequest;
    }

    public Conditions[] getForecast (int forecastLength)
    {
        if (Jse == null) fetch();

        if (forecastLength > 10)  forecastLength = 10;
        String weekday;
        String day;
        String icon;
        Conditions[] forecastConditions = new Conditions[forecastLength];
        JsonArray forecast =  Jse.getAsJsonObject().get("forecast").getAsJsonObject().getAsJsonObject().get("simpleforecast").getAsJsonObject().getAsJsonObject().get("forecastday").getAsJsonArray();

        //adds forecast conditions to the array
        for ( int i = 0; i < forecastLength; i++)
        {
            forecastConditions[i] = new Conditions(); // need to initialize new instance of conditions class before trying to assign values to the public data fields
            weekday = forecast.get(i).getAsJsonObject().get("date").getAsJsonObject().get("weekday_short").getAsString();
            day = forecast.get(i).getAsJsonObject().get("date").getAsJsonObject().get("day").getAsString();
            forecastConditions[i].temp = "";
            forecastConditions[i].weather = forecast.get(i).getAsJsonObject().get("conditions").getAsString();
            //icon = "images/" + forecast.get(i).getAsJsonObject().get("icon").getAsString() + ".gif";
            forecastConditions[i].icon = forecast.get(i).getAsJsonObject().get("icon").getAsString(); // need just the icon name (without "images/" or ".gif" to get it out of drawable
            forecastConditions[i].high = forecast.get(i).getAsJsonObject().get("high").getAsJsonObject().get("fahrenheit").getAsString();
            forecastConditions[i].low = forecast.get(i).getAsJsonObject().get("low").getAsJsonObject().get("fahrenheit").getAsString();
            forecastConditions[i].day = weekday + " " + day;
        }
        forecastConditions[0].temp = getData( "temp_f");
        forecastConditions[0].city = getFull();
        forecastConditions[0].wind_mph = getData("wind_mph") + " mph";
        forecastConditions[0].humidity = getData("relative_humidity");

        return forecastConditions;
    }

    public int hourlySize()
    {
        if (Jse == null) fetch();
        return Jse.getAsJsonObject().get("hourly_forecast").getAsJsonArray().size();
    }

    public Hourly[] getHourly()
    {
        if (Jse == null) fetch();
        JsonArray hourly = Jse.getAsJsonObject().get("hourly_forecast").getAsJsonArray();
        int size = hourlySize();

        //public Hourly(String hour, String temp, String weather, GImage icon){
        Hourly[] h = new Hourly[size];

        for(int i = 0; i < size; i++)
        {
            String hour = hourly.get(i).getAsJsonObject().get("FCTTIME").getAsJsonObject().get("hour").getAsString();
            String temp = hourly.get(i).getAsJsonObject().get("temp").getAsJsonObject().get("english").getAsString();
            String weather = hourly.get(i).getAsJsonObject().get("condition").getAsString();
            String icon = hourly.get(i).getAsJsonObject().get("icon").getAsString();
            h[i] = new Hourly(hour, temp, weather, icon);
        }
        return h;
    }

    public static void main(String[] args)
    {
        Weather b = new Weather("95603");

        Weather w = new Weather("Rocklin, CA");
        System.out.println(w.cityName);
        System.out.println(w.stateSymbol);

    }
}

