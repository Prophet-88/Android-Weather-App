package com.example.android.androidweatherapp;

/**
 * Created by bcauc on 2018-05-02.
 */

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class Suggestions {

    String[] suggestions;

    public Suggestions(String in) {
        in = trimSpaces(in);
        in = in.replace(" ", "%20"); //remove commas

        String autocomplete = "http://autocomplete.wunderground.com/aq?query=" + in;
        System.out.println(autocomplete);

        JsonElement auto = fetch(autocomplete);
        int size = auto.getAsJsonObject().get("RESULTS").getAsJsonArray().size();
        suggestions = new String[size];
        for (int i = 0; i < size; i++) {
            suggestions[i] = auto.getAsJsonObject().get("RESULTS").getAsJsonArray().get(i).getAsJsonObject().get("name").getAsString();
        }
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

    public String[] getSuggestions()
    {
        return suggestions;
    }

    public JsonElement fetch(String request)
    {
        try
        {
            URL weatherURL = new URL(request);

            InputStream is = weatherURL.openStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);

            JsonElement ret = new JsonParser().parse(br);

            return ret;

        }
        catch (java.net.MalformedURLException mue)
        {
            System.out.println("URL not well formed");
            mue.printStackTrace();
        }
        catch (java.io.IOException ioe)
        {
            System.out.println("Got IO Exception");
            ioe.printStackTrace();
        }
        //Catch for internet
        return null;
    }
}

