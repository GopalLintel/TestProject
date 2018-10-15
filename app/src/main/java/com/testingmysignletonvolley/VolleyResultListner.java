package com.testingmysignletonvolley;

import com.android.volley.VolleyError;

import org.json.JSONObject;

public interface VolleyResultListner {

    public void onSuccess(int requestCode,JSONObject response);
    public void onError(int requestCode,VolleyError error);

}
