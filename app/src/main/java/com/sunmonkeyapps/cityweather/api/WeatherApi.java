package com.sunmonkeyapps.cityweather.api;

import com.sunmonkeyapps.cityweather.model.WeatherResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {

    //https://api.openweathermap.org/data/2.5/forecast?q=San%20Francisco&mode=json&units=imperial&appid=a06520bedb3ef45479f28f0af38e7624


    @GET("data/2.5/forecast")
    Call<WeatherResponse> getWeatherForCity(
            @Query("q") String cityName,
            @Query("mode") String mode,
            @Query("units") String units,
            @Query("appid") String appid
    );
}
