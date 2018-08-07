package com.lkt.retrofittest.interfacer;

import com.lkt.retrofittest.bean.POWeather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("/telematics/v3/weather")
    Call<POWeather> getWeather(@Query("location") String location, @Query("output") String ouput, @Query("ak") String ak);
}
