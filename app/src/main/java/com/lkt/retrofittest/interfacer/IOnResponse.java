package com.lkt.retrofittest.interfacer;

import com.android.volley.VolleyError;

public interface IOnResponse {
    void onSuccess(String response);

    void onError(VolleyError error);
}
