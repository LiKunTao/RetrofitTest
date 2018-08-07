package com.lkt.retrofittest.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.lkt.retrofittest.interfacer.IOnResponse;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Map;

public abstract class BaseActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public void request(Map<String, String> datas, String url, final IOnResponse iOnResponse) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JSONObject jsonObject = new JSONObject(datas);
        final String jsonObjectStr = "username=hello_world&password=123456&sex=1";
        Request responseA = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            public byte[] getBody() {
                //防止直接传jsonobject无效的场景，
                try {
                    return jsonObjectStr.toString().getBytes("UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                return null;
            }
        };

        Request request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                iOnResponse.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                iOnResponse.onError(error);
            }
        });
        requestQueue.add(request);
        requestQueue.start();
    }

    ;
}
