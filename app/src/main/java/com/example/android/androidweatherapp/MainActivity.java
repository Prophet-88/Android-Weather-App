package com.example.android.androidweatherapp;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new GetWeatherInBackground().execute("autoip");

    }

    public void getWeather(View v)
    {
        // Get the text from the input field
        EditText location = (EditText) findViewById(R.id.inputLocation);
        String locationStr = location.getText().toString();
        new GetWeatherInBackground().execute(locationStr);

        // Hide the keyboard
        if(v != null)
        {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        }
    }

    public void showHourly(View v)
    {
        TextView day1 = (TextView) findViewById(R.id.day1);
        TextView day2 = (TextView) findViewById(R.id.day2);
        TextView day3 = (TextView) findViewById(R.id.day3);
        TextView day4 = (TextView) findViewById(R.id.day4);

        day1.setVisibility(View.INVISIBLE);
        day2.setVisibility(View.INVISIBLE);
        day3.setVisibility(View.INVISIBLE);
        day4.setVisibility(View.INVISIBLE);

        TextView high1 = (TextView) findViewById(R.id.high1);
        TextView high2 = (TextView) findViewById(R.id.high2);
        TextView high3 = (TextView) findViewById(R.id.high3);
        TextView high4 = (TextView) findViewById(R.id.high4);

        high1.setVisibility(View.INVISIBLE);
        high2.setVisibility(View.INVISIBLE);
        high3.setVisibility(View.INVISIBLE);
        high4.setVisibility(View.INVISIBLE);

        TextView low1 = (TextView) findViewById(R.id.low1);
        TextView low2 = (TextView) findViewById(R.id.low2);
        TextView low3 = (TextView) findViewById(R.id.low3);
        TextView low4 = (TextView) findViewById(R.id.low4);

        low1.setVisibility(View.INVISIBLE);
        low2.setVisibility(View.INVISIBLE);
        low3.setVisibility(View.INVISIBLE);
        low4.setVisibility(View.INVISIBLE);

        ImageView cond1 = (ImageView) findViewById(R.id.cond1);
        ImageView cond2 = (ImageView) findViewById(R.id.cond2);
        ImageView cond3 = (ImageView) findViewById(R.id.cond3);
        ImageView cond4 = (ImageView) findViewById(R.id.cond4);

        cond1.setVisibility(View.INVISIBLE);
        cond2.setVisibility(View.INVISIBLE);
        cond3.setVisibility(View.INVISIBLE);
        cond4.setVisibility(View.INVISIBLE);


        //Hourly
        TextView hour1 = (TextView) findViewById(R.id.hour1);
        TextView hour2 = (TextView) findViewById(R.id.hour2);
        TextView hour3 = (TextView) findViewById(R.id.hour3);
        TextView hour4 = (TextView) findViewById(R.id.hour4);

        hour1.setVisibility(View.VISIBLE);
        hour2.setVisibility(View.VISIBLE);
        hour3.setVisibility(View.VISIBLE);
        hour4.setVisibility(View.VISIBLE);

        TextView hourtemp1 = (TextView) findViewById(R.id.hourtemp1);
        TextView hourtemp2 = (TextView) findViewById(R.id.hourtemp2);
        TextView hourtemp3 = (TextView) findViewById(R.id.hourtemp3);
        TextView hourtemp4 = (TextView) findViewById(R.id.hourtemp4);

        hourtemp1.setVisibility(View.VISIBLE);
        hourtemp2.setVisibility(View.VISIBLE);
        hourtemp3.setVisibility(View.VISIBLE);
        hourtemp4.setVisibility(View.VISIBLE);

        TextView hourweather1 = (TextView) findViewById(R.id.hourweather1);
        TextView hourweather2 = (TextView) findViewById(R.id.hourweather2);
        TextView hourweather3 = (TextView) findViewById(R.id.hourweather3);
        TextView hourweather4 = (TextView) findViewById(R.id.hourweather4);

        hourweather1.setVisibility(View.VISIBLE);
        hourweather2.setVisibility(View.VISIBLE);
        hourweather3.setVisibility(View.VISIBLE);
        hourweather4.setVisibility(View.VISIBLE);

        ImageView hourcond1 = (ImageView) findViewById(R.id.hourcond1);
        ImageView hourcond2 = (ImageView) findViewById(R.id.hourcond2);
        ImageView hourcond3 = (ImageView) findViewById(R.id.hourcond3);
        ImageView hourcond4 = (ImageView) findViewById(R.id.hourcond4);

        hourcond1.setVisibility(View.VISIBLE);
        hourcond2.setVisibility(View.VISIBLE);
        hourcond3.setVisibility(View.VISIBLE);
        hourcond4.setVisibility(View.VISIBLE);

        WebView radar = (WebView) findViewById(R.id.radar);
        radar.setVisibility(View.INVISIBLE);

        hideCurrentConditions(v);
    }

    public void showForecast(View v)
    {
        //Forecast
        TextView day1 = (TextView) findViewById(R.id.day1);
        TextView day2 = (TextView) findViewById(R.id.day2);
        TextView day3 = (TextView) findViewById(R.id.day3);
        TextView day4 = (TextView) findViewById(R.id.day4);

        day1.setVisibility(View.VISIBLE);
        day2.setVisibility(View.VISIBLE);
        day3.setVisibility(View.VISIBLE);
        day4.setVisibility(View.VISIBLE);

        TextView high1 = (TextView) findViewById(R.id.high1);
        TextView high2 = (TextView) findViewById(R.id.high2);
        TextView high3 = (TextView) findViewById(R.id.high3);
        TextView high4 = (TextView) findViewById(R.id.high4);

        high1.setVisibility(View.VISIBLE);
        high2.setVisibility(View.VISIBLE);
        high3.setVisibility(View.VISIBLE);
        high4.setVisibility(View.VISIBLE);

        TextView low1 = (TextView) findViewById(R.id.low1);
        TextView low2 = (TextView) findViewById(R.id.low2);
        TextView low3 = (TextView) findViewById(R.id.low3);
        TextView low4 = (TextView) findViewById(R.id.low4);

        low1.setVisibility(View.VISIBLE);
        low2.setVisibility(View.VISIBLE);
        low3.setVisibility(View.VISIBLE);
        low4.setVisibility(View.VISIBLE);

        ImageView cond1 = (ImageView) findViewById(R.id.cond1);
        ImageView cond2 = (ImageView) findViewById(R.id.cond2);
        ImageView cond3 = (ImageView) findViewById(R.id.cond3);
        ImageView cond4 = (ImageView) findViewById(R.id.cond4);

        cond1.setVisibility(View.VISIBLE);
        cond2.setVisibility(View.VISIBLE);
        cond3.setVisibility(View.VISIBLE);
        cond4.setVisibility(View.VISIBLE);


        //Hourly
        TextView hour1 = (TextView) findViewById(R.id.hour1);
        TextView hour2 = (TextView) findViewById(R.id.hour2);
        TextView hour3 = (TextView) findViewById(R.id.hour3);
        TextView hour4 = (TextView) findViewById(R.id.hour4);

        hour1.setVisibility(View.INVISIBLE);
        hour2.setVisibility(View.INVISIBLE);
        hour3.setVisibility(View.INVISIBLE);
        hour4.setVisibility(View.INVISIBLE);

        TextView hourtemp1 = (TextView) findViewById(R.id.hourtemp1);
        TextView hourtemp2 = (TextView) findViewById(R.id.hourtemp2);
        TextView hourtemp3 = (TextView) findViewById(R.id.hourtemp3);
        TextView hourtemp4 = (TextView) findViewById(R.id.hourtemp4);

        hourtemp1.setVisibility(View.INVISIBLE);
        hourtemp2.setVisibility(View.INVISIBLE);
        hourtemp3.setVisibility(View.INVISIBLE);
        hourtemp4.setVisibility(View.INVISIBLE);

        TextView hourweather1 = (TextView) findViewById(R.id.hourweather1);
        TextView hourweather2 = (TextView) findViewById(R.id.hourweather2);
        TextView hourweather3 = (TextView) findViewById(R.id.hourweather3);
        TextView hourweather4 = (TextView) findViewById(R.id.hourweather4);

        hourweather1.setVisibility(View.INVISIBLE);
        hourweather2.setVisibility(View.INVISIBLE);
        hourweather3.setVisibility(View.INVISIBLE);
        hourweather4.setVisibility(View.INVISIBLE);

        ImageView hourcond1 = (ImageView) findViewById(R.id.hourcond1);
        ImageView hourcond2 = (ImageView) findViewById(R.id.hourcond2);
        ImageView hourcond3 = (ImageView) findViewById(R.id.hourcond3);
        ImageView hourcond4 = (ImageView) findViewById(R.id.hourcond4);

        hourcond1.setVisibility(View.INVISIBLE);
        hourcond2.setVisibility(View.INVISIBLE);
        hourcond3.setVisibility(View.INVISIBLE);
        hourcond4.setVisibility(View.INVISIBLE);

        WebView radar = (WebView) findViewById(R.id.radar);
        radar.setVisibility(View.INVISIBLE);

        hideCurrentConditions(v);
    }

    public void showSatellite(View v)
    {
        WebView radar = (WebView) findViewById(R.id.radar);
        radar.setVisibility(View.VISIBLE);

        hideCurrentConditions(v);
        hideHourly(v);
        hideForeCast(v);
    }

    private String toAMPM(String hour)
    {
        try {
            DateFormat inputFormat = new SimpleDateFormat("H");
            DateFormat outputFormat = new SimpleDateFormat("hh a");
            return outputFormat.format(inputFormat.parse(hour));

        }
        catch(ParseException e)
        {
            throw new NullPointerException();
        }
    }

    public void showCurrentConditions(View v) {
        ImageView iconView = findViewById(R.id.icon);
        iconView.setVisibility(View.VISIBLE);
        TextView weather = (TextView) findViewById(R.id.weather);
        weather.setVisibility(View.VISIBLE);
        TextView temp = (TextView) findViewById(R.id.temp);
        temp.setVisibility(View.VISIBLE);
        TextView humidity = (TextView) findViewById(R.id.humidity);
        humidity.setVisibility(View.VISIBLE);
        TextView wind = (TextView) findViewById(R.id.windspeed);
        wind.setVisibility(View.VISIBLE);

        hideForeCast(v);
        hideHourly(v);
        hideSatellite(v);
    }

    public void hideCurrentConditions(View v) {
        ImageView iconView = findViewById(R.id.icon);
        iconView.setVisibility(View.INVISIBLE);
        TextView weather = (TextView) findViewById(R.id.weather);
        weather.setVisibility(View.INVISIBLE);
        TextView temp = (TextView) findViewById(R.id.temp);
        temp.setVisibility(View.INVISIBLE);
        TextView humidity = (TextView) findViewById(R.id.humidity);
        humidity.setVisibility(View.INVISIBLE);
        TextView wind = (TextView) findViewById(R.id.windspeed);
        wind.setVisibility(View.INVISIBLE);
    }

    public void hideForeCast(View v) {
        TextView day1 = (TextView) findViewById(R.id.day1);
        TextView day2 = (TextView) findViewById(R.id.day2);
        TextView day3 = (TextView) findViewById(R.id.day3);
        TextView day4 = (TextView) findViewById(R.id.day4);

        day1.setVisibility(View.INVISIBLE);
        day2.setVisibility(View.INVISIBLE);
        day3.setVisibility(View.INVISIBLE);
        day4.setVisibility(View.INVISIBLE);

        TextView high1 = (TextView) findViewById(R.id.high1);
        TextView high2 = (TextView) findViewById(R.id.high2);
        TextView high3 = (TextView) findViewById(R.id.high3);
        TextView high4 = (TextView) findViewById(R.id.high4);

        high1.setVisibility(View.INVISIBLE);
        high2.setVisibility(View.INVISIBLE);
        high3.setVisibility(View.INVISIBLE);
        high4.setVisibility(View.INVISIBLE);

        TextView low1 = (TextView) findViewById(R.id.low1);
        TextView low2 = (TextView) findViewById(R.id.low2);
        TextView low3 = (TextView) findViewById(R.id.low3);
        TextView low4 = (TextView) findViewById(R.id.low4);

        low1.setVisibility(View.INVISIBLE);
        low2.setVisibility(View.INVISIBLE);
        low3.setVisibility(View.INVISIBLE);
        low4.setVisibility(View.INVISIBLE);

        ImageView cond1 = (ImageView) findViewById(R.id.cond1);
        ImageView cond2 = (ImageView) findViewById(R.id.cond2);
        ImageView cond3 = (ImageView) findViewById(R.id.cond3);
        ImageView cond4 = (ImageView) findViewById(R.id.cond4);

        cond1.setVisibility(View.INVISIBLE);
        cond2.setVisibility(View.INVISIBLE);
        cond3.setVisibility(View.INVISIBLE);
        cond4.setVisibility(View.INVISIBLE);
    }

    public void hideHourly(View v) {
        TextView hour1 = (TextView) findViewById(R.id.hour1);
        TextView hour2 = (TextView) findViewById(R.id.hour2);
        TextView hour3 = (TextView) findViewById(R.id.hour3);
        TextView hour4 = (TextView) findViewById(R.id.hour4);

        hour1.setVisibility(View.INVISIBLE);
        hour2.setVisibility(View.INVISIBLE);
        hour3.setVisibility(View.INVISIBLE);
        hour4.setVisibility(View.INVISIBLE);

        TextView hourtemp1 = (TextView) findViewById(R.id.hourtemp1);
        TextView hourtemp2 = (TextView) findViewById(R.id.hourtemp2);
        TextView hourtemp3 = (TextView) findViewById(R.id.hourtemp3);
        TextView hourtemp4 = (TextView) findViewById(R.id.hourtemp4);

        hourtemp1.setVisibility(View.INVISIBLE);
        hourtemp2.setVisibility(View.INVISIBLE);
        hourtemp3.setVisibility(View.INVISIBLE);
        hourtemp4.setVisibility(View.INVISIBLE);

        TextView hourweather1 = (TextView) findViewById(R.id.hourweather1);
        TextView hourweather2 = (TextView) findViewById(R.id.hourweather2);
        TextView hourweather3 = (TextView) findViewById(R.id.hourweather3);
        TextView hourweather4 = (TextView) findViewById(R.id.hourweather4);

        hourweather1.setVisibility(View.INVISIBLE);
        hourweather2.setVisibility(View.INVISIBLE);
        hourweather3.setVisibility(View.INVISIBLE);
        hourweather4.setVisibility(View.INVISIBLE);

        ImageView hourcond1 = (ImageView) findViewById(R.id.hourcond1);
        ImageView hourcond2 = (ImageView) findViewById(R.id.hourcond2);
        ImageView hourcond3 = (ImageView) findViewById(R.id.hourcond3);
        ImageView hourcond4 = (ImageView) findViewById(R.id.hourcond4);

        hourcond1.setVisibility(View.INVISIBLE);
        hourcond2.setVisibility(View.INVISIBLE);
        hourcond3.setVisibility(View.INVISIBLE);
        hourcond4.setVisibility(View.INVISIBLE);
    }

    public void hideSatellite(View v) {
        WebView radar = (WebView) findViewById(R.id.radar);
        radar.setVisibility(View.INVISIBLE);
    }

    private class GetWeatherInBackground extends AsyncTask<String, Void, Weather>
    {
        @Override
        protected Weather doInBackground(String... locations)
        {
            Suggestions s = new Suggestions(locations[0]);
            String[] suggestions = s.getSuggestions();
            Weather w;
            if (suggestions.length > 0)
            {
                w = new Weather(suggestions[0]);
            }
            else
            {
                w = new Weather("autoip");
            }
            w.fetch();
            return w;
        }


        @Override
        protected void onPostExecute(Weather w)
        {
            try {
                // Update the data on the screen
                Conditions[] cond = w.getForecast(7);

                // Current conditions ----------------------------------------------------
                // Set the text of the city field
                TextView city = (TextView) findViewById(R.id.city);
                city.setText(cond[0].city);

                // Set icon image
                int iconId = getResources().getIdentifier(cond[0].icon, "drawable", getPackageName());
                ImageView iconView = findViewById(R.id.icon);
                iconView.setImageResource(iconId);

                // Set the text of the weather field
                TextView weather = (TextView) findViewById(R.id.weather);
                weather.setText(cond[0].weather);

                // Set the text of the temperature field
                TextView temp = (TextView) findViewById(R.id.temp);
                temp.setText(cond[0].temp + " °F");

                // Set the text of the humidity field
                TextView humidity = (TextView) findViewById(R.id.humidity);
                humidity.setText("Humidity: " + cond[0].humidity);

                // Set the text of the windspeed field
                TextView wind = (TextView) findViewById(R.id.windspeed);
                wind.setText("Windspeed: " + cond[0].wind_mph);

                // Forecast ----------------------------------------------------------------
                // set high temps 4day

                TextView high1 = (TextView) findViewById(R.id.high1);
                high1.setText(cond[1].high + " °F");

                TextView high2 = (TextView) findViewById(R.id.high2);
                high2.setText(cond[2].high + " °F");

                TextView high3 = (TextView) findViewById(R.id.high3);
                high3.setText(cond[3].high + " °F");

                TextView high4 = (TextView) findViewById(R.id.high4);
                high4.setText(cond[4].high + " °F");

                // set low temp 4day

                TextView low1 = (TextView) findViewById(R.id.low1);
                low1.setText(cond[1].low + " °F");

                TextView low2 = (TextView) findViewById(R.id.low2);
                low2.setText(cond[2].low + " °F");

                TextView low3 = (TextView) findViewById(R.id.low3);
                low3.setText(cond[3].low + " °F");

                TextView low4 = (TextView) findViewById(R.id.low4);
                low4.setText(cond[4].low + " °F");

                // set day 4day

                TextView day1 = (TextView) findViewById(R.id.day1);
                day1.setText(cond[1].day);

                TextView day2 = (TextView) findViewById(R.id.day2);
                day2.setText(cond[2].day);

                TextView day3 = (TextView) findViewById(R.id.day3);
                day3.setText(cond[3].day);

                TextView day4 = (TextView) findViewById(R.id.day4);
                day4.setText(cond[4].day);

                //set icon 4day

                int cond1Id = getResources().getIdentifier(cond[1].icon, "drawable", getPackageName());
                ImageView cond1View = findViewById(R.id.cond1);
                cond1View.setImageResource(cond1Id);

                int cond2Id = getResources().getIdentifier(cond[2].icon, "drawable", getPackageName());
                ImageView cond2View = findViewById(R.id.cond2);
                cond2View.setImageResource(cond2Id);

                int cond3Id = getResources().getIdentifier(cond[3].icon, "drawable", getPackageName());
                ImageView cond3View = findViewById(R.id.cond3);
                cond3View.setImageResource(cond3Id);

                int cond4Id = getResources().getIdentifier(cond[4].icon, "drawable", getPackageName());
                ImageView cond4View = findViewById(R.id.cond4);
                cond4View.setImageResource(cond4Id);

                String satImgURL = w.getSatImageURL();
                WebView radarView = findViewById(R.id.radar);
                radarView.loadUrl(satImgURL);


                //Hourly Section
                Hourly[] hourly = w.getHourly();

                //Hour1------------------------------------------------------------------------------------------------
                TextView hour1 = (TextView) findViewById(R.id.hour1);
                hour1.setText(toAMPM(hourly[0].hour));

                TextView hourtemp1 = (TextView) findViewById(R.id.hourtemp1);
                hourtemp1.setText(hourly[0].temp + " °F");

                TextView hourweather1 = (TextView) findViewById(R.id.hourweather1);
                hourweather1.setText(hourly[0].weather);

                int hourcond1Id = getResources().getIdentifier(hourly[0].icon, "drawable", getPackageName());
                ImageView hourcond1 = findViewById(R.id.hourcond1);
                hourcond1.setImageResource(hourcond1Id);

                //Hour2------------------------------------------------------------------------------------------------
                TextView hour2 = (TextView) findViewById(R.id.hour2);
                hour2.setText(toAMPM(hourly[1].hour));

                TextView hourtemp2 = (TextView) findViewById(R.id.hourtemp2);
                hourtemp2.setText(hourly[1].temp + " °F");

                TextView hourweather2 = (TextView) findViewById(R.id.hourweather2);
                hourweather2.setText(hourly[1].weather);

                int hourcond2Id = getResources().getIdentifier(hourly[1].icon, "drawable", getPackageName());
                ImageView hourcond2 = findViewById(R.id.hourcond2);
                hourcond2.setImageResource(hourcond2Id);

                //Hour3------------------------------------------------------------------------------------------------
                TextView hour3 = (TextView) findViewById(R.id.hour3);
                hour3.setText(toAMPM(hourly[2].hour));

                TextView hourtemp3 = (TextView) findViewById(R.id.hourtemp3);
                hourtemp3.setText(hourly[2].temp + " °F");

                TextView hourweather3 = (TextView) findViewById(R.id.hourweather3);
                hourweather3.setText(hourly[2].weather);

                int hourcond3Id = getResources().getIdentifier(hourly[2].icon, "drawable", getPackageName());
                ImageView hourcond3 = findViewById(R.id.hourcond3);
                hourcond3.setImageResource(hourcond3Id);

                //Hour4------------------------------------------------------------------------------------------------
                TextView hour4 = (TextView) findViewById(R.id.hour4);
                hour4.setText(toAMPM(hourly[3].hour));

                TextView hourtemp4 = (TextView) findViewById(R.id.hourtemp4);
                hourtemp4.setText(hourly[3].temp + " °F");

                TextView hourweather4 = (TextView) findViewById(R.id.hourweather4);
                hourweather4.setText(hourly[3].weather);

                int hourcond4Id = getResources().getIdentifier(hourly[3].icon, "drawable", getPackageName());
                ImageView hourcond4 = findViewById(R.id.hourcond4);
                hourcond4.setImageResource(hourcond4Id);

                View v = findViewById(R.id.mainview);

                showCurrentConditions(v);
            }
            catch (Exception e)
            {
                EditText inputBox = findViewById(R.id.inputLocation);
                inputBox.setText("Error getting weather.");
            }
        }
    }
}


