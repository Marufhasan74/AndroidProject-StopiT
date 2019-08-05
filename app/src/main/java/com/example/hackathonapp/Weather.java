package com.example.hackathonapp;

import android.os.AsyncTask;



import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class Weather extends AsyncTask<String,Void,String> {

    String result;

    @Override
    protected String doInBackground(String... urls) {
        result="";
        URL link;
        HttpURLConnection myConnection;
        try{
            link=new URL(urls[0]);
            myConnection=(HttpURLConnection)link.openConnection();
            InputStream in=myConnection.getInputStream();
            InputStreamReader myStreamReader=new InputStreamReader(in);
            int data=myStreamReader.read();
            while (data!=-1){
                char current =(char) data;
                result += current;
                data =myStreamReader.read();
            }
            return result;
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    protected void onPostExecute(String s){
        super.onPostExecute(s);
        try{
            JSONObject myObject = new JSONObject(result);
            JSONObject main = new JSONObject(myObject.getString("main"));
            String temperature = main.getString("temp");
            String wType = myObject.getJSONArray("weather").getJSONObject(0).getString("main");
            if(wType.equals("Rain"))
            {
                Fun_page.weather_Type.setText("\uF017");
            }
            else if(wType.equals("Clouds")){
                Fun_page.weather_Type.setText("\uF013");
            }
            else{
                Fun_page.weather_Type.setText("\uF00D");
            }
            Fun_page.temp.setText(temperature+"\uF03C");

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
