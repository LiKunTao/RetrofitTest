package com.lkt.retrofittest.activity;

import android.app.DownloadManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.lkt.retrofittest.R;
import com.lkt.retrofittest.interfacer.IOnResponse;

public class ActivityVolleyTest extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley);
        final TextView textView = findViewById(R.id.textView);
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        Request request = new StringRequest("https://www.baidu.com/", new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                textView.setText(Html.fromHtml(response));
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        });
//        requestQueue.add(request);
//        requestQueue.start();

        request("https://www.baidu.com/", new IOnResponse() {
            @Override
            public void onSuccess(String response) {
                textView.setText(Html.fromHtml(response));
            }

            @Override
            public void onError(VolleyError error) {

            }
        });
    }
}
