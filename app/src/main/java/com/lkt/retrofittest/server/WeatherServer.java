package com.lkt.retrofittest.server;

import android.app.ProgressDialog;
import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.lkt.retrofittest.bean.POWeather;
import com.lkt.retrofittest.interfacer.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherServer {

    public static void getBook(final Context context, final IGetBookSre iGetBookSre) {
        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.show();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.map.baidu.com/")
                //增加返回值为Gson的支持(以实体类返回)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService service = retrofit.create(ApiService.class);

        final Call<POWeather> weather = service.getWeather("广州", "JSON", "FK9mkfdQsloEngodbFl4FeY3");


        try {
            weather.enqueue(new Callback<POWeather>() {
                @Override
                public void onResponse(Call<POWeather> call, final Response<POWeather> response) {
                    progressDialog.dismiss();
                    String message = "{message:" + response.body().getMessage() + "}";
                    Gson gson = new Gson();
                    POWeather poWeather = gson.fromJson(message, POWeather.class);

                    iGetBookSre.onSuccess(poWeather);
                    if (progressDialog != null && progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<POWeather> call, Throwable t) {
                    iGetBookSre.onFaile("{statue:failure}");
                    if (progressDialog != null && progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public interface IGetBookSre {

        void onSuccess(POWeather poWeather);

        void onFaile(String faileRes);
    }
}
