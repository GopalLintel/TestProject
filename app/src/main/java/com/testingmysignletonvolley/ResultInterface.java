package com.testingmysignletonvolley;

import com.android.volley.VolleyError;

import org.json.JSONObject;

public interface ResultInterface {
    void onResult(int requestCode, JSONObject response);
    void onError(int reqId, VolleyError error);
}
