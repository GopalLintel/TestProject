package com.testingmysignletonvolley;

import com.android.volley.VolleyError;

import org.json.JSONObject;

public interface VolleyResultListner {

    void onSuccess(int requestCode, JSONObject response);
    void onError(int requestCode, VolleyError error);

}
