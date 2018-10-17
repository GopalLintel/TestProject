package com.testingmysignletonvolley;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

class Testsingle {
    private static final Testsingle ourInstance = new Testsingle();

    private static final String TAG = "gopal";

    public static String BASE_URL = "http://27.109.18.60";
    public static String BASE_PATH = "/eNACHAPI/api/Common/";

    RequestQueue queue;

    VolleyResultListner mResultCallback = null;
    Context mContext;

    static Testsingle getInstance() {
        return ourInstance;
    }


    public void postDataRequestVolley(final int requestCode, String CallMethod,JSONObject sendObj,Context context,VolleyResultListner listner){
        try {
            String url=BASE_URL+BASE_PATH+CallMethod;
            mResultCallback=listner;
            JsonObjectRequest jsonObj = new JsonObjectRequest(Request.Method.POST,url,sendObj, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    if(mResultCallback != null)
                        mResultCallback.onSuccess(requestCode,response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    if(mResultCallback != null)
                        mResultCallback.onError(requestCode,error);
                }
            });
            Volley.newRequestQueue(context).add(jsonObj);
        }catch(Exception e){
        }
    }
}
