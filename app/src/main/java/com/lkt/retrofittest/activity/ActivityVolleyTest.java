package com.lkt.retrofittest.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.lkt.retrofittest.R;
import com.lkt.retrofittest.interfacer.IOnResponse;

import java.util.HashMap;
import java.util.Map;

public class ActivityVolleyTest extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley);
        final TextView textView = findViewById(R.id.textView);
        Map<String, String> map = new HashMap<>();
        request(map, "https://www.baidu.com/", new IOnResponse() {
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
